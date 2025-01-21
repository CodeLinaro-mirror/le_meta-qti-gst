FILESEXTRAPATHS:prepend:qcom-custom-bsp := "${THISDIR}/gstreamer1.0-plugins-good/1.22:"

SRC_URI:append:qcom-custom-bsp = "\
    file://0001-v4l2-Add-support-for-V4L2_PIX_FMT_QC08C-format.patch \
"
