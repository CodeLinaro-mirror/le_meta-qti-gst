do_write_config:append:class-target() {
    # Write out a qemu wrapper that will be used as exe_wrapper so that meson
    # can run target helper binaries through that.
    qemu_binary="${@qemu_wrapper_cmdline(d, '$STAGING_DIR_HOST', ['$STAGING_DIR_HOST/${libdir}','$STAGING_DIR_HOST/${base_libdir}','$STAGING_DIR_HOST/usr/aarch64-linux-gnu/lib'])}"
    cat > ${WORKDIR}/meson-qemuwrapper << EOF
#!/bin/sh
# Use a modules directory which doesn't exist so we don't load random things
# which may then get deleted (or their dependencies) and potentially segfault
export GIO_MODULE_DIR=${STAGING_LIBDIR}/gio/modules-dummy

# meson sets this wrongly (only to libs in build-dir), qemu_wrapper_cmdline() and GIR_EXTRA_LIBS_PATH take care of it properly
unset LD_LIBRARY_PATH

$qemu_binary "\$@"
EOF
    chmod +x ${WORKDIR}/meson-qemuwrapper
}

DEPENDS += "tiff shared-mime-info"