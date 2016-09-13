package com.android.beautifulthing.DiscoverFragment.presenter.impl;

import com.android.beautifulthing.DiscoverFragment.bean.DetailBean;
import com.android.beautifulthing.DiscoverFragment.model.IDetailModel;
import com.android.beautifulthing.DiscoverFragment.model.impl.DetailModel;
import com.android.beautifulthing.DiscoverFragment.presenter.IDailyPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.IDetailPresenter;
import com.android.beautifulthing.DiscoverFragment.view.IDetailView;

/**
 * Created by ydy on 2016/9/7.
 */
public class DetailPresenter implements IDetailPresenter, IDetailPresenter.Callback{

    IDetailModel detailModel = new DetailModel();
    IDetailView detailView;

    public DetailPresenter(IDetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void getDetailDatas(int product_id) {
        detailModel.loadDetailDatas(product_id, this);
    }

    @Override
    public void success(DetailBean body) {
        detailView.detailDataResult(body);
    }
}
