inherit cmake pkgconfig

SUMMARY = "Qualcomm open-source GStreamer base"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS:append:qcom-custom-bsp = " qcom-fastcv-binaries"
DEPENDS += "opencv"
DEPENDS += "virtual/kernel"
DEPENDS += "virtual/egl"
DEPENDS += "virtual/libgles2"
DEPENDS += "json-glib"
DEPENDS += "qcom-camera-server"

RDEPENDS:${PN} += "opencv"

FILESPATH =+ "${WORKSPACE}/:"
SRC_URI = "file://gst-plugins-qti-oss/gst-plugin-base"
S = "${WORKDIR}/gst-plugins-qti-oss/gst-plugin-base"

# Install directries.
INSTALL_INCDIR := "${includedir}"
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.20.7"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_INCDIR}/linux-msm"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_INCDIR=${INSTALL_INCDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

PACKAGECONFIG = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libgbm', 'gbm', 'gbm', '', d)} "
PACKAGECONFIG[gbm] = " , ,gbm,gbm"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

INSANE_SKIP:${PN} = "dev-so"
