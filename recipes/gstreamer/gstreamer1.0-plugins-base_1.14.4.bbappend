DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/qti-patches:${THISDIR}/gstreamer1.0-plugins-base:"

SRC_URI += "\
            file://0001-Add-flac-wma-and-alac-support-in-audioringbuffer.patch \
            "