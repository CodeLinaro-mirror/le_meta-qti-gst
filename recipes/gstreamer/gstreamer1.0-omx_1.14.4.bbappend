FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-omx:"

SRC_URI += "file://0001-omxaacenc-fix-samples-per-buffer-calculation.patch"
SRC_URI += "file://0001-Enabling-omx-aac-and-h264-encoder-component.patch"
SRC_URI += "file://0001-Enabling-omx-video-h265-component.patch"
SRC_URI += "file://0001-omxh264enc-enable-vui-timing-info.patch"
SRC_URI += "file://0001-fix-gstomx-PortSettingsChanged-event.patch"
SRC_URI += "file://0001-gstomxh264enc-support-8K-resolution.patch"

#Including media dependency as we need libOmxCore.so
DEPENDS += "media"
RDEPENDS_${PN} = "media"

CFLAGS_remove_qrb5165 = " -I${S}/omx/openmax"
CFLAGS_append_qrb5165 = " -DOMX_VERSION_MAJOR=1 -DOMX_VERSION_MINOR=0 -DOMX_VERSION_REVISION=0 -DOMX_VERSION_STEP=0"

LICENSE_FLAGS_WHITELIST = "commercial"

GSTREAMER_1_0_OMX_CORE_NAME = "${libdir}/libOmxCore.so"

EXTRA_OECONF += " --enable-vui-timing-info=yes"
EXTRA_OECONF_append_qrb5165 = " --libdir=${libdir}/${UBUN_TARGET_SYS}"

FILES_${PN}_append_qrb5165 = " ${libdir}/${UBUN_TARGET_SYS}/*"
INSANE_SKIP_${PN} += "file-rdeps"
