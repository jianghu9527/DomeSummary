package com.cd.ruileda.cc.view.common;

import android.os.Environment;

import java.io.File;

public class CommonPath {

    public static final String ROOT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "1_table_farm";
    public static final String TABLE_DIR = ROOT_DIR + File.separator + "table";



}
