package com.android.beautifulthing.CommonActivity.presenter;

import com.android.beautifulthing.CommonActivity.bean.ApkUpdateBean;

/**
 * Created by ydy on 2016/9/18.
 */
public interface IUpdatePresenter {

    void getUpdateDatas();

    interface Callback {
        void success(ApkUpdateBean.DataBean data);
    }

}
