DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

CPPFLAGS += " ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '-D__GBM__', '', d)} "

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-bad/1.16.2:"

SRC_URI:append = " \
       file://0001-waylandsink-support-fullscreen-for-waylandsink-for-g.patch \
       file://0002-waylandsink-support-gbm-buffer-backend-protocol.patch \
       file://0003-waylandsink-release-pending-buffers-in-composer.patch \
       file://0004-waylandsink-support-gap-buffers.patch \
       file://0004-waylandsink-fix-for-video-playback-crash-after-HPD-o.patch \
"

do_gbm_configure() {
  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend/
  cp ${WORKSPACE}/display/weston/protocol/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend || cp ${STAGING_DIR}/${MACHINE}/usr/share/libweston-8/protocols/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend
}

EXTRA_OECONF += "${@bb.utils.contains('COMBINED_FEATURES', 'fbdev', '--enable-fbdev-compositor', '', d)}"
EXTRA_OECONF += "${@bb.utils.contains('COMBINED_FEATURES', 'drm', '--enable-drm-compositor', '', d)}"
do_configure[prefuncs] += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'do_gbm_configure',     '', d)}"
