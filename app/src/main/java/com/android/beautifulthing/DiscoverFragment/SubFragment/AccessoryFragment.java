package com.android.beautifulthing.DiscoverFragment.subfragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.beautifulthing.DiscoverFragment.adapter.CommonAdapter;
import com.android.beautifulthing.DiscoverFragment.bean.CommonBean;
import com.android.beautifulthing.DiscoverFragment.presenter.ICommonPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.impl.CommonPresenter;
import com.android.beautifulthing.DiscoverFragment.view.ICommonView;
import com.android.beautifulthing.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydy on 2016/9/6.
 * SubFragment---配饰
 */
public class AccessoryFragment extends Fragment implements ICommonView{

    public static final int CATEGORY_TAG = 4;
    private int PAGER = 1;
    private int PAGER_SIZE = 20;

    private Context mContext;
    private PullToRefreshListView mRefreshListView;
    private ICommonPresenter commonPresenter;
    private List<CommonBean.DataBean.ProductsBean> products = new ArrayList<>();
    private CommonAdapter mCommonAdapter;

    public static AccessoryFragment newInstance(){
        AccessoryFragment accessoryFragment = new AccessoryFragment();
        return accessoryFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subfragment_common_main_view, container, false);
        mRefreshListView = (PullToRefreshListView) view.findViewById(R.id.subfragment_common_refresh_listview);
        //数据源
        commonPresenter = new CommonPresenter(this);
        commonPresenter.getDatas(CATEGORY_TAG, PAGER, PAGER_SIZE);
        return view;
    }

    @Override
    public void dataResult(CommonBean.DataBean dataBean) {
        products.addAll(dataBean.getProducts());
        mCommonAdapter = new CommonAdapter(mContext, products);
        mRefreshListView.setAdapter(mCommonAdapter);
    }
}
