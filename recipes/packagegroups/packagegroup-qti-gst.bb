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
      ${@bb.utils.contains_any("COMBINED_FEATURES", "qti-video qti-audio", "gstreamer1.0-omx", "", d)} \
      ${@bb.utils.contains_any("COMBINED_FEATURES", "qti-video qti-audio", "gstreamer1.0-libav", "", d)} \
      ${@bb.utils.contains("DISTRO_FEATURES", "qti-qmmf", "gstreamer1.0-plugins-qti-oss-qmmfsrc", "", d)} \
      ${@bb.utils.contains("DISTRO_FEATURES", "qti-qmmf", "gstreamer1.0-plugins-qti-oss-videotransform", "", d)} \
    "
