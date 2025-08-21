SUMMARY = "QTI Gstreamer package groups"
LICENSE = "BSD-3-Clause-Clear"

PROVIDES = "${PACKAGES}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = " \
      packagegroup-qti-gst-minimal \
    "

RDEPENDS:packagegroup-qti-gst-minimal = " \
      gstreamer1.0 \
      gstreamer1.0-plugins-base \
      gstreamer1.0-plugins-good \
      gstreamer1.0-plugins-bad \
      gstreamer1.0-rtsp-server \
      gstreamer1.0-plugins-qti-oss-base \
      gstreamer1.0-plugins-qti-oss-tools \
      gstreamer1.0-plugins-qti-oss-overlay \
      gstreamer1.0-plugins-qti-oss-vtransform \
      ${@bb.utils.contains_any("COMBINED_FEATURES", "qti-video qti-audio", "gstreamer1.0-omx", "", d)} \
      ${@bb.utils.contains("DISTRO_FEATURES", "qti-qmmf", "gstreamer1.0-plugins-qti-oss-qmmfsrc", "", d)} \
    "
