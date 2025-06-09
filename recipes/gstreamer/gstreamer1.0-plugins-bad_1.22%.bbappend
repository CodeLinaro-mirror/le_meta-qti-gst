DEPENDS += "gbm"
RDEPENDS:${PN} += "gbm"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/1.22:"

SRC_URI:append = "\
  file://0001-waylandsink-support-position-and-dimensions.patch \
  file://0002-waylandsink-support-gbm-protocol-and-Q08C-Q10C.patch \
  file://0003-waylandsink-release-pending-buffers-in-composer.patch \
  file://0004-waylandsink-support-gap-buffers.patch \
"

do_configure:prepend:sdmsteppe() {
  install -d ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend/
  cp ${TMPDIR}/sysroots-components/${TUNE_PKGARCH}/weston/usr/share/libweston-13/protocols/gbm-buffer-backend.xml ${STAGING_DIR_HOST}${datadir}/wayland-protocols/stable/gbm-buffer-backend
}
