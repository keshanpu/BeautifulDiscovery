package com.android.beautifulthing.DesignerFragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ydy on 2016/9/5.
 */
public class DesignerFragment extends Fragment {

    private Context mContext;

    public static DesignerFragment newInstance(){
        DesignerFragment designerFragment = new DesignerFragment();
        return designerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(mContext);
        tv.setText("设计师");
        tv.setTextColor(Color.parseColor("#ffffff"));
        return tv;
    }

}
