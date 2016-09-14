package com.android.beautifulthing.DiscoverFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.beautifulthing.DiscoverFragment.subfragment.AccessoryFragment;
import com.android.beautifulthing.DiscoverFragment.subfragment.BagFragment;
import com.android.beautifulthing.DiscoverFragment.subfragment.DailyFragment;
import com.android.beautifulthing.DiscoverFragment.subfragment.JewelryFragment;
import com.android.beautifulthing.DiscoverFragment.subfragment.MenFragment;
import com.android.beautifulthing.DiscoverFragment.subfragment.MylikeFragment;
import com.android.beautifulthing.DiscoverFragment.subfragment.OthersFragment;
import com.android.beautifulthing.DiscoverFragment.subfragment.ShoesFragment;
import com.android.beautifulthing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydy on 2016/9/5.
 */
public class DiscoverFragment extends Fragment {

    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private MyPagerAdapter mPagerAdapter;

    public static DiscoverFragment newInstance(){
        DiscoverFragment discoverFragment = new DiscoverFragment();
        return discoverFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery_view, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化DiscoverFragment视图
     */
    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.fragment_discovery_tablayout);
        mViewPager = (ViewPager) view.findViewById(R.id.fragment_discovery_viewpager);
        //设置TabLayout模式
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //数据源
        loadDatas();
        //适配器创建与绑定
        mPagerAdapter = new MyPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        //关联TabLayout和ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 加载数据，这里的数据就是subFragment
     */
    private void loadDatas() {
        titles.add("Daily");
        titles.add("首饰");
        titles.add("包袋");
        titles.add("鞋履");
        titles.add("Men");
        titles.add("配饰");
        titles.add("其他");
        fragments.add(DailyFragment.newInstance());
        fragments.add(JewelryFragment.newInstance());
        fragments.add(BagFragment.newInstance());
        fragments.add(ShoesFragment.newInstance());
        fragments.add(MenFragment.newInstance());
        fragments.add(AccessoryFragment.newInstance());
        fragments.add(OthersFragment.newInstance());
//        titles.add("我喜欢的");
//        fragments.add(MylikeFragment.newInstance());
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
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

}
