package com.cd.ruileda.cc.day01.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cd.ruileda.cc.day01.R;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {


    private  List<String> mFruitList;
    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView fruitName;
        public ViewHolder (View view)
        {
            super(view);
            fruitName = (TextView) view.findViewById(R.id.name);
        }

    }

    public  FruitAdapter (List<String> fruitList){
        mFruitList = fruitList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainlayout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        String fruit = mFruitList.get(position);
//        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit);
        holder.fruitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String  names= mFruitList.get(position);

               if (items!=null){
                   items.onckitem(position);
               }

            }
        });



    }

    @Override
    public int getItemCount(){
        return mFruitList.size();
    }

    onItem  items;
    public interface  onItem{
        void onckitem(int postion);

    }
    public void onClickItems( onItem  items){
        this.items=items;
    }

}
