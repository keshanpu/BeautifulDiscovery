package com.android.beautifulthing.MagazineFragment.model;

import com.android.beautifulthing.MagazineFragment.presenter.IMagazineDetailActivityPresenter;

/**
 * Created by keshanpu on 16/9/11.
 */
public interface IMagazineDetailActivityModel {

//    http://design.zuimeia.com/api/v1/article/70

    void queryMagazineDetail(int id, IMagazineDetailActivityPresenter.Callback callback);

}
