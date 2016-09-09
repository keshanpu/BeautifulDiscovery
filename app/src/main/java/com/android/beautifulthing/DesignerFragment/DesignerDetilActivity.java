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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.beautifulthing.DesignerFragment.Adapter.DesignerDetilAdapter;
import com.android.beautifulthing.DesignerFragment.bean.DesignerDetilBean;
import com.android.beautifulthing.DesignerFragment.designerDetilFragment.BuyFragment;
import com.android.beautifulthing.DesignerFragment.designerDetilFragment.ShopFragment;
import com.android.beautifulthing.DesignerFragment.designerDetilFragment.WroksFragment;
import com.android.beautifulthing.DesignerFragment.presenter.IDesignerPresent;
import com.android.beautifulthing.DesignerFragment.presenter.impl.DesignerPresenter;
import com.android.beautifulthing.DesignerFragment.url.url;
import com.android.beautifulthing.DesignerFragment.view.IDesignerDetilView;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class DesignerDetilActivity extends AppCompatActivity implements IDesignerDetilView{
//   private List<DesignerDetilBean.DataBean> designerDetilBeanList =new ArrayList<>();
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
//    @BindView(R.id.activity_designerdetil_radio_group)
//    RadioGroup mRadioGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designerdetil);
        mContext = this ;
        Intent intent =getIntent();
        designer_id = intent.getIntExtra("designer_id",0);
        Log.d("androidxx", "onCreate: "+designer_id);
        iDesignerPresent = new DesignerPresenter(this);
        iDesignerPresent.getDesignerList(url.DESTIGNER_DETAILS_URL,designer_id+"");
        fragmentManager = getSupportFragmentManager();
        initView();

    }

    private void loadDatas() {
        fragments.add(WroksFragment.newInstance());
        fragments.add(ShopFragment.newInstance());
        fragments.add(BuyFragment.newInstance());
        viewPagerAdapter = new ViewPagerAdapter(fragmentManager);
        mViewpager2.setAdapter(viewPagerAdapter);


    }

//    private void initListener() {
//        mViewpager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            switch (position){
//                case 0 :
//                    mRadioGroup.check(R.id.activity_designerdetil_bottom_2);
//                    break;
//                case 1:
//                    mRadioGroup.check(R.id.activity_designerdetil_bottom_3);
//                    break;
//                case 2 :
//                    mRadioGroup.check(R.id.activity_designerdetil_bottom_4);
//                    break;
//            }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }
//
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


    }



    private void initView() {
        ButterKnife.bind(this);
        loadDatas();
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewpager2);
            mTabLayout.getTabAt(0).setText("作品");
            mTabLayout.getTabAt(1).setText("旗舰门面");
            mTabLayout.getTabAt(2).setText("线上购买");

//        mRadioGroup.check(R.id.activity_designerdetil_bottom_2);
//        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.activity_designerdetil_bottom_2:
//                        mViewpager2.setCurrentItem(0);
//                        break;
//                    case R.id.activity_designerdetil_bottom_3:
//                        mViewpager2.setCurrentItem(1);
//                        break;
//                    case R.id.activity_designerdetil_bottom_4:
//                        mViewpager2.setCurrentItem(2);
//                        break;
//                }
//            }
//        });
    }

    @Override
    public void refreshListView(DesignerDetilBean designerDetilBean) {
//        designerDetilBeanList.add(designerDetilBean.getData());
        dataBean = designerDetilBean.getData();
//        Log.d("andoridxx", "refreshListView: "+designerDetilBeanList.toString());
//        name.setText(designerDetilBeanList.get(0).getName());
        Log.d("andoridxx", "refreshListView: "+dataBean.toString());
        name.setText(dataBean.getName());
        label.setText(dataBean.getLabel());
        cocept.setText(dataBean.getConcept());
        description.setText(dataBean.getDescription());
        Picasso.with(mContext).load(dataBean.getAvatar_url()).into(imageView);
        introduce_images = dataBean.getIntroduce_images();
        Log.d("===", "refreshListView: "+introduce_images);
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
