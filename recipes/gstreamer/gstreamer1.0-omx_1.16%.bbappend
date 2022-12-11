FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-omx:${THISDIR}/gstreamer1.0-omx/1.16.2:"

DEPENDS_remove = "virtual/libomxil"

SRC_URI += "file://0001-omxaacenc-fix-samples-per-buffer-calculation.patch"
SRC_URI += "file://0001-omxh264enc-Set-maximum-caps-resolution-range-to-MAX.patch"
SRC_URI += "file://0001-omx-Add-support-for-QTI-target.patch"
SRC_URI += "file://0002-omxvideo-Add-support-for-GBM-memory.patch"
SRC_URI += "file://0003-omx-Add-support-for-QTI-target-specific-extensions.patch"
SRC_URI += "file://0004-omxvideoenc-Add-support-for-ROI-encoding-on-QTI-targ.patch"
SRC_URI += "file://0004-omxvideoenc-property-to-support-rotation-using-VPU.patch"
SRC_URI += "file://0005-omxvideodec-Support-for-dynamic-resolution-change.patch"

DEPENDS += "media"
RDEPENDS:${PN} = "media"

# Including gbm and kernel dependency as we need libgbm and some kernel headers
DEPENDS += "gbm linux-msm-headers"
RDEPENDS:${PN} += "gbm"

# Add path to the kernel headers.
EXTRA_OEMESON += "-Dsanitized_headers=${STAGING_INCDIR}/linux-msm/usr/include"

# Set path to the OpenMAX headers.
EXTRA_OEMESON += "-Dheader_path=${STAGING_INCDIR}"

GSTREAMER_1_0_OMX_TARGET = "qti"
GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libOmxCore.so"
