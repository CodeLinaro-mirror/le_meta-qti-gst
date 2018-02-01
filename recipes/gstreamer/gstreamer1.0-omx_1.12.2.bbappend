FILESEXTRAPATHS_prepend := "${THISDIR}/qti-patches:"

SRC_URI += "file://0001-omxaacenc-fix-samples-per-buffer-calculation.patch"
SRC_URI += "file://0001-Enabling-omx-aac-encoder-component.patch"

#Including media dependency as we need libOmxCore.so
DEPENDS += "media"
RDEPENDS_${PN} = "media"

GSTREAMER_1_0_OMX_CORE_NAME ?= "${libdir}/libOmxCore.so"
