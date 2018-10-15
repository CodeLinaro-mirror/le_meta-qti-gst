DEPENDS += "gobject-introspection gobject-introspection-native"
DEPENDS_append_apq8017 += "ffmpeg"
DEPENDS_append_apq8009 += "ffmpeg"

FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-libav:"

SRC_URI += "\
            file://0001-Expose-support-for-DSD.patch \
            file://0002-avdemux-Expose-IFF-container-support.patch \
            "

LICENSE_FLAGS_WHITELIST = "commercial"
