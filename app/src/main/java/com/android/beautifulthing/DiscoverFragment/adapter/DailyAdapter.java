package com.android.beautifulthing.DiscoverFragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.beautifulthing.CommonActivity.DetailActivity;
import com.android.beautifulthing.DiscoverFragment.bean.DailyBean;
import com.android.beautifulthing.DiscoverFragment.tools.DateUtils;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ydy on 2016/9/6.
 */
public class DailyAdapter extends BaseAdapter {
    //flagX作为position=0的标示
    boolean flagX = false, flagY = true;
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
        return dataList == null ? 0 : dataList.size() + 1;
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
        HeadViewHolder headViewHolder = null;
        View view = convertView;
        view = null;
        if (position == 0){
            if(flagY){
                flagY = false;
                flagX = true;
                view = null;
            }
            if (view == null){
                view = mInflater.inflate(R.layout.subfragment_daily_head_view, parent, false);
                headViewHolder = new HeadViewHolder(view);
            }else {
                headViewHolder = (HeadViewHolder) view.getTag();
            }
            String currentDate = DateUtils.getCurrentDate();
            long TIMESTAMP = DateUtils.getTimeStamp(currentDate);
            String date = DateUtils.timeStampToDate2(TIMESTAMP);
            Date transDate = new Date(TIMESTAMP);
            String week = DateUtils.DateToWeek(transDate);
            headViewHolder.mHeadTime.setText(date + "," + week);
            return view;
        }
        view = null;
        if ( position != 0){
//            if(flagX){
                flagX = false;
                flagY = true;
                view = null;
//            }
            if (view == null){
                view = mInflater.inflate(R.layout.subfragment_daily_item, parent, false);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            //获取位置
            DailyBean.DataBean.ProductsBean productsBean = dataList.get(position - 1);
            //向控件填值
            String imageurl = productsBean.getCover_images().get(0);
            Picasso.with(mContext).load(imageurl).into(viewHolder.mImageIv);
            String productName = productsBean.getName();
            viewHolder.mNameTv.setText(productName);
            String briefStr = productsBean.getBrief().toString();
            String brief = briefStr.substring(productName.length()).trim();
            if (brief != null) {
                viewHolder.mBriefTv.setText(brief);
            }
            String avatar_url = productsBean.getDesigner().getAvatar_url();
            String name = productsBean.getDesigner().getName();
            String label = productsBean.getDesigner().getLabel();
            Picasso.with(mContext).load(avatar_url).into(viewHolder.mAuthorimageTv);
            viewHolder.mDesignerNameTv.setText(name);
            viewHolder.mDesignerLabelTv.setText(label);
            //点击事件向下界面传递的id
            viewHolder.id = productsBean.getId();
        }
        return view;
    }

    class HeadViewHolder{

        private TextView mHeadTime;

        public HeadViewHolder(View view) {
            view.setTag(this);
            mHeadTime = (TextView) view.findViewById(R.id.head_view);
        }
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
        RadioButton mFaceDislike;
        @BindView(R.id.daily_item_face_like)
        RadioButton mFaceLike;

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
