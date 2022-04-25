inherit cmake

SUMMARY = "QTI open-source GStreamer Plug-in for Machine Learning Engine"
HOMEPAGE = "https://git.codelinaro.org"
SECTION = "multimedia"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gstreamer1.0-plugins-qti-oss-base"
DEPENDS += "gstreamer1.0-plugins-qti-oss-mlmeta"
DEPENDS += "util-linux"
DEPENDS += "fastcv-noship"
DEPENDS += "libion"
DEPENDS += "liblog"
DEPENDS += "libutils"

PACKAGECONFIG ??= " \
  ${@bb.utils.contains('DISTRO_FEATURES', 'tensorflow-lite', 'tensorflow-lite', '', d)} \
  ${@bb.utils.contains('DISTRO_FEATURES', 'qti-snpe', 'snpe', '', d)} \
  ${@bb.utils.contains('MACHINE_FEATURES','qti-tflite-delegate', 'delegate', '', d)} \
"

PACKAGECONFIG[tensorflow-lite] = " -DTFLITE_ENABLE=true, -DTFLITE_ENABLE=false, tensorflow-lite,"
PACKAGECONFIG[snpe] = " -DSNPE_ENABLE=true, -DSNPE_ENABLE=false, snpe,"
PACKAGECONFIG[delegate] = " -DDELEGATE_SUPPORT=true, -DDELEGATE_SUPPORT=false,,"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-mle/"
S = "${WORKDIR}/gst-plugin-mle"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_ENGINEDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_LICENSE=${LICENSE}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_VERSION=${PV}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_PACKAGE=${PN}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_SUMMARY="${SUMMARY}""
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_ORIGIN=${HOMEPAGE}"

FILES_${PN} += "${INSTALL_BINDIR}"
FILES_${PN} += "${INSTALL_LIBDIR}"
FILES_${PN} += "/data/misc/camera/"
INSANE_SKIP_${PN} += "file-rdeps"

FILES_${PN}-dbg += "${INSTALL_LIBDIR}/gstreamer-1.0/.debug"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
