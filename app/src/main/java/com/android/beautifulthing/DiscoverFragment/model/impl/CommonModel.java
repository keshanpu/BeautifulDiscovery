package com.android.beautifulthing.DiscoverFragment.model.impl;

import com.android.beautifulthing.DiscoverFragment.bean.CommonBean;
import com.android.beautifulthing.DiscoverFragment.http.HttpUtils;
import com.android.beautifulthing.DiscoverFragment.model.ICommonModel;
import com.android.beautifulthing.DiscoverFragment.presenter.ICommonPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ydy on 2016/9/6.
 */
public class CommonModel implements ICommonModel {

    @Override
    public void loadDatas(int tag, int page, int page_size, final ICommonPresenter.Callback callback) {
        HttpUtils.init().getCommonDatas(tag, page, page_size).enqueue(new Callback<CommonBean>() {
            @Override
            public void onResponse(Call<CommonBean> call, Response<CommonBean> response) {
                CommonBean.DataBean data = response.body().getData();
                callback.success(data);
            }

            @Override
            public void onFailure(Call<CommonBean> call, Throwable t) {

            }
        });
    }
}
