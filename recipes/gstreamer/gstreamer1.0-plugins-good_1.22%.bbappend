FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/1.22:"

SRC_URI += "\
    ${@bb.utils.contains("DISTRO_FEATURES", "pulseaudio", "file://0001-Add-pulsedirectsink-plugin.patch", "", d)} \
    file://0001-v4l2-Add-support-for-fd-memory-import.patch \
"
