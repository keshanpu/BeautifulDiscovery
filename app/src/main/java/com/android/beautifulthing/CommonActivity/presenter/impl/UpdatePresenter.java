package com.android.beautifulthing.CommonActivity.presenter.impl;

import com.android.beautifulthing.CommonActivity.bean.ApkUpdateBean;
import com.android.beautifulthing.CommonActivity.model.IUpdateModel;
import com.android.beautifulthing.CommonActivity.model.impl.UpdateModel;
import com.android.beautifulthing.CommonActivity.presenter.IUpdatePresenter;
import com.android.beautifulthing.CommonActivity.view.IUpdateView;

/**
 * Created by ydy on 2016/9/18.
 */
public class UpdatePresenter implements IUpdatePresenter,IUpdatePresenter.Callback{

    IUpdateModel updateModel = new UpdateModel();
    IUpdateView  updateView;

    public UpdatePresenter(IUpdateView updateView) {
        this.updateView = updateView;
    }

    @Override
    public void getUpdateDatas() {
        updateModel.loadDatas(this);
    }

    @Override
    public void success(ApkUpdateBean.DataBean data) {
        updateView.updateResult(data);
    }
}
