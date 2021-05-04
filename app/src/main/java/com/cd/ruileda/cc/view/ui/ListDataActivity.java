package com.cd.ruileda.cc.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cd.ruileda.cc.view.R;
import com.cd.ruileda.cc.view.widget.ListDataScreenView;
import com.cd.ruileda.cc.view.widget.ListScreenMenuAdapter;

public class ListDataActivity  extends AppCompatActivity {

    private ListDataScreenView mListDataScreenView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listdata);
        mListDataScreenView = (ListDataScreenView) findViewById(R.id.list_data_screen_view);
        mListDataScreenView.setAdapter(new ListScreenMenuAdapter(this));

    }
    public void click(View view){
        Toast.makeText(this,"111",Toast.LENGTH_LONG).show();
    }

}
