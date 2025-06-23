DEPENDS += "gbm"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/1.22:"

SRC_URI:append = "\
  file://0001-waylandsink-support-fullscreen-for-waylandsink-for-g.patch \
"

do_configure:prepend() {
  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend/
  if [ ${BASEMACHINE} == "qrb5165" ] || [ ${BASEMACHINE} == "qcm2290-mtp" ]; then
      cp ${WORKSPACE}/display/weston/protocol/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend ||
      cp ${STAGING_DIR}/${MACHINE}/usr/share/libweston-8/protocols/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend

  else
      cp ${WORKSPACE}/display/vendor/qcom/opensource/display/weston/protocol/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend ||
      cp ${STAGING_DIR}/${MACHINE}/usr/share/libweston-10/protocols/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend
  fi
}
