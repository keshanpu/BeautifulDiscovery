package com.android.beautifulthing.DiscoverFragment.subfragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.beautifulthing.DiscoverFragment.adapter.CommonAdapter;
import com.android.beautifulthing.DiscoverFragment.bean.CommonBean;
import com.android.beautifulthing.DiscoverFragment.presenter.ICommonPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.impl.CommonPresenter;
import com.android.beautifulthing.DiscoverFragment.view.ICommonView;
import com.android.beautifulthing.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydy on 2016/9/6.
 * SubFragment---Men
 */
public class MenFragment extends Fragment implements ICommonView{

    public static final int CATEGORY_TAG = 65;
    private int PAGER = 1;
    private int PAGER_SIZE = 20;

    private Context mContext;
    private PullToRefreshListView mRefreshListView;
    private ICommonPresenter commonPresenter;
    private List<CommonBean.DataBean.ProductsBean> products = new ArrayList<>();
    private CommonAdapter mCommonAdapter;
    private ProgressDialog mProgressDialog;

    public static MenFragment newInstance(){
        return new MenFragment();
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mRefreshListView.onRefreshComplete();
            mCommonAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("努力加载中...");
        mProgressDialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subfragment_daily_main_view, container, false);
        mRefreshListView = (PullToRefreshListView) view.findViewById(R.id.subfragment_daily_refresh_listview);
        //数据源
        commonPresenter = new CommonPresenter(this);
        commonPresenter.getDatas(CATEGORY_TAG, PAGER, PAGER_SIZE);
        return view;
    }

    @Override
    public void dataResult(final CommonBean.DataBean dataBean) {
        products.addAll(dataBean.getProducts());
        mProgressDialog.dismiss();
        mCommonAdapter = new CommonAdapter(mContext, products);
        mRefreshListView.setAdapter(mCommonAdapter);
        //刷新事件
        mRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                products.clear();
                commonPresenter.getDatas(CATEGORY_TAG, PAGER, PAGER_SIZE);
                mHandler.sendEmptyMessageDelayed(1,1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (dataBean.getHas_next() != 0){
                    PAGER += 1;
                    commonPresenter.getDatas(CATEGORY_TAG, PAGER, PAGER_SIZE);
                } else {
                    Toast.makeText(mContext, "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
                mHandler.sendEmptyMessageDelayed(1,1000);
            }
        });
    }
}
