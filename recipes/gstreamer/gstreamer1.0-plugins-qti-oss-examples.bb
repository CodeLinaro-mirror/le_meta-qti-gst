inherit cmake pkgconfig

SUMMARY = "Generic examples for GStreamer pipelines."
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-qti-oss-mlmeta"
DEPENDS += "binder"
DEPENDS += "gstreamer1.0-plugins-qti-oss-base"

DEPENDS_append_sdmsteppe += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-camera', bb.utils.contains('DISTRO_FEATURES', 'qti-camera-metadata', 'camera-metadata', 'libcamera-client', d), '', d)}"
DEPENDS_append_qrb5165 += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-camera', 'libhardware', '', d)}"
DEPENDS_append_qrb5165 += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-camera', 'camera-metadata', '', d)}"
DEPENDS_append_qrbx210 += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-camera', 'libhardware', '', d)}"
DEPENDS_append_qrbx210 += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-camera', 'camera-metadata', '', d)}"
DEPENDS_append_qcs6490 += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-camera', 'libhardware', '', d)}"
DEPENDS_append_qcs6490 += "${@bb.utils.contains('DISTRO_FEATURES', 'qti-camera', 'camera-metadata', '', d)}"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"
SRC_URI = "file://gst-plugin-examples/"
S = "${WORKDIR}/gst-plugin-examples/"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

PACKAGECONFIG ??= " \
   ${@bb.utils.contains("COMBINED_FEATURES", "qti-afr-algo", "auto-framing", "", d)} \
   "

PACKAGECONFIG[auto-framing] = "-DENABLE_TRACKING_CAM=true, -DENABLE_TRACKING_CAM=false, qti-auto-framing-stabilization, qti-auto-framing-stabilization"

CAMERA_CLIENT_DISABLED := "FALSE"
CAMERA_CLIENT_DISABLED_sdmsteppe := "${@bb.utils.contains('DISTRO_FEATURES', 'qti-camera-metadata', 'TRUE', 'FALSE', d)}"
CAMERA_CLIENT_DISABLED_qrb5165 := "TRUE"
CAMERA_CLIENT_DISABLED_qrbx210 := "TRUE"
CAMERA_CLIENT_DISABLED_qcs6490 := "TRUE"

CODEC2_ENCODE := "FALSE"
CODEC2_ENCODE_qrbx210 := "TRUE"
CODEC2_ENCODE_qcs6490 := "TRUE"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"
EXTRA_OECMAKE += "-DCAMERA_CLIENT_DISABLED=${CAMERA_CLIENT_DISABLED}"
EXTRA_OECMAKE += "-DCODEC2_ENCODE=${CODEC2_ENCODE}"

FILES_${PN} += "${INSTALL_BINDIR}"
FILES_${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
