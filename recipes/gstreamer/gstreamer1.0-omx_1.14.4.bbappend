FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-omx:${THISDIR}/gstreamer1.0-omx/1.14.4:"

SRC_URI += "file://0001-omxaacenc-fix-samples-per-buffer-calculation.patch"
SRC_URI += "file://0001-omxh264enc-Set-maximum-caps-resolution-range-to-MAX.patch"
SRC_URI += "file://0001-omx-Add-support-for-QTI-target.patch"
SRC_URI += "file://0002-omxvideo-Add-support-for-GBM-memory.patch"
SRC_URI += "file://0003-omx-Add-support-for-QTI-target-specific-extensions.patch"
SRC_URI += "file://0004-omxvideoenc-Add-support-for-ROI-encoding-on-QTI-targ.patch"

# Including media dependency as we need libOmxCore.so
DEPENDS += "media"
RDEPENDS_${PN} = "media"

# Including gbm and kernel dependency as we need libgbm and some kernel headers
DEPENDS += "gbm virtual/kernel"
RDEPENDS_${PN} += "gbm"

# Add path to the kernel headers.
EXTRA_OECONF += "--with-sanitized-headers=${STAGING_KERNEL_BUILDDIR}/usr/include"

LICENSE_FLAGS_WHITELIST = "commercial"

GSTREAMER_1_0_OMX_TARGET = "qti"
GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libOmxCore.so"
