package com.example.ceshi3.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ceshi3.R;

public class ViewHolder {
    ImageView pictureImg;//图片
    TextView contentTv;//内容
    Button bt;//内容

    /**
     * 构造器
     *
     * @param view 视图组件（ListView的子项视图）
     */
    ViewHolder(View view) {
        pictureImg = (ImageView) view.findViewById(R.id.im);
        contentTv = (TextView) view.findViewById(R.id.tt);
        bt = (Button) view.findViewById(R.id.bt);

    }
}
