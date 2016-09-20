package com.android.beautifulthing.DesignerFragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.beautifulthing.DesignerFragment.Adapter.DesignerAdapter;
import com.android.beautifulthing.DesignerFragment.bean.DesignerBean;
import com.android.beautifulthing.DesignerFragment.presenter.IDesignerPresent;
import com.android.beautifulthing.DesignerFragment.presenter.impl.DesignerPresenter;
import com.android.beautifulthing.DesignerFragment.view.IDesignerView;
import com.android.beautifulthing.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydy on 2016/9/5
 */
public class DesignerFragment extends Fragment implements IDesignerView {

    private Context mContext;
    private IDesignerPresent iDesignerPresent;

    private List<DesignerBean.DataBean.DesignersBean>designersBeanLists = new ArrayList<>();
    private PullToRefreshListView mPullToRefresh;

    private int page = 1;
    private DesignerAdapter designerAdapter;
    private ProgressDialog dialog;

    public static DesignerFragment newInstance(){
        DesignerFragment designerFragment = new DesignerFragment();
        return designerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        iDesignerPresent = new DesignerPresenter(this);
        dialog = new ProgressDialog(mContext);
        dialog.setMessage("正在加载...");
        dialog.show();
        iDesignerPresent.getDesignerList(page,30);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_designer_main,container,false);
        mPullToRefresh = (PullToRefreshListView) view.findViewById(R.id.fragment_designer_main_mrefreshlv);
        mPullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        initListener();
        designerAdapter = new DesignerAdapter(mContext,designersBeanLists);
        mPullToRefresh.setAdapter(designerAdapter);
        return view;//
    }

    /**
     * 下拉刷新
     */
    private void initListener() {
        mPullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                initDatas();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                initDatas();
            }
        });
    }
    private void initDatas() {
        iDesignerPresent.getDesignerList(page,30);
    }

    @Override
    public void refreshListView(List<DesignerBean.DataBean.DesignersBean> designersBeanList) {
        designersBeanLists.addAll(designersBeanList);
        designerAdapter.notifyDataSetChanged();
        if (dialog != null)
        {
            dialog.dismiss();
        }
    }

}
