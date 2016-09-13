package com.android.beautifulthing.DiscoverFragment.model;

import com.android.beautifulthing.DiscoverFragment.presenter.IDailyPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.IDetailPresenter;

/**
 * Created by ydy on 2016/9/6.
 */
public interface IDetailModel {

    void loadDetailDatas(int product_id, IDetailPresenter.Callback callback);

}
