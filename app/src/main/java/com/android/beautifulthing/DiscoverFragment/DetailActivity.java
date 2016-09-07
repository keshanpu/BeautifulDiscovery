package com.android.beautifulthing.DiscoverFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.beautifulthing.DiscoverFragment.adapter.DetailCommentAdapter;
import com.android.beautifulthing.DiscoverFragment.adapter.DetailGridAdapter;
import com.android.beautifulthing.DiscoverFragment.adapter.DetailListAdapter;
import com.android.beautifulthing.DiscoverFragment.adapter.DetailPagerAdapter;
import com.android.beautifulthing.DiscoverFragment.bean.DetailBean;
import com.android.beautifulthing.DiscoverFragment.custom.MyGridView;
import com.android.beautifulthing.DiscoverFragment.custom.MyListView;
import com.android.beautifulthing.DiscoverFragment.presenter.IDetailPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.impl.DetailPresenter;
import com.android.beautifulthing.DiscoverFragment.view.IDetailView;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ydy on 2016/9/6.
 */
public class DetailActivity extends AppCompatActivity implements IDetailView{

    private int id;
    private Context mContext;
    private IDetailPresenter detailPresenter;

    private ViewPager mViewPager;
    private MyListView mListView;
    private MyGridView mGridView;

    private CircleImageView mDesignerIv;

//    @BindView(R.id.detail_item_face_dislike)
//    ImageView mFaceDislike;
//    @BindView(R.id.detail_item_face_like)
//    ImageView mFaceLike;
    @BindView(R.id.detail_item_digest_tv)
    TextView mDigestTv;
    @BindView(R.id.detail_item_name_tv)
    TextView mNameTv;
    @BindView(R.id.detail_item_desc_tv)
    TextView mDescTv;
    @BindView(R.id.detail_designer_name_tv)
    TextView mDesignerName;
    @BindView(R.id.detail_designer_label_tv)
    TextView mDesignerLabel;
    @BindView(R.id.detail_add_attention_btn)
    Button mAddAttentionBtn;
    @BindView(R.id.detail_description_tv)
    TextView mDescriptionTv;
    @BindView(R.id.comment_number)
    TextView mCommentNum;
    @BindView(R.id.detail_comments_number)
    TextView mCommentsNumber;

    private List<String> cover_images = new ArrayList<>();
    private List<String> images = new ArrayList<>();
    private List<DetailBean.DataBean.ReferProductsBean> refer_products = new ArrayList<>();
    private List<DetailBean.DataBean.CommentsBean> comments = new ArrayList<>();
    private int unlike_user_num;
    private int like_user_num;
    private String digest;
    private String name;
    private String desc;
    private String avatar_url;
    private String designerName;
    private String designerLabel;
    private String description;
    private String comment_num;
    private DetailListAdapter mDetailListAdapter;
    private DetailGridAdapter mDetailGridAdapter;
    private DetailPagerAdapter mDetailPagerAdapter;
    private int left;
    private MyListView mCommentListView;
    private DetailCommentAdapter mDetailCommentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_view);
        mContext = this;
        //接收传来的数据
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        //数据源
        detailPresenter = new DetailPresenter(this);
        detailPresenter.getDetailDatas(id);
    }


    @Override
    public void detailDataResult(DetailBean body) {
        //全部数据
        DetailBean.DataBean data = body.getData();
        cover_images = data.getCover_images();//ViewPager数据源
        images = data.getImages();//ListView数据源
        refer_products = data.getRefer_products();//GridView数据源
        comments = data.getComments();//评论数据源
        unlike_user_num = data.getUnlike_user_num();//哭脸数
        like_user_num = data.getLike_user_num();//笑脸数
        digest = data.getDigest();//digest
        name = data.getName();//商品名称
        desc = data.getDesc();//商品材料描述
        comment_num = String.valueOf(data.getComment_num());//评论数量
        //设计师信息
        DetailBean.DataBean.DesignerBean designer = data.getDesigner();
        avatar_url = designer.getAvatar_url();//设计师头像
        designerName = designer.getName();//设计师名
        designerLabel = designer.getLabel();//设计师标签
        description = designer.getDescription();//设计师简介
        //
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        ButterKnife.bind(this);
        //ViewPager
        initViewPager();
        //ListView
        mListView = (MyListView) findViewById(R.id.detail_item_listview);
        mDetailListAdapter = new DetailListAdapter(mContext, images);
        mListView.setAdapter(mDetailListAdapter);
        //评论ListView
        mCommentListView = (MyListView) findViewById(R.id.detail_comments_listview);
        mDetailCommentAdapter = new DetailCommentAdapter(mContext, comments);
        mCommentListView.setAdapter(mDetailCommentAdapter);
        //GridView
        mGridView = (MyGridView) findViewById(R.id.detail_gridview);
        if (refer_products.size() != 0){
            mDetailGridAdapter = new DetailGridAdapter(mContext, refer_products);
            mGridView.setAdapter(mDetailGridAdapter);
        }
        //设计师头像
        mDesignerIv = (CircleImageView) findViewById(R.id.detail_designer_image);
        Picasso.with(mContext).load(avatar_url).into(mDesignerIv);
        setValue();
    }

    /**
     * 对ViewPager的操作
     */
    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.detail_view_pager);
        final LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        mDetailPagerAdapter = new DetailPagerAdapter(mContext, cover_images);
        mViewPager.setAdapter(mDetailPagerAdapter);
        for (int i = 0; i < cover_images.size(); i++) {
            //动态加载灰色点
            ImageView grayIv = new ImageView(mContext);
            grayIv.setBackgroundResource(R.drawable.ic_point_unselected);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //从第二个开始有边距
            if (i > 0) {
                layoutParams.leftMargin = 20;   //注意单位是px
            }
            grayIv.setLayoutParams(layoutParams);
            ll.addView(grayIv);
        }
        final ImageView whiteIv = new ImageView(this);
        whiteIv.setImageResource(R.drawable.ic_point_selected);
        rl.addView(whiteIv);
        //任何一个组件都可以得到视图树
        whiteIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //视图完成绘制的时候调用
            @Override
            public void onGlobalLayout() {
                left = ll.getChildAt(1).getLeft() - ll.getChildAt(0).getLeft();
                System.out.println(left);
                //移除视图树的监听
                whiteIv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //导航页滑动的时候调用
            //positionOffset:滑动的百分比（[0,1]）
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) whiteIv.getLayoutParams();
                layoutParams.leftMargin = (int) (left * positionOffset + position * left);
                whiteIv.setLayoutParams(layoutParams);
            }
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 控件填值
     */
    private void setValue() {
        mDigestTv.setText(digest);
        mNameTv.setText(name);
        mDescTv.setText(desc);
        mCommentNum.setText(comment_num);
        mCommentsNumber.setText(comment_num);
        //设计师
        mDesignerName.setText(designerName);
        mDesignerLabel.setText(designerLabel);
        mDescriptionTv.setText(description);
    }

    /**
     * 点击事件
     * @param view Activity
     */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.detail_view_back://退出
                finish();
                break;
            case R.id.detail_item_face_dislike://哭脸
                Toast.makeText(DetailActivity.this, "真可怜~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.detail_item_face_like://笑脸
                Toast.makeText(DetailActivity.this, "谢谢喔~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.start_comment://进入评论界面
                Toast.makeText(DetailActivity.this, "评论开始", Toast.LENGTH_SHORT).show();
                break;
            case R.id.website://品牌官网
                Toast.makeText(DetailActivity.this, "品牌官网", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
