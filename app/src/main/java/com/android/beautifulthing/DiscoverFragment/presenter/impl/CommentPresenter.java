package com.android.beautifulthing.DiscoverFragment.presenter.impl;

import com.android.beautifulthing.DiscoverFragment.bean.CommentBean;
import com.android.beautifulthing.DiscoverFragment.model.ICommentModel;
import com.android.beautifulthing.DiscoverFragment.model.impl.CommentModel;
import com.android.beautifulthing.DiscoverFragment.presenter.ICommentPresenter;
import com.android.beautifulthing.DiscoverFragment.view.ICommentView;

/**
 * Created by ydy on 2016/9/10.
 */
public class CommentPresenter implements ICommentPresenter, ICommentPresenter.Callback{

    ICommentModel commentModel = new CommentModel();
    ICommentView  commentView;

    public CommentPresenter(ICommentView commentView) {
        this.commentView = commentView;
    }

    @Override
    public void getCommentDatas(int product_id) {
        commentModel.loadCommentsDatas(product_id, this);
    }

    @Override
    public void success(CommentBean.DataBean data) {
        commentView.backCommentData(data);
    }
}
