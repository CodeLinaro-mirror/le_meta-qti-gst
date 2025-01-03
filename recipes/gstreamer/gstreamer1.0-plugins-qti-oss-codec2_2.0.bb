inherit cmake sdllvm
inherit cmake pkgconfig

SUMMARY = "QTI open-source GStreamer Plug-in for video encoding decoding with Codec 2.0"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"


# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "codec2"
DEPENDS += "media"
DEPENDS += "llvm-arm-toolchain-native"
DEPENDS += "displaydlkm-headers"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-codec2/"
S = "${WORKDIR}/gst-plugin-codec2"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

CODEC2_CONFIG_VERSION := "2.0"
#1.16.3
EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.20.3"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OECMAKE += "-DKERNEL_DIR=${STAGING_KERNEL_DIR}/include/media/"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_LICENSE=BSD"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_VERSION=${PV}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_PACKAGE=${PN}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_SUMMARY="${SUMMARY}""
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_ORIGIN="Unknown package origin""
EXTRA_OECMAKE += "-DGST_CODEC2_CONFIG_VERSION=${CODEC2_CONFIG_VERSION}"

FILES:${PN} += "${INSTALL_BINDIR}  \
               ${INSTALL_LIBDIR} "

PACKAGE_ARCH = "${TUNE_ARCH}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
TOOLCHAIN = "sdllvm"
