FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-omx:"

SRC_URI += "file://0001-omxaacenc-fix-samples-per-buffer-calculation.patch"
SRC_URI += "file://0001-Enabling-omx-aac-and-h264-encoder-component.patch"
SRC_URI += "file://0001-Enabling-omx-video-h265-component.patch"
SRC_URI += "file://0001-fix-gstomx-PortSettingsChanged-event.patch"
SRC_URI += "file://0001-add-NV12_UBWC-supported.patch"

#Including media dependency as we need libOmxCore.so
DEPENDS += "media"
RDEPENDS_${PN} = "media"

CFLAGS_remove_sdm845 = " -I${S}/omx/openmax"
CFLAGS_append_sdm845 = " -DOMX_VERSION_MAJOR=1 -DOMX_VERSION_MINOR=0 -DOMX_VERSION_REVISION=0 -DOMX_VERSION_STEP=0"
EXTRA_OECONF_append_sdm845 = " --with-omx-header-path=${STAGING_INCDIR}/mm-core"
CFLAGS_remove_sm8250 = " -I${S}/omx/openmax"
CFLAGS_append_sm8250 = " -DOMX_VERSION_MAJOR=1 -DOMX_VERSION_MINOR=0 -DOMX_VERSION_REVISION=0 -DOMX_VERSION_STEP=0"
CFLAGS_append_sm8250 = " -I${WORKSPACE}/hardware/qcom/media/mm-core/inc"
CFLAGS_append_sm8250 += "-I${STAGING_KERNEL_BUILDDIR}/usr/include"

LICENSE_FLAGS_WHITELIST = "commercial"

GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libOmxCore.so"
