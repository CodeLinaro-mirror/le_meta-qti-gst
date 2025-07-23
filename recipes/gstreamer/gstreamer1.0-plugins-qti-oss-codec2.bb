inherit cmake pkgconfig

SUMMARY = "QTI open-source GStreamer Plug-in for video encoding decoding with Codec 2.0"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gstreamer1.0-plugins-qti-oss-base"
DEPENDS += "codec2"
DEPENDS:append:kalama += "media"
DEPENDS:append:kalama += "media-external"
DEPENDS:append:pineapple += "media"
DEPENDS:append:pineapple += "media-external"
DEPENDS:append:sun += "media"
DEPENDS:append:sun += "media-external"
DEPENDS:append:bengal += "media"
DEPENDS:append:bengal += "media-external"
DEPENDS:append:qrb5165 += "media-codec2"
DEPENDS:append:qcs6490 += "media-codec2"
DEPENDS += "qti-c2-module"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-codec2/"
S = "${WORKDIR}/gst-plugin-codec2"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

CODEC2_CONFIG_VERSION := "1.0"
CODEC2_CONFIG_VERSION:kalama := "2.0"
CODEC2_CONFIG_VERSION:pineapple := "2.0"
CODEC2_CONFIG_VERSION:bengal := "2.0"
CODEC2_CONFIG_VERSION:sun := "2.0"

ENABLE_LINEAR_DMABUF:qrb5165 := "TRUE"
ENABLE_LINEAR_DMABUF:kalama := "TRUE"
ENABLE_LINEAR_DMABUF:bengal := "TRUE"
ENABLE_AUDIO_PLUGINS:kalama := "TRUE"
ENABLE_AUDIO_PLUGINS:pineapple := "TRUE"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_INCDIR}/linux-msm"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_LICENSE=BSD"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_VERSION=${PV}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_PACKAGE=${PN}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_SUMMARY="${SUMMARY}""
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_ORIGIN="Unknown package origin""
EXTRA_OECMAKE += "-DGST_CODEC2_CONFIG_VERSION=${CODEC2_CONFIG_VERSION}"
EXTRA_OECMAKE += "-DGST_ENABLE_LINEAR_DMABUF=${ENABLE_LINEAR_DMABUF}"
EXTRA_OECMAKE += "-DGST_ENABLE_AUDIO_PLUGINS=${ENABLE_AUDIO_PLUGINS}"

do_configure:prepend() {
    if echo "${PN}" | grep -q "^lib32-"; then
        echo "Applying 32-bit specific configuration for ${PN}"

        # Symlinks for 32-bit gcc libs
        mkdir -p ${WORKDIR}/lib32-recipe-sysroot/usr/lib/gcc/arm-oemllib32-linux-gnueabi/11.5.0
        ln -sf ${WORKDIR}/lib32-recipe-sysroot/usr/lib/arm-oemllib32-linux-gnueabi/11.5.0/* \
               ${WORKDIR}/lib32-recipe-sysroot/usr/lib/gcc/arm-oemllib32-linux-gnueabi/11.5.0/

        cp -r "${GIT_CEILING_DIRECTORIES}/recipe-sysroot/usr/include/"  "${PKG_CONFIG_SYSROOT_DIR}/usr/"

        # Fix VFP ABI mismatch: switch from hard to softfp
        sed -i 's/-mfloat-abi=hard/-mfloat-abi=softfp/g' ${WORKDIR}/toolchain.cmake || true

        # Add softfp stub header
        target_incdir="${WORKDIR}/lib32-recipe-sysroot/usr/include/gnu"
        if [ ! -f "${target_incdir}/stubs-soft.h" ]; then
            echo "/* Stub: redirected to stubs-hard.h for build workaround */" > ${target_incdir}/stubs-soft.h
            echo '#include "stubs-hard.h"' >> ${target_incdir}/stubs-soft.h
        fi
    fi
}

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "/usr/lib/gstreamer-1.0/libgstqtic2venc.so"
FILES:${PN} += "/usr/lib/gstreamer-1.0/libgstqtic2vdec.so"

do_install:append() {
    if echo "${PN}" | grep -q "^lib32-"; then
        if [ -d "${D}/usr/lib64" ]; then
            echo "Moving lib64 files to lib for 32-bit build"
            mkdir -p ${D}/usr/lib
            cp -r ${D}/usr/lib64/* ${D}/usr/lib/ || true
            rm -rf ${D}/usr/lib64
        fi
    fi
}


SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
TOOLCHAIN = "sdllvm"

# Apply your proven camera fix approach for 32-bit builds
python __anonymous() {
    if not d.getVar("PN").startswith("lib32-"):
        return

    d.appendVar("EXTRA_OECMAKE", " -DUSE_32BIT_C2_LIBS=ON")
    d.appendVar("DEPENDS", " lib32-gcc-runtime lib32-glibc")

    d.setVar("GCC_VER", "11.5.0")
    d.setVar("TARGET_TRIPLE", "arm-oemllib32-linux-gnueabi")

    gcc_flags = "\
      -DCMAKE_C_COMPILER=${RECIPE_SYSROOT_NATIVE}/usr/bin/llvm-arm-toolchain/bin/clang \
      -DCMAKE_CXX_COMPILER=${RECIPE_SYSROOT_NATIVE}/usr/bin/llvm-arm-toolchain/bin/clang++ \
      -DCMAKE_C_FLAGS='--target=${TARGET_TRIPLE} --sysroot=${RECIPE_SYSROOT} -fuse-ld=lld -march=armv7-a -mthumb -mfpu=neon -mfloat-abi=softfp' \
      -DCMAKE_CXX_FLAGS='--target=${TARGET_TRIPLE} --sysroot=${RECIPE_SYSROOT} -fuse-ld=lld -march=armv7-a -mthumb -mfpu=neon -mfloat-abi=softfp' \
      -DCMAKE_EXE_LINKER_FLAGS='-fuse-ld=lld' \
      -DCMAKE_SHARED_LINKER_FLAGS='-fuse-ld=lld' \
    "
    d.appendVar("EXTRA_OECMAKE", gcc_flags)
}
