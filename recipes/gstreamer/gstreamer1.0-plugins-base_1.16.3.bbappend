FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-base/1.16:"

SRC_URI += "\
  file://0001-video-format-add-gst_video_format_info_component.patch \
  file://0002-video-info-add-gst_video_info_align_full.patch \
  file://0003-videometa-add-alignment-field.patch \
  file://0004-video-Add-support-for-NV12_Q08C-compressed-8-bit-for.patch \
  file://0005-video-Add-support-for-NV12_Q10LE32C-compressed-10-bit-format.patch \
"
