inherit cmake pkgconfig

SUMMARY = "QTI open-source GStreamer Plug-in for qmmf-sdk"
HOMEPAGE = "http://www.qualcomm.com"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gstreamer1.0-plugins-qti-oss-base"
DEPENDS += "qmmf-sdk"
DEPENDS:remove:qcm6490 = "qmmf-sdk"
DEPENDS:append:qcm6490 = " camera-server"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"
SRC_URI = "file://gst-plugin-qmmfsrc/"
S = "${WORKDIR}/gst-plugin-qmmfsrc"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

# Default platform definitions.
IMAGE_MAX_WIDTH         := "4096"
IMAGE_MAX_HEIGHT        := "4096"
VIDEO_MAX_WIDTH         := "4096"
VIDEO_MAX_HEIGHT        := "4096"
VIDEO_MAX_FPS           := "120/1"
CAMERA_METADATA_VERSION := "1.0"
VIDEO_TYPE_SUPPORT      := "FALSE"
CAMERA_SERVICE          := "QMMF"

# Default QMMF Feature Set
FEATURE_LOGICAL_CAMERA_SUPPORT                := "FALSE"
FEATURE_LOGICAL_CAMERA_SENSOR_SWITCH          := "FALSE"

# Overwrite the default platform definitions for qrb5165.
IMAGE_MAX_WIDTH:qrb5165          := "9248"
IMAGE_MAX_HEIGHT:qrb5165         := "6944"
VIDEO_MAX_WIDTH:qrb5165          := "9248"
VIDEO_MAX_HEIGHT:qrb5165         := "6944"
VIDEO_MAX_FPS:qrb5165            := "480/1"
CAMERA_METADATA_VERSION:qrb5165  := "1.1"
VIDEO_UYVY_FORMAT_ENABLE:qrb5165 := "TRUE"
IMAGE_NV12_FORMAT_ENABLE:qrb5165 := "TRUE"

# Overwrite the default platform definitions for kalama.
IMAGE_MAX_WIDTH:kalama                 := "15360"
IMAGE_MAX_HEIGHT:kalama                := "7680"
VIDEO_MAX_WIDTH:kalama                 := "15360"
VIDEO_MAX_HEIGHT:kalama                := "7680"
VIDEO_MAX_FPS:kalama                   := "480/1"
CAMERA_METADATA_VERSION:kalama         := "1.0ns"
VIDEO_TYPE_SUPPORT:kalama              := "TRUE"
VIDEO_P010_10LE_FORMAT_ENABLE:kalama   := "TRUE"
VIDEO_NV12_10LE32_FORMAT_ENABLE:kalama := "TRUE"
IMAGE_NV12_FORMAT_ENABLE:kalama        := "TRUE"
FEATURE_LOGICAL_CAMERA_SUPPORT:kalama                := "TRUE"
FEATURE_LOGICAL_CAMERA_SENSOR_SWITCH:kalama          := "TRUE"

# Overwrite the default platform definitions for pineapple.
IMAGE_MAX_WIDTH:pineapple                 := "16384"
IMAGE_MAX_HEIGHT:pineapple                := "8192"
VIDEO_MAX_WIDTH:pineapple                 := "16384"
VIDEO_MAX_HEIGHT:pineapple                := "8192"
VIDEO_MAX_FPS:pineapple                   := "480/1"
CAMERA_METADATA_VERSION:pineapple         := "1.0ns"
VIDEO_TYPE_SUPPORT:pineapple              := "TRUE"
VIDEO_P010_10LE_FORMAT_ENABLE:pineapple   := "TRUE"
VIDEO_NV12_10LE32_FORMAT_ENABLE:pineapple := "TRUE"
IMAGE_NV12_FORMAT_ENABLE:pineapple        := "TRUE"
FEATURE_LOGICAL_CAMERA_SUPPORT:pineapple                := "TRUE"
FEATURE_LOGICAL_CAMERA_SENSOR_SWITCH:pineapple          := "TRUE"

