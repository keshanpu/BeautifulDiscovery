package com.android.beautifulthing.DesignerFragment.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class DesignerDetilAdapter extends PagerAdapter{
    private List<String> stringList;
    private Context context;

    public DesignerDetilAdapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return stringList == null ? 0 : stringList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setAdjustViewBounds(true);
        //设置最小高度
        imageView.setMinimumHeight(10);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.with(context).load(stringList.get(position)).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
    }
}
