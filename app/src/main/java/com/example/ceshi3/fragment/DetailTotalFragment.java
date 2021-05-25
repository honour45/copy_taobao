package com.example.ceshi3.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ceshi3.R;
import com.example.ceshi3.adapter.VPAdapter;
import com.example.ceshi3.bean.VerViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailTotalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailTotalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public DetailTotalFragment() {
        // Required empty public constructor
    }
    VerViewPager vp;//垂直viewpager
    VPAdapter mVPAdapter;
    FragmentManager fm;//内层fm要用child fm
    List<Fragment> fgList=new ArrayList();//上下切换
    int which_page=0;//当前垂直viewpager的选中页
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailTotalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailTotalFragment newInstance(String param1, String param2) {
        DetailTotalFragment fragment = new DetailTotalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        return inflater.inflate(R.layout.fragment_detail_total, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vp=view.findViewById(R.id.ver_vp);
        fm=getChildFragmentManager();
        /**
         * 添加商品详情页 第一页与第二页 装到垂直viewpager中
         */
        fgList.add(DetailFragment.newInstance("",""));
        fgList.add(DetailNextFragment.newInstance("",""));
        mVPAdapter=new VPAdapter(fm,fgList);
        vp.setAdapter(mVPAdapter);
        vp.setCurrentItem(0);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //发送当前页面给activity处理title
                which_page=position;
                if (mListener != null) {
                    Uri.Builder builder=new Uri.Builder();
                    Uri uri=builder.scheme("scheme")
                            .fragment("DetailTotalFragment")
                            .authority("authority")
                            .appendQueryParameter("which_page",which_page+"")
                            .build();
                    mListener.onFragmentInteraction(uri);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}