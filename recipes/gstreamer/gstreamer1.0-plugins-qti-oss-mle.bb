inherit cmake

SUMMARY = "QTI open-source GStreamer Plug-in for Machine Learning Engine"
HOMEPAGE = "https://source.codeaurora.org"
SECTION = "multimedia"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gstreamer1.0-plugins-qti-oss-mlmeta"
DEPENDS += "tensorflow-lite"
DEPENDS += "util-linux"
DEPENDS += "jsoncpp"
DEPENDS += "fastcv-noship"
DEPENDS += "libion"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-mle/"
S = "${WORKDIR}/gst-plugin-mle"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

SNPE_ROOT := "Please specify workspace location"
SNPE_LIB_DIR="arm-oe-linux-gcc8.2hf"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_LICENSE=${LICENSE}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_VERSION=${PV}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_PACKAGE=${PN}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_SUMMARY="${SUMMARY}""
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_ORIGIN=${HOMEPAGE}"

EXTRA_OECMAKE += "-DSNPE_ENABLE=${@bb.utils.contains('DISTRO_FEATURES','snpe-enable', 'true', 'false', d)}"
EXTRA_OECMAKE += "-DSNPE_INCLUDE_DIR=${@bb.utils.contains('DISTRO_FEATURES','snpe-enable', '${SNPE_ROOT}/include/zdl', '', d)}"
EXTRA_OECMAKE += "-DSNPE_LIB_DIR=${@bb.utils.contains('DISTRO_FEATURES','snpe-enable', '${SNPE_ROOT}/lib/${SNPE_LIB_DIR}', '', d)}"

FILES_${PN} += "${INSTALL_BINDIR}"
FILES_${PN} += "${INSTALL_LIBDIR}"
FILES_${PN} += "/data/misc/camera/"
INSANE_SKIP_${PN} += "file-rdeps"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
