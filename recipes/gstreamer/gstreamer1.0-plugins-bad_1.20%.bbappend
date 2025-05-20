require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', bb.utils.contains('DISTRO_FEATURES', 'qimsdk-layers', 'gstreamer-common.inc', '', d), '', d), '', d)}
require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', bb.utils.contains('DISTRO_FEATURES', 'qimsdk-layers', 'update-alternatives/gstreamer1.0-plugins-bad.inc', '', d), '', d), '', d)}

DISTRO_FEATURES:remove:pineapple += "opengl"

DEPENDS += "gbm"

FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-bad/1.20.4:"

SRC_URI:append = "\
  file://0001-waylandsink-support-position-and-dimensions.patch \
  file://0002-waylandsink-support-scaler-protocol.patch \
  file://0003-waylandsink-support-gbm-protocol-and-Q08C-Q10C.patch \
  file://0004-waylandsink-release-pending-buffers-in-composer.patch \
  file://0005-waylandsink-support-gap-buffers.patch \
  file://0005-videoparser-support-protected-content-caps.patch \
  file://0006-videoparser-update-width-and-height-on-resolution-ch.patch \
  file://0006-waylandsink-increase-timeout-limitation-in-gst_wl_wi.patch \
"

do_configure:prepend() {
  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend/
  if [ ${BASEMACHINE} == "qrb5165" ] || [ ${BASEMACHINE} == "qcm2290-mtp" ] || [ ${BASEMACHINE} == "qcm4325-mtp" ]; then
      cp ${WORKSPACE}/display/weston/protocol/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend ||
      cp ${STAGING_DIR}/${MACHINE}/usr/share/libweston-8/protocols/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend

  else
      cp ${WORKSPACE}/display/vendor/qcom/opensource/display/weston/protocol/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend ||
      cp ${STAGING_DIR}/${MACHINE}/usr/share/libweston-10/protocols/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend
  fi
}
