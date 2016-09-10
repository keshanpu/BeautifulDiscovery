package com.android.beautifulthing.DiscoverFragment.presenter.impl;

import com.android.beautifulthing.DiscoverFragment.bean.CategoryBean;
import com.android.beautifulthing.DiscoverFragment.bean.CommonBean;
import com.android.beautifulthing.DiscoverFragment.model.ICategoryModel;
import com.android.beautifulthing.DiscoverFragment.model.ICommonModel;
import com.android.beautifulthing.DiscoverFragment.model.impl.CategoryModel;
import com.android.beautifulthing.DiscoverFragment.model.impl.CommonModel;
import com.android.beautifulthing.DiscoverFragment.presenter.ICategoryPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.ICommonPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.IDetailPresenter;
import com.android.beautifulthing.DiscoverFragment.view.ICategoryView;
import com.android.beautifulthing.DiscoverFragment.view.ICommonView;

import java.util.List;

/**
 * Created by ydy on 2016/9/6.
 */
public class CategoryPresenter implements ICategoryPresenter, ICategoryPresenter.Callback{

    ICategoryModel categoryModel;
    ICategoryView  categoryView;

    public CategoryPresenter(ICategoryView categoryView) {
        categoryModel = new CategoryModel(this);
        this.categoryView = categoryView;
    }

    @Override
    public void getCategoryDatas() {
        categoryModel.loadCategoryDatas();
    }

    @Override
    public void success(CategoryBean.DataBean data) {
        categoryView.categoryDatas(data);
    }
}
