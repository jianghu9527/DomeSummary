package com.cd.ruileda.cc.view.ui;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import com.cd.ruileda.cc.view.R;
import com.cd.ruileda.cc.view.common.CommonPath;
import com.cd.ruileda.cc.view.db.TStudent;
import com.cd.ruileda.cc.view.db.StudentDatabase;
import com.cd.ruileda.cc.view.util.FileSizeUtil;
import com.cd.ruileda.cc.view.util.SetRandom;
import com.cd.ruileda.cc.view.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener {
    TextView  tvshowinfor;
    Button add,detele,updata,check;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
             setContentView(R.layout.layoutroom);

        tvshowinfor=  findViewById(R.id.textViewshow);
        add=  findViewById(R.id.buttonadd); add.setOnClickListener(this);
        detele=  findViewById(R.id.button2detele);    detele.setOnClickListener(this);
        updata=  findViewById(R.id.buttonupdata);    updata.setOnClickListener(this);
        check=  findViewById(R.id.buttoncheck);    check.setOnClickListener(this);

          FileSizeUtil .FileCreatePath(CommonPath.TABLE_DIR);

        verifyStoragePermissions(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonadd:
                ToastUtils.showShort(this,"buttonadd");
                insert();
                break;
            case R.id.button2detele:
                ToastUtils.showShort(this,"button2detele");
                delete();
                break;
            case R.id.buttonupdata:
                ToastUtils.showShort(this,"buttonupdata");
                Updata();
                break;
            case R.id.buttoncheck:
                ToastUtils.showShort(this,"query");
                query();
                break;
        }
    }

    public void insert(){
        final List<TStudent> insertStudents = new ArrayList<>();
        insertStudents.add(new TStudent("小明"+ SetRandom.getNumber(100),  SetRandom.getNumber(7), 2));
        insertStudents.add(new TStudent("小王"+ SetRandom.getNumber(100), SetRandom.getNumber(8), 1));
        new Thread(new Runnable() {
            @Override
            public void run() {
                StudentDatabase.getInstance(RoomActivity.this).getStudentDao().insertStudents(insertStudents);

            }
        }).start();
    };


    public void Updata(){

        final TStudent.UpdateCode uploadCode = new TStudent.UpdateCode(1, "100001");
        new Thread(new Runnable() {
            @Override
            public void run() {
//                TestDatabase.getInstance(RoomActivity.this).getStudentDao().updateCode(uploadCode);
            }
        }).start();

    }

    public void delete(){
        final TStudent.DeleteByClassGrade delete = new TStudent.DeleteByClassGrade(1,1);
        new Thread(new Runnable() {
            @Override
            public void run() {
//                TestDatabase.getInstance(RoomActivity.this).getStudentDao().deleteStudents(delete);
            }
        }).start();

    }

    public void query(){
        StudentDatabase.getInstance(this).getStudentDao().getStudentsByGender(1)
                .observe(this, new Observer<List<TStudent>>() {
                    @Override
                    public void onChanged(List<TStudent> students) {
                        // todo 查询到结果后处理,每当数据库数据有变更时此处会被调用，所以此处可能会被多次调用
             StringBuffer  str=new StringBuffer();

                        for (TStudent  data:students){
                            Log.d("-----------","------query-------"+data.toString());
                            str.append("\n"+data.toString());
                        }

                        tvshowinfor.setText("-----------"+str.toString());

                    }
                });
    }
    //先定义
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    //然后通过一个函数来申请
    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
