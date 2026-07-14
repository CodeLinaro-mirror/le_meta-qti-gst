inherit update-alternatives-ubuntu_v2

ALTERNATIVE_PRIORITY:gstreamer1.0-plugins-ugly = "100"

ALTERNATIVE:gstreamer1.0-plugins-ugly = "libgsta52dec.so libgstasf.so libgstdvdlpcmdec.so libgstdvdsub.so libgstrealmedia.so libgstxingmux.so"

QTI_SEPECIFIC_LINK[libgsta52dec.so] = "/usr/lib/gstreamer-1.0/libgsta52dec.so"
ALTERNATIVE_LINK_NAME[libgsta52dec.so] = "/usr/lib/aarch64-linux-gnu/gstreamer-1.0/libgsta52dec.so"

QTI_SEPECIFIC_LINK[libgstasf.so] = "/usr/lib/gstreamer-1.0/libgstasf.so"
ALTERNATIVE_LINK_NAME[libgstasf.so] = "/usr/lib/aarch64-linux-gnu/gstreamer-1.0/libgstasf.so"

QTI_SEPECIFIC_LINK[libgstdvdlpcmdec.so] = "/usr/lib/gstreamer-1.0/libgstdvdlpcmdec.so"
ALTERNATIVE_LINK_NAME[libgstdvdlpcmdec.so] = "/usr/lib/aarch64-linux-gnu/gstreamer-1.0/libgstdvdlpcmdec.so"

QTI_SEPECIFIC_LINK[libgstdvdsub.so] = "/usr/lib/gstreamer-1.0/libgstdvdsub.so"
ALTERNATIVE_LINK_NAME[libgstdvdsub.so] = "/usr/lib/aarch64-linux-gnu/gstreamer-1.0/libgstdvdsub.so"

QTI_SEPECIFIC_LINK[libgstrealmedia.so] = "/usr/lib/gstreamer-1.0/libgstrealmedia.so"
ALTERNATIVE_LINK_NAME[libgstrealmedia.so] = "/usr/lib/aarch64-linux-gnu/gstreamer-1.0/libgstrealmedia.so"

QTI_SEPECIFIC_LINK[libgstxingmux.so] = "/usr/lib/gstreamer-1.0/libgstxingmux.so"
ALTERNATIVE_LINK_NAME[libgstxingmux.so] = "/usr/lib/aarch64-linux-gnu/gstreamer-1.0/libgstxingmux.so"
