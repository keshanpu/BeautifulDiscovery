package com.android.beautifulthing.DiscoverFragment.model.impl;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.beautifulthing.DiscoverFragment.bean.CategoryBean;
import com.android.beautifulthing.DiscoverFragment.bean.CommonBean;
import com.android.beautifulthing.DiscoverFragment.http.HttpUtils;
import com.android.beautifulthing.DiscoverFragment.model.ICategoryModel;
import com.android.beautifulthing.DiscoverFragment.model.ICommonModel;
import com.android.beautifulthing.DiscoverFragment.presenter.ICategoryPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.ICommonPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ydy on 2016/9/6.
 */
public class CategoryModel implements ICategoryModel {

    private ICategoryPresenter.Callback callback;
    private CategoryBean.DataBean data;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            callback.success(data);
        }
    };

    public CategoryModel(ICategoryPresenter.Callback callback) {
        this.callback = callback;
    }

    @Override
    public void loadCategoryDatas() {
        HttpUtils.init().getCategoryDatas().enqueue(new Callback<CategoryBean>() {

            @Override
            public void onResponse(Call<CategoryBean> call, Response<CategoryBean> response) {
                data = response.body().getData();
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(Call<CategoryBean> call, Throwable t) {

            }
        });
    }
}
