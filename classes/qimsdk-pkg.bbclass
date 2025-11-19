# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

SSTATETASKS += "do_generate_qim_sdk "
SSTATE_OUT_DIR = "${DEPLOY_DIR}/qimsdk_artifacts/"
SSTATE_IN_DIR = "${TOPDIR}/${SDK_PN}"
TMP_SSTATE_IN_DIR = "${TOPDIR}/${SDK_PN}_tmp"

python __anonymous () {
    package_type = d.getVar("IMAGE_PKGTYPE", True)
    if package_type == "ipk":
        bb.build.addtask('do_generate_qim_sdk', 'do_package_write_ipk', 'do_packagedata' , d)
    if package_type == "deb":
        bb.build.addtask('do_generate_qim_sdk', 'do_package_write_deb', 'do_packagedata' , d)
}

addtask do_generate_qim_sdk_setscene
do_generate_qim_sdk[sstate-inputdirs] = "${SSTATE_IN_DIR}"
do_generate_qim_sdk[sstate-outputdirs] = "${SSTATE_OUT_DIR}"
do_generate_qim_sdk[dirs] = "${SSTATE_IN_DIR} ${SSTATE_OUT_DIR}"
do_generate_qim_sdk[cleandirs] = "${SSTATE_IN_DIR} ${SSTATE_OUT_DIR}"
do_generate_qim_sdk[stamp-extra-info] = "${MACHINE_ARCH}"
do_generate_qim_sdk[depends] = " \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstd:do_package_write_ipk", "gstd:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0:do_package_write_ipk", "gstreamer1.0:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-base:do_package_write_ipk", "gstreamer1.0-plugins-base:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-good:do_package_write_ipk", "gstreamer1.0-plugins-good:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-bad:do_package_write_ipk", "gstreamer1.0-plugins-bad:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-rtsp-server:do_package_write_ipk", "gstreamer1.0-rtsp-server:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-base:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-base:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-tools:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-tools:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-batch:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-batch:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-metamux:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-metamux:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-samplemux:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-samplemux:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-mldemux:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-mldemux:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-mlmeta:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-mlmeta:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-mlvconverter:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-mlvconverter:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-mlvclassification:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-mlvclassification:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-mlvdetection:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-mlvdetection:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-mlvpose:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-mlvpose:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-mlvsegmentation:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-mlvsegmentation:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-overlay:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-overlay:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains("DISTRO_FEATURES", "qti-qmmf", "gstreamer1.0-plugins-qti-oss-qmmfsrc:do_package_write_ipk", "", d), bb.utils.contains("DISTRO_FEATURES", "qti-qmmf", "gstreamer1.0-plugins-qti-oss-qmmfsrc:do_package_write_deb", "", d), d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-socket:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-socket:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-vcomposer:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-vcomposer:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-samplestitching:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-samplestitching:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-vsplit:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-vsplit:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-vtransform:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-vtransform:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-codec2:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-codec2:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-examples:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-examples:do_package_write_deb", d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains("MACHINE_FEATURES", "qti-aic", "gstreamer1.0-plugins-qti-oss-mlaic:do_package_write_ipk", "", d), bb.utils.contains("MACHINE_FEATURES", "qti-aic", "gstreamer1.0-plugins-qti-oss-mlaic:do_package_write_deb", "", d), d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains("COMBINED_FEATURES", "qti-uvc", "gstreamer1.0-plugins-qti-oss-umd-daemon:do_package_write_ipk", "", d), bb.utils.contains("COMBINED_FEATURES", "qti-uvc", "gstreamer1.0-plugins-qti-oss-umd-daemon:do_package_write_deb", "", d), d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains_any("COMBINED_FEATURES", "qti-video qti-audio", "gstreamer1.0-libav:do_package_write_ipk", "",d), bb.utils.contains_any("COMBINED_FEATURES", "qti-video qti-audio", "gstreamer1.0-libav:do_package_write_deb", "",d), d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains("DISTRO_FEATURES", "qti-dfs", "gstreamer1.0-plugins-qti-oss-dfs:do_package_write_ipk", "", d), bb.utils.contains("DISTRO_FEATURES", "qti-dfs", "gstreamer1.0-plugins-qti-oss-dfs:do_package_write_deb", "", d), d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains_any("MACHINE_FEATURES", "qti-cvp qti-eva", "gstreamer1.0-plugins-qti-oss-cv-imgpyramid:do_package_write_ipk", "", d), bb.utils.contains_any("MACHINE_FEATURES", "qti-cvp qti-eva", "gstreamer1.0-plugins-qti-oss-cv-imgpyramid:do_package_write_deb", "", d), d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains_any("MACHINE_FEATURES", "qti-cvp qti-eva", "gstreamer1.0-plugins-qti-oss-cv-optclflow:do_package_write_ipk", "", d), bb.utils.contains_any("MACHINE_FEATURES", "qti-cvp qti-eva", "gstreamer1.0-plugins-qti-oss-cv-optclflow:do_package_write_deb", "", d), d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains("BASEMACHINE", "kalama", "gstreamer1.0-plugins-qti-oss-jpegenc:do_package_write_ipk", "", d), bb.utils.contains("BASEMACHINE", "kalama", "gstreamer1.0-plugins-qti-oss-jpegenc:do_package_write_deb", "", d), d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains("BASEMACHINE", "qrb5165", "gstreamer1.0-plugins-qti-oss-jpegenc:do_package_write_ipk", "", d), bb.utils.contains("BASEMACHINE", "kalama", "gstreamer1.0-plugins-qti-oss-jpegenc:do_package_write_deb", "", d), d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains("BASEMACHINE", "qcs6490", "gstreamer1.0-plugins-qti-oss-jpegenc:do_package_write_ipk", "", d), bb.utils.contains("BASEMACHINE", "kalama", "gstreamer1.0-plugins-qti-oss-jpegenc:do_package_write_deb", "", d), d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains("BASEMACHINE", "qrb5165", "gstreamer1.0-plugins-qti-oss-drmdecryptor:do_package_write_ipk", "", d), bb.utils.contains("BASEMACHINE", "qrb5165", "gstreamer1.0-plugins-qti-oss-drmdecryptor:do_package_write_deb", "", d), d)} \
    ${@bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", bb.utils.contains("BASEMACHINE", "qcs6490", bb.utils.contains_any("COMBINED_FEATURES", "qti-video qti-audio", "gstreamer1.0-omx:do_package_write_ipk", "", d), "", d), bb.utils.contains("BASEMACHINE", "qcs6490", bb.utils.contains_any("COMBINED_FEATURES", "qti-video qti-audio", "gstreamer1.0-omx:do_package_write_deb", "", d), "", d), d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "tensorflow-lite", bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-mltflite:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-mltflite:do_package_write_deb", d), "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "qti-snpe", bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-mlsnpe:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-mlsnpe:do_package_write_deb", d), "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "qti-qnn", bb.utils.contains(d.getVar("IMAGE_PKGTYPE", True), "ipk", "gstreamer1.0-plugins-qti-oss-mlqnn:do_package_write_ipk", "gstreamer1.0-plugins-qti-oss-mlqnn:do_package_write_deb", d), "", d)} \
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
    tar -zcf ${SSTATE_IN_DIR}/${SDK_PN}_${PV}.tar.gz ./${SDK_PN}/*
    mkdir -p ./${SDK_PN}/dev/
    for f in `find . -type f \( -name "*-dev_*" \)`
    do
        mv $f ./${SDK_PN}/dev/
    done
    tar -zcf ${SSTATE_IN_DIR}/${SDK_PN}-dev_${PV}.tar.gz ./${SDK_PN}/dev/*
    rm -rf ./${SDK_PN}/dev
    mkdir -p ./${SDK_PN}/dbg/
    for f in `find . -type f \( -name "*-dbg_*" \)`
    do
        mv $f ./${SDK_PN}/dbg/
    done
    tar -zcf ${SSTATE_IN_DIR}/${SDK_PN}-dbg_${PV}.tar.gz ./${SDK_PN}/dbg/*
    rm -rf ./${SDK_PN}/dbg
    for f in `find . -type f \( -name "*-doc_*" -o -name "*-staticdev_*" \)`
    do
        rm -rf $f
    done
    for f in `find . -type f \( -name "*-locale-*" -o -name "*-src_*" \)`
    do
        rm -rf $f
    done

    tar -zcf ${SSTATE_IN_DIR}/${SDK_PN}-rel_${PV}.tar.gz ./${SDK_PN}/*
    rm -rf ${TMP_SSTATE_IN_DIR}
}

def get_pkgs_list(d):
    import os
    pkgtype = d.getVar("IMAGE_PKGTYPE", True)
    deploydir = d.getVar("DEPLOY_DIR", True)
    timestampfile = os.path.join(deploydir, "qimsdk-timestamp")
    pkgslist = []
    dep_list = []
    for _, pkgdirs, _ in os.walk(os.path.join(deploydir, pkgtype)):
        for pkgdir in pkgdirs:
            for f in os.listdir(os.path.join(deploydir, pkgtype, pkgdir)):
                if "gstreamer" in os.path.basename(f) or "libgst" in os.path.basename(f) :
                    pkgslist.append(os.path.join(deploydir, pkgtype, pkgdir, f))
                else:
                    for dep in dep_list:
                        if dep in os.path.basename(f):
                            pkgslist.append(os.path.join(deploydir, pkgtype, pkgdir, f))
                            dep_list.remove(dep)

    return " \\\n ".join(pkgslist)

python do_generate_qim_sdk_setscene() {
    sstate_setscene(d)
}

RM_WORK_EXCLUDE += "${PN}"
