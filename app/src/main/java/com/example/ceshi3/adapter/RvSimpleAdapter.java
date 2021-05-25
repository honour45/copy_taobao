package com.example.ceshi3.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ceshi3.R;
import com.example.ceshi3.bean.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class RvSimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mList;
    private List<String> images;
    private Context mContext;
    public RvSimpleAdapter(Context context, List<String> lists) {
        this.mContext=context;
        this.mList=lists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            View view= LayoutInflater.from(mContext).inflate(R.layout.item_simple_2,parent,false);
            return new BannerViewHolder(view);
        }else{
            View view=LayoutInflater.from(mContext).inflate(R.layout.item_simple_1,parent,false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder){
            images=new ArrayList<>();
            images.add("https://gsnapshot.alicdn.com/imgextra/i3/890482188/O1CN01jcRCg41S29C2Ltckp_!!890482188.jpg_430x430.jpg?time=1590941509000");
            images.add("https://gsnapshot.alicdn.com/imgextra/i3/890482188/O1CN01pZxbM61S29BgOf6Id_!!890482188.jpg_430x430.jpg?time=1590941509000");
            images.add("https://gsnapshot.alicdn.com/imgextra/i3/890482188/O1CN01pZxbM61S29BgOf6Id_!!890482188.jpg_430x430.jpg?time=1590941509000");
            ((BannerViewHolder) holder).mBanner.setImages(images).setImageLoader(new GlideImageLoader()).isAutoPlay(false).setBannerStyle(BannerConfig.NUM_INDICATOR).start();
        }
        if (holder instanceof MyViewHolder){
            if (position==mList.size()-1){
                ((MyViewHolder) holder).tv.setText("上滑查看图文详情");
                ((MyViewHolder) holder).tv.setBackgroundColor(Color.parseColor("#4D88FF"));
            }else {
                ((MyViewHolder) holder).tv.setText("NIKE鞋         ");

                ((MyViewHolder) holder).tv.setBackgroundColor(Color.parseColor("#eeeeee"));
            }

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);
        }
    }
    class BannerViewHolder extends RecyclerView.ViewHolder{
        Banner mBanner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            mBanner=itemView.findViewById(R.id.banner);
        }
    }

}