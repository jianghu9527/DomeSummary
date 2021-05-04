package com.cd.ruileda.cc.view.widget;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hcDarren on 2017/7/23.
 * 筛选菜单的 Adapter
 */

public abstract class BaseMenuAdapter {
    // 获取总共有多少条
    public abstract int getCount();
    // 获取当前的TabView
    public abstract View getTabView(int position, ViewGroup parent);
    // 获取当前的菜单内容
    public abstract View getMenuView(int position,ViewGroup parent);

    /**
     * 菜单打开
     * @param tabView
     */
    public void menuOpen(View tabView) {

    }

    /**
     * 菜单关闭
     * @param tabView
     */
    public void menuClose(View tabView) {

    }
}
