package com.android.beautifulthing.DesignerFragment.designerDetilFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.beautifulthing.R;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class BuyFragment extends Fragment {
    private Context mContext;
    public static BuyFragment newInstance(){
        BuyFragment buyFragment = new BuyFragment();
        return buyFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.buyfragment,container,false);
        return view;
    }
}
