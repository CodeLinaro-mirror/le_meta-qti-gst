DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"
FILESEXTRAPATHS_prepend_apq8098 := "${THISDIR}/qti-patches:"
FILESEXTRAPATHS_prepend_sdmsteppe := "${THISDIR}/qti-patches:"

SRC_URI_append_apq8098 = " \
       file://gstreamer-baseparse-add-suport-zero-copy-for-qtivdec.patch \
"
SRC_URI_append_sdmsteppe = " \
       file://gstreamer-baseparse-add-suport-zero-copy-for-qtivdec.patch \
"

EXTRA_OECONF = "--libexecdir=${libdir}/${BPN}"
