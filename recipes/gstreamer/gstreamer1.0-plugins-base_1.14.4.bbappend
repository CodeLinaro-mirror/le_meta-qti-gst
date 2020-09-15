DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/qti-patches:${THISDIR}/gstreamer1.0-plugins-base:"

PACKAGECONFIG_append_sm8250 += " wayland gles2 egl gbm "
DEPENDS += "libion libsync libcutils"

SRC_URI += "\
            file://0001-Add-flac-wma-and-alac-support-in-audioringbuffer.patch \
            file://0003-add-UBWC-format-for-gst-plugins-base.patch \
            file://0001-add-NV12_UBWC-supported.patch \
            "
CFLAGS_append_sm8250 += "-I${STAGING_KERNEL_BUILDDIR}/usr/include"

do_install_append_qcs40x() {
   if [ "${TARGET_ARCH}" = "aarch64" ];then
      mv "${D}/${bindir}/gst-device-monitor-1.0" "${D}/${bindir}/gst-device-monitor_64-1.0"
      mv "${D}/${bindir}/gst-discoverer-1.0" "${D}/${bindir}/gst-discoverer_64-1.0"
      mv "${D}/${bindir}/gst-play-1.0" "${D}/${bindir}/gst-play_64-1.0"
   fi
}

do_install_append_sm8250() {
   if [ "${TARGET_ARCH}" = "aarch64" ];then
      mv "${D}/${bindir}/gst-device-monitor-1.0" "${D}/${bindir}/gst-device-monitor_64-1.0"
      mv "${D}/${bindir}/gst-discoverer-1.0" "${D}/${bindir}/gst-discoverer_64-1.0"
      mv "${D}/${bindir}/gst-play-1.0" "${D}/${bindir}/gst-play_64-1.0"
   fi
}
