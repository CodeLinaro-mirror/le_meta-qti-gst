DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/qti-patches:${THISDIR}/gstreamer1.0-plugins-base:"

SRC_URI += "\
            file://0001-Add-flac-wma-and-alac-support-in-audioringbuffer.patch \
            "
do_install:append() {
   if [ "${TARGET_ARCH}" = "aarch64" ];then
      mv "${D}/${bindir}/gst-device-monitor-1.0" "${D}/${bindir}/gst-device-monitor_64-1.0"
      mv "${D}/${bindir}/gst-discoverer-1.0" "${D}/${bindir}/gst-discoverer_64-1.0"
      mv "${D}/${bindir}/gst-play-1.0" "${D}/${bindir}/gst-play_64-1.0"
   fi
}
