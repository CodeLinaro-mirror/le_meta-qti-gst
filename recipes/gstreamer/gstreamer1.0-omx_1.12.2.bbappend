FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-omx:"

SRC_URI += "file://0001-omxaacenc-fix-samples-per-buffer-calculation.patch"
SRC_URI += "file://0001-Enabling-omx-aac-encoder-component.patch"
SRC_URI += "file://0001-Enabling-omx-video-h264-component.patch"
SRC_URI += "file://0002-omxh264dec-fix-reconfigure-release-buffer.patch"

#Including media dependency as we need libOmxCore.so
DEPENDS += "media"
RDEPENDS_${PN} = "media"

LICENSE_FLAGS_WHITELIST = "commercial"

GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libOmxCore.so"
