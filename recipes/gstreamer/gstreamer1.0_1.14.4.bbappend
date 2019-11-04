DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"
FILESEXTRAPATHS_prepend := "${THISDIR}/qti-patches:"

SRC_URI_append = " \
       file://gstreamer-baseparse-add-suport-zero-copy-for-qtivdec.patch \
"

EXTRA_OECONF = "--libexecdir=${libdir}/${BPN}"
