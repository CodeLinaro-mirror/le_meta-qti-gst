inherit cmake pkgconfig

SUMMARY = "QTI open-source GStreamer Plug-in for video streams mixing"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"
LIC_FILES_CHKSUM:qcm6490 = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gstreamer1.0-plugins-qti-oss-base"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-vcomposer/"
S = "${WORKDIR}/gst-plugin-vcomposer"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

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
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_ORIGIN="Unknown package origin""

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

FILES:${PN}-dbg += "${INSTALL_LIBDIR}/gstreamer-1.0/.debug"

FILES:${PN} += "${libdir}/gstreamer-1.0/*.so*"
FILES:${PN} += "${libdir}/gstreamer-1.0/ml/modules/*.so*"
FILES:${PN} += "/usr/lib64/gstreamer-1.0/*.so*"
FILES:${PN} += "/usr/lib64/gstreamer-1.0/ml/modules/*.so*"

do_install:append() {
    if echo "${PN}" | grep -q "^lib32-"; then
        if [ -d "${D}/usr/lib64" ]; then
            echo "Moving lib64 files to lib for 32-bit build"
            mkdir -p ${D}/usr/lib
            cp -r ${D}/usr/lib64/* ${D}/usr/lib/ || true
            rm -rf ${D}/usr/lib64
        fi
    fi
}

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
