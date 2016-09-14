package com.android.beautifulthing.DesignerFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.beautifulthing.DesignerFragment.Adapter.DesignerDetilAdapter;
import com.android.beautifulthing.DesignerFragment.Url.url;
import com.android.beautifulthing.DesignerFragment.bean.DesignerDetilBean;
import com.android.beautifulthing.DesignerFragment.bean.DesignerShopBean;
import com.android.beautifulthing.DesignerFragment.designerDetilFragment.BuyFragment;
import com.android.beautifulthing.DesignerFragment.designerDetilFragment.ShopFragment;
import com.android.beautifulthing.DesignerFragment.designerDetilFragment.WroksFragment;
import com.android.beautifulthing.DesignerFragment.presenter.IDesignerPresent;
import com.android.beautifulthing.DesignerFragment.presenter.IDesignerShopPreseter;
import com.android.beautifulthing.DesignerFragment.presenter.impl.DesignerPresenter;
import com.android.beautifulthing.DesignerFragment.presenter.impl.DesignerShopPresnter;
import com.android.beautifulthing.DesignerFragment.view.IDesignerDetilView;
import com.android.beautifulthing.DesignerFragment.view.IDesignerShop2View;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/6
 */
public class DesignerDetilActivity extends AppCompatActivity implements IDesignerDetilView,IDesignerShop2View {

    private  DesignerDetilBean.DataBean dataBean;
    private IDesignerPresent iDesignerPresent;
    private int designer_id;
    private Context mContext;
    private DesignerDetilAdapter designerDetilAdapter;
    private List<String> introduce_images =new ArrayList<>();
    private int childCount;
    private List<Fragment>fragments = new ArrayList<>();
    private FragmentManager fragmentManager;
    private ViewPagerAdapter viewPagerAdapter;
    private IDesignerShopPreseter iDesignerShopPreseter;

    @BindView(R.id.activity_designerdetil_btn)
    Button btn;
    @BindView(R.id.activity_designerdetil_iv)
    ImageView imageView;
    @BindView(R.id.activity_designerdetil_name)
    TextView name;
    @BindView(R.id.activity_designerdetil_label)
    TextView label;
    @BindView(R.id.activity_designerdetil_cocept)
    TextView cocept;
    @BindView(R.id.activity_designerdetil_description)
    TextView description;
    @BindView(R.id.activity_designerdetil_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.activity_designerdetil_indicator)
    LinearLayout indicator;
    @BindView(R.id.activity_designerdetil_viewpager2)
    ViewPager mViewpager2;
    @BindView(R.id.activity_designerdetil_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.show_more)
    RelativeLayout mShowMore;
    @BindView(R.id.spread)
    ImageView mImageSpread;
    @BindView(R.id.shrink_up)
    ImageView mImageShrinkUp;

    private static final int VIDEO_CONTENT_DESC_MAX_LINE = 3;// 默认展示最大行数3行
    private static final int SHOW_CONTENT_NONE_STATE = 0;// 扩充
    private static final int SHRINK_UP_STATE = 1;// 收起状态
    private static final int SPREAD_STATE = 2;// 展开状态
    private static int mState = SHRINK_UP_STATE;//默认收起状态
    private List<DesignerShopBean.DataBean.ShopsBean> shops;
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designerdetil);
        mContext = this ;
        fragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        designer_id = intent.getIntExtra("designer_id",0);
        //
        iDesignerPresent = new DesignerPresenter(this);
        iDesignerPresent.getDesignerList(url.DESTIGNER_DETAILS_URL,designer_id+"");
        //旗舰、在线购买
        iDesignerShopPreseter = new DesignerShopPresnter(this);
        iDesignerShopPreseter.getDesignerShopList(url.DESTIGNER_DETAILS2_URL,designer_id+"");
        initView();
    }

    @Override
    public void refreshListView2(DesignerShopBean designerShopBean) {
        DesignerShopBean.DataBean data = designerShopBean.getData();
        shops = data.getShops();
        loadDatas();
    }

    private void initView() {
        ButterKnife.bind(this);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewpager2);
    }

    private void loadDatas() {
        if (shops.size() != 0) {
            mTabLayout.removeAllTabs();
            titles.add("作品");
            titles.add("旗舰门面");
            titles.add("线上购买");
            fragments.add(WroksFragment.newInstance(designer_id));
            fragments.add(new ShopFragment(designer_id));
            fragments.add(BuyFragment.newInstance(designer_id));
        } else {
            mTabLayout.removeAllTabs();
            titles.add("作品");
            titles.add("线上购买");
            fragments.add(WroksFragment.newInstance(designer_id));
            fragments.add(BuyFragment.newInstance(designer_id));
        }
        viewPagerAdapter = new ViewPagerAdapter(fragmentManager);
        mViewpager2.setAdapter(viewPagerAdapter);
    }

    public void click(View view) {
        switch (view.getId()){
            case R.id.show_more:
            {
                if (mState == SPREAD_STATE){
                    description.setMaxLines(VIDEO_CONTENT_DESC_MAX_LINE);
                    description.requestLayout();
                    mImageShrinkUp.setVisibility(View.GONE);
                    mImageSpread.setVisibility(View.VISIBLE);
                    mState = SHRINK_UP_STATE;
                }else if(mState == SHRINK_UP_STATE){
                    description.setMaxLines(Integer.MAX_VALUE);
                    description.requestLayout();
                    mImageShrinkUp.setVisibility(View.VISIBLE);
                    mImageSpread.setVisibility(View.GONE);
                    mState = SPREAD_STATE;
                }
                break;
            }
            case R.id.activity_designerdetil_back:
                finish();
                break;
            case R.id.activity_designerdetil_btn:
                Toast.makeText(DesignerDetilActivity.this, "123", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    @Override
    public void refreshListView(DesignerDetilBean designerDetilBean) {
        dataBean = designerDetilBean.getData();
        name.setText(dataBean.getName());
        label.setText(dataBean.getLabel());
        cocept.setText(dataBean.getConcept());
        description.setText(dataBean.getDescription());
        Picasso.with(mContext).load(dataBean.getAvatar_url()).into(imageView);
        introduce_images = dataBean.getIntroduce_images();
        designerDetilAdapter = new DesignerDetilAdapter(introduce_images,this);
        mViewPager.setAdapter(designerDetilAdapter);
        childCount = indicator.getChildCount();
        controlIndicator(0);
        designerDetilAdapter.notifyDataSetChanged();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                controlIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void controlIndicator(int index) {
       ImageView view = (ImageView) indicator.getChildAt(index);
        for (int i = 0; i <childCount ; i++) {
            ImageView childView = (ImageView) indicator.getChildAt(i);
            childView.setEnabled(false);
        }
        view.setEnabled(true);
    }

}
