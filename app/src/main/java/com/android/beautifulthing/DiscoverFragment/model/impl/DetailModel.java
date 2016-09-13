package com.android.beautifulthing.DiscoverFragment.model.impl;

import android.util.Log;

import com.android.beautifulthing.DiscoverFragment.bean.DetailBean;
import com.android.beautifulthing.DiscoverFragment.http.HttpUtils;
import com.android.beautifulthing.DiscoverFragment.model.IDetailModel;
import com.android.beautifulthing.DiscoverFragment.presenter.IDetailPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ydy on 2016/9/7.
 */
public class DetailModel implements IDetailModel{

    @Override
    public void loadDetailDatas(int product_id, final IDetailPresenter.Callback callback) {

        HttpUtils.init().getDetailDatas(product_id).enqueue(new Callback<DetailBean>() {

            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {
                DetailBean body = response.body();
                callback.success(body);
            }

            @Override
            public void onFailure(Call<DetailBean> call, Throwable t) {

            }
        });
    }

}
