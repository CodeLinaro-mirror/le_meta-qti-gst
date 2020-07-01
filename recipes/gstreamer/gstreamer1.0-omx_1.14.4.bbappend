FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-omx:"

SRC_URI += "file://0001-omxaacenc-fix-samples-per-buffer-calculation.patch"
SRC_URI += "file://0001-omxh264enc-Set-maximum-caps-resolution-range-to-MAX.patch"
SRC_URI += "file://0001-omx-Add-support-for-QTI-target.patch"
SRC_URI += "file://0002-omx-Fix-PortSettingsChanged-event.patch"
SRC_URI += "file://0002-omxvideo-Add-support-for-GBM-memory.patch"
SRC_URI += "file://0003-omxvideoenc-Add-support-for-QTI-target-specific-exte.patch"
SRC_URI += "file://0003-omxaacdec-set-audio-DualMono-config.patch"

#Including media dependency as we need libOmxCore.so
DEPENDS += "media gbm"
RDEPENDS_${PN} = "media gbm"

LICENSE_FLAGS_WHITELIST = "commercial"

GSTREAMER_1_0_OMX_TARGET = "qti"
GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libOmxCore.so"
