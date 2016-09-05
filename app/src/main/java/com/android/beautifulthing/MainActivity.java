package com.android.beautifulthing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.android.beautifulthing.DesignerFragment.DesignerFragment;
import com.android.beautifulthing.DiscoverFragment.DiscoverFragment;
import com.android.beautifulthing.MagazineFragment.MagazineFragment;
import com.android.beautifulthing.MineFragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydy on 2016/9/5.
 * The main view of beautiful discovery
 */
public class MainActivity extends AppCompatActivity {

    private MagazineFragment mMagazineFragment;
    private DiscoverFragment mDiscoverFragment;
    private DesignerFragment mDesignerFragment;
    private MineFragment mMineFragment;
    private List<Fragment> fragmentList = new ArrayList<>();
    private RadioGroup mRadioGroup;
    private Fragment mCurrentFragment;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportFragmentManager = getSupportFragmentManager();
        initFragment();
        initDatas();
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_bottom_radio_group);
        //默认选择项
        mRadioGroup.check(R.id.main_bottom_magazine_rb);
        controlFragment(mMagazineFragment);
        //选择监听
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_bottom_magazine_rb:
                        controlFragment(mMagazineFragment);
                        break;
                    case R.id.main_bottom_discover_rb:
                        controlFragment(mDiscoverFragment);
                        break;
                    case R.id.main_bottom_designer_rb:
                        controlFragment(mDesignerFragment);
                        break;
                    case R.id.main_bottom_mine_rb:
                        controlFragment(mMineFragment);
                        break;
                }
            }
        });

    }

    private void controlFragment(Fragment newFragment){
        //1、
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        //2、
        if (mCurrentFragment != null && mCurrentFragment.isAdded()) {
            fragmentTransaction.hide(mCurrentFragment);
        }
        if (! newFragment.isAdded()) {
            fragmentTransaction.add(R.id.main_fragment_layout, newFragment);
        } else {
            fragmentTransaction.show(newFragment);
        }
        //3、
        fragmentTransaction.commit();
        mCurrentFragment = newFragment;
    }

    /**
     * 初始化数据源，这里就是这四个Fragment
     */
    private void initDatas() {
        fragmentList.add(mMagazineFragment);
        fragmentList.add(mDiscoverFragment);
        fragmentList.add(mDesignerFragment);
        fragmentList.add(mMineFragment);
    }

    /**
     * 初始化Fragment对象
     */
    private void initFragment() {
        mMagazineFragment = MagazineFragment.newInstance();
        mDiscoverFragment = DiscoverFragment.newInstance();
        mDesignerFragment = DesignerFragment.newInstance();
        mMineFragment = MineFragment.newInstance();
        mCurrentFragment = mMagazineFragment;
    }


}
