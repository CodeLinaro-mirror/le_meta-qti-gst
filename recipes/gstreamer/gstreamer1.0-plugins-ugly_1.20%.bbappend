require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', 'gstreamer-common.inc', '', d), '', d)}

PACKAGECONFIG:remove = "mpeg2dec"

