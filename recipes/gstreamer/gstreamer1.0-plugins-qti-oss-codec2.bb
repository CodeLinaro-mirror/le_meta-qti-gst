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
DEPENDS:append:kalama += "media"
DEPENDS:append:kalama += "media-external"
DEPENDS:append:pineapple += "media"
DEPENDS:append:pineapple += "media-external"
DEPENDS:append:kera += "media"
DEPENDS:append:kera += "media-external"
DEPENDS:append:sun += "media"
DEPENDS:append:sun += "media-external"
DEPENDS:append:bengal += "media"
DEPENDS:append:bengal += "media-external"
DEPENDS:append:qrb5165 += "media-codec2"
DEPENDS:append:qcs6490 += "media-codec2"
DEPENDS += "qti-c2-module"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-codec2/"
S = "${WORKDIR}/gst-plugin-codec2"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

CODEC2_CONFIG_VERSION := "1.0"
CODEC2_CONFIG_VERSION:kalama := "2.0"
CODEC2_CONFIG_VERSION:pineapple := "2.0"
CODEC2_CONFIG_VERSION:kera := "2.0"
CODEC2_CONFIG_VERSION:bengal := "2.0"
CODEC2_CONFIG_VERSION:sun := "2.0"

ENABLE_LINEAR_DMABUF:qrb5165 := "TRUE"
ENABLE_LINEAR_DMABUF:kalama := "TRUE"
ENABLE_LINEAR_DMABUF:kera := "TRUE"
ENABLE_LINEAR_DMABUF:bengal := "TRUE"
ENABLE_AUDIO_PLUGINS:kalama := "TRUE"
ENABLE_AUDIO_PLUGINS:pineapple := "TRUE"
ENABLE_AUDIO_PLUGINS:kera := "TRUE"

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
EXTRA_OECMAKE += "-DGST_CODEC2_CONFIG_VERSION=${CODEC2_CONFIG_VERSION}"
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
