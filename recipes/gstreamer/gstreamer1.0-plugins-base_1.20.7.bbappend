FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/1.20:"

SRC_URI += "\
    file://0001-video-Add-support-for-NV12_Q08C-compressed-8-bit-for.patch \
    file://0002-video-Add-support-for-NV12_Q10LE32C-compressed-10-bit-format.patch \
    file://0003-video-Add-new-colorimetry-support-color-full-range.patch \
"

