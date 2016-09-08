package com.android.beautifulthing.DiscoverFragment.presenter.impl;

import com.android.beautifulthing.DiscoverFragment.bean.CommonBean;
import com.android.beautifulthing.DiscoverFragment.model.ICommonModel;
import com.android.beautifulthing.DiscoverFragment.model.impl.CommonModel;
import com.android.beautifulthing.DiscoverFragment.presenter.ICommonPresenter;
import com.android.beautifulthing.DiscoverFragment.view.ICommonView;

/**
 * Created by ydy on 2016/9/6.
 */
public class CommonPresenter implements ICommonPresenter, ICommonPresenter.Callback{

    ICommonModel commonModel = new CommonModel();
    ICommonView commonView;

    public CommonPresenter(ICommonView jewelryView) {
        this.commonView = jewelryView;
    }

    @Override
    public void getDatas(int tag, int page, int page_size) {
        commonModel.loadDatas(tag, page, page_size, this);
    }

    @Override
    public void success(CommonBean.DataBean dataBean) {
        commonView.dataResult(dataBean);
    }
}
