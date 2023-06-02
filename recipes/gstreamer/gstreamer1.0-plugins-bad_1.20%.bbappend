FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-bad/1.20.4:"

SRC_URI:append = "\
  file://0001-waylandsink-support-position-and-dimensions.patch \
  file://0002-waylandsink-support-scaler-protocol.patch \
  file://0003-waylandsink-support-gbm-buffer-backend-protocol.patch \
  file://0004-waylandsink-release-pending-buffers-in-composer.patch \
"

do_configure:prepend() {
  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend/
  if [ ${BASEMACHINE} == "qrb5165" ]; then
      cp ${WORKSPACE}/display/weston/protocol/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend ||
      cp ${STAGING_DIR}/${MACHINE}/usr/share/libweston-8/protocols/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend

  else
      cp ${WORKSPACE}/display/vendor/qcom/opensource/display/weston/protocol/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend ||
      cp ${STAGING_DIR}/${MACHINE}/usr/share/libweston-10/protocols/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend
  fi
}