# Overwrite the default platform definitions for kera.
IMAGE_MAX_WIDTH:kera                 := "9248"
IMAGE_MAX_HEIGHT:kera                := "6944"
VIDEO_MAX_WIDTH:kera                 := "9248"
VIDEO_MAX_HEIGHT:kera                := "6944"
VIDEO_MAX_FPS:kera                   := "480/1"
CAMERA_METADATA_VERSION:kera         := "1.0ns"
VIDEO_TYPE_SUPPORT:kera              := "TRUE"
VIDEO_P010_10LE_FORMAT_ENABLE:kera   := "TRUE"
VIDEO_NV12_10LE32_FORMAT_ENABLE:kera := "TRUE"
IMAGE_NV12_FORMAT_ENABLE:kera        := "TRUE"
FEATURE_LOGICAL_CAMERA_SUPPORT:kera                := "TRUE"
FEATURE_LOGICAL_CAMERA_SENSOR_SWITCH:kera          := "TRUE"

# Overwrite the default platform definitions for alor.
IMAGE_MAX_WIDTH:alor                 := "9248"
IMAGE_MAX_HEIGHT:alor                := "6944"
VIDEO_MAX_WIDTH:alor                 := "9248"
VIDEO_MAX_HEIGHT:alor                := "6944"
VIDEO_MAX_FPS:alor                   := "480/1"
CAMERA_METADATA_VERSION:alor         := "1.0ns"
VIDEO_TYPE_SUPPORT:alor              := "TRUE"
VIDEO_P010_10LE_FORMAT_ENABLE:alor   := "TRUE"
VIDEO_NV12_10LE32_FORMAT_ENABLE:alor := "TRUE"
IMAGE_NV12_FORMAT_ENABLE:alor        := "TRUE"
FEATURE_LOGICAL_CAMERA_SUPPORT:alor                := "TRUE"
FEATURE_LOGICAL_CAMERA_SENSOR_SWITCH:alor          := "TRUE"

# Overwrite the default platform definitions for vienna.
IMAGE_MAX_WIDTH:vienna      := "9248"
IMAGE_MAX_HEIGHT:vienna     := "6944"
VIDEO_MAX_WIDTH:vienna      := "9248"
VIDEO_MAX_HEIGHT:vienna     := "6944"
VIDEO_MAX_FPS:vienna        := "480/1"
CAMERA_METADATA_VERSION:vienna         := "1.0ns"
VIDEO_TYPE_SUPPORT:vienna   := "TRUE"
VIDEO_P010_10LE_FORMAT_ENABLE:vienna   := "TRUE"
VIDEO_NV12_10LE32_FORMAT_ENABLE:vienna := "TRUE"
IMAGE_NV12_FORMAT_ENABLE:vienna        := "TRUE"
FEATURE_LOGICAL_CAMERA_SUPPORT:vienna     := "TRUE"
FEATURE_LOGICAL_CAMERA_SENSOR_SWITCH:vienna := "TRUE"

# Overwrite the default platform definitions for qcm2290-mtp and qcm4325-mtp.
IMAGE_MAX_WIDTH:bengal          := "9248"
IMAGE_MAX_HEIGHT:bengal         := "6944"
VIDEO_MAX_WIDTH:bengal          := "9248"
VIDEO_MAX_HEIGHT:bengal         := "6944"
VIDEO_MAX_FPS:bengal            := "480/1"
CAMERA_METADATA_VERSION:bengal  := "1.1"
VIDEO_UYVY_FORMAT_ENABLE:bengal := "TRUE"
IMAGE_NV12_FORMAT_ENABLE:bengal := "TRUE"

# Overwrite the default platform definitions for qcs6490.
VIDEO_MAX_FPS:qcs6490            := "240/1"
CAMERA_METADATA_VERSION:qcs6490  := "1.1"
VIDEO_YUY2_FORMAT_ENABLE:qcs6490 := "TRUE"

# Overwrite the default platform definitions for qcm6490
VIDEO_TYPE_SUPPORT:qcm6490 := "TRUE"
CAMERA_SERVICE:qcm6490     := "LECAM"


