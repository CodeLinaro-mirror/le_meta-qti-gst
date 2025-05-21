DEPENDS += "gbm"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/1.22:"

SRC_URI:append = "\
  file://0001-waylandsink-support-fullscreen-for-waylandsink-for-g.patch \
"
