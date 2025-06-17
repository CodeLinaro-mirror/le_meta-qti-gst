SUMMARY = "QTI DRM player example for GStreamer"
DESCRIPTION = "QTI DRM player example for GStreamer with support for PlayReady and Widevine"
HOMEPAGE = "https://git.codelinaro.org"
SECTION = "multimedia"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DEPENDS += "\
    glib-2.0 \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    libxml2 \
    curl \
    media-headers \
    libutils \
    libcutils \
    libsoup-2.4 \
"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"
SRC_URI = "file://gst-plugin-examples/"
S = "${WORKDIR}/gst-plugin-examples/"

SRCREV = "${AUTOREV}"

inherit cmake pkgconfig

CXXFLAGS += "\
    -I${STAGING_INCDIR}/glib-2.0 \
    -I${STAGING_INCDIR}/gstreamer-1.0 \
    -I${STAGING_INCDIR}/libxml2 \
"
