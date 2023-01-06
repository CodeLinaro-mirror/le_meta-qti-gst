DEPENDS += "qemu-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/qti-patches:${THISDIR}/gstreamer1.0-plugins-good:"

EXTRA_OECONF:append = " --disable-cairo"
EXTRA_OEMESON:append = " -Dcairo=disabled"
EXTRA_OEMESON:append = " -Dpulse=enabled"
