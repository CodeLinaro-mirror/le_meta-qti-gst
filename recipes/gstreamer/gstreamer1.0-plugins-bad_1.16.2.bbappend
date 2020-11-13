DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

CPPFLAGS += " ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '-D__GBM__', '', d)} "

PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OECONF += "${@bb.utils.contains('COMBINED_FEATURES', 'fbdev', '--enable-fbdev-compositor', '', d)}"
EXTRA_OECONF += "${@bb.utils.contains('COMBINED_FEATURES', 'drm', '--enable-drm-compositor', '', d)}"
