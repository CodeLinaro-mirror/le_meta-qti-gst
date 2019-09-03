DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

CPPFLAGS += " ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '-D__GBM__', '', d)} "

DEPENDS_append_apq8098 = " wayland wayland-native libdrm weston"
DEPENDS_append_sdmsteppe = " wayland wayland-native libdrm weston"

PACKAGECONFIG_append_apq8098 = "wayland"
PACKAGECONFIG_append_sdmsteppe = "wayland"

FILESEXTRAPATHS_prepend_apq8098 := "${THISDIR}/qti-patches:"
FILESEXTRAPATHS_prepend_sdmsteppe := "${THISDIR}/qti-patches:"

SRC_URI_append_apq8098 = " \
       file://gst-bad-plugins-h264-h265-zero-copy-support-for-qtivdec.patch \
       file://0001-waylandsink-support-fullscreen.patch \
       file://0002-waylandsink-support-for-xdg-shell-protocol.patch \
       file://0003-waylandsink-support-for-scaler-protocol.patch \
       file://0004-waylandsink-support-gbm-buffer-backend-protocol.patch \
"
SRC_URI_append_sdmsteppe = " \
       file://gst-bad-plugins-h264-h265-zero-copy-support-for-qtivdec.patch \
       file://0001-waylandsink-support-fullscreen.patch \
       file://0002-waylandsink-support-for-xdg-shell-protocol.patch \
       file://0003-waylandsink-support-for-scaler-protocol.patch \
       file://0004-waylandsink-support-gbm-buffer-backend-protocol.patch \
"

EXTRA_OEMAKE_append_apq8098 += "WAYLAND_PROTOCOLS_DATADIR=${STAGING_DATADIR}/wayland-protocols"
EXTRA_OEMAKE_append_sdmsteppe += "WAYLAND_PROTOCOLS_DATADIR=${STAGING_DATADIR}/wayland-protocols"

do_configure_prepend_apq8098() {
  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend/
  cp ${WORKSPACE}/display/weston/protocol/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend

  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/scaler/
  cp ${WORKSPACE}/display/weston/protocol/scaler.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/scaler

  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/xdg-shell/
  cp ${WORKSPACE}/display/weston/protocol/xdg-shell.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/xdg-shell
}

do_configure_prepend_sdmsteppe() {
  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend/
  cp ${WORKSPACE}/display/weston/protocol/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend

  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/scaler/
  cp ${WORKSPACE}/display/weston/protocol/scaler.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/scaler

  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/xdg-shell/
  cp ${WORKSPACE}/display/weston/protocol/xdg-shell.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/xdg-shell
}
