DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/qti-patches:${THISDIR}/gstreamer1.0-plugins-base:"

SRC_URI += "\
  file://0001-Add-flac-wma-and-alac-support-in-audioringbuffer.patch \
  file://0001-audioringbuffer-Don-t-calculate-bytes-per-sample-unl.patch \
  file://0002-audiobasesrc-Handle-capture-of-non-PCM-data.patch \
  file://0003-audioringbuffer-Add-support-for-IEC61937-payloaded-d.patch \
  https://git.codelinaro.org/clo/le/gstreamer/gst-plugins-base/patch/?id=5767d65321ce34daeaf6a63d757390b80d44f8c2;downloadfilename=5767d65321ce34daeaf6a63d757390b80d44f8c2.patch;name=he-aac-rate-fix \
  file://0005-audio-channels-Add-a-number-of-auxiliary-channels.patch \
  file://0001-Adding-DSD-codec-type-in-GST_AUDIO_RING_BUFFER.patch \
"

SRC_URI[he-aac-rate-fix.md5sum] = "6d6171657a4163081d3bd0ffe6d6968b"
SRC_URI[he-aac-rate-fix.sha256sum] = "0b57468c8179705782419a98ca3ba6cdb49f48c26859295a35e811aaae881dfe"