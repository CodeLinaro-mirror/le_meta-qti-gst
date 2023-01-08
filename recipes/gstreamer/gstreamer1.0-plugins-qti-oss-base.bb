inherit cmake pkgconfig

SUMMARY = "QTI open-source GStreamer base"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gbm"
DEPENDS += "adreno"

# Conditional dependency in IB2C library used in GLES Video Converter.
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-ib2c', 'qti-ib2c', '', d)}"
RDEPENDS:${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-ib2c', 'qti-ib2c', '', d)}"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-base/"
S = "${WORKDIR}/gst-plugin-base"

# Default platform definitions.
C2D_CONVERTER := "TRUE"
GLES_CONVERTER := "${@bb.utils.contains('DISTRO_FEATURES', 'qti-ib2c', 'TRUE', 'FALSE', d)}"
ION_BUFFER_POOL := "TRUE"
C2D_CONVERTER:kalama := "FALSE"
ION_BUFFER_POOL:kalama := "FALSE"

# Install directries.
INSTALL_INCDIR := "${includedir}"
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_INCDIR}/linux-msm"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_INCDIR=${INSTALL_INCDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DGST_C2D_CONVERTER:BOOL=${C2D_CONVERTER}"
EXTRA_OECMAKE += "-DGST_GLES_CONVERTER:BOOL=${GLES_CONVERTER}"
EXTRA_OECMAKE += "-DGST_ION_POOL:BOOL=${ION_BUFFER_POOL}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
