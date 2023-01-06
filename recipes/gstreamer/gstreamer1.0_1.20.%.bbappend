DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

EXTRA_OECONF = "--libexecdir=${libdir}/${BPN}"

do_install:append() {
   if [ "${TARGET_ARCH}" = "aarch64" ];then
      mv "${D}/${bindir}/gst-inspect-1.0" "${D}/${bindir}/gst-inspect_64-1.0"
      mv "${D}/${bindir}/gst-launch-1.0" "${D}/${bindir}/gst-launch_64-1.0"
      mv "${D}/${bindir}/gst-stats-1.0" "${D}/${bindir}/gst-stats_64-1.0"
      mv "${D}/${bindir}/gst-typefind-1.0" "${D}/${bindir}/gst-typefind_64-1.0"
   fi
}
