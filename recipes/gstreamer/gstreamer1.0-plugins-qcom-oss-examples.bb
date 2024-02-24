inherit cmake pkgconfig qimsdk-base

SUMMARY = "Generic examples for GStreamer pipelines."
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-qcom-oss-mlmeta"
DEPENDS += "binder"
DEPENDS += "gstreamer1.0-plugins-qcom-oss-base"
DEPENDS += "securemsm"
DEPENDS += "media-headers"
DEPENDS += "libutils"
DEPENDS += "qmmf-sdk"

SRCDIR = "gst-plugins-qcom-oss"
SRC_URI = "file://${SRCDIR}"
S = "${WORKDIR}/${SRCDIR}"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

PACKAGECONFIG ??= " \
   ${@bb.utils.contains("QCOM_AFR_ALGO", "TRUE", "auto-framing", "", d)} \
   "

PACKAGECONFIG[auto-framing] = "-DENABLE_TRACKING_CAM=true, -DENABLE_TRACKING_CAM=false, qti-auto-framing-stabilization, qti-auto-framing-stabilization"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"
EXTRA_OECMAKE += "-DCAMERA_CLIENT_DISABLED=${CAMERA_CLIENT_DISABLED}"
EXTRA_OECMAKE += "-DCODEC2_ENCODE=${CODEC2_ENCODE}"
EXTRA_OECMAKE += "-DCAMERA_METADATA_VERSION=${CAMERA_METADATA_VERSION}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
