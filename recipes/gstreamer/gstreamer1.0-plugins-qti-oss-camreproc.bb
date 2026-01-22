inherit cmake pkgconfig

SUMMARY = "QTI open-source GStreamer Plug-in for reprocessing via camera module"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-qti-bsp/files/common-licenses/${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"
LIC_FILES_CHKSUM:qcm6490 = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "gstreamer1.0-plugins-qti-oss-base"
DEPENDS += "qmmf-sdk"

FILESPATH =+ "${WORKSPACE}/vendor/qcom/opensource/gst-plugins-qti-oss/:"

SRC_URI = "file://gst-plugin-camreproc/"
S = "${WORKDIR}/gst-plugin-camreproc"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

# Default platform definations.
CAMERA_METADATA_VERSION := "1.0"

# Overwrite the default platform definitions for qrb5165.
CAMERA_METADATA_VERSION:qrb5165  := "1.1"

# Overwrite the default platform definitions for qcm2290-mtp and qcm4325-mtp.
CAMERA_METADATA_VERSION:bengal  := "1.1"

# Overwrite the default platform definitions for kalama.
CAMERA_METADATA_VERSION:kalama   := "1.0ns"

# Overwrite the default platform definitions for kera.
CAMERA_METADATA_VERSION:kera   := "1.0ns"

# Overwrite the default platform definitions for alor.
CAMERA_METADATA_VERSION:alor   := "1.0ns"

# Overwrite the default platform definitions for vienna.
CAMERA_METADATA_VERSION:vienna   := "1.0ns"

# Overwrite the default platform definitions for qcs6490.
CAMERA_METADATA_VERSION:qcs6490  := "1.1"

# Overwrite the default platform definitions for sun.
CAMERA_METADATA_VERSION:sun   := "1.0ns"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DKERNEL_BUILDDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DCAMERA_METADATA_VERSION=${CAMERA_METADATA_VERSION}"

EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_LICENSE=BSD"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_VERSION=${PV}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_PACKAGE=${PN}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_SUMMARY="${SUMMARY}""
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_ORIGIN="Unknown package origin""

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

FILES:${PN}-dbg += "${INSTALL_LIBDIR}/gstreamer-1.0/.debug"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
