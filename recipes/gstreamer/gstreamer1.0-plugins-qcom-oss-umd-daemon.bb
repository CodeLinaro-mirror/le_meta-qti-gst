inherit cmake pkgconfig qimsdk-base

SUMMARY = "GStreamer based daemon utilizing Qualcomm UMD gadget library"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-qcom-oss-mlmeta"
DEPENDS += "qti-umd-gadget"
DEPENDS += "${@bb.utils.contains("QCOM_AFR_ALGO", "TRUE", "qti-auto-framing-stabilization", "", d)}"

RDEPENDS:${PN} := "qti-umd-gadget"
RDEPENDS:${PN} += "${@bb.utils.contains("QCOM_AFR_ALGO", "TRUE", "qti-auto-framing-stabilization", "", d)}"

SRCDIR = "gst-umd-daemon"
SRC_URI = "file://${SRCDIR}"
S = "${WORKDIR}/${SRCDIR}"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"
FILES:${PN} += "/data/"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
