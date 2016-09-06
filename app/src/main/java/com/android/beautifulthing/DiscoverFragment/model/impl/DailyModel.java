package com.android.beautifulthing.DiscoverFragment.model.impl;

import com.android.beautifulthing.DiscoverFragment.bean.DailyBean;
import com.android.beautifulthing.DiscoverFragment.http.HttpUtils;
import com.android.beautifulthing.DiscoverFragment.model.IDailyModel;
import com.android.beautifulthing.DiscoverFragment.presenter.IDailyPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ydy on 2016/9/6.
 */
public class DailyModel implements IDailyModel{

    private DailyBean dataBody;

    @Override
    public void loadDatas(String timestamp, final IDailyPresenter.Callback callback) {

        HttpUtils.init().getDailyDatas(timestamp).enqueue(new Callback<DailyBean>() {

            @Override
            public void onResponse(Call<DailyBean> call, Response<DailyBean> response) {
                dataBody = response.body();
                callback.success(dataBody.getData().getProducts());
            }

            @Override
            public void onFailure(Call<DailyBean> call, Throwable t) {

            }
        });
    }


}
