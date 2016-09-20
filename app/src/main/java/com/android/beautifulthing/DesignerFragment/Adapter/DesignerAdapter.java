package com.android.beautifulthing.DesignerFragment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.beautifulthing.CommonActivity.LoginActivity;
import com.android.beautifulthing.DesignerFragment.DesignerDetilActivity;
import com.android.beautifulthing.DesignerFragment.bean.DesignerBean;
import com.android.beautifulthing.MineFragment.LoginInfo;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class DesignerAdapter  extends BaseAdapter{
    private Context mContext;
    private List<DesignerBean.DataBean.DesignersBean> designersBeanLists = new ArrayList<>();
//    @BindView(R.id.pop_window_qq)
   private ImageButton qqLogin;
//    @BindView(R.id.pop_window_sina)
  private   ImageButton sinaLogin;
//    @BindView(R.id.pop_window_wenxin)
   private ImageButton wenxinLogin;
    private PopupWindow mPWindow;

    //解决viewholder复用问题导致关注后,复用的view都关注了;
    List<Integer> forkFlagList = new ArrayList<>();
    private int current_item_position;

    public DesignerAdapter(Context mContext, List<DesignerBean.DataBean.DesignersBean> designersBeanLists) {
        this.mContext = mContext;
        this.designersBeanLists = designersBeanLists;
    }

    @Override
    public int getCount() {
        return designersBeanLists == null ? 0 : designersBeanLists.size();
    }

    @Override
    public Object getItem(int position) {
        return designersBeanLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.fragment_designer_main_item,parent,false);
            viewHolder = new ViewHolder(view);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DesignerBean.DataBean.DesignersBean bean = designersBeanLists.get(position);
        viewHolder.name.setText(bean.getName());
        viewHolder.label.setText(bean.getLabel());
        viewHolder.cocept.setText(bean.getConcept());
        String imagePath1 = bean.getRecommend_images().get(0);
        String imagePath2 = bean.getAvatar_url();
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.imageView2.setImageResource(R.mipmap.ic_launcher);
        Picasso.with(mContext).load(imagePath1).into(viewHolder.imageView);
        Picasso.with(mContext).load(imagePath2).into(viewHolder.imageView2);

        int designer_id = bean.getId();
        view.setTag(R.id.designer_id,designer_id);

        current_item_position = designer_id;
        for (int i = 0, j = forkFlagList.size(); i < j;i++){
            if (forkFlagList.get(i) == designer_id){
                viewHolder.btn.setText("+已关注");
            }
        }
        return view;
    }

    public class ViewHolder implements View.OnClickListener{
        public int designer_id;

        @BindView(R.id.fragment_designer_main_item_iv)
        ImageView imageView;
        @BindView(R.id.fragment_designer_main_item_iv2)
        CircleImageView imageView2;
        @BindView(R.id.fragment_designer_main_item_name)
        TextView name;
        @BindView(R.id.fragment_designer_main_item_label)
        TextView label;
        @BindView(R.id.fragment_designer_main_item_cocept)
        TextView cocept;
        @BindView(R.id.fragment_designer_main_item_btn)
        Button btn;

        public ViewHolder(final View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
            btn.setOnClickListener(this);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    designer_id= (int) v.getTag(R.id.designer_id);
                    Intent intent = new Intent(mContext,DesignerDetilActivity.class);
                    intent.putExtra("designer_id",designer_id);
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (false == LoginInfo.current_login_flag){
                Intent intent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(intent);
            } else {
                if (false == LoginInfo.designerAdapter_Fork_flag){
                    btn.setText("已关注");
                }
                else {
                    btn.setText("+关注");
                }
                LoginInfo.designerAdapter_Fork_flag = !LoginInfo.designerAdapter_Fork_flag;

                forkFlagList.add(current_item_position);


            }
        }

    }

}
