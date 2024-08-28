require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', 'gstreamer-common.inc', '', d), '', d)}
require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', 'update-alternatives/gstreamer1.0-libav.inc', '', d), '', d)}

export PKG_CONFIG_PATH="${STAGING_DIR_HOST}/lib/pkgconfig:${STAGING_DIR_HOST}${libdir}/pkgconfig:${STAGING_DIR_HOST}/usr/share/pkgconfig:"
