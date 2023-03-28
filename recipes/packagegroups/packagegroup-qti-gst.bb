SUMMARY = "QTI Gstreamer package groups"
LICENSE = "BSD-3-Clause"

PROVIDES = "${PACKAGES}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = " \
      packagegroup-qti-gst \
    "

RDEPENDS_packagegroup-qti-gst = " \
      gstreamer1.0 \
      gstreamer1.0-plugins-base \
      gstreamer1.0-plugins-good \
      gstreamer1.0-plugins-bad \
      gstreamer1.0-plugins-ugly \
      gstreamer1.0-rtsp-server \
      gstreamer1.0-libav \
      gstreamer1.0-omx \
      ${@bb.utils.contains("BASEMACHINE", "waipio", "gstreamer1.0-plugins-qti-oss-codec2", "", d)} \
    "
