package com.android.beautifulthing.DiscoverFragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ydy on 2016/9/7.
 */
public class DetailListAdapter extends BaseAdapter{

    private Context mContext;
    private List<String> imageList;
    private LayoutInflater mInflater;

    public DetailListAdapter(Context context, List<String> images) {
        mContext = context;
        imageList = images;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return imageList == null ? 0 : imageList.size();
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
        View view = mInflater.inflate(R.layout.detail_listview_item, parent, false);
        ImageView mImage = (ImageView) view.findViewById(R.id.detail_listview_item);
        String image_url = imageList.get(position);
        Picasso.with(mContext).load(image_url).into(mImage);
        return view;
    }

}
