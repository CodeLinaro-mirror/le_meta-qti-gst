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
      gstreamer1.0-rtsp-server \
      ${@bb.utils.contains("MACHINE", "qcm6490", bb.utils.contains('PRODUCT', 'ubuntu', 'gstreamer1.0-libav' , '' , d), 'gstreamer1.0-libav', d)} \
    "

