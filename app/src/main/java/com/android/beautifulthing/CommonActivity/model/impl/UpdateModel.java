package com.android.beautifulthing.CommonActivity.model.impl;

import com.android.beautifulthing.CommonActivity.bean.ApkUpdateBean;
import com.android.beautifulthing.CommonActivity.http.UpdateUtils;
import com.android.beautifulthing.CommonActivity.model.IUpdateModel;
import com.android.beautifulthing.CommonActivity.presenter.IUpdatePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ydy on 2016/9/18.
 */
public class UpdateModel implements IUpdateModel{

    @Override
    public void loadDatas(final IUpdatePresenter.Callback callback) {
        UpdateUtils.init().getUpdateMsg().enqueue(new Callback<ApkUpdateBean>() {
            @Override
            public void onResponse(Call<ApkUpdateBean> call, Response<ApkUpdateBean> response) {
                ApkUpdateBean.DataBean data = response.body().getData();
                callback.success(data);
            }

            @Override
            public void onFailure(Call<ApkUpdateBean> call, Throwable t) {

            }
        });
    }

}
