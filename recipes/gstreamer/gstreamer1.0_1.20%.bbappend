require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', 'gstreamer-common.inc', '', d), '', d)}
require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', 'update-alternatives/gstreamer1.0.inc', '', d), '', d)}

FILES:${PN}-dbg:remove = "${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', '${datadir}/gstreamer-1.0/gdb', '', d), '', d)}"