inherit cmake pkgconfig

SUMMARY = "QTI open-source GStreamer Plug-in for video encoding decoding with Codec 2.0"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gstreamer1.0-plugins-qti-oss-base"
DEPENDS += "codec2"
DEPENDS += "qti-c2-module"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-codec2/"
S = "${WORKDIR}/gst-plugin-codec2"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

CODEC2_CONFIG_VERSION_MAJOR := "1"
CODEC2_CONFIG_VERSION_MAJOR:kalama := "2"
CODEC2_CONFIG_VERSION_MAJOR:pineapple := "2"
CODEC2_CONFIG_VERSION_MAJOR:kera := "2"
CODEC2_CONFIG_VERSION_MAJOR:bengal := "2"
CODEC2_CONFIG_VERSION_MAJOR:sun := "2"
CODEC2_CONFIG_VERSION_MAJOR:vienna := "2"

CODEC2_CONFIG_VERSION_MINOR := "0"
CODEC2_CONFIG_VERSION_MINOR:kalama := "1"
CODEC2_CONFIG_VERSION_MINOR:pineapple := "1"
CODEC2_CONFIG_VERSION_MINOR:kera := "1"
CODEC2_CONFIG_VERSION_MINOR:sun := "1"

ENABLE_LINEAR_DMABUF:qrb5165 := "TRUE"
ENABLE_LINEAR_DMABUF:kalama := "TRUE"
ENABLE_LINEAR_DMABUF:kera := "TRUE"
ENABLE_LINEAR_DMABUF:vienna := "TRUE"
ENABLE_LINEAR_DMABUF:bengal := "TRUE"
ENABLE_AUDIO_PLUGINS:kalama := "TRUE"
ENABLE_AUDIO_PLUGINS:pineapple := "TRUE"
ENABLE_AUDIO_PLUGINS:sun := "TRUE"
ENABLE_AUDIO_PLUGINS:kera := "TRUE"
ENABLE_AUDIO_PLUGINS:vienna := "TRUE"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_INCDIR}/linux-msm"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_LICENSE=BSD"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_VERSION=${PV}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_PACKAGE=${PN}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_SUMMARY="${SUMMARY}""
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_ORIGIN="Unknown package origin""
EXTRA_OECMAKE += "-DGST_CODEC2_CONFIG_VERSION_MAJOR=${CODEC2_CONFIG_VERSION_MAJOR}"
EXTRA_OECMAKE += "-DGST_CODEC2_CONFIG_VERSION_MINOR=${CODEC2_CONFIG_VERSION_MINOR}"
EXTRA_OECMAKE += "-DGST_ENABLE_LINEAR_DMABUF=${ENABLE_LINEAR_DMABUF}"
EXTRA_OECMAKE += "-DGST_ENABLE_AUDIO_PLUGINS=${ENABLE_AUDIO_PLUGINS}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
TOOLCHAIN = "sdllvm"

placeholder := "${TARGET_SYS}"
TARGET_SYS = "${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', bb.utils.contains('DISTRO_FEATURES', 'qimsdk-layers', '${TARGET_ARCH}-linux-gnu', '${placeholder}', d), '${placeholder}', d), '${placeholder}', d)}"
TARGET_CFLAGS += "${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', bb.utils.contains('DISTRO_FEATURES', 'qimsdk-layers', '-I${STAGING_INCDIR}/c++', '', d), '', d), '', d)}"
DEBUG_PREFIX_MAP:remove = "-fcanon-prefix-map"
