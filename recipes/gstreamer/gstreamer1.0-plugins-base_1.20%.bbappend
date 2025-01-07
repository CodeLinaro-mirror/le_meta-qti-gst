require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', bb.utils.contains('DISTRO_FEATURES', 'qimsdk-layers', 'gstreamer-common.inc', '', d), '', d), '', d)}
require ${@bb.utils.contains('BASEMACHINE', 'kalama', bb.utils.contains('PRODUCT', 'ubuntu', bb.utils.contains('DISTRO_FEATURES', 'qimsdk-layers', 'update-alternatives/gstreamer1.0-plugins-base.inc', '', d), '', d), '', d)}

DISTRO_FEATURES:remove:pineapple += "opengl"
