inherit cmake pkgconfig gobject-introspection

SUMMARY = "QTI open-source GStreamer base"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"
LIC_FILES_CHKSUM:qcm6490 = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "json-glib"
DEPENDS += "python3-pygobject"
DEPENDS += "gbm"
DEPENDS += "adreno"
DEPENDS:remove:pineapple = "adreno"
DEPENDS += "qmmf-sdk"

# Dependency on FastCV library used in FCV Video Converter.
DEPENDS += "fastcv-noship"
DEPENDS:remove:qcm6490 = "fastcv-noship"
DEPENDS:append:qcm6490 = " fastcv-binaries"

# Remove unsupported dependency
DEPENDS:remove:qrbx210 = "python3-pygobject"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-base/"
S = "${WORKDIR}/gst-plugin-base"

# Install directries.
INSTALL_INCDIR := "${includedir}"
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"
INSTALL_DATADIR := "${datadir}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DSYSROOT_BINDIR=${STAGING_BINDIR}"
EXTRA_OECMAKE += "-DPYTHON_SITEPACKAGES_DIR=${PYTHON_SITEPACKAGES_DIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_INCDIR=${INSTALL_INCDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_DATADIR=${INSTALL_DATADIR}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"
FILES:${PN} += "${INSTALL_DATADIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

INSANE_SKIP:${PN} = "dev-so"
