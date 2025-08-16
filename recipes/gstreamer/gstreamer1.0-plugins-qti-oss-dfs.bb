inherit cmake pkgconfig

SUMMARY = "QTI open-source GStreamer Plug-in for DFS (Depth From Stereo)"
HOMEPAGE = "https://git.codelinaro.org"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM ="file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gstreamer1.0-plugins-qti-oss-base"
DEPENDS += "vslam"
DEPENDS:remove:qcs6490 += "vslam"
DEPENDS:remove:kalama += "vslam"
DEPENDS:remove:pineapple += "vslam"
DEPENDS:append:qcs6490 += "rv"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-dfs/"
S = "${WORKDIR}/gst-plugin-dfs"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

# Default RVSDK api version definitions.
RVSDK_API_VERSION := "0x202207"

#Overwrite the default RVSDK version definitions for qrb5165
RVSDK_API_VERSION:qrb5165 := "0x202307"

#Overwrite the default RVSDK version definitions for qcm2290-mtp and qcm4325-mtp
RVSDK_API_VERSION:bengal := "0x202307"

#Overwrite the default RVSDK version definitions for qcs6490
RVSDK_API_VERSION:qcs6490 := "0x202403"

#Overwrite the default RVSDK version definitions for kalama
RVSDK_API_VERSION:kalama := "0x202404"

#Overwrite the default RVSDK version definitions for pineapple
RVSDK_API_VERSION:pineapple := "0x202404"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_LICENSE=BSD"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_VERSION=${PV}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_PACKAGE=${PN}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_SUMMARY="${SUMMARY}""
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_ORIGIN=${HOMEPAGE}"
EXTRA_OECMAKE += "-DRVSDK_API_VERSION=${RVSDK_API_VERSION}"

INSANE_SKIP:${PN} = "already-stripped"
FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
