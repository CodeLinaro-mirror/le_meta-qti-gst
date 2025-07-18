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
    "

RDEPENDS:packagegroup-qti-gst:remove:qrb5165 = " \
      gstd \
      gstreamer1.0-plugins-qti-oss-cv-optclflow \
    "

RDEPENDS:packagegroup-qti-gst:remove:bengal = " \
      gstd \
      gstreamer1.0-plugins-qti-oss-cv-optclflow \
    "

RDEPENDS:packagegroup-qti-gst:remove:qcm6490 = " \
      gstd \
      gstreamer1.0-plugins-ugly \
      gstreamer1.0-plugins-qti-oss-codec2 \
    "

RDEPENDS:packagegroup-qti-gst:append:qcm6490 = " \
      gstreamer1.0-plugins-qti-oss-qmmfsrc \
    "
