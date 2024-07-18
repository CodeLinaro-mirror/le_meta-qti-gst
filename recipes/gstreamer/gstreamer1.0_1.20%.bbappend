require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', bb.utils.contains('DISTRO_FEATURES', 'qimsdk-layers', 'gstreamer-common.inc', '', d), '', d), '', d)}
require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', bb.utils.contains('DISTRO_FEATURES', 'qimsdk-layers', 'update-alternatives/gstreamer1.0.inc', '', d), '', d), '', d)}

FILES:${PN}-dbg:remove = "${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', bb.utils.contains('DISTRO_FEATURES', 'qimsdk-layers', '${datadir}/gstreamer-1.0/gdb', '', d), '', d), '', d)}"

