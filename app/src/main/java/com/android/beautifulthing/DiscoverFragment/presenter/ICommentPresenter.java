package com.android.beautifulthing.DiscoverFragment.presenter;

import com.android.beautifulthing.DiscoverFragment.bean.CommentBean;

/**
 * Created by ydy on 2016/9/10.
 */
public interface ICommentPresenter {

    void getCommentDatas(int product_id);

    interface Callback {
        void success(CommentBean.DataBean data);
    }

}
