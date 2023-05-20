# Disable v4l2 node probing by v4l2 plugins on init
EXTRA_OEMESON:remove = "-Dv4l2-probe=true"
EXTRA_OEMESON:append= " -Dv4l2-probe=false "
