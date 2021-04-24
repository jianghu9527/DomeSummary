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
        mdata.add("进度条");
        mdata.add("Tinker");
        mdata.add("room");
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
            case "进度条":
                getonitem(ProgressActivity.class);
                break;
            case "Tinker":
                getonitem(TinkerActivity.class);
                break;
            case "room":
                getonitem(RoomActivity.class);
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
