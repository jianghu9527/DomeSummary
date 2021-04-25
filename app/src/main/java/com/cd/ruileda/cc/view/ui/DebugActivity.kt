package com.cd.ruileda.cc.view.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.cd.ruileda.cc.view.BuildConfig
import com.cd.ruileda.cc.view.R
import com.cd.ruileda.cc.view.util.LogUtil
import com.cd.ruileda.cc.view.util.SpUtil
import kotlinx.android.synthetic.main.layoutdebug.*

public class DebugActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layoutdebug)

        var  str=StringBuffer();
        var mallkey   =    SpUtil.mmkv?.allKeys();
        mallkey?.forEach {
            if (TextUtils.isEmpty(SpUtil.mmkv?.decodeString(it))){
                str.append(it+"="+SpUtil.mmkv?.decodeBool(it)+";     \n");
            }else{
                str.append(it+"="+SpUtil.mmkv?.decodeString(it)+";     \n");
            }
            LogUtil.i("-----------mmkv--key--"+it)
        }

        str.append("    \n");
        str.append("版本="+ BuildConfig.VERSION_NAME+";     \n");
        str.append("isdebug="+ BuildConfig.isdebug+";     \n");


//    var mmap=    SpUtil.mmkv?.all;
        debugtv.setText(""+str.toString())






    }


}