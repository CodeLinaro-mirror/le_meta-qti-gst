FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-bad/1.20.7:"

SRC_URI += "\
  file://0001-waylandsink-support-position-and-dimensions.patch \
  file://0002-waylandsink-support-scaler-protocol.patch \
  file://0003-waylandsink-support-gbm-buffer-backend-protocol.patch \
  file://0004-waylandsink-release-pending-buffers-in-composer.patch \
  file://0005-waylandsink-support-gap-buffers.patch \
  file://0006-waylandsink-increase-timeout-limitation-in-gst_wl_wi.patch \
  file://0007-waylandsink-fix-thread-hang-during-shutdown-with-sec.patch \
"

DEPENDS += " weston libnice libsrtp wayland-protocols"

do_gbm_configure() {
  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend/
  cp ${STAGING_DIR_HOST}${datadir}/libweston-8/protocols/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend/
}

do_configure[prefuncs] += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'do_gbm_configure',     '', d)}"
