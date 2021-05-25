package com.example.ceshi3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;


import java.util.ArrayList;
import java.util.List;

import com.example.ceshi3.fragment.frag_yindaoye1;
import com.example.ceshi3.fragment.frag_yindaoye2;
import com.example.ceshi3.fragment.frag_yindaoye3;


public class second_yindaoye extends AppCompatActivity {
private ViewPager vp;
private List<Fragment>fragments=new ArrayList<>();
private RadioButton second_yindaoye_radiobutton1,second_yindaoye_radiobutton2,second_yindaoye_radiobutton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_yindaoye);

        vp=(ViewPager)findViewById(R.id.second_yindaoye_viewpager);
        second_yindaoye_radiobutton1=(RadioButton)findViewById(R.id.second_yindaoye_radiobutton1);
        second_yindaoye_radiobutton2=(RadioButton)findViewById(R.id.second_yindaoye_radiobutton2);
        second_yindaoye_radiobutton3=(RadioButton)findViewById(R.id.second_yindaoye_radiobutton3);

        second_yindaoye_radiobutton1.setChecked(true);



        fragments.add(new frag_yindaoye1());
        fragments.add(new frag_yindaoye2());
        fragments.add(new frag_yindaoye3());
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new adapter(getSupportFragmentManager()));
        listen();

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
//                Log.v("tag",position+"");
                switch (position) {
                    case 0:
                        second_yindaoye_radiobutton1.setChecked(true);
                        break;
                    case 1:
                        second_yindaoye_radiobutton2.setChecked(true);
                        break;
                    case 2:
                        second_yindaoye_radiobutton3.setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void listen(){
        second_yindaoye_radiobutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0);
            }
        });
        second_yindaoye_radiobutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1);
            }
        });
        second_yindaoye_radiobutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(2);
            }
        });


    }

        class adapter extends FragmentPagerAdapter{

            public adapter(FragmentManager fm) {
                super(fm);
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        }

}
