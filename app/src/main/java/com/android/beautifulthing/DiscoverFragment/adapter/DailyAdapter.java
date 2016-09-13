package com.android.beautifulthing.DiscoverFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.beautifulthing.DiscoverFragment.DetailActivity;
import com.android.beautifulthing.DiscoverFragment.bean.DailyBean;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ydy on 2016/9/6.
 */
public class DailyAdapter extends BaseAdapter {

    private Context mContext;
    private List<DailyBean.DataBean.ProductsBean> dataList;
    private LayoutInflater mInflater;



    public DailyAdapter(Context context, List<DailyBean.DataBean.ProductsBean> productsBeanList) {
        mContext = context;
        dataList = productsBeanList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.subfragment_daily_item, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //获取位置
        DailyBean.DataBean.ProductsBean productsBean = dataList.get(position);
        //向控件填值
        String imageurl = productsBean.getCover_images().get(0);
        Picasso.with(mContext).load(imageurl).into(viewHolder.mImageIv);
        viewHolder.mNameTv.setText(productsBean.getName());
        String briefStr = productsBean.getBrief().toString();
        String brief = briefStr.substring(briefStr.indexOf("\n")).trim();
        viewHolder.mBriefTv.setText(brief);
        String avatar_url = productsBean.getDesigner().getAvatar_url();
        String name = productsBean.getDesigner().getName();
        String label = productsBean.getDesigner().getLabel();
        Picasso.with(mContext).load(avatar_url).into(viewHolder.mAuthorimageTv);
        viewHolder.mDesignerNameTv.setText(name);
        viewHolder.mDesignerLabelTv.setText(label);
        //点击事件向下界面传递的id
        viewHolder.id = productsBean.getId();
        return convertView;
    }

    class ViewHolder implements View.OnClickListener{

        private int id;
        private CircleImageView mAuthorimageTv;
        private RelativeLayout mRelativeLayout;

        @BindView(R.id.daily_item_image_iv)
        ImageView mImageIv;
        @BindView(R.id.daily_item_name_tv)
        TextView mNameTv;
        @BindView(R.id.daily_item_brief_tv)
        TextView mBriefTv;
        @BindView(R.id.daily_item_designer_name)
        TextView mDesignerNameTv;
        @BindView(R.id.daily_item_designer_label)
        TextView mDesignerLabelTv;
        @BindView(R.id.daily_item_face_dislike)
        ImageView mFaceDislike;
        @BindView(R.id.daily_item_face_like)
        ImageView mFaceLike;

        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this, view);
            mAuthorimageTv = (CircleImageView) view.findViewById(R.id.daily_item_authorimage_iv);
            mRelativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout_click);
            mRelativeLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("id", id);
            mContext.startActivity(intent);
        }
    }
}
