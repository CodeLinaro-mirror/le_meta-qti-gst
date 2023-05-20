SUMMARY = "QTI Gstreamer package groups"
LICENSE = "BSD-3-Clause-Clear"

PROVIDES = "${PACKAGES}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = " \
      packagegroup-qti-gst-basic \
    "

RDEPENDS:packagegroup-qti-gst-basic = " \
      gstreamer1.0 \
      gstreamer1.0-plugins-base \
      gstreamer1.0-plugins-good \
      gstreamer1.0-plugins-bad \
      gstreamer1.0-libav \
      gstreamer1.0-rtsp-server \
      gstreamer1.0-plugins-qti-oss-codec2 \
      gstreamer1.0-plugins-qti-oss-decryptor \
      gstreamer1.0-plugins-qti-oss-examples \
      gstreamer1.0-plugins-qti-oss-qmmfsrc \
      gstreamer1.0-plugins-qti-oss-tools \
      gstreamer1.0-plugins-qti-oss-vtransform \
      gstreamer1.0-plugins-qti-oss-vcomposer \
    "
