package com.cd.ruileda.cc.day01.common;

import android.os.Environment;

import java.io.File;

public class CommonPath {

    public static final String ROOT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "1_hospital_face";
    public static final String TABLE_DIR = ROOT_DIR + File.separator + "table";



}
