FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-omx:${THISDIR}/gstreamer1.0-omx/1.16.2:"

DEPENDS_remove = "virtual/libomxil"

SRC_URI += "file://0001-omxh264enc-Set-maximum-caps-resolution-range-to-MAX.patch"
SRC_URI += "file://0001-omx-Add-support-for-QTI-target.patch"
SRC_URI += "file://0002-omxvideo-Add-support-for-GBM-memory.patch"
SRC_URI += "file://0002-omx-Fix-PortSettingsChanged-event.patch"
SRC_URI += "file://0003-omxvideoenc-Add-support-for-QTI-target-specific-exte.patch"

DEPENDS += "media"
RDEPENDS_${PN} = "media"

# Including gbm and kernel dependency as we need libgbm and some kernel headers
DEPENDS += "gbm linux-msm-headers"
RDEPENDS_${PN} += "gbm"

# Add path to the kernel headers.
EXTRA_OEMESON += "-Dsanitized_headers=${STAGING_INCDIR}/linux-msm/usr/include"

# Set path to the OpenMAX headers.
EXTRA_OEMESON += "-Dheader_path=${STAGING_INCDIR}"

GSTREAMER_1_0_OMX_TARGET = "qti"
GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libOmxCore.so"
