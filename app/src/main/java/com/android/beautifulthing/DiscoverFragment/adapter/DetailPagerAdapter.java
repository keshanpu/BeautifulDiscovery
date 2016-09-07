package com.android.beautifulthing.DiscoverFragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ydy on 2016/9/7.
 */
public class DetailPagerAdapter extends android.support.v4.view.PagerAdapter{

    private Context mContext;
    private List<String> imagesList;
    private LayoutInflater mInflater;

    public DetailPagerAdapter(Context context, List<String> cover_images) {
        mContext = context;
        imagesList = cover_images;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return imagesList == null ? 0 : imagesList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.detail_viewpager_item, container, false);
        ImageView mImageIv = (ImageView) view.findViewById(R.id.detail_viewpager_item);
        String image_url = imagesList.get(position);
        Picasso.with(mContext).load(image_url).into(mImageIv);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
