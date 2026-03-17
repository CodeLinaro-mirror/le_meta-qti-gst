SUMMARY = "QTI Gstreamer package groups"
LICENSE = "BSD-3-Clause-Clear"

PROVIDES = "${PACKAGES}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = " \
      packagegroup-qti-gst \
    "

RDEPENDS_packagegroup-qti-gst = " \
      gstd \
      gstreamer1.0 \
      gstreamer1.0-plugins-base \
      gstreamer1.0-plugins-good \
      gstreamer1.0-libav \
      gstreamer1.0-plugins-bad \
      gstreamer1.0-plugins-ugly \
      gstreamer1.0-rtsp-server \
      gstreamer1.0-plugins-qti-oss-base \
      gstreamer1.0-plugins-qti-oss-tools \
      gstreamer1.0-plugins-qti-oss-examples \
      gstreamer1.0-plugins-qti-oss-overlay \
      gstreamer1.0-plugins-qti-oss-socket \
      gstreamer1.0-plugins-qti-oss-vtransform \
      gstreamer1.0-plugins-qti-oss-vsplit \
      gstreamer1.0-plugins-qti-oss-vcomposer \
      gstreamer1.0-plugins-qti-oss-voverlay \
      gstreamer1.0-plugins-qti-oss-batch \
      gstreamer1.0-plugins-qti-oss-metamux \
      gstreamer1.0-plugins-qti-oss-mlvconverter \
      gstreamer1.0-plugins-qti-oss-mlvdetection \
      gstreamer1.0-plugins-qti-oss-mlvclassification \
      gstreamer1.0-plugins-qti-oss-mlvsegmentation \
      gstreamer1.0-plugins-qti-oss-mlvsuperresolution \
      gstreamer1.0-plugins-qti-oss-mlvpose \
      gstreamer1.0-plugins-qti-oss-mldemux \
      gstreamer1.0-plugins-qti-oss-dummydrm \
      gstreamer1.0-plugins-qti-oss-securefilesink \
      gstreamer1.0-plugins-qti-oss-h264secparser \
      ${@bb.utils.contains("MACHINE_FEATURES", "qti-aic", "gstreamer1.0-plugins-qti-oss-mlaic", "", d)} \
      ${@bb.utils.contains("DISTRO_FEATURES", "tensorflow-lite", "gstreamer1.0-plugins-qti-oss-mltflite", "", d)} \
      ${@bb.utils.contains("DISTRO_FEATURES", "qti-snpe", "gstreamer1.0-plugins-qti-oss-mlsnpe", "", d)} \
      ${@bb.utils.contains("DISTRO_FEATURES", "qti-qnn", "gstreamer1.0-plugins-qti-oss-mlqnn", "", d)} \
      ${@bb.utils.contains("COMBINED_FEATURES", "qti-uvc", "gstreamer1.0-plugins-qti-oss-umd-daemon", "", d)} \
      ${@bb.utils.contains("DISTRO_FEATURES", "qti-qmmf", "gstreamer1.0-plugins-qti-oss-qmmfsrc", "", d)} \
      ${@bb.utils.contains("DISTRO_FEATURES", "qti-dfs", "gstreamer1.0-plugins-qti-oss-dfs", "", d)} \
      ${@bb.utils.contains("MACHINE_FEATURES", "qti-cvp", "gstreamer1.0-plugins-qti-oss-cv-optclflow", "", d)} \
      ${@bb.utils.contains("MACHINE_FEATURES", "qti-cvp", "gstreamer1.0-plugins-qti-oss-cv-imgpyramid", "", d)} \
      ${@bb.utils.contains_any("BASEMACHINE", "sdmsteppe qcs6490", "gstreamer1.0-plugins-qti-oss-jpegenc", "", d)} \
      ${@bb.utils.contains_any("BASEMACHINE", "qrbx210 qcs6490", "gstreamer1.0-plugins-qti-oss-codec2", "", d)} \
    "
