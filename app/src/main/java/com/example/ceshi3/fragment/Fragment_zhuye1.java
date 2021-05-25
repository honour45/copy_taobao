package com.example.ceshi3.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ceshi3.R;
import com.example.ceshi3.adapter.LoopViewAdapter;
import com.example.ceshi3.adapter.ViewHolder;
import com.example.ceshi3.pagerOnClickListener;
import com.example.ceshi3.shangpin;
import com.example.ceshi3.shangpinsql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment_zhuye1 extends Fragment implements View.OnClickListener   {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // 模拟listview中加载的数据
//    private static final String[] CONTENTS = { };
    private List<String> contentList;
    private ListView mListView;
    private String mParam1;
    private String mParam2;
    private ViewPager viewPager;  //轮播图模块
    private int[] mImg,image2;
    private int[] mImg_id;
    private String[] mDec,mDec2,mDec3;
    private ArrayList<ImageView> mImgList;
    private LinearLayout ll_dots_container;
    private TextView loop_dec;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;
     shangpinsql a3sql;
//    List<CityItem> cityList;
    private GridView gridView;
    private static final String TAG = "banner_log";
    private List<Map<String, Object>> list;
    private int images[] = {
            R.drawable.yifu,
            R.drawable.xiexue,
            R.drawable.jiadian,
            R.drawable.shuma,
            R.drawable.shipin,
            R.drawable.baihuo };
    private int images2[] = {
            R.drawable.xiexue,//1
            R.drawable.shuma,
            R.drawable.yifu,
            R.drawable.shipin,
            R.drawable.baihuo,
            R.drawable.xiexue,
            R.drawable.yifu,
            R.drawable.baihuo,
            R.drawable.yifu,
            R.drawable.xiexue,//10
            R.drawable.shuma,
            R.drawable.yifu,
            R.drawable.shipin,
            R.drawable.baihuo,
            R.drawable.xiexue,
            R.drawable.yifu,
            R.drawable.baihuo,
            R.drawable.yifu//18
    };
    public static Fragment_zhuye1 newInstance(String param1) {
        Fragment_zhuye1 fragment = new Fragment_zhuye1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zhuye1, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        mListView=(ListView)getView().findViewById(R.id.listview);
        viewPager = (ViewPager)getView().findViewById(R.id.loopviewpager);
        ll_dots_container = (LinearLayout)getView().findViewById(R.id.ll_dots_loop);
        loop_dec = (TextView)getView().findViewById(R.id.loop_dec);

//        gridView = (GridView) getView().findViewById(R.id.grid);
//        ItemImage = (ImageView) getView().findViewById(R.id.ItemImage);
        gridView = (GridView) getView().findViewById(R.id.gv);
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < images.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", images[i]);
            map.put("text", "商品种类"+i );

            list.add(map);
        }
//        setData();
//        setGridView();
        // 图片资源id数组
        mImg = new int[]{
                R.drawable.banner_1,
                R.drawable.banner_2,
                R.drawable.banner_3,
                R.drawable.banner_4,
                R.drawable.banner_5
        };
        // 文本描述
        mDec = new String[]{
                "banner1",
                "banner2",
                "banner3",
                "banner4",
                "banner5"
        };
        mDec2 = new String[]{
                "商品1", "商品2", "商品3", "商品4", "商品5",
                "商品6", "商品7", "商品8", "商品9",
                "商品10", "商品11", "商品12", "商品13", "商品14",
                "商品15", "商品16", "商品17", "商品18"
        };
        mDec3 = new String[]{
                "11", "22", "33", "33", "44",
                "55", "66", "77", "88",
                "99", "111", "222", "333", "444",
                "555", "666", "777", "888"
        };

        mImg_id = new int[]{
                R.id.pager_img1,
                R.id.pager_img2,
                R.id.pager_img3,
                R.id.pager_img4,
                R.id.pager_img5
        };
        // 初始化要展示的5个ImageView
        mImgList = new ArrayList<ImageView>();
        ImageView imageView;
        View dotView;
        LinearLayout.LayoutParams layoutParams;
        for(int i=0;i<mImg.length;i++){
            //初始化要显示的图片对象
            imageView = new ImageView(getContext());
            imageView.setBackgroundResource(mImg[i]);
            imageView.setId(mImg_id[i]);
            imageView.setOnClickListener(new pagerOnClickListener(getContext().getApplicationContext()));
            mImgList.add(imageView);
            //加引导点
            dotView = new View(getContext());
            dotView.setBackgroundResource(R.drawable.dot);
            layoutParams = new LinearLayout.LayoutParams(10,10);
            if(i!=0){
                layoutParams.leftMargin=10;
            }
            //设置默认所有都不可用
            dotView.setEnabled(false);
            ll_dots_container.addView(dotView,layoutParams);
        }
        ll_dots_container.getChildAt(0).setEnabled(true);
        loop_dec.setText(mDec[0]);
        previousSelectedPosition=0;
        //设置适配器
        viewPager.setAdapter(new LoopViewAdapter(mImgList));
        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;
        int m = (Integer.MAX_VALUE / 2) %mImgList.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m;
        viewPager.setCurrentItem(currentPosition);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                int newPosition = i % mImgList.size();
                loop_dec.setText(mDec[newPosition]);
                ll_dots_container.getChildAt(previousSelectedPosition).setEnabled(false);
                ll_dots_container.getChildAt(newPosition).setEnabled(true);
                previousSelectedPosition = newPosition;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // 开启轮询
        new Thread(){
            public void run(){
                isRunning = true;
                while(isRunning){
                    try{
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //下一条
                    if (getActivity() == null)
                        return;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                        }
                    });
                }
            }
        }.start();
        // 2.为数据源设置适配器
        MyAdapter adapter = new MyAdapter();
        // 3.将适配过后点数据显示在GridView 上
        gridView.setAdapter(adapter);
        // item点击事件处理
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //
                Intent intent = new Intent(getActivity(), shangpin.class);
                startActivity(intent);
            }
        });








        final MyAdapter2 myAdapter2=new MyAdapter2();
        mListView.setAdapter(myAdapter2);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), shangpin.class);
                startActivity(intent);
            }
        });
    }





    @Override
    public void onClick(View v) {

    }
