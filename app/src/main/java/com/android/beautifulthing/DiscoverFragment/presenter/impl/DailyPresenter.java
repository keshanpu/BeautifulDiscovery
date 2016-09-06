package com.android.beautifulthing.DiscoverFragment.presenter.impl;

import com.android.beautifulthing.DiscoverFragment.bean.DailyBean;
import com.android.beautifulthing.DiscoverFragment.model.IDailyModel;
import com.android.beautifulthing.DiscoverFragment.model.impl.DailyModel;
import com.android.beautifulthing.DiscoverFragment.presenter.IDailyPresenter;
import com.android.beautifulthing.DiscoverFragment.view.IDailyView;

import java.util.List;

/**
 * Created by ydy on 2016/9/6.
 */
public class DailyPresenter implements IDailyPresenter, IDailyPresenter.Callback {

    IDailyModel dailyModel = new DailyModel();
    IDailyView dailyView;

    @Override
    public void getDatas(String timestamp) {
        dailyModel.loadDatas(timestamp, this);
    }

    public DailyPresenter(IDailyView dailyView) {
        this.dailyView = dailyView;
    }


    @Override
    public void success(List<DailyBean.DataBean.ProductsBean> productsBeanList) {
        dailyView.dataResult(productsBeanList);
    }
}
