package com.example.ceshi3.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class VPAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmetnmanager;  //创建FragmentManager
    private List<Fragment> listfragment; //创建一个List<Fragment>
    //定义构造带两个参数
    public VPAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.fragmetnmanager=fm;
        this.listfragment=list;
    }

    @Override
    public Fragment getItem(int arg0) {
        return listfragment.get(arg0); //返回第几个fragment
    }

    @Override
    public int getCount() {
        return listfragment.size(); //总共有多少个fragment
    }
}
