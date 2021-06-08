package com.cd.ruileda.cc.view.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cd.ruileda.cc.view.R;
import com.cd.ruileda.cc.view.adapter.FruitAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String>  mdata=new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
              setContentView(R.layout.mainlayout);

        recyclerView=findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(getData ());
        recyclerView.setAdapter(adapter);
        adapter.onClickItems(new FruitAdapter.onItem() {
            @Override
            public void onckitem(int postion) {
                SwitchItem(getData ().get(postion));
            }
        });

    }

    public  List<String>  getData (){
        mdata.add("QQ计步器");
        mdata.add("字体变色效果分析");
        mdata.add("ProgreeBar进度条");
        mdata.add("Tinker");
        mdata.add("room");
        mdata.add("debug");
        mdata.add("状态栏颜色");
        mdata.add("评分控件RatingBar");
        mdata.add("常见多条目菜单筛选");
        mdata.add("字母索引列表");
        mdata.add("贝塞尔曲线数学课");

        mdata.add("沉浸式状态栏");
        mdata.add("自定义Behavior");


        return mdata;
    }


    public void SwitchItem(String  name){

        switch (name){
            case "QQ计步器":
                getonitem(QQActivity.class);
                break;
            case "字体变色效果分析":
                getonitem(UtilsViewPageActivity.class);
                break;
            case "ProgreeBar进度条":
                getonitem(ProgressActivity.class);
                break;
            case "Tinker":
                getonitem(TinkerActivity.class);
                break;
            case "room":
                getonitem(RoomActivity.class);
                break;
            case "debug":
                getonitem(DebugActivity.class);
                break;
            case "状态栏颜色":
                getonitem(StatusColorActivity.class);
                break;
            case "评分控件RatingBar":
                getonitem(RatingBarActivity.class);
                break;
            case "常见多条目菜单筛选":
                getonitem(ListDataActivity.class);
                break;
            case "字母索引列表":
                getonitem(LetterSideBarActivity.class);
                break;
            case "贝塞尔曲线数学课":
                getonitem(darrenErActivity.class);
                break;
            case "沉浸式状态栏":
                getonitem(DesignActivity.class);
                break;
            case "自定义Behavior":
                getonitem(BehaviorActivity.class);
                break;

        }

    }

    /**
     *
     */
    public void getonitem(Class intent){

        Intent  mintent=new Intent();
        mintent.setClass(this,intent);
           startActivity(mintent);
    }


}
