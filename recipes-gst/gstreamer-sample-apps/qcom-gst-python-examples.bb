inherit pkgconfig

SUMMARY = "Generic ref python example apps for GStreamer pipelines."
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

# Dependencies.
RDEPENDS:${PN} := "gstreamer1.0-python"

FILESPATH =+ "${WORKSPACE}/gst-plugins-qti-oss/:"
SRC_URI = "file://gst-python-examples"
S = "${WORKDIR}/gst-python-examples"

do_install() {
    mkdir -p ${D}${bindir}
    install -m 755 ${S}/*.py ${D}${bindir}/
}
