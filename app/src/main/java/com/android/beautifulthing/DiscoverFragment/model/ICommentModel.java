package com.android.beautifulthing.DiscoverFragment.model;

import com.android.beautifulthing.DiscoverFragment.presenter.ICommentPresenter;

/**
 * Created by ydy on 2016/9/10.
 */
public interface ICommentModel {

    void loadCommentsDatas(int product_id, ICommentPresenter.Callback callback);

}
