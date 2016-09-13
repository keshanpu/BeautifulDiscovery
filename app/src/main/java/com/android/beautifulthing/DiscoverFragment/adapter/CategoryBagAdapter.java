package com.android.beautifulthing.DiscoverFragment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.beautifulthing.DiscoverFragment.bean.CategoryBean;
import com.android.beautifulthing.DiscoverFragment.subfragment.BagFragment;
import com.android.beautifulthing.DiscoverFragment.subfragment.JewelryFragment;
import com.android.beautifulthing.R;

import java.util.List;

/**
 * Created by ydy on 2016/9/9.
 */
public class CategoryBagAdapter extends BaseAdapter{

    private Context mContext;
    private List<CategoryBean.DataBean.CategoriesBean.SubCategoriesBean> categoriesList;
    private LayoutInflater mInflater;
    private BagFragment bagFragment;

    public CategoryBagAdapter(Context context, List<CategoryBean.DataBean.CategoriesBean.SubCategoriesBean> sub_categories, BagFragment bagFragment) {
        mContext = context;
        categoriesList = sub_categories;
        mInflater = LayoutInflater.from(mContext);
        this.bagFragment = bagFragment;
    }

    @Override
    public int getCount() {
        return categoriesList == null ? 0 : categoriesList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoriesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.category_gridview_item, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //获取位置
        CategoryBean.DataBean.CategoriesBean.SubCategoriesBean subCategoriesBean = categoriesList.get(position);
        viewHolder.id = subCategoriesBean.getId();
        viewHolder.name = subCategoriesBean.getName();
        String name = subCategoriesBean.getName();
        //填值
        viewHolder.mCategotyName.setText(name);
        return convertView;
    }

    class ViewHolder implements View.OnClickListener {

        private String name;
        private int id;
        private TextView mCategotyName;

        public ViewHolder(View view) {
            view.setTag(this);
            mCategotyName = (TextView) view.findViewById(R.id.category_name_tv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mCategotyName.setTextColor(Color.parseColor("#000000"));
            mCategotyName.setBackgroundResource(R.drawable.textview_background);
            bagFragment.refreshView(id, name);
        }
    }
}
