inherit cmake pkgconfig

SUMMARY = "Generic examples for GStreamer pipelines."
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-qti-oss-base"
DEPENDS:append += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-camera', 'qmmf-sdk', '', d)}"

DEPENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'qti-afr-algo', 'qti-auto-framing-stabilization gstreamer1.0-plugins-qti-oss-mlmeta', '', d)}"
RDEPENDS:${PN} += "${@bb.utils.contains('COMBINED_FEATURES', 'qti-afr-algo', 'qti-auto-framing-stabilization', '', d)}"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"
SRC_URI = "file://gst-plugin-examples/"
S = "${WORKDIR}/gst-plugin-examples/"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

# Camera-related variables
ENABLE_CAMERA := "TRUE"

# Encode-related variables
ENABLE_VIDEO_ENCODE := "TRUE"

CODEC2_ENCODE := "TRUE"
CODEC2_ENCODE_qrbx210 := "TRUE"
CODEC2_ENCODE_qcs6490 := "TRUE"

# Decode-related variables
ENABLE_VIDEO_DECODE := "TRUE"

# Display-related variables
ENABLE_DISPLAY := "TRUE"

# ML-related variables
ENABLE_ML := "TRUE"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"
EXTRA_OECMAKE += "-DCODEC2_ENCODE=${CODEC2_ENCODE}"
EXTRA_OECMAKE += "-DENABLE_CAMERA=${ENABLE_CAMERA}"
EXTRA_OECMAKE += "-DENABLE_VIDEO_ENCODE=${ENABLE_VIDEO_ENCODE}"
EXTRA_OECMAKE += "-DENABLE_VIDEO_DECODE=${ENABLE_VIDEO_DECODE}"
EXTRA_OECMAKE += "-DENABLE_DISPLAY=${ENABLE_DISPLAY}"
EXTRA_OECMAKE += "-DENABLE_ML=${ENABLE_ML}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
