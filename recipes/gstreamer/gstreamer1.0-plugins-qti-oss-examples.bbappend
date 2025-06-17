# add dependency on ce-cdm to enable Widevine
DEPENDS:append:ar-sg1 = "cecdm"
# EXTRA_OECMAKE:append:ar-sg1 = " -b:BOOL=ON"
EXTRA_OECMAKE:append:ar-sg1 = " -DHAVE_CDM_H:BOOL=ON"

# since sa8295adp-2 copied quin-gvm-gen4-2, need add remove here
DEPENDS:remove:sa8295adp-2 = "ce-cdm"
EXTRA_OECMAKE:remove:sa8295adp-2 = "-DHAVE_CDM_H:BOOL=ON"