//gard分类
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder1 holder = null;
            if (convertView == null) {
                // 第一次加载创建View，其余复用 View
                convertView = LayoutInflater.from(getActivity()).inflate(
                        R.layout.gard_list, null);
                holder = new ViewHolder1();
                holder.imageView = (ImageView) convertView
                        .findViewById(R.id.grid_img);
                holder.textView = (TextView) convertView
                        .findViewById(R.id.grid_tv);
                // 打标签
                convertView.setTag(holder);

            } else {
                // 从标签中获取数据
                holder = (ViewHolder1) convertView.getTag();
            }

            // 根据key值设置不同数据内容
            holder.imageView.setImageResource((Integer) list.get(position).get("image"));
            holder.textView.setText((String) list.get(position).get("text"));

            return convertView;
        }
    }

    class ViewHolder1 {
        ImageView imageView;
        TextView textView;

    }

//商品列表
class MyAdapter2 extends BaseAdapter{

    @Override
    public int getCount() {
        return mDec2.length;
    }

    @Override
    public Object getItem(int position) {
        return mDec2[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
        convertView=View.inflate(getActivity(),R.layout.list_item,null);
        TextView tt=(TextView)convertView.findViewById(R.id.tt);
        TextView tt2=(TextView)convertView.findViewById(R.id.tt2);
        ImageView im=(ImageView)convertView.findViewById(R.id.im);
        Button bt=(Button)convertView.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Log.v("position",mDec2[position]+"") ;
//                Toast.makeText(getActivity(), "点击的btID = " + position, Toast.LENGTH_SHORT).show();
//             tt.getText().textView.getText().toString();
                try {
                    a3sql =new shangpinsql(getActivity());
                    SQLiteDatabase db=a3sql.getWritableDatabase();
                    ContentValues values=new ContentValues();
                    values.put("mingzi",mDec2[position]);
                    values.put("jiage",mDec3[position]);
                    db.insert("sp",null,values);
                    db.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        tt.setText(mDec2[position]);
        tt2.setText(mDec3[position]);
        im.setBackgroundResource(images2[position]);
        return convertView;
    }
}





    }


    /**设置数据*/
//    private void setData() {
//        cityList = new ArrayList<CityItem>();
//        CityItem item = new CityItem();
//        item.setCityName("衣服");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("鞋靴");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("家电");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("数码");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("食品");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("百货");
//        cityList.add(item);
//        cityList.addAll(cityList);
//        image2 = new int[]{
//                R.drawable.yifu,
//                R.drawable.xiexue,
//                R.drawable.jiadian,
//                R.drawable.shuma,
//                R.drawable.shipin,
//                R.drawable.baihuo
//
//        };
//    }
//    /**设置GirdView参数，绑定数据*/
//    private void setGridView() {
//        int size = cityList.size();
//        int length = 100;
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        float density = dm.density;
//        int gridviewWidth = (int) (size * (length + 4) * density);
//        int itemWidth = (int) (length * density);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
//        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
//        gridView.setColumnWidth(itemWidth); // 设置列表项宽
//        gridView.setHorizontalSpacing(5); // 设置列表项水平间距
//        gridView.setStretchMode(GridView.NO_STRETCH);
//        gridView.setNumColumns(size); // 设置列数量=列表集合数
//
//        GridViewAdapter adapter = new GridViewAdapter(getActivity().getApplicationContext(),
//                cityList);
//        gridView.setAdapter(adapter);
//    }
//
//    /**GirdView 数据适配器*/
//    public class GridViewAdapter extends BaseAdapter {
//        Context context;
//        List<CityItem> list;
//
//        public GridViewAdapter(Context _context, List<CityItem> _list) {
//            this.list = _list;
//            this.context = _context;
//        }
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return list.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater layoutInflater = LayoutInflater.from(context);
//            convertView = layoutInflater.inflate(R.layout.gard_list, null);
//            ImageView imageView=(ImageView)convertView.findViewById(R.id.ItemImage);
//            TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
//            CityItem city = list.get(position);
//            imageView.setBackgroundResource(image2[position]);
//            tvCity.setText(city.getCityName());
//            return convertView;
//        }
//    }
//
//    public class CityItem {
//        private String cityName;
//        private String cityCode;
//
//        public String getCityName() {
//            return cityName;
//        }
//
//        public void setCityName(String cityName) {
//            this.cityName = cityName;
//        }
//
//        public String getCityCode() {
//            return cityCode;
//        }
//
//        public void setCityCode(String cityCode) {
//            this.cityCode = cityCode;
//        }
//    }


