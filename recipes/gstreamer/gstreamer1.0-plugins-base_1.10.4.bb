require gstreamer1.0-plugins-base.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=c54ce9345727175ff66d17b67ff51f58 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gst-plugins-base/gst-plugins-base-${PV}.tar.xz \
    file://get-caps-from-src-pad-when-query-caps.patch \
    file://0003-ssaparse-enhance-SSA-text-lines-parsing.patch \
    file://0004-subparse-set-need_segment-after-sink-pad-received-GS.patch \
    file://encodebin-Need-more-buffers-in-output-queue-for-bett.patch \
    file://make-gio_unix_2_0-dependency-configurable.patch \
    file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
    file://0001-audioringbuffer-do-not-require-4-byte-multiple-for-e.patch \
    file://0001-audioringbuffer-Also-support-raw-AAC.patch \
    file://Add-Flac.patch \
    file://Add-Vorbis.patch \
    file://0001-audioringbuffer-add-wma-and-alac-to-encoded-audio-fo.patch \
    file://0002-audioringbuffer-Fix-8kHz-MP3-playback-issue.patch \
"
SRC_URI[md5sum] = "f6b46f8fac01eb773d556e3efc369e86"
SRC_URI[sha256sum] = "f6d245b6b3d4cb733f81ebb021074c525ece83db0c10e932794b339b8d935eb7"

S = "${WORKDIR}/gst-plugins-base-${PV}"
