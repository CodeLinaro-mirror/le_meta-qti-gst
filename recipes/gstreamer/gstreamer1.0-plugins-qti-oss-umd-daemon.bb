inherit cmake pkgconfig

SUMMARY = "GStreamer based daemon utilizing QTI UMD gadget library"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "qti-umd-gadget"
DEPENDS += "qti-auto-framing-stabilization"

RDEPENDS:${PN} := "qti-umd-gadget"
RDEPENDS:${PN} += "qti-auto-framing-stabilization"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"
SRC_URI = "file://gst-umd-daemon/"
S = "${WORKDIR}/gst-umd-daemon/"

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
