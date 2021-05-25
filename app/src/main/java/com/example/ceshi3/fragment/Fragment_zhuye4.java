package com.example.ceshi3.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ceshi3.R;
import com.example.ceshi3.login;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_zhuye4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_zhuye4 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    private TextView zhuye4_yonghuming;
private LinearLayout zhuye4_l1;
private  TextView zhuye4_yonghuming;
private  TextView zhuye4_jilu;


    public static Fragment_zhuye4 newInstance(String param1 ) {
        Fragment_zhuye4 fragment = new Fragment_zhuye4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zhuye4, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        zhuye4_l1=(LinearLayout)getView().findViewById(R.id.zhuye4_l1);
        zhuye4_yonghuming=(TextView)getView().findViewById(R.id.zhuye4_yonghuming);
        zhuye4_jilu=(TextView)getView().findViewById(R.id.zhuye4_jilu);

        SharedPreferences sp=getContext().getSharedPreferences("gerenxinxi",MODE_PRIVATE);
        String yonghu=sp.getString("yonghuming","");
        String pan=sp.getString("panduan","");

        if (pan.equals("true")){
            zhuye4_yonghuming.setText(yonghu+"");
        }else {
        }

        zhuye4_l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), login.class);
                startActivity(intent);

            }
        });


    }


}
