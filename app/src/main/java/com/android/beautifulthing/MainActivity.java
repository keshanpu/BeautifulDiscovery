package com.android.beautifulthing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.android.beautifulthing.CommonActivity.bean.ApkUpdateBean;
import com.android.beautifulthing.CommonActivity.presenter.IUpdatePresenter;
import com.android.beautifulthing.CommonActivity.presenter.impl.UpdatePresenter;
import com.android.beautifulthing.CommonActivity.tools.ApkDownLoad;
import com.android.beautifulthing.CommonActivity.view.IUpdateView;
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
public class MainActivity extends AppCompatActivity implements IUpdateView{

    private MagazineFragment mMagazineFragment;
    private DiscoverFragment mDiscoverFragment;
    private DesignerFragment mDesignerFragment;
    private MineFragment mMineFragment;
    private Context mContext;
    private List<Fragment> fragmentList = new ArrayList<>();
    private RadioGroup mRadioGroup;
    private Fragment mCurrentFragment;
    private FragmentManager supportFragmentManager;
    private IUpdatePresenter updatePresenter;
    private String apk_url;
    private String update_desc;
    public int last_ver_code;
    public int  CODE_VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        supportFragmentManager = getSupportFragmentManager();
        updatePresenter = new UpdatePresenter(this);
        updatePresenter.getUpdateDatas();
        //初始化Fragment对象
        initFragment();
        //初始化数据源
        initDatas();
        //初始化视图
        initView();
    }

    /**
     * apk更新提示
     */
    private void apkUpdate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("更新提示");
        builder.setMessage(update_desc);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ApkDownLoad downLoad = new ApkDownLoad(mContext);
                downLoad.apkDownload(apk_url);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        // 将builder对象转化为AlertDialog对象
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void updateResult(ApkUpdateBean.DataBean data) {
        update_desc = data.getUpdate_desc();
        apk_url = data.getApk_url();
        last_ver_code = data.getLast_ver_code();
        if (CODE_VERSION < last_ver_code){
            //apk更新提示
            apkUpdate();
            CODE_VERSION = last_ver_code;
        }

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

    /**
     * Fragment控制
     */
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
