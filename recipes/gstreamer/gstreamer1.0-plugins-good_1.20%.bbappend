DEPENDS += "qemu-native"
FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-good/1.20.4:"

SRC_URI += "\
     ${@bb.utils.contains("DISTRO_FEATURES", "pulseaudio", "file://0001-Add-pulsedirectsink-plugin.patch", "", d)} \
    "
