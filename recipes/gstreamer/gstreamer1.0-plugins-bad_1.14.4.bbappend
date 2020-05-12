DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

CPPFLAGS += " ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '-D__GBM__', '', d)} "
CPPFLAGS += "-DUSE_GBM"

DEPENDS_append_apq8098 = " wayland wayland-native libdrm weston"
DEPENDS_append_sm8250 = " wayland wayland-native libdrm weston"

PACKAGECONFIG_remove_sm8250 += " rsvg "

PACKAGECONFIG_append_apq8098 = "wayland"
PACKAGECONFIG_append_sm8250 = " wayland gl opencv "

FILESEXTRAPATHS_prepend_apq8098 := "${THISDIR}/qti-patches:"
SRC_URI_append_apq8098 = " \
       file://waylandsink-Add-support-for-gbm-buffer-backend.patch \
       file://gst-bad-plugins-h264-h265-zero-copy-support-for-qtivdec.patch \
       file://waylandsink-Add-fullscreen-support.patch \
"
FILESEXTRAPATHS_prepend_sm8250 := "${THISDIR}/qti-patches:"
SRC_URI_append_sm8250 = " \
       file://waylandsink-Add-support-for-gbm-buffer-backend.patch \
       file://gst-bad-plugins-h264-h265-zero-copy-support-for-qtivdec.patch \
       file://waylandsink-Add-fullscreen-support.patch \
"

python do_after_patch_apq8098 () {
    import os

    cmd = "install -d ${S}/gst-libs/gst/ionbuf/ && (echo -n "" > ${S}/gst-libs/gst/ionbuf/Makefile.am)"
    os.system(cmd)
}
python do_after_patch_sm8250 () {
    import os

    cmd = "install -d ${S}/gst-libs/gst/ionbuf/ && (echo -n "" > ${S}/gst-libs/gst/ionbuf/Makefile.am)"
    os.system(cmd)
}

addtask after_patch_apq8098 after do_patch_apq8098
addtask after_patch_sm8250 after do_patch_sm8250
