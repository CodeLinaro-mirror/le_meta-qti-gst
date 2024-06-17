inherit update-alternatives-ubuntu_v2

ALTERNATIVE_PRIORITY:gstreamer1.0 = "100"

ALTERNATIVE:gstreamer1.0 = "gst-inspect-1.0 gst-launch-1.0 gst-stats-1.0 gst-typefind-1.0 libgstcoreelements.so libgstbase-1.0.so.0 libgstcheck-1.0.so.0 libgstcontroller-1.0.so.0 libgstnet-1.0.so.0 libgstreamer-1.0.so.0 gst-plugin-scanner gst-ptp-helper Gst-1.0.typelib GstBase-1.0.typelib GstCheck-1.0.typelib GstController-1.0.typelib GstNet-1.0.typelib"

ALTERNATIVE_LINK_NAME[gst-inspect-1.0] = "/usr/bin/gst-inspect-1.0"

ALTERNATIVE_LINK_NAME[gst-launch-1.0] = "/usr/bin/gst-launch-1.0"

ALTERNATIVE_LINK_NAME[gst-stats-1.0] = "/usr/bin/gst-stats-1.0"

ALTERNATIVE_LINK_NAME[gst-typefind-1.0] = "/usr/bin/gst-typefind-1.0"

QTI_SEPECIFIC_LINK[libgstcoreelements.so] = "/usr/lib/gstreamer-1.0/libgstcoreelements.so"
ALTERNATIVE_LINK_NAME[libgstcoreelements.so] = "/usr/lib/aarch64-linux-gnu/gstreamer-1.0/libgstcoreelements.so"

QTI_SEPECIFIC_LINK[libgstbase-1.0.so.0] = "/usr/lib/libgstbase-1.0.so.0"
ALTERNATIVE_LINK_NAME[libgstbase-1.0.so.0] = "/usr/lib/aarch64-linux-gnu/libgstbase-1.0.so.0"

QTI_SEPECIFIC_LINK[libgstcheck-1.0.so.0] = "/usr/lib/libgstcheck-1.0.so.0"
ALTERNATIVE_LINK_NAME[libgstcheck-1.0.so.0] = "/usr/lib/aarch64-linux-gnu/libgstcheck-1.0.so.0"

QTI_SEPECIFIC_LINK[libgstcontroller-1.0.so.0] = "/usr/lib/libgstcontroller-1.0.so.0"
ALTERNATIVE_LINK_NAME[libgstcontroller-1.0.so.0] = "/usr/lib/aarch64-linux-gnu/libgstcontroller-1.0.so.0"

QTI_SEPECIFIC_LINK[libgstnet-1.0.so.0] = "/usr/lib/libgstnet-1.0.so.0"
ALTERNATIVE_LINK_NAME[libgstnet-1.0.so.0] = "/usr/lib/aarch64-linux-gnu/libgstnet-1.0.so.0"

QTI_SEPECIFIC_LINK[libgstreamer-1.0.so.0] = "/usr/lib/libgstreamer-1.0.so.0"
ALTERNATIVE_LINK_NAME[libgstreamer-1.0.so.0] = "/usr/lib/aarch64-linux-gnu/libgstreamer-1.0.so.0"

QTI_SEPECIFIC_LINK[gst-plugin-scanner] = "/usr/libexec/gstreamer-1.0/gst-plugin-scanner"
ALTERNATIVE_LINK_NAME[gst-plugin-scanner] = "/usr/lib/aarch64-linux-gnu/gstreamer1.0/gstreamer-1.0/gst-plugin-scanner"

QTI_SEPECIFIC_LINK[gst-ptp-helper] = "/usr/libexec/gstreamer-1.0/gst-ptp-helper"
ALTERNATIVE_LINK_NAME[gst-ptp-helper] = "/usr/lib/aarch64-linux-gnu/gstreamer1.0/gstreamer-1.0/gst-ptp-helper"

QTI_SEPECIFIC_LINK[Gst-1.0.typelib] = "/usr/lib/girepository-1.0/Gst-1.0.typelib"
ALTERNATIVE_LINK_NAME[Gst-1.0.typelib] = "/usr/lib/aarch64-linux-gnu/girepository-1.0/Gst-1.0.typelib"

QTI_SEPECIFIC_LINK[GstBase-1.0.typelib] = "/usr/lib/girepository-1.0/GstBase-1.0.typelib"
ALTERNATIVE_LINK_NAME[GstBase-1.0.typelib] = "/usr/lib/aarch64-linux-gnu/girepository-1.0/GstBase-1.0.typelib"

QTI_SEPECIFIC_LINK[GstCheck-1.0.typelib] = "/usr/lib/girepository-1.0/GstCheck-1.0.typelib"
ALTERNATIVE_LINK_NAME[GstCheck-1.0.typelib] = "/usr/lib/aarch64-linux-gnu/girepository-1.0/GstCheck-1.0.typelib"

QTI_SEPECIFIC_LINK[GstController-1.0.typelib] = "/usr/lib/girepository-1.0/GstController-1.0.typelib"
ALTERNATIVE_LINK_NAME[GstController-1.0.typelib] = "/usr/lib/aarch64-linux-gnu/girepository-1.0/GstController-1.0.typelib"

QTI_SEPECIFIC_LINK[GstNet-1.0.typelib] = "/usr/lib/girepository-1.0/GstNet-1.0.typelib"
ALTERNATIVE_LINK_NAME[GstNet-1.0.typelib] = "/usr/lib/aarch64-linux-gnu/girepository-1.0/GstNet-1.0.typelib"
