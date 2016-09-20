package com.android.beautifulthing.MagazineFragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.beautifulthing.MagazineFragment.bean.MagazineDetailBean;
import com.android.beautifulthing.R;
import com.appeaser.deckview.de.hdodenhof.circleimageview.CircleImageView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by keshanpu on 16/9/14.
 */
public class DetailCommentListAdapter extends BaseAdapter{
    private Context mContext;
    private MagazineDetailBean.DataBean dataBean;
    private List<MagazineDetailBean.DataBean.CommentsBean> comments;
    public DetailCommentListAdapter(Context mContext, MagazineDetailBean.DataBean dataBean) {
        this.mContext = mContext;
        this.dataBean = dataBean;
        comments = dataBean.getComments();
    }


    @Override
    public int getCount() {
        return comments == null ? 0 : comments.size();
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        CommentViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_magazinedetail_recyerview_item_comment, viewGroup,false);
            viewHolder = new CommentViewHolder(view);
        } else {
            viewHolder = (CommentViewHolder) view.getTag();
        }

        viewHolder.avatar_url.setImageResource(R.mipmap.ic_launcher);
        MagazineDetailBean.DataBean.CommentsBean commentsBean = comments.get(i);
        Picasso.with(mContext).load(commentsBean.getAuthor().getAvatar_url()).into(viewHolder.avatar_url);
        viewHolder.username.setText(commentsBean.getAuthor().getUsername());
        viewHolder.commnet.setText(commentsBean.getContent());
        viewHolder.createtime.setText(getDateToString(commentsBean.getCreated_at()));
        return view;
    }

    class CommentViewHolder {

        public CircleImageView avatar_url;
        public TextView username;
        public TextView commnet;
        public TextView createtime;

        public CommentViewHolder(View view){
            view.setTag(this);
            avatar_url = (CircleImageView) view.findViewById(R.id.magazinedetail_recyer_item_author_avatar_url);
            username = (TextView) view.findViewById(R.id.magazinedetail_recyer_item_author_username);
            commnet = (TextView) view.findViewById(R.id.magazinedetail_recyer_item_content);
            createtime = (TextView) view.findViewById(R.id.magazinedetail_recycler_item_createat);

        }

    }


    /**将时间戳转换为字符串;
     * @param time
     * @return
     */
    public String getDateToString(long time) {

        Date d = new Date(time);
//        long coment_time = d.getTime();//时间戳;
//        Log.d("xxyy", "getDateToString: "+coment_time);
//
//        Date currentTime = new Date();
//        long current_time = currentTime.getTime();
//
//        Log.d("xxyy", "getDateToString: "+current_time);
//
//        //实际相差;
//        long temptime = current_time - coment_time;
//
//        //86400000时间戳的间隔;1天多少秒;000毫秒;

        SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
        return sf.format(d);
    }
}
