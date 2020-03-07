DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"
FILESEXTRAPATHS_prepend_apq8098 := "${THISDIR}/qti-patches:"
FILESEXTRAPATHS_prepend_sm8250  := "${THISDIR}/qti-patches:"

SRC_URI_append_apq8098 = " \
       file://gstreamer-baseparse-add-suport-zero-copy-for-qtivdec.patch \
"
SRC_URI_append_sm8250 = " \
       file://gstreamer-baseparse-add-suport-zero-copy-for-qtivdec.patch \
"

EXTRA_OECONF = "--libexecdir=${libdir}/${BPN}"

do_install_append_qcs40x() {
   if [ "${TARGET_ARCH}" = "aarch64" ];then
      mv "${D}/${bindir}/gst-inspect-1.0" "${D}/${bindir}/gst-inspect_64-1.0"
      mv "${D}/${bindir}/gst-launch-1.0" "${D}/${bindir}/gst-launch_64-1.0"
      mv "${D}/${bindir}/gst-stats-1.0" "${D}/${bindir}/gst-stats_64-1.0"
      mv "${D}/${bindir}/gst-typefind-1.0" "${D}/${bindir}/gst-typefind_64-1.0"
   fi
}

do_install_append_sm8250() {
   if [ "${TARGET_ARCH}" = "aarch64" ];then
      mv "${D}/${bindir}/gst-inspect-1.0" "${D}/${bindir}/gst-inspect_64-1.0"
      mv "${D}/${bindir}/gst-launch-1.0" "${D}/${bindir}/gst-launch_64-1.0"
      mv "${D}/${bindir}/gst-stats-1.0" "${D}/${bindir}/gst-stats_64-1.0"
      mv "${D}/${bindir}/gst-typefind-1.0" "${D}/${bindir}/gst-typefind_64-1.0"
   fi
}
