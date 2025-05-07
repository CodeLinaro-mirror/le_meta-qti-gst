FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-bad/1.24:"

SRC_URI:append = "\
  file://0001-gstreamer1.0-plugins-bad-Add-meson-option-to-build-a.patch \
  file://0002-waylandsink-release-pending-buffers-in-composer.patch \
  file://0003-waylandsink-support-gap-buffers.patch \
  file://0004-waylandsink-increase-timeout-limitation-in-gst_wl_wi.patch \
"

PACKAGECONFIG:append = " webrtc sctp srt srtp"
DEPENDS:append = " libnice libsrtp srt"
