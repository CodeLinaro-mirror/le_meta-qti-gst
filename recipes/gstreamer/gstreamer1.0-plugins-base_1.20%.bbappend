require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', bb.utils.contains('DISTRO_FEATURES', 'qimsdk-layers', 'gstreamer-common.inc', '', d), '', d), '', d)}
require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', bb.utils.contains('DISTRO_FEATURES', 'qimsdk-layers', 'update-alternatives/gstreamer1.0-plugins-base.inc', '', d), '', d), '', d)}

DISTRO_FEATURES:remove:pineapple += "opengl"
FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-base/1.20:"

SRC_URI += "\
    file://0001-video-Add-support-for-NV12_Q08C-compressed-8-bit-for.patch \
    file://0002-video-Add-new-colorimetry-support-color-full-range.patch \
"
