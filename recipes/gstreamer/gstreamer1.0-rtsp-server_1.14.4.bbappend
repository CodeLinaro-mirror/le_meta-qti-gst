DEPENDS += "gobject-introspection gobject-introspection-native qemu-native"

EXTRA_OECONF_append_sm8250 += " --enable-examples --enable-tests "
