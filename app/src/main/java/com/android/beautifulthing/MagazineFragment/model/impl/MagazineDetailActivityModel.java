package com.android.beautifulthing.MagazineFragment.model.impl;

import com.android.beautifulthing.MagazineFragment.Tools.http.HttpUtils;
import com.android.beautifulthing.MagazineFragment.bean.MagazineDetailBean;
import com.android.beautifulthing.MagazineFragment.model.IMagazineDetailActivityModel;
import com.android.beautifulthing.MagazineFragment.presenter.IMagazineDetailActivityPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by keshanpu on 16/9/11.
 */
public class MagazineDetailActivityModel implements IMagazineDetailActivityModel {

    @Override
    public void queryMagazineDetail(int id, final IMagazineDetailActivityPresenter.Callback callback) {

        HttpUtils.newInstance().queryMagazineDetail(id).enqueue(new Callback<MagazineDetailBean>() {
            @Override
            public void onResponse(Call<MagazineDetailBean> call, Response<MagazineDetailBean> response) {
                MagazineDetailBean magazineDetailBean = response.body();
                callback.success(magazineDetailBean);
            }

            @Override
            public void onFailure(Call<MagazineDetailBean> call, Throwable t) {
//                t.printStackTrace();
                callback.failed(1000);
            }
        });

    }
}
