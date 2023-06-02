SUMMARY = "Tensorflow Lite Prebuilts"
DESCRIPTION = "TensorFlow Lite C++ Prebuilt Library"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "\
	file://tflite-dev.tar.gz \
	"

S = "${WORKDIR}"

do_install() {

	cp -r ${S}/usr ${D}/

}

FILES:${PN} = "${libdir}/lib*.so ${bindir}/*"
FILES:${PN}-dev += "${includedir}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
