FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append = "  file://set_gst_env.sh"

DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

EXTRA_OECONF = "--libexecdir=${libdir}/${BPN}"


do_install_append () {
    install -d ${D}/${sysconfdir}/${BPN}
    install -d ${D}/${sysconfdir}/${BPN}/.cache
    sed -i "s#@registry@#${sysconfdir}/${BPN}/.cache/registry.${TUNE_ARCH}.bin#g" \
            ${WORKDIR}/set_gst_env.sh
    install -m 0644 ${WORKDIR}/set_gst_env.sh ${D}/${sysconfdir}/${BPN}/set_gst_env.sh
}


FILES_${PN} += "${sysconfdir}/*"
