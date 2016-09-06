package com.android.beautifulthing.DiscoverFragment.model;

import com.android.beautifulthing.DiscoverFragment.presenter.IDailyPresenter;

/**
 * Created by ydy on 2016/9/6.
 */
public interface IDailyModel {

    void loadDatas(String timestamp, IDailyPresenter.Callback callback);

}
