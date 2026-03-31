FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRCREV = "d924fcbc2123dcfcb35242ecf5dc2fc3049004b3"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI += "\
           file://gstd.service \
           file://0001-Unblock-GSTD-pipeline-if-a-plugin-refuses-to-change-.patch \
           file://gstd-env_wayland \
           "
SRC_URI:remove = "\
           file://0001-gstd-yocto-compatibility.patch \
           file://0001-Look-for-gtk-doc.make-in-builddir.patch \
           "

SRC_URI:append:qti-distro-perf = "\
           file://0001-Disable-logging-on-perf-builds.patch \
           "

DEPENDS += "libsoup-2.4 jansson"
DEPENDS:append:sun      = " readline python3-pip-native"
DEPENDS:append:kera     = " readline python3-pip-native"
DEPENDS:append:alor     = " readline python3-pip-native"
DEPENDS:append:vienna   = " readline python3-pip-native"

inherit systemd

EXTRA_OECONF = "--with-gstd-runstatedir=/tmp \
                --with-gstd-logstatedir=/tmp/ \
                "

EXTRA_OEMESON = "-Dwith-gstd-logstatedir=/tmp \
                 -Dwith-gstd-runstatedir=/tmp/ \
                 "

do_configure:prepend() {
        echo -n "" > ${WORKDIR}/git/libgstc/python/Makefile.am
}

do_install:prepend() {
        if ${@'true' if bb.data.inherits_class('autotools', d) else 'false'}; then
          install -d ${D}${localstatedir}/run/gstd
          install -d ${D}${localstatedir}/log/gstd
        elif ${@'true' if bb.data.inherits_class('meson', d) else 'false'}; then
          install -d ${D}${exec_prefix}${localstatedir}/run/gstd
          install -d ${D}${exec_prefix}${localstatedir}/log/gstd
        fi
}

do_install:append() {
        install -d ${D}${sysconfdir}/default

        MACHINES="kalama qcs6490 pineapple sun kera vienna alor"

        if echo "$MACHINES" | grep -wq "${BASEMACHINE}"; then
          install -m 666 ${WORKDIR}/gstd-env_wayland ${D}${sysconfdir}/default/gstd
        else
          echo "OPTARGS=\"-a 0.0.0.0\"" >> ${D}${sysconfdir}/default/gstd
          echo "XDG_RUNTIME_DIR=/dev/socket/weston" >> ${D}${sysconfdir}/default/gstd
          echo "GST_REGISTRY=${sysconfdir}/gstreamer1.0/.cache/registry.${TUNE_ARCH}.bin" >> \
            ${D}${sysconfdir}/default/gstd
        fi

        if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
          echo "d /data/gst/ 0755 system video -" \
            > ${D}${sysconfdir}/tmpfiles.d/${BPN}.conf
        fi

        install -d ${D}${systemd_system_unitdir}
        install -m 644 ${WORKDIR}/gstd.service ${D}${systemd_system_unitdir}

        install -d ${D}/tmp
        rm -rf ${D}${exec_prefix}/tmp
        rm -rf ${D}${localstatedir}/run
        rm -rf ${D}${bindir}/gstd-client
        ln -s /usr/bin/gst-client-1.0 ${D}${bindir}/gstd-client

        GSTD_PV="${PV}"

        if [ -f ${D}${bindir}/gstd-${GSTD_PV%%+*} ]; then
          mv ${D}${bindir}/gstd-${GSTD_PV%%+*} ${D}${bindir}/gstd-gstd
        elif [ -f ${D}/${bindir}/gstd ]; then
          mv ${D}${bindir}/gstd ${D}${bindir}/gstd-gstd
        fi

        if [ -f ${D}${bindir}/gstd-gstd ]; then
          ln -snf gstd-gstd ${D}${bindir}/gstd
        fi
}

SYSTEMD_SERVICE:${PN} = "gstd.service"

FILES:${PN} += " /tmp \
               "

INSANE_SKIP:${PN} += "useless-rpaths empty-dirs"
