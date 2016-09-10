package com.android.beautifulthing.DiscoverFragment.presenter;

import com.android.beautifulthing.DiscoverFragment.bean.CategoryBean;

import java.util.List;

/**
 * Created by ydy on 2016/9/6.
 */
public interface ICategoryPresenter {

    void getCategoryDatas();

    interface Callback{
        void success(CategoryBean.DataBean data);
    }

}
