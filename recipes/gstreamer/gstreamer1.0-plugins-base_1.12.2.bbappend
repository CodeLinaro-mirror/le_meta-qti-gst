DEPENDS += "gobject-introspection gobject-introspection-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/qti-patches:"

SRC_URI += "file://0001-Add-flac-wma-and-alac-support-in-audioringbuffer.patch"
