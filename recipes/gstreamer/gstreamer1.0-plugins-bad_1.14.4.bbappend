DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

CPPFLAGS += " ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '-D__GBM__', '', d)} "

PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGECONFIG_remove = " \
       ${@bb.utils.contains('DISTRO_FEATURES','wayland', '', 'wayland', d)} \
      "

FILESEXTRAPATHS_prepend := "${THISDIR}/qti-patches:"

SRC_URI_append = " \
       file://gst-bad-plugins-h264-h265-zero-copy-support-for-qtivdec.patch \
       file://0001-waylandsink-support-fullscreen.patch \
       file://0002-waylandsink-support-for-xdg-shell-protocol.patch \
       file://0003-waylandsink-support-for-scaler-protocol.patch \
       file://0004-waylandsink-support-gbm-buffer-backend-protocol.patch \
       file://0005-waylandsink-Forcibly-release-pending-buffers-on-PAUS.patch \
       file://0006-wayland-add-support-for-multiple-xdg-shell-protocols.patch \
"

do_gbm_configure() {
  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend/
  cp ${WORKSPACE}/display/weston/protocol/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend
}

do_xdg_scaler_configure () {
  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/scaler/
  cp ${WORKSPACE}/display/weston/protocol/scaler.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/scaler

  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/xdg-shell/
  cp ${WORKSPACE}/display/weston/protocol/xdg-shell.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/xdg-shell
}

EXTRA_OECONF += "${@bb.utils.contains('COMBINED_FEATURES', 'fbdev', '--with-xdg-shell-protocol=v1', '--with-xdg-shell-protocol=v2', d)}"
EXTRA_OECONF += "${@bb.utils.contains('COMBINED_FEATURES', 'drm', '--enable-scaler-protocol', '', d)}"
EXTRA_OECONF += "${@bb.utils.contains('COMBINED_FEATURES', 'fbdev', '--enable-fbdev-compositor', '--enable-drm-compositor', d)}"
EXTRA_OEMAKE += "WAYLAND_PROTOCOLS_DATADIR=${STAGING_DATADIR}/wayland-protocols"

do_configure[prefuncs] += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'do_gbm_configure', '', d)}"
do_configure[prefuncs] += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', bb.utils.contains('COMBINED_FEATURES', 'drm', 'do_xdg_scaler_configure', '', d), '', d)}"
