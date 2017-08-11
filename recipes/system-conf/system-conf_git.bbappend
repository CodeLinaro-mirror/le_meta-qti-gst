FILESPATH_prepend := "${THISDIR}/system-conf:"
SRC_URI_append = " \
    file://001_open_ssid.patch \
    "

do_install_append() {
    # Temporary workaround QCMAP looking for the script at the wrong place
    ln -sfn ${sysconfdir}/initscripts/wlan ${D}${sysconfdir}/init.d/
}
