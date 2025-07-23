inherit cmake pkgconfig

SUMMARY = "QTI open-source GStreamer Plug-in for overlay"
HOMEPAGE = "https://git.codelinaro.org"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"
LIC_FILES_CHKSUM:qcm6490 = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gstreamer1.0-plugins-qti-oss-mlmeta"
DEPENDS += "gstreamer1.0-plugins-qti-oss-base"
DEPENDS += "adreno"
DEPENDS += "graphicsdlkm"
DEPENDS += "cairo"
DEPENDS += "liblog"
DEPENDS:remove:qcm6490 = "liblog"
DEPENDS:append:qcm6490 = " property-vault syslog-plumber"
DEPENDS:remove:qcs6490 = "graphicsdlkm"
DEPENDS:remove:pineapple = "adreno"
DEPENDS:remove:pineapple = "graphicsdlkm"

RDEPENDS:${PN}:append:qcm6490 = " property-vault"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-overlay/"
S = "${WORKDIR}/gst-plugin-overlay"

# Install directories
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_INCDIR}/linux-msm"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_LICENSE=BSD"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_VERSION=${PV}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_PACKAGE=${PN}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_SUMMARY="${SUMMARY}""
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_ORIGIN=${HOMEPAGE}"
EXTRA_OECMAKE += "-DPKG_CONFIG_SYSROOT_DIR=${PKG_CONFIG_SYSROOT_DIR}"

do_configure:prepend() {
    if echo "${PN}" | grep -q "^lib32-"; then
        echo "Applying 32-bit specific configuration for ${PN}"

        # Create necessary directory structure
        mkdir -p ${WORKDIR}/lib32-recipe-sysroot/usr/lib/gcc/arm-oemllib32-linux-gnueabi/11.5.0
        ln -sf ${WORKDIR}/lib32-recipe-sysroot/usr/lib/arm-oemllib32-linux-gnueabi/11.5.0/* \
               ${WORKDIR}/lib32-recipe-sysroot/usr/lib/gcc/arm-oemllib32-linux-gnueabi/11.5.0/

        # Ensure linker is accessible in PATH
        export PATH="${RECIPE_SYSROOT_NATIVE}/usr/bin/arm-oemllib32-linux-gnueabi:${PATH}"
        
        # Create linker symlink if missing
        if [ ! -f "${RECIPE_SYSROOT_NATIVE}/usr/bin/arm-oemllib32-linux-gnueabi/ld" ]; then
            ln -sf ld.bfd "${RECIPE_SYSROOT_NATIVE}/usr/bin/arm-oemllib32-linux-gnueabi/ld"
        fi

        cp -r "${GIT_CEILING_DIRECTORIES}/recipe-sysroot/usr/include/"  "${PKG_CONFIG_SYSROOT_DIR}/usr/"

        # Use hard-float to match your toolchain's expected ABI
        sed -i 's/-mfloat-abi=softfp/-mfloat-abi=hard/g' ${WORKDIR}/toolchain.cmake || true

        # Add hard-float stub header if needed
        target_incdir="${WORKDIR}/lib32-recipe-sysroot/usr/include/gnu"
        if [ ! -f "${target_incdir}/stubs-hard.h" ]; then
            echo "/* Hard-float stub header */" > ${target_incdir}/stubs-hard.h
            echo '#ifndef __GNUC_STUBS_H' >> ${target_incdir}/stubs-hard.h
            echo '#define __GNUC_STUBS_H' >> ${target_incdir}/stubs-hard.h
            echo '#endif' >> ${target_incdir}/stubs-hard.h
        fi
    fi
}

## CRITICAL: Add do_install override to move lib64 files to lib
#do_install:append() {
#    if echo "${PN}" | grep -q "^lib32-"; then
#        # Move any lib64 files to lib for 32-bit builds
#        if [ -d "${D}/usr/lib64" ]; then
#            echo "Moving lib64 files to lib for 32-bit build"
#            mkdir -p ${D}/usr/lib
#            cp -r ${D}/usr/lib64/* ${D}/usr/lib/ || true
#            rm -rf ${D}/usr/lib64
#        fi
#    fi
#}

# Include both lib and lib64 paths temporarily to catch all files
FILES:${PN} = "${libdir}/gstreamer-1.0/*.so*"
FILES:${PN} += "${libdir}/*.so*"
FILES:${PN} += "${bindir}/*"
FILES:${PN} += "${libdir}/*.cl"
# Temporary: Include lib64 paths until we fix the installation
FILES:${PN} += "/usr/lib/gstreamer-1.0/*.so*"
FILES:${PN} += "/usr/lib/*.so*"
FILES:${PN} += "/usr/lib/*.cl"

FILES:${PN}-dev = "${includedir}/*"
FILES:${PN}-dbg = "${libdir}/.debug/*"
FILES:${PN}-dbg += "${libdir}/gstreamer-1.0/.debug/*"
FILES:${PN}-dbg += "/usr/lib/.debug/*"
FILES:${PN}-dbg += "/usr/lib/gstreamer-1.0/.debug/*"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

# Fixed VFP ABI configuration for 32-bit builds
python __anonymous() {
    if not d.getVar("PN").startswith("lib32-"):
        return

    d.appendVar("EXTRA_OECMAKE", " -DUSE_32BIT_OVERLAY_LIBS=ON")
    d.appendVar("DEPENDS", " lib32-gcc-runtime lib32-glibc")

    d.setVar("GCC_VER", "11.5.0")
    d.setVar("TARGET_TRIPLE", "arm-oemllib32-linux-gnueabi")

    # Multiple attempts to force lib installation instead of lib64
    d.appendVar("EXTRA_OECMAKE", " -DCMAKE_INSTALL_LIBDIR=lib")
    d.appendVar("EXTRA_OECMAKE", " -DLIB_INSTALL_DIR=/usr/lib")
    d.appendVar("EXTRA_OECMAKE", " -DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=/usr/lib")
    
    # Use GCC toolchain with proper LDFLAGS for GNU_HASH
    gcc_flags = "\
      -DCMAKE_C_COMPILER=${RECIPE_SYSROOT_NATIVE}/usr/bin/arm-oemllib32-linux-gnueabi/arm-oemllib32-linux-gnueabi-gcc \
      -DCMAKE_CXX_COMPILER=${RECIPE_SYSROOT_NATIVE}/usr/bin/arm-oemllib32-linux-gnueabi/arm-oemllib32-linux-gnueabi-g++ \
      -DCMAKE_C_FLAGS='-march=armv7-a -mthumb -mfpu=neon -mfloat-abi=hard' \
      -DCMAKE_CXX_FLAGS='-march=armv7-a -mthumb -mfpu=neon -mfloat-abi=hard' \
      -DCMAKE_EXE_LINKER_FLAGS='${LDFLAGS} -Wl,--hash-style=gnu -Wl,--no-warn-mismatch' \
      -DCMAKE_SHARED_LINKER_FLAGS='${LDFLAGS} -Wl,--hash-style=gnu -Wl,--no-warn-mismatch' \
    "
    d.appendVar("EXTRA_OECMAKE", gcc_flags)
}

