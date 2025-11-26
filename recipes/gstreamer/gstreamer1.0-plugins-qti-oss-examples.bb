inherit cmake pkgconfig

SUMMARY = "Generic examples for GStreamer pipelines."
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"
LIC_FILES_CHKSUM:qcm6490 = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-qti-oss-base"
DEPENDS += "securemsm"
DEPENDS += "media-headers"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-camera', 'qmmf-sdk', '', d)}"
DEPENDS += "curl"

RDEPENDS:${PN} += "${@bb.utils.contains('COMBINED_FEATURES', 'qti-afr-algo', 'qti-auto-framing-stabilization', '', d)}"

DEPENDS:append:qcm6490 = " camera-server"
DEPENDS:remove:qcm6490 = "securemsm"
DEPENDS:remove:qcm6490 = "media-headers"
DEPENDS:remove:pineapple = "securemsm"
DEPENDS:remove:pineapple = "media-headers"
DEPENDS:remove:sun = "securemsm"
DEPENDS:remove:sun = "media-headers"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"
SRC_URI = "file://gst-plugin-examples/"
S = "${WORKDIR}/gst-plugin-examples"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

# Camera-related variables
ENABLE_CAMERA := "TRUE"

# Encode-related variables
ENABLE_VIDEO_ENCODE := "TRUE"

CODEC2_ENCODE := "FALSE"
CODEC2_ENCODE:qrbx210 := "TRUE"
CODEC2_ENCODE:kalama := "TRUE"
CODEC2_ENCODE:pineapple := "TRUE"
CODEC2_ENCODE:kera := "TRUE"
CODEC2_ENCODE:vienna := "TRUE"
CODEC2_ENCODE:sun := "TRUE"
CODEC2_ENCODE:qcs6490 := "TRUE"

# Decode-related variables
ENABLE_VIDEO_DECODE := "TRUE"

# Display-related variables
ENABLE_DISPLAY := "TRUE"

# ML-related variables
ENABLE_ML := "TRUE"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
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

CFLAGS:append:sun = " \
    -Wno-error=incompatible-pointer-types"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
