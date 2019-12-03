DEPENDS += "gobject-introspection gobject-introspection-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-libav:"

SRC_URI += "\
            file://0001-Expose-support-for-DSD-and-IFF-for-1.14.4.patch \
            "

LICENSE_FLAGS_WHITELIST = "commercial"

###### GROUPS FOR LIBAV PLUGIN ######

AUDIO_PARSE_ENABLE_LIBAV = " --enable-demuxer=ape \
                             --enable-demuxer=iff  \
                             --enable-demuxer=dsf "

AUDIO_DECODE_ENABLE_LIBAV = " --enable-decoder=ape \
                              --enable-decoder=mp3 \
                              --enable-decoder=flac \
                              --enable-decoder=aac  \
                              --enable-decoder=alac \
                              --enable-decoder=wmalossless \
                              --enable-decoder=wmapro  \
                              --enable-decoder=wmav1  \
                              --enable-decoder=wmav2 \
                              --enable-decoder=wmavoice  \
                              --enable-decoder=dsd_lsbf \
                              --enable-decoder=dsd_lsbf_planar \
                              --enable-decoder=dsd_msbf \
                              --enable-decoder=dsd_msbf_planar "

AUDIO_ENCODE_ENABLE_LIBAV = " --enable-encoder=aac "

DISABLE_ALL_LIBAV = "--disable-everything "

CONFIGURE_PLUGINS_LIBAV = " ${DISABLE_ALL_LIBAV} \
                            ${AUDIO_PARSE_ENABLE_LIBAV} \
                            ${AUDIO_DECODE_ENABLE_LIBAV} \
                            ${AUDIO_ENCODE_ENABLE_LIBAV}"

LIBAV_EXTRA_CONFIGURE_COMMON_ARG_append = "${@bb.utils.contains("DISTRO_FEATURES", "audio-only-libav", " ${CONFIGURE_PLUGINS_LIBAV}", "",d)}"
