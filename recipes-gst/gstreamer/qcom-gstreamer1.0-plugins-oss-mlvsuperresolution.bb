inherit cmake pkgconfig

SUMMARY = "Qualcomm open-source GStreamer Plug-in for ML image super resolution"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "qcom-gstreamer1.0-plugins-oss-base"

FILESPATH =+ "${WORKSPACE}/:"
SRC_URI = "file://gst-plugins-qti-oss/gst-plugin-mlvsuperresolution"
S = "${WORKDIR}/gst-plugins-qti-oss/gst-plugin-mlvsuperresolution"

# Install directories.
INSTALL_INCDIR := "${includedir}"
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.20.7"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_INCDIR=${INSTALL_INCDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

FILES:${PN}-dbg += "${INSTALL_LIBDIR}/gstreamer-1.0/.debug"
FILES:${PN}-dbg += "${INSTALL_LIBDIR}/gstreamer-1.0/ml/modules/.debug"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

python do_package:prepend() {
    bb.warn("This mlvsuperresolution plugin will be deprecated in the future! "
        "Use qtimlpostprocess instead.")
}
