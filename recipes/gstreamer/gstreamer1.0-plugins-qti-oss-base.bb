inherit cmake

SUMMARY = "QTI open-source GStreamer base"
SECTION = "multimedia"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gbm"
DEPENDS += "adreno"

# Conditional dependency in IB2C library used in GLES Video Converter.
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-ib2c', 'qti-ib2c', '', d)}"
RDEPENDS_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-ib2c', 'qti-ib2c', '', d)}"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-base/"
S = "${WORKDIR}/gst-plugin-base"

# Default platform definitions.
C2D_CONVERTER := "TRUE"
GLES_CONVERTER := "${@bb.utils.contains('DISTRO_FEATURES', 'qti-ib2c', 'TRUE', 'FALSE', d)}"
GBM_FREE_FD := "FALSE"

# Install directries.
INSTALL_INCDIR := "${includedir}"
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_INCDIR=${INSTALL_INCDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DGST_C2D_CONVERTER:BOOL=${C2D_CONVERTER}"
EXTRA_OECMAKE += "-DGST_GLES_CONVERTER:BOOL=${GLES_CONVERTER}"
EXTRA_OECMAKE += "-DGBM_FREE_FD:BOOL=${GBM_FREE_FD}"

FILES_${PN} += "${INSTALL_BINDIR}"
FILES_${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
