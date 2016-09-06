package com.android.beautifulthing.DiscoverFragment.subfragment;

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

import com.android.beautifulthing.DiscoverFragment.adapter.DailyAdapter;
import com.android.beautifulthing.DiscoverFragment.bean.DailyBean;
import com.android.beautifulthing.DiscoverFragment.presenter.IDailyPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.impl.DailyPresenter;
import com.android.beautifulthing.DiscoverFragment.view.IDailyView;
import com.android.beautifulthing.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by ydy on 2016/9/6.
 * SubFragment---Daily
 */
public class DailyFragment extends Fragment implements IDailyView{

    private Context mContext;
    private PullToRefreshListView mRefreshListView;

    private String TIMESTAMP = "1472918400000";
    private IDailyPresenter dailyPresenter;
    private DailyAdapter mDailyAdapter;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    public static DailyFragment newInstance(){
        DailyFragment dailyFragment = new DailyFragment();
        return dailyFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subfragment_daily_view, container, false);
        mRefreshListView = (PullToRefreshListView) view.findViewById(R.id.subfragment_daily_refresh_listview);
        //数据源
        dailyPresenter = new DailyPresenter(this);
        dailyPresenter.getDatas(TIMESTAMP);
        return view;
    }

    @Override
    public void dataResult(List<DailyBean.DataBean.ProductsBean> productsBeanList) {
        mDailyAdapter = new DailyAdapter(mContext, productsBeanList);
        mRefreshListView.setAdapter(mDailyAdapter);
        //刷新事件
        mRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }
}
