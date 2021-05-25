package com.example.ceshi3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.ceshi3.bean.JsonBean;
import com.example.ceshi3.bean.Zhucesqlhelp;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zhuce extends AppCompatActivity {
private TextView dizhi,dizhixianshi,zhuce_tv1,zhuce_tv2,zhuce_tv3,zhuce_tv4,zhuce_tv5;
private EditText zhuce_et1,zhuce_et2,zhuce_et3,zhuce_et4;
private Button zhuce_bt,zhuce_bt2;

//Zhucesqlhelp zhucesqlhelpers;

    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private static boolean isLoaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        dizhi=(TextView) findViewById(R.id.dizhi);
        dizhixianshi=(TextView) findViewById(R.id.zhuce_tv5);

        zhuce_tv1=(TextView) findViewById(R.id.zhuce_tv1);
        zhuce_tv2=(TextView) findViewById(R.id.zhuce_tv2);
        zhuce_tv3=(TextView) findViewById(R.id.zhuce_tv3);
        zhuce_tv4=(TextView) findViewById(R.id.zhuce_tv4);
        zhuce_tv5=(TextView) findViewById(R.id.zhuce_tv5);

        zhuce_et1=(EditText) findViewById(R.id.zhuce_et1);
        zhuce_et2=(EditText) findViewById(R.id.zhuce_et2);
        zhuce_et3=(EditText) findViewById(R.id.zhuce_et3);
        zhuce_et4=(EditText) findViewById(R.id.zhuce_et4);


        zhuce_bt=(Button) findViewById(R.id.zhuce_bt);
        zhuce_bt2=(Button) findViewById(R.id.zhuce_bt2);

        zhuce_tv1.setVisibility(View.INVISIBLE);
        zhuce_tv2.setVisibility(View.INVISIBLE);
        zhuce_tv3.setVisibility(View.INVISIBLE);
        zhuce_tv4.setVisibility(View.INVISIBLE);

        initJsonData();//地址选择
        shuruxianzhi();
        zhuce_bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("gerenxinxi",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                editor.commit();

//
            }
        });


        dizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerView();
            }
        });
        zhuce_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuce();
            }
        });
    }


    private void zhuce(){
//        SQLiteDatabase db;
//        ContentValues values;
        String a=zhuce_et1.getText().toString();
        String b=zhuce_et2.getText().toString();
        String c=zhuce_et3.getText().toString();
        String d=zhuce_et4.getText().toString();
        String e=zhuce_tv5.getText().toString();
        if (!a.equals("")&&!b.equals("")&&!c.equals("")&&!d.equals("")&&!e.equals("")) {

            SharedPreferences sp=getSharedPreferences("gerenxinxi",MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("yonghuming",a);
            editor.putString("mima",b);
            editor.putString("nicheng",d);
            editor.putString("dizhi",e);
            editor.putString("panduan","true");

            editor.commit();
            Toast.makeText(this,"已添加",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(zhuce.this, login.class));

        }
//        else if (b==null){
//                zhuce_tv2.setVisibility(View.VISIBLE);
//                zhuce_tv2.setText("没有输入");
//        }
//                if (c==null){
//                    zhuce_tv3.setVisibility(View.VISIBLE);
//                    zhuce_tv3.setText("没有输入");
//                }
//                if (d==null){
//                    zhuce_tv4.setVisibility(View.VISIBLE);
//                    zhuce_tv4.setText("没有输入");
//                }




    }


    private void shuruxianzhi() {
        zhuce_et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editable = zhuce_et1.getText().toString();
                String regEx = "[^a-zA-Z0-9]";  //只能输入字母或数字
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(editable);
                String str = m.replaceAll("").trim();    //删掉不是字母或数字的字符
                if (!editable.equals(str)) {
                    zhuce_et1.setText(str);  //设置EditText的字符
                    zhuce_et1.setSelection(str.length()); //因为删除了字符，要重写设置新的光标所在位置
                }
            }
        });

        zhuce_et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editable = zhuce_et2.getText().toString();
                String regEx = "[^a-zA-Z0-9]";  //只能输入字母或数字
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(editable);
                String str = m.replaceAll("").trim();    //删掉不是字母或数字的字符
                if (!editable.equals(str)) {
                    zhuce_et2.setText(str);  //设置EditText的字符
                    zhuce_et2.setSelection(str.length()); //因为删除了字符，要重写设置新的光标所在位置
                }
            }
        });

        zhuce_et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editable = zhuce_et3.getText().toString();
                String regEx = "[^a-zA-Z0-9]";  //只能输入字母或数字
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(editable);
                String str = m.replaceAll("").trim();    //删掉不是字母或数字的字符
                if (!editable.equals(str)) {
                    zhuce_et3.setText(str);  //设置EditText的字符
                    zhuce_et3.setSelection(str.length()); //因为删除了字符，要重写设置新的光标所在位置
                }
            }
        });
    }

    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;
                Toast.makeText(zhuce.this, tx, Toast.LENGTH_SHORT).show();
                dizhixianshi.setText(tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(cityList);
            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

//        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }
    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }






}
