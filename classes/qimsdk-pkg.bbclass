# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

SSTATETASKS += "do_generate_qim_sdk "
SSTATE_OUT_DIR = "${DEPLOY_DIR}/qimsdk_artifacts/"
SSTATE_IN_DIR = "${TOPDIR}/${SDK_PN}"
TMP_SSTATE_IN_DIR = "${TOPDIR}/${SDK_PN}_tmp"
SAMPLES_PATH ?= "NULL"
TOOLCHAIN_PATH ?= "NULL"
TOOLS_PATH ?= "NULL"
README_PATH ?= "NULL"
SETUP_PATH ?= "NULL"

python __anonymous () {
    package_type = d.getVar("IMAGE_PKGTYPE", True)
    if package_type == "ipk":
        bb.build.addtask('do_generate_qim_sdk', 'do_package_write_ipk', 'do_packagedata' , d)
}

addtask do_generate_qim_sdk_setscene
do_generate_qim_sdk[postfuncs] += "organize_qim_sdk_files"
do_generate_qim_sdk[sstate-inputdirs] = "${SSTATE_IN_DIR}"
do_generate_qim_sdk[sstate-outputdirs] = "${SSTATE_OUT_DIR}"
do_generate_qim_sdk[dirs] = "${SSTATE_IN_DIR} ${SSTATE_OUT_DIR} ${TMP_SSTATE_IN_DIR}"
do_generate_qim_sdk[cleandirs] = "${SSTATE_IN_DIR} ${SSTATE_OUT_DIR} ${TMP_SSTATE_IN_DIR}"
do_generate_qim_sdk[stamp-extra-info] = "${MACHINE_ARCH}"
do_generate_qim_sdk[depends] = " \
      gstreamer1.0:do_packagedata \
      gstreamer1.0-plugins-base:do_packagedata \
      gstreamer1.0-plugins-good:do_packagedata \
      gstreamer1.0-plugins-bad:do_packagedata \
      gstreamer1.0-rtsp-server:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-base:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-tools:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-batch:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-metamux:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-mldemux:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-mlmeta:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-mlvconverter:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-mlvclassification:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-mlvdetection:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-mlvpose:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-mlvsegmentation:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-overlay:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-qmmfsrc:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-socket:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-vcomposer:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-vsplit:do_packagedata \
      gstreamer1.0-plugins-qcom-oss-vtransform:do_packagedata \
    "


# Add a task to generate QIM sdk
do_generate_qim_sdk () {
    # generate QIM SDK package
    if [ ! -d ${TMP_SSTATE_IN_DIR}/${SDK_PN} ]; then
        mkdir -p ${TMP_SSTATE_IN_DIR}/${SDK_PN}/
    fi
    cp -r ${WORKDIR}/*install.sh ${TMP_SSTATE_IN_DIR}/${SDK_PN}/
    PKG_LISTS="${@get_pkgs_list(d)}"
    for pkg in "${PKG_LISTS}"
    do
        cp ${pkg} ${TMP_SSTATE_IN_DIR}/${SDK_PN}/
    done
    cd ${TMP_SSTATE_IN_DIR}
    tar -zcf ${SSTATE_IN_DIR}/${SDK_PN}.tar.gz ./${SDK_PN}/*
}

# Add a task to copy sample code/toolchain/setup scripts,
# and orgnanize as finial sdk artifact
organize_qim_sdk_files () {
    # orgnanize runtime packages
    if ls ${SSTATE_IN_DIR}/${SDK_PN}* >/dev/null 2>&1; then
        install -d ${SSTATE_IN_DIR}/${SDK_PN}/runtime
        mv ${SSTATE_IN_DIR}/${SDK_PN}*.tar.gz ${SSTATE_IN_DIR}/${SDK_PN}/runtime/
    else
        bbfatal "No ${SDK_PN} packages generated, will miss base function! Please check it!"
    fi

    # orgnanize README docs
    if ls ${README_PATH} >/dev/null 2>&1; then
        cp -r ${README_PATH} ${SSTATE_IN_DIR}/${SDK_PN}/
    else
        bbwarn "No README docs find in ${README_PATH}, Please Note it!"
    fi

    # organize all files as finial sdk
    cd ${SSTATE_IN_DIR}
    tar -zcf ${SSTATE_IN_DIR}/${SDK_PN}_${PV}.tar.gz ./${SDK_PN}/*
    rm -r ${SSTATE_IN_DIR}/${SDK_PN}
}

def get_pkgs_list(d):
  import os
  pkgtype = d.getVar("IMAGE_PKGTYPE", True)
  deploydir = d.getVar("DEPLOY_DIR", True)
  timestampfile = os.path.join(deploydir, "qimsdk-timestamp")
  pkgslist = []
  for _, pkgdirs, _ in os.walk(os.path.join(deploydir, pkgtype)):
    for pkgdir in pkgdirs:
      for f in os.listdir(os.path.join(deploydir, pkgtype, pkgdir)):
        if "gstreamer" in os.path.basename(f) or "libgst" in os.path.basename(f) :
          #bb.warn(os.path.basename(f))
          pkgslist.append(os.path.join(deploydir, pkgtype, pkgdir, f))
  return " \\\n ".join(pkgslist)

python do_generate_qim_sdk_setscene() {
    sstate_setscene(d)
}
