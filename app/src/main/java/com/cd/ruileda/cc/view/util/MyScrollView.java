package com.cd.ruileda.cc.view.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by hcDarren on 2017/7/10.
 */

public class MyScrollView extends ScrollView{
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mListener!=null){
            mListener.onScroll(l,t,oldl,oldt);
        }
    }

    public interface ScrollChangeListener{
        public void onScroll(int l, int t, int oldl, int oldt);
    }

    private ScrollChangeListener mListener;
    public void setOnScrollChangeListener(ScrollChangeListener listener){
        mListener = listener;
    }
}
