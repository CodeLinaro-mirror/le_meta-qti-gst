inherit update-alternatives-ubuntu_v2

ALTERNATIVE_PRIORITY:gstreamer1.0-libav = "100"

ALTERNATIVE:gstreamer1.0-libav = "libgstlibav.so"

QTI_SEPECIFIC_LINK[libgstlibav.so] = "/usr/lib/gstreamer-1.0/libgstlibav.so"
ALTERNATIVE_LINK_NAME[libgstlibav.so] = "/usr/lib/aarch64-linux-gnu/gstreamer-1.0/libgstlibav.so"
