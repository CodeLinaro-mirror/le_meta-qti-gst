FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append = " \
       file://0002-bufferpool-unblock-acquire-thread-discard-buffer.patch \
       file://0003-gstreamer-Don-t-return-more-data-in-baseparse.patch \
"