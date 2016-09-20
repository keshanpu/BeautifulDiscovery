package com.android.beautifulthing.MagazineFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.beautifulthing.CommonActivity.CommentsActivity;
import com.android.beautifulthing.CommonActivity.LoginActivity;
import com.android.beautifulthing.MagazineFragment.tools.customview.Mylistview;
import com.android.beautifulthing.MagazineFragment.adapter.DetailCommentListAdapter;
import com.android.beautifulthing.MagazineFragment.adapter.DetailListAdapter;
import com.android.beautifulthing.MagazineFragment.bean.MagazineDetailBean;
import com.android.beautifulthing.MagazineFragment.presenter.impl.MagazineDetailActivityPresenter;
import com.android.beautifulthing.MagazineFragment.scroll.TopDecoration;
import com.android.beautifulthing.MagazineFragment.scroll.TopTrackListener;
import com.android.beautifulthing.MagazineFragment.view.IMagazineDetailActivityView;
import com.android.beautifulthing.MineFragment.LoginInfo;
import com.android.beautifulthing.R;
import com.appeaser.deckview.de.hdodenhof.circleimageview.CircleImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by keshanpu on 16/9/9.
 */
public class MagazineDetailActivity extends AppCompatActivity implements IMagazineDetailActivityView{

    private int mId;
    private RecyclerView mRecyclerView;
    private ImageButton mTopImageBtn;
    private MagazineDetailActivityPresenter magazineDetailActivityPresenter;
    private SimpleAdapter simpleAdapter;

    private DetailListAdapter detailListAdapter;//作者简介;

    public DetailCommentListAdapter detailCommentListAdapter;//评论listview
    private MagazineDetailBean.DataBean dataBean;
    private ProgressDialog dialog;
    private TextView likeNum;
    private TextView commentNum;
    private TextView commentfootviewTv;
    public EditText commentEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazinedetail_main_view);
        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);

       initView();


        //准备数据源;
        loadDatas();
    }

    private void initView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.magezinedetail_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTopImageBtn = (ImageButton) findViewById(R.id.magezinedetail_top_view);
        mRecyclerView.addItemDecoration(new TopDecoration(mTopImageBtn));
        mRecyclerView.addOnScrollListener(new TopTrackListener(mTopImageBtn));
        //如果要添加底部toorbar就设置下面;
//        mRecyclerView.addOnScrollListener(new BottomTrackListener(findViewById(R.id.main_bottom_view)));

        mTopImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTopImageBtn.setOnTouchListener(new View.OnTouchListener() {
            //这块一定将mTopImageBtn的布局scaleType="center",而非fitCenter.
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //重新设置按下时的背景图片
                    mTopImageBtn.setImageResource(R.drawable.ic_circle_back_pressed_normal);

                } else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    mTopImageBtn.setImageResource(R.drawable.ic_circle_back_normal);
                }
                return false;
            }
        });



        //button toolbar
        commentNum = (TextView) findViewById(R.id.article_comment_icon_num);
        likeNum = (TextView) findViewById(R.id.article_like_normal_num);
        commentEdit = (EditText) findViewById(R.id.article_comment_icon_edit);
        commentEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MagazineDetailActivity.this, CommentsActivity.class);
                intent.putExtra("id",dataBean.getId());
                startActivity(intent);
            }
        });
    }

    private void loadDatas() {
        dialog = new ProgressDialog(MagazineDetailActivity.this);
        dialog.setMessage("正在加载...");
        dialog.show();
        magazineDetailActivityPresenter = new MagazineDetailActivityPresenter(this);
        magazineDetailActivityPresenter.getMagazineDetail(mId);

    }

    /**
     * 数据获取成功;
     */
    @Override
    public void refreshMagazineDetail(MagazineDetailBean.DataBean dataBean) {
        this.dataBean = dataBean;
//        Log.d("TAG", "refreshMagazineDetail: "+dataBean);
        commentNum.setText(dataBean.getComments().size()+"");
        likeNum.setText(dataBean.getFavor_user_num()+"");

        simpleAdapter = new SimpleAdapter();
        mRecyclerView.setAdapter(simpleAdapter);

        simpleAdapter.notifyDataSetChanged();
        if(dialog != null){
            dialog.dismiss();
        }
    }

    /**数据获取失败;
     * @param result
     */
    @Override
    public void refreshDataFailed(int result) {
        if(dialog != null){
            dialog.dismiss();
        }
    }

    private class SimpleAdapter extends RecyclerView.Adapter<SimpleHolder> implements View.OnClickListener{

        @Override
        public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SimpleHolder(LayoutInflater.from(MagazineDetailActivity.this).inflate(R.layout.activity_magazinedetail_recyerview_item, null));
        }

        @Override
        public void onBindViewHolder(SimpleHolder holder, int position) {

            //设计师作品以上;
            detailListAdapter = new DetailListAdapter(MagazineDetailActivity.this,dataBean);
            holder.decription_lv.setAdapter(detailListAdapter);
            detailListAdapter.notifyDataSetChanged();

            //设计师作品;
            if(dataBean.getDesigners().size() != 0) {
                MagazineDetailBean.DataBean.DesignersBean designersBean = dataBean.getDesigners().get(0);
                holder.fork_circleImageView.setImageResource(R.mipmap.ic_launcher);
                Picasso.with(MagazineDetailActivity.this).load(designersBean.getAvatar_url()).into(holder.fork_circleImageView);
                holder.fork_name.setText(designersBean.getName());
                holder.fork_subtitle.setText(designersBean.getLabel());
                holder.descriptionView.setText(designersBean.getDescription());
            }

            if(dataBean.getComments().size() > 0) {
                holder.txtnum.setText(dataBean.getComments().size() + "");

                View footview = LayoutInflater.from(MagazineDetailActivity.this).inflate(R.layout.activity_magazinedetail_recyerview_item_comment_footview, null, false);
                commentfootviewTv = (TextView) footview.findViewById(R.id.magazinedetail_bottom_footview);
                commentfootviewTv.setOnClickListener(this);
                holder.comment_listview.addFooterView(footview);
                detailCommentListAdapter = new DetailCommentListAdapter(MagazineDetailActivity.this, dataBean);
                holder.comment_listview.setAdapter(detailCommentListAdapter);
            } else {
                holder.txtnum.setText("无");
            }

        }

        @Override
        public int getItemCount() {
            return 1;
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.magazinedetail_bottom_footview:
                    //评论的底部视图;->查看所有评论;
//                    Toast.makeText(MagazineDetailActivity.this,"dd",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MagazineDetailActivity.this, CommentsActivity.class);
                    intent.putExtra("id",dataBean.getId());
                    startActivity(intent);
                    break;
            }
        }
    }

    private class SimpleHolder extends RecyclerView.ViewHolder {
        public RelativeLayout share_article;
        public RelativeLayout became_author;
        public Mylistview decription_lv;
        public CircleImageView fork_circleImageView;
        public TextView fork_name;
        public TextView fork_subtitle;
        public Button forkbtn;

        //做折叠效果
        public TextView descriptionView;
        public View layoutView;
        public View expandView;
        public TextView expand_view_txt;
        public TextView txtnum;
        public Mylistview comment_listview;

        public SimpleHolder(View itemView) {
            super(itemView);
            initForSimpleHolder(itemView);
            //监听事件;
            forkbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //关注:
//                    Toast.makeText(MagazineDetailActivity.this,"a",Toast.LENGTH_SHORT).show();
                    if (LoginInfo.current_login_flag){//已登陆
                        if (!LoginInfo.magazineDetailActivity_Fork_flag){
                            forkbtn.setText("已关注");
                        } else {
                            forkbtn.setText("+关注");
                        }
                        LoginInfo.magazineDetailActivity_Fork_flag = !LoginInfo.magazineDetailActivity_Fork_flag;
                    } else {
                        Intent intent = new Intent(MagazineDetailActivity.this, LoginActivity.class);
                        MagazineDetailActivity.this.startActivity(intent);
                    }

                }
            });

            //做折叠;
            expand_view_txt = (TextView) itemView.findViewById(R.id.magazinedetail_recycler_item_design_expand_view_txt);

            layoutView = itemView.findViewById(R.id.magazinedetail_recycler_item_design_fork);
            descriptionView = (TextView) itemView.findViewById(R.id.magazinedetail_recycler_item_design_description_view);
            expandView = itemView.findViewById(R.id.magazinedetail_recycler_item_design_expand_view);
            //设置显示行数,默认展开行数设置其高度,并根据其是否已完全显示(当前展示行数是否大于等于实际行数)来判断需不需要点击更多按钮;
            //设置文本
//            descriptionView.setText(getText(R.string.mydefaulttext));//viewholder中去获取;
            descriptionView.setHeight(descriptionView.getLineHeight()*5);//假设仅显示行
            descriptionView.post(new Runnable() {
                @Override
                public void run() {
                    expandView.setVisibility(descriptionView.getLineCount() > 5 ? View.VISIBLE:View.GONE);
                }
            });

            layoutView.setOnClickListener(new View.OnClickListener() {
                boolean isExpand;//是否已展开的状态

                @Override
                public void onClick(View v) {
                    isExpand = !isExpand;
                    if(isExpand){
                        expand_view_txt.setText("收起");
                    } else {
                        expand_view_txt.setText("展开");
                    }

                    descriptionView.clearAnimation();//清楚动画效果
                    final int deltaValue;//默认高度，即前边由maxLine确定的高度
                    final int startValue = descriptionView.getHeight();//起始高度
                    int durationMillis = 350;//动画持续时间
                    if (isExpand) {
                        /**
                         * 折叠动画
                         * 从实际高度缩回起始高度
                         */
                        deltaValue = descriptionView.getLineHeight() * descriptionView.getLineCount() - startValue;
                        RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        animation.setDuration(durationMillis);
                        animation.setFillAfter(true);
                        expandView.startAnimation(animation);
                    } else {
                        /**
                         * 展开动画
                         * 从起始高度增长至实际高度
                         */
                        deltaValue = descriptionView.getLineHeight() * 5 - startValue;
                        RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        animation.setDuration(durationMillis);
                        animation.setFillAfter(true);
                        expandView.startAnimation(animation);
                    }
                    Animation animation = new Animation() {
                        protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                            descriptionView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                        }
                    };
                    animation.setDuration(durationMillis);
                    descriptionView.startAnimation(animation);
                }
            });

            txtnum = (TextView) itemView.findViewById(R.id.magazinedetail_recycler_item_comment_txtnum);
            comment_listview = (Mylistview)itemView.findViewById(R.id.magazinedetail_recycler_item_comment_lv);





        }

        private void initForSimpleHolder(View itemView) {
            decription_lv = (Mylistview) itemView.findViewById(R.id.magazinedetail_recycler_item_description_listview);
            became_author = (RelativeLayout) itemView.findViewById(R.id.magazinedetail_recycler_item_became_author);
            share_article = (RelativeLayout) itemView.findViewById(R.id.magazinedetail_recycler_item_share_author);
            //设计师&作品;
            fork_circleImageView = (CircleImageView) itemView.findViewById(R.id.magazinedetail_recycler_item_design_fork_circleview);
            fork_name = (TextView) itemView.findViewById(R.id.magazinedetail_recycler_item_design_name);
            fork_subtitle = (TextView) itemView.findViewById(R.id.magazinedetail_recycler_item_design_description);
            forkbtn = (Button) itemView.findViewById(R.id.magazinedetail_recycler_item_design_btn);
        }
    }
}
