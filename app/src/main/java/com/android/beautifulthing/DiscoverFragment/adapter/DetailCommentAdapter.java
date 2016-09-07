package com.android.beautifulthing.DiscoverFragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.beautifulthing.DiscoverFragment.bean.DetailBean;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ydy on 2016/9/8.
 */
public class DetailCommentAdapter extends BaseAdapter{

    private Context mContext;
    private List<DetailBean.DataBean.CommentsBean> commentsList;
    private LayoutInflater mInflater;

    public DetailCommentAdapter(Context context, List<DetailBean.DataBean.CommentsBean> comments) {
        mContext = context;
        commentsList = comments;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return commentsList == null ? 0 : commentsList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.detail_comments_item, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //
        DetailBean.DataBean.CommentsBean commentsBean = commentsList.get(position);
        String avatar_url = commentsBean.getAuthor().getAvatar_url();
        String username = commentsBean.getAuthor().getUsername();
        String content = commentsBean.getContent();
        //
        Picasso.with(mContext).load(avatar_url).into(viewHolder.mUserImage);
        viewHolder.mUserName.setText(username);
        viewHolder.mUserContent.setText(content);
        return convertView;
    }
    class ViewHolder {

        private CircleImageView mUserImage;
        private TextView mUserName;
        private TextView mUserContent;

        public ViewHolder(View view){
            view.setTag(this);
            mUserImage = (CircleImageView) view.findViewById(R.id.detail_comment_user_image);
            mUserName = (TextView) view.findViewById(R.id.detail_comment_user_name);
            mUserContent = (TextView) view.findViewById(R.id.detail_comment_user_content);
        }
    }
}
