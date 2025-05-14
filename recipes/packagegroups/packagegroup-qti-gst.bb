SUMMARY = "QTI Gstreamer package groups"
LICENSE = "BSD-3-Clause-Clear"

PROVIDES = "${PACKAGES}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = " \
      packagegroup-qti-gst \
    "

RDEPENDS:packagegroup-qti-gst = " \
      gstreamer1.0 \
      gstreamer1.0-plugins-base \
      gstreamer1.0-plugins-good \
      gstreamer1.0-plugins-bad \
      gstreamer1.0-plugins-ugly \
      gstreamer1.0-rtsp-server \
      ${@bb.utils.contains("MACHINE", "ar-sg1", "gstreamer1.0-plugins-qti-oss-examples", "", d)} \
      ${@bb.utils.contains("MACHINE", "sxrneo-ar-sg1", "gstreamer1.0-libav", "", d)} \
      ${@bb.utils.contains("MACHINE", "ar-sg1", "gstreamer1.0-libav", "", d)} \
      gstreamer1.0-omx \
      ${@bb.utils.contains("MACHINE", "sxrneo-ar-sg1", "gstreamer1.0-plugins-qti-oss-codec2", "", d)} \
      ${@bb.utils.contains("MACHINE", "ar-sg1", "gstreamer1.0-plugins-qti-oss-codec2", "", d)} \
    "
