FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append = "  file://0001-change-registry-path-to-conf-directory.patch"
SRC_URI:append = "  file://0002-bufferpool-unblock-acquire-thread-discard-buffer.patch"

DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

EXTRA_OECONF = "--libexecdir=${libdir}/${BPN}"


do_install:append () {
    install -d ${D}/${sysconfdir}/${BPN}
    install -d ${D}/${sysconfdir}/${BPN}/.cache
}


FILES:${PN} += "${sysconfdir}/*"
