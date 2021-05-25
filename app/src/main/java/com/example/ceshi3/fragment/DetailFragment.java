package com.example.ceshi3.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ceshi3.R;
import com.example.ceshi3.adapter.RvSimpleAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    private List<String> datas;
    private RecyclerView mRecyclerView;
    private RvSimpleAdapter mAdapter;
    private Banner banner;//实际的banner，自行替换
    private FrameLayout.LayoutParams mFrameLayoutParams;
    private int scrollY;//mRecyclerView的总体滑动距离
    private int banner_height;//mRecyclerView第一条banner的高度
    private int real_height;

    private List<String> images;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
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
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView=view.findViewById(R.id.rv);

        datas=new ArrayList<>();
        //假数据
        for (int i = 0; i < 10; i++) {
            datas.add("");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter=new RvSimpleAdapter(getActivity(),datas));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollY+=dy;//计算总滑动距离
                if (scrollY==0){
                    banner_height=mRecyclerView.getLayoutManager().getChildAt(0).getHeight();//计算第一条banner高度
                }
                if (scrollY<banner_height){
                    View view=recyclerView.getLayoutManager().getChildAt(0).findViewById(R.id.banner);
                    if (view!=null){
                        ConstraintLayout.LayoutParams mLayoutParams=(ConstraintLayout.LayoutParams)(view.getLayoutParams());
                        mLayoutParams.topMargin=(int)(scrollY/1.5);
                        Log.e("tag","topMargin="+scrollY/2);
                        view.setLayoutParams(mLayoutParams);
                    }
                }
                if (mListener != null) {//发送回调给activity接收处理，数据封在uri里
                    Uri.Builder builder=new Uri.Builder();
                    Uri uri=builder.scheme("scheme")
                            .fragment("DetailFragment")
                            .authority("authority")
                            .appendQueryParameter("rv_scrollY",scrollY+"")
                            .appendQueryParameter("banner_height",banner_height+"")
                            .build();
                    mListener.onFragmentInteraction(uri);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetailFragment.OnFragmentInteractionListener) {
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
