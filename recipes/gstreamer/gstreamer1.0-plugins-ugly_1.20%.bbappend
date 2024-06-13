require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', 'gstreamer-common.inc', '', d), '', d)}
require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', 'update-alternatives/gstreamer1.0-plugins-ugly.inc', '', d), '', d)}

PACKAGECONFIG:remove = "mpeg2dec"

