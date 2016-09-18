package com.android.beautifulthing.CommonActivity.model;

import com.android.beautifulthing.CommonActivity.presenter.IUpdatePresenter;

/**
 * Created by ydy on 2016/9/18.
 */
public interface IUpdateModel {

    void loadDatas(IUpdatePresenter.Callback callback);

}
