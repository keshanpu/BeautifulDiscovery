package com.android.beautifulthing.DiscoverFragment.model;

import com.android.beautifulthing.DiscoverFragment.presenter.ICommonPresenter;

/**
 * Created by ydy on 2016/9/6.
 */
public interface ICommonModel {

    void loadDatas(int tag, int page, int page_size, ICommonPresenter.Callback callback);

}
