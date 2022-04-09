DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/qti-patches:${THISDIR}/gstreamer1.0-plugins-base:"

SRC_URI += "\
  file://0001-Add-flac-wma-and-alac-support-in-audioringbuffer.patch \
  file://0001-audioringbuffer-Don-t-calculate-bytes-per-sample-unl.patch \
  file://0002-audiobasesrc-Handle-capture-of-non-PCM-data.patch \
  file://0003-audioringbuffer-Add-support-for-IEC61937-payloaded-d.patch \
  https://git.codelinaro.org/clo/le/gstreamer/gst-plugins-base/-/commit/5767d65321ce34daeaf6a63d757390b80d44f8c2.diff;downloadfilename=he-aac-rate-fix.patch;name=he-aac-rate-fix;  \
  file://0005-audio-channels-Add-a-number-of-auxiliary-channels.patch \
  file://0001-Adding-DSD-codec-type-in-GST_AUDIO_RING_BUFFER.patch \
"
SRC_URI[he-aac-rate-fix.md5sum] = "1324a3b167a939be43d5a1f85628ccbb"
