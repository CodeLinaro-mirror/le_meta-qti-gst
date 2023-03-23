FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRCREV = "d924fcbc2123dcfcb35242ecf5dc2fc3049004b3"

SRC_URI += "\
           file://gstd.service \
           file://0001-Unblock-GSTD-pipeline-if-a-plugin-refuses-to-change-.patch \
           "

SRC_URI:remove = "\
           file://0001-gstd-yocto-compatibility.patch \
           file://0001-Look-for-gtk-doc.make-in-builddir.patch \
           "

SRC_URI:append:qti-distro-perf = "\
           file://0001-Disable-logging-on-perf-builds.patch \
           "

DEPENDS += "libsoup-2.4 jansson"

inherit systemd

EXTRA_OECONF = "--with-gstd-runstatedir=/run \
                --with-gstd-logstatedir=${localstatedir}/log/ \
                "

do_configure:prepend() {
        echo -n "" > ${WORKDIR}/git/libgstc/python/Makefile.am
}

do_install:prepend:kalama() {
        install -d ${D}${localstatedir}/run/gstd
        install -d ${D}${localstatedir}/log/gstd
}

do_install:append() {
        install -d ${D}${sysconfdir}/default
        echo "OPTARGS=\"-a 0.0.0.0\"" >> ${D}${sysconfdir}/default/gstd
        echo "XDG_RUNTIME_DIR=/dev/socket/weston" >> ${D}${sysconfdir}/default/gstd
        echo "GST_REGISTRY=${sysconfdir}/gstreamer1.0/.cache/registry.${TUNE_ARCH}.bin" >> \
        ${D}${sysconfdir}/default/gstd

        install -d ${D}${systemd_system_unitdir}
        install -m 644 ${WORKDIR}/gstd.service ${D}${systemd_system_unitdir}

        install -d ${D}/run
        install -d ${D}${localstatedir}/log
        rm -rf ${D}${localstatedir}/run
        rm -rf ${D}${bindir}/gstd-client
        ln -s /usr/bin/gst-client-1.0 ${D}${bindir}/gstd-client
}

SYSTEMD_SERVICE:${PN} = "gstd.service"

FILES:${PN} += "/run \
                ${localstatedir}/log \
               "

INSANE_SKIP:${PN} += "useless-rpaths empty-dirs"
