# Packages installed to the root file system

IMAGE_INSTALL += "${@bb.utils.contains('MULTILIB_VARIANTS', 'lib32', 'lib32-gstreamer1.0', 'gstreamer1.0', d)}"
IMAGE_INSTALL += "${@bb.utils.contains('MULTILIB_VARIANTS', 'lib32', 'lib32-gstreamer1.0-plugins-base', 'gstreamer1.0-plugins-base', d)}"
IMAGE_INSTALL += "${@bb.utils.contains('MULTILIB_VARIANTS', 'lib32', 'lib32-gstreamer1.0-plugins-good', 'gstreamer1.0-plugins-good', d)}"
IMAGE_INSTALL += "${@bb.utils.contains('MULTILIB_VARIANTS', 'lib32', 'lib32-gstreamer1.0-plugins-bad', 'gstreamer1.0-plugins-bad', d)}"
IMAGE_INSTALL += "${@bb.utils.contains('MULTILIB_VARIANTS', 'lib32', 'lib32-gstreamer1.0-rtsp-server', 'gstreamer1.0-rtsp-server', d)}"
IMAGE_INSTALL += "${@bb.utils.contains('MULTILIB_VARIANTS', 'lib32', 'lib32-gstreamer1.0-plugins-ugly', 'gstreamer1.0-plugins-ugly', d)}"
IMAGE_INSTALL += "${@bb.utils.contains('MULTILIB_VARIANTS', 'lib32', 'lib32-gstreamer1.0-omx', 'gstreamer1.0-omx', d)}"
IMAGE_INSTALL += "${@bb.utils.contains('MULTILIB_VARIANTS', 'lib32', 'lib32-gstreamer1.0-libav', 'gstreamer1.0-libav', d)}"
