package com.example.ceshi3.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ceshi3.R;
import com.example.ceshi3.jiesuanye;
import com.example.ceshi3.shangpinsql;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_zhuye3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_zhuye3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView money3,del,jiesuan;
    private  int mone;
    private ListView lv;
    shangpinsql a4sql;
    List<pai> ls = new ArrayList<>();
    private BaseAdapter baseAdapter;

    public Fragment_zhuye3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment Fragment_zhuye3.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_zhuye3 newInstance(String param1) {
        Fragment_zhuye3 fragment = new Fragment_zhuye3();
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
        return inflater.inflate(R.layout.fragment_zhuye3, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        a4sql = new shangpinsql(getActivity());
        del = (TextView) getView().findViewById(R.id.del);
        money3 = (TextView) getView().findViewById(R.id.money3);
        jiesuan = (TextView) getView().findViewById(R.id.jiesuan);

        lv = (ListView) getView().findViewById(R.id.flis4);
        //删库
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ls.clear();
                baseAdapter.notifyDataSetChanged();
                SQLiteDatabase db=a4sql.getWritableDatabase();
                db.delete("sp",null,null);
                db.close();
                money3.setText(0+"");
            }
        });

        /*
        读取数据
        填充数据到listview里
         */
        int a=0;
        SQLiteDatabase db = a4sql.getReadableDatabase();
        Cursor cursor = db.query("sp", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String a1 = cursor.getString(cursor.getColumnIndex("mingzi"));
            String a2 = cursor.getString(cursor.getColumnIndex("jiage"));
            pai lls=new pai(a1,a2);
            ls.add(lls);
            a=Integer.parseInt(a2)+a;
            money3.setText(a+"");
            mone=a;
        }
        cursor.close();
        db.close();
        /*
        适配器
         */
        baseAdapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return ls.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView=View.inflate(getActivity(),R.layout.zhuye4_list,null);
                pai lis=ls.get(position);
                TextView t1=(TextView)convertView.findViewById(R.id.name);
                TextView t2=(TextView)convertView.findViewById(R.id.money);
                t1.setText(lis.getName1());
                t2.setText(lis.getMon1());
                return convertView;
            }
        };
        lv.setAdapter(baseAdapter);


    /*
    结算页跳转
     */
    jiesuan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            NotificationManager manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
//            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext());
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//            //获取当前时间
//            Date date = new Date(System.currentTimeMillis());
//            String a=simpleDateFormat.format(date);
//            mBuilder .setContentTitle("您的购买金额")  //设置标题
//                    .setContentText("您于"+a+"花费"+mone+"元") //内容
//                    .setWhen(System.currentTimeMillis())  //设置时间
//                    .setSmallIcon(R.drawable.taobao1)  //设置小图标
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.taobao1));   //设置大图标
//            manager.notify(10, mBuilder.build());
            Intent intent = new Intent(getActivity(), jiesuanye.class);
            startActivity(intent);
        }
    });



    }





    class pai {
        String name1;
        String mon1;

        public pai(String name1, String mon1) {
            this.mon1 = mon1;
            this.name1 = name1;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public String getMon1() {
            return mon1;
        }

        public void setMon1(String mon1) {
            this.mon1 = mon1;
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ls.clear();

    }
}
