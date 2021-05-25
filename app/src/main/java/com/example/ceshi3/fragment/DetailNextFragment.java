package com.example.ceshi3.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ceshi3.MainActivity;
import com.example.ceshi3.R;
import com.example.ceshi3.shangpin;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailNextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailNextFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public DetailNextFragment() {
        // Required empty public constructor
    }
    TextView tv_tips;
    ViewGroup.LayoutParams layoutParams;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailNextFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailNextFragment newInstance(String param1, String param2) {
        DetailNextFragment fragment = new DetailNextFragment();
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
        return inflater.inflate(R.layout.fragment_detail_next, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * 这里为了不引起沉浸式状态栏切换时的微闪烁问题，固定用一个占位view
         */
        tv_tips=view.findViewById(R.id.tv_tips);
        layoutParams = tv_tips.getLayoutParams();
        //状态栏高度+图文详情页标题高度，把实际内容顶在下面正常显示
        layoutParams.height = ((shangpin)getActivity()).getStatusBarHeight()+((shangpin)getActivity()).dp2px(44);
        tv_tips.setLayoutParams(layoutParams);
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
