package com.android.beautifulthing.DiscoverFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.beautifulthing.DiscoverFragment.DetailActivity;
import com.android.beautifulthing.DiscoverFragment.bean.DetailBean;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ydy on 2016/9/7.
 */
public class DetailGridAdapter extends BaseAdapter{

    private Context mContext;
    private List<DetailBean.DataBean.ReferProductsBean> referList;
    private LayoutInflater mInflater;

    public DetailGridAdapter(Context context, List<DetailBean.DataBean.ReferProductsBean> refer_products) {
        mContext = context;
        referList = refer_products;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return referList == null ? 0 : referList.size();
    }

    @Override
    public Object getItem(int position) {
        return referList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.detail_gridview_item, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //
        viewHolder.id = referList.get(position).getId();
        String image_url = referList.get(position).getImages().get(0);
        Picasso.with(mContext).load(image_url).into(viewHolder.mImageIv);
        return convertView;
    }

    class ViewHolder implements View.OnClickListener{

        private int id;
        private ImageView mImageIv;

        public ViewHolder(View view) {
            view.setTag(this);
            mImageIv = (ImageView) view.findViewById(R.id.detail_gridview_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "" + id, Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(mContext, DetailActivity.class);
//            intent.putExtra("id", id);
//            mContext.startActivity(intent);
        }
    }
}
