package com.example.ceshi3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.RadioGroup;

import com.example.ceshi3.fragment.Fragment_zhuye1;
import com.example.ceshi3.fragment.Fragment_zhuye2;
import com.example.ceshi3.fragment.Fragment_zhuye3;
import com.example.ceshi3.fragment.Fragment_zhuye4;

public class MainActivity extends AppCompatActivity {
    private RadioGroup mTabRadioGroup;
    private SparseArray<Fragment> mFragmentSparseArray;
    String yonghuming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        /*
        根据intent的传值来判断加载那个fragment页面
         */
        int id = getIntent().getIntExtra("id", 3);
        if (id == 3) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,new Fragment_zhuye3())
                    .addToBackStack(null)
                    .commit();
        }

    }


    private void initView() {
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        mFragmentSparseArray = new SparseArray<>();
        mFragmentSparseArray.append(R.id.today_tab, Fragment_zhuye1.newInstance("首页"));
        mFragmentSparseArray.append(R.id.record_tab, Fragment_zhuye2.newInstance("分类"));
        mFragmentSparseArray.append(R.id.contact_tab, Fragment_zhuye3.newInstance("购物车"));
        mFragmentSparseArray.append(R.id.settings_tab, Fragment_zhuye4.newInstance("我的"));
        mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 具体的fragment切换逻辑可以根据应用调整，例如使用show()/hide()
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        mFragmentSparseArray.get(checkedId)).commit();
            }
        });
        // 默认显示第一个
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                mFragmentSparseArray.get(R.id.today_tab)).commit();
//        findViewById(R.id.sign_iv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, shangpin.class));
//            }
//        });
    }











}
