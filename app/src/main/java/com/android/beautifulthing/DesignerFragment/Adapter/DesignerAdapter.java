package com.android.beautifulthing.DesignerFragment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.beautifulthing.DesignerFragment.DesignerDetilActivity;
import com.android.beautifulthing.DesignerFragment.bean.DesignerBean;
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
        return view;
    }

    public class ViewHolder {
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

        public ViewHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "123", Toast.LENGTH_SHORT).show();
                    if (mPWindow!=null){
                        if (mPWindow.isShowing()){
                            mPWindow.dismiss();
                        }else {
                            initPopupWindow();
                        }
                    }else {
                        initPopupWindow();
                    }

                }

                private void initPopupWindow() {
                    View windowView = LayoutInflater.from(mContext).inflate(R.layout.pop_window,null,false);
                    //ButterKnife.bind(mContext,windowView);
                   qqLogin= (ImageButton) windowView.findViewById(R.id.pop_window_qq);
                    qqLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "qq", Toast.LENGTH_SHORT).show();
                        }
                    });
                    wenxinLogin= (ImageButton) windowView.findViewById(R.id.pop_window_wenxin);
                    wenxinLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "wen", Toast.LENGTH_SHORT).show();
                        }
                    });
                    sinaLogin= (ImageButton) windowView.findViewById(R.id.pop_window_sina);
                    sinaLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "sina", Toast.LENGTH_SHORT).show();
                        }
                    });
                    mPWindow = new PopupWindow(windowView, 500, 500);

                    //设置参数，让点击屏幕空白区域，可以使PopupWindow消失
                     mPWindow.setBackgroundDrawable(new ColorDrawable());
                      mPWindow.setOutsideTouchable(false);
                     mPWindow.setFocusable(true);
                     mPWindow.setBackgroundDrawable(new BitmapDrawable());

                    /**
                     * 参数：
                     * 1，传入一个View，最后showAtLocation会以这个View的父容器为参照，决定显示位置
                     * 2，重力方向的设置，参照物是第一个参数的父容器
                     * 3，x轴上的偏移量
                     * 4，y轴上的偏移量
                     */
                    mPWindow.showAtLocation(windowView, Gravity.CENTER, 0, 50);
                }
            });
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
    }
}
