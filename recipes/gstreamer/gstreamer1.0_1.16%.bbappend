FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0/1.16.3:"

SRC_URI_append += " \
       file://0001-gst-gstpad.c-avoid-more-warning-logs-in-gstpad.patch \
"

