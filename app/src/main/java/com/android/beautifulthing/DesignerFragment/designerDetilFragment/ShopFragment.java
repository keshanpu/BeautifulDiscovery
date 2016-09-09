package com.android.beautifulthing.DesignerFragment.designerDetilFragment;

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
public class ShopFragment extends Fragment {
    public static ShopFragment newInstance(){
        ShopFragment shopFragment = new ShopFragment();
        return  shopFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.shopfragment,container,false);
        return view;
    }
}
