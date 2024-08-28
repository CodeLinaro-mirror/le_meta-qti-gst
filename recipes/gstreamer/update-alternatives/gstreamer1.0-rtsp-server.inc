inherit update-alternatives-ubuntu_v2

ALTERNATIVE_PRIORITY:gstreamer1.0-rtsp-server = "100"

ALTERNATIVE:gstreamer1.0-rtsp-server = "GstRtspServer-1.0.typelib libgstrtspclientsink.so libgstrtspserver-1.0.so.0"

QTI_SEPECIFIC_LINK[GstRtspServer-1.0.typelib] = "/usr/lib/girepository-1.0/GstRtspServer-1.0.typelib"
ALTERNATIVE_LINK_NAME[GstRtspServer-1.0.typelib] = "/usr/lib/aarch64-linux-gnu/girepository-1.0/GstRtspServer-1.0.typelib"

QTI_SEPECIFIC_LINK[libgstrtspclientsink.so] = "/usr/lib/gstreamer-1.0/libgstrtspclientsink.so"
ALTERNATIVE_LINK_NAME[libgstrtspclientsink.so] = "/usr/lib/aarch64-linux-gnu/gstreamer-1.0/libgstrtspclientsink.so"

QTI_SEPECIFIC_LINK[libgstrtspserver-1.0.so.0] = "/usr/lib/libgstrtspserver-1.0.so.0"
ALTERNATIVE_LINK_NAME[libgstrtspserver-1.0.so.0] = "/usr/lib/aarch64-linux-gnu/libgstrtspserver-1.0.so.0"
