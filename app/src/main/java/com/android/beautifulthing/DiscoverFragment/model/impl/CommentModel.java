package com.android.beautifulthing.DiscoverFragment.model.impl;

import com.android.beautifulthing.DiscoverFragment.bean.CommentBean;
import com.android.beautifulthing.DiscoverFragment.http.HttpUtils;
import com.android.beautifulthing.DiscoverFragment.model.ICommentModel;
import com.android.beautifulthing.DiscoverFragment.presenter.ICommentPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ydy on 2016/9/10.
 */
public class CommentModel implements ICommentModel {

    @Override
    public void loadCommentsDatas(int product_id, final ICommentPresenter.Callback callback) {
        HttpUtils.init().getCommentsDatas(product_id).enqueue(new Callback<CommentBean>() {
            @Override
            public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                CommentBean.DataBean data = response.body().getData();
                callback.success(data);
            }

            @Override
            public void onFailure(Call<CommentBean> call, Throwable t) {

            }
        });
    }

}
