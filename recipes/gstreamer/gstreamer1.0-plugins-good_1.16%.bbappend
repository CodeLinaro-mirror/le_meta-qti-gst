DEPENDS += "qemu-native"
FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-plugins-good/1.16.2:"

SRC_URI += "\
     ${@bb.utils.contains("DISTRO_FEATURES", "pulseaudio", "file://0001-pulse-Add-pulsedirectsink-plugin.patch", "", d)} \
    "

