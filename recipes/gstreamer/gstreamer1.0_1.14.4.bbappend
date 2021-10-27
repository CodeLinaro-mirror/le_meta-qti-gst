FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append = "  file://0001-change-registry-path-to-conf-directory.patch"

DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

EXTRA_OECONF = "--libexecdir=${libdir}/${BPN}"


do_install_append () {
    install -d ${D}/${sysconfdir}/${BPN}
    install -d ${D}/${sysconfdir}/${BPN}/.cache
}


FILES_${PN} += "${sysconfdir}/*"
