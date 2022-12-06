DEPENDS += "qemu-native"
FILESEXTRAPATHS_prepend := "${THISDIR}/qti-patches:${THISDIR}/gstreamer1.0-plugins-good:${THISDIR}/gstreamer1.0-plugins-good/1.14.4:"

SRC_URI += "\
  file://0001-pulse-Expose-the-correct-max-rate-that-we-support.patch \
  file://0001-pulse-Fix-format-info-to-caps-conversion-for-PCM.patch \
  file://0002-pulsesrc-Move-to-extended-stream-API.patch \
  file://0003-pulsesrc-Add-support-for-passthrough-capture.patch \
  file://0004-WIP-pulsesrc-Add-support-for-IEC61937-capture.patch \
  file://0001-pulse-Add-the-ability-to-set-stream-flags-on-pulsesi.patch \
  file://0006-pulse-Add-channel-mapping-for-newly-added-positions.patch \
  file://0007-pulse-Add-support-for-auxiliary-channel-positions.patch \
  file://0001-pulsedirectsink_1.14.4.patch \
  file://f0bdec32c1e8c4d67b0a8a0414900f2e1061740c.patch \
  file://0001-add-support-to-set-prebuf-in-properties.patch \
  file://0001-gstreamer1.0-plugins-good-Adding-dsd-compressed-playback.patch \
  file://0001-pulse-Add-a-pulsedirectsrc-without-audioringbuffer.patch \
  file://0001-qtdemux-fix-allocation-explosion-with-stsd-entries.patch \
"
PACKAGECONFIG[v4l2] = "--enable-gst_v4l2,--disable-gst_v4l2"

SRC_URI[pulsesink-null-caps.md5] = "9db41a6c98cb66a040bcbc5400beb94e"
SRC_URI[pulsesink-null-caps.sha256sum] = "0f3843f44389579121f1516d5c1b62a4665124531685cf853a978a155ffb7f12"
