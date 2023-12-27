inherit cmake pkgconfig

SUMMARY = "QTI open-source GStreamer base"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gbm"
DEPENDS += "adreno"
DEPENDS += "fastcv-noship"

# Conditional dependency in IB2C library used in GLES Video Converter.
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-ib2c', 'qti-ib2c', '', d)}"
RDEPENDS:${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-ib2c', 'qti-ib2c', '', d)}"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-base/"
S = "${WORKDIR}/gst-plugin-base"

# Install directries.
INSTALL_INCDIR := "${includedir}"
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_INCDIR}/linux-msm"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_INCDIR=${INSTALL_INCDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