# Overwrite the default platform definitions for sun.
IMAGE_MAX_WIDTH:sun                 := "9248"
IMAGE_MAX_HEIGHT:sun                := "8192"
VIDEO_MAX_WIDTH:sun                 := "9248"
VIDEO_MAX_HEIGHT:sun                := "8192"
VIDEO_MAX_FPS:sun                   := "480/1"
CAMERA_METADATA_VERSION:sun         := "1.0ns"
VIDEO_TYPE_SUPPORT:sun              := "TRUE"
VIDEO_P010_10LE_FORMAT_ENABLE:sun   := "TRUE"
VIDEO_NV12_10LE32_FORMAT_ENABLE:sun := "TRUE"
IMAGE_NV12_FORMAT_ENABLE:sun        := "TRUE"
FEATURE_LOGICAL_CAMERA_SUPPORT:sun                := "TRUE"
FEATURE_LOGICAL_CAMERA_SENSOR_SWITCH:sun          := "TRUE"

# Overwrite the default platform definitions for qrbx210-rbx.
CAMERA_METADATA_VERSION_qrbx210-rbx := "1.1"
VIDEO_TYPE_SUPPORT_qrbx210-rbx      := "TRUE"

# Qmmf Feature Set
QMMF_FEATURE_SET := \
    "-DFEATURE_LOGICAL_CAMERA_SUPPORT=${FEATURE_LOGICAL_CAMERA_SUPPORT}"
QMMF_FEATURE_SET += \
    "-DFEATURE_LOGICAL_CAMERA_SENSOR_SWITCH=${FEATURE_LOGICAL_CAMERA_SENSOR_SWITCH}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_LICENSE=BSD"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_VERSION=${PV}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_PACKAGE=${PN}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_SUMMARY="${SUMMARY}""
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_ORIGIN=${HOMEPAGE}"

EXTRA_OECMAKE += "-DGST_IMAGE_MAX_WIDTH=${IMAGE_MAX_WIDTH}"
EXTRA_OECMAKE += "-DGST_IMAGE_MAX_HEIGHT=${IMAGE_MAX_HEIGHT}"

EXTRA_OECMAKE += "-DGST_VIDEO_MAX_WIDTH=${VIDEO_MAX_WIDTH}"
EXTRA_OECMAKE += "-DGST_VIDEO_MAX_HEIGHT=${VIDEO_MAX_HEIGHT}"
EXTRA_OECMAKE += "-DGST_VIDEO_MAX_FPS=${VIDEO_MAX_FPS}"
EXTRA_OECMAKE += "-DGST_VIDEO_H265_ENABLE=${VIDEO_H265_ENABLE}"
EXTRA_OECMAKE += "-DCAMERA_METADATA_VERSION=${CAMERA_METADATA_VERSION}"
EXTRA_OECMAKE += "-DGST_VIDEO_YUY2_FORMAT_ENABLE=${VIDEO_YUY2_FORMAT_ENABLE}"
EXTRA_OECMAKE += "-DGST_VIDEO_UYVY_FORMAT_ENABLE=${VIDEO_UYVY_FORMAT_ENABLE}"
EXTRA_OECMAKE += "-DGST_VIDEO_P010_10LE_FORMAT_ENABLE=${VIDEO_P010_10LE_FORMAT_ENABLE}"
EXTRA_OECMAKE += "-DGST_VIDEO_NV12_10LE32_FORMAT_ENABLE=${VIDEO_NV12_10LE32_FORMAT_ENABLE}"
EXTRA_OECMAKE += "-DGST_IMAGE_NV12_FORMAT_ENABLE=${IMAGE_NV12_FORMAT_ENABLE}"
EXTRA_OECMAKE += "-DGST_VIDEO_TYPE_SUPPORT=${VIDEO_TYPE_SUPPORT}"
EXTRA_OECMAKE += "-DCAMERA_SERVICE=${CAMERA_SERVICE}"
EXTRA_OECMAKE += "${QMMF_FEATURE_SET}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
