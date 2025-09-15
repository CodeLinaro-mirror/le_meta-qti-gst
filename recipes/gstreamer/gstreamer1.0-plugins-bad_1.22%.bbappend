FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/1.22:"

SRC_URI:append = "\
  file://0001-wayland-Add-support-for-NV12_Q08C-compressed-8-bit-f.patch \
  file://0002-Hack-wayland-Add-NV12_Q08C-to-shm-formats.patch \
  file://0003-waylandsink-release-pending-buffers-in-composer.patch \
  file://0004-waylandsink-support-gap-buffers.patch \
  file://0005-waylandsink-increase-timeout-limitation-in-gst_wl_wi.patch \
  file://0006-waylandsink-support-position-and-dimensions.patch \
  file://0007-waylandsink-support-gbm-protocol-and-Q08C-Q10C.patch \
"

PACKAGECONFIG:append = " webrtc sctp srt srtp"
DEPENDS:append = " libnice libsrtp srt gbm"

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
