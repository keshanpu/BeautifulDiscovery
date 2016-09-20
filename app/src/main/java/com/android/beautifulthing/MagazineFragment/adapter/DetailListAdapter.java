package com.android.beautifulthing.MagazineFragment.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.beautifulthing.MagazineFragment.tools.html.GetImgFromHtml;
import com.android.beautifulthing.MagazineFragment.bean.MagazineDetailBean;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

/**
 * Created by keshanpu on 16/9/12.
 */
public class DetailListAdapter extends BaseAdapter {

    private Context mContext;
    private MagazineDetailBean.DataBean dataBean;
    public DetailListAdapter(Context mContext,MagazineDetailBean.DataBean dataBean){
        this.mContext = mContext;
        this.dataBean = dataBean;
    }

    @Override
    public int getCount() {
        return dataBean == null ? 0 : 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder viewHolder;
        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_magazinedetail_recyerview_item_description, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.title.setText(dataBean.getTitle());
        viewHolder.subTitle.setText(dataBean.getSub_title());
        //下载
        viewHolder.image.setImageResource(R.mipmap.ic_launcher);
        Picasso.with(mContext).load(dataBean.getImage_url()).into(viewHolder.image);
        //author
        viewHolder.avatar_url.setImageResource(R.mipmap.ic_launcher);
        MagazineDetailBean.DataBean.AuthorBean author = dataBean.getAuthor();
        Picasso.with(mContext).load(author.getAvatar_url()).into(viewHolder.avatar_url);
        viewHolder.username.setText(author.getUsername());
        viewHolder.author_sign.setText(author.getSign());

        Spanned sp = Html.fromHtml(dataBean.getContent(),new GetImgFromHtml(mContext),null);
        viewHolder.content.setText(sp);
        return view;
    }


    class ViewHolder {

        public ImageView image;
        public TextView title;
        public TextView subTitle;
        //author
        public TextView author_sign;
        public TextView username;
        public ImageView avatar_url;
        public TextView content;

        public ViewHolder(View view) {
            view.setTag(this);
            title = (TextView) view.findViewById(R.id.item_description_title);
            subTitle = (TextView) view.findViewById(R.id.item_description_subtitle);
            image = (ImageView) view.findViewById(R.id.item_description_image);

            avatar_url = (ImageView) view.findViewById(R.id.author_avatar_url);
            username = (TextView) view.findViewById(R.id.author_username);
            author_sign = (TextView) view.findViewById(R.id.author_sign);

            content = (TextView) view.findViewById(R.id.item_description_content);

        }
    }

}
