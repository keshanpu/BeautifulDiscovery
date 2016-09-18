package com.android.beautifulthing.MagazineFragment.model;

import android.app.ProgressDialog;

import com.android.beautifulthing.MagazineFragment.presenter.IMagazineFragmentPresenter;

/**
 * Created by keshanpu on 16/9/6.
 */
public interface IMagazineFragmentModel {

    void queryMagazine(int page, int pagesize, IMagazineFragmentPresenter.Callback callback);
}
