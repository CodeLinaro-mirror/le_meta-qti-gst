DEPENDS += "qemu-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/qti-patches:${THISDIR}/gstreamer1.0-plugins-good:"

EXTRA_OECONF_append_qcs40x = " --disable-cairo"

SRC_URI += "\
	    file://0001-pulse-Expose-the-correct-max-rate-that-we-support.patch \
            "
PACKAGECONFIG_remove_sdm845 = "v4l2"
