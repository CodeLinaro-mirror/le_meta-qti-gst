inherit cmake pkgconfig

SUMMARY = "QTI open-source GStreamer base"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"
LIC_FILES_CHKSUM:qcm6490 = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gbm"
DEPENDS += "adreno"
DEPENDS:remove:pineapple = "adreno"
DEPENDS += "qmmf-sdk"

# Dependency on FastCV library used in FCV Video Converter.
DEPENDS += "fastcv-noship"
DEPENDS:remove:qcm6490 = "fastcv-noship"
DEPENDS:append:qcm6490 = " fastcv-binaries"

# Dependency on IB2C library used in GLES Video Converter.
DEPENDS += "qti-ib2c"
RDEPENDS:${PN} += "qti-ib2c"
DEPENDS:remove:qcs6490 = "qti-ib2c"
RDEPENDS:${PN}:remove:qcs6490 = "qti-ib2c"
DEPENDS:remove:pineapple = "qti-ib2c"
RDEPENDS:${PN}:remove:pineapple = "qti-ib2c"
DEPENDS:remove:qcm4325-mtp = "qti-ib2c"
RDEPENDS:${PN}:remove:qcm4325-mtp = "qti-ib2c"
DEPENDS:remove:qcm2290-mtp-32 = "qti-ib2c"
RDEPENDS:${PN}:remove:qcm2290-mtp-32 = "qti-ib2c"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-base/"
S = "${WORKDIR}/gst-plugin-base"

# Install directories - Force lib for 32-bit builds
INSTALL_INCDIR := "${includedir}"
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"
INSTALL_LIBDIR:virtclass-multilib-lib32 = "lib"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_INCDIR}/linux-msm"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_INCDIR=${INSTALL_INCDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

do_configure:prepend() {
    if [ ${@bb.utils.contains('ARMPKGARCH', 'armv7a','true','', d)} ]; then
         cp -r "${GIT_CEILING_DIRECTORIES}/recipe-sysroot/usr/include/"  "${PKG_CONFIG_SYSROOT_DIR}/usr/"
    fi
}

## FILES configuration that handles both architectures
#FILES:${PN} = "${libdir}/*.so*"
#FILES:${PN} += "${bindir}/*"
## Add explicit lib64 path for when CMake installs there incorrectly
#FILES:${PN} += "/usr/lib64/*.so*"
#
## Development files
#FILES:${PN}-dev = "${includedir}/gstreamer-1.0/gst/utils/*"
#FILES:${PN}-dev += "${includedir}/gstreamer-1.0/gst/ml/*"
#FILES:${PN}-dev += "${includedir}/gstreamer-1.0/gst/video/*"
#FILES:${PN}-dev += "${includedir}/gstreamer-1.0/gst/cv/*"
#FILES:${PN}-dev += "${includedir}/gstreamer-1.0/gst/allocators/*"
#FILES:${PN}-dev += "${includedir}/gstreamer-1.0/gst/memory/*"
#
## Debug files
#FILES:${PN}-dbg = "${libdir}/.debug/*"
#FILES:${PN}-dbg += "/usr/lib64/.debug/*"
#
## Enable shared library versioning
SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

INSANE_SKIP:${PN} = "dev-so"
