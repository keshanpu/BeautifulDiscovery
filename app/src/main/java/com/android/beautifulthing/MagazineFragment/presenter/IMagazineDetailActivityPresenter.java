package com.android.beautifulthing.MagazineFragment.presenter;

import com.android.beautifulthing.MagazineFragment.bean.MagazineDetailBean;

/**
 * Created by keshanpu on 16/9/11.
 */
public interface IMagazineDetailActivityPresenter {
    void getMagazineDetail(int id);

    interface Callback{
        void success(MagazineDetailBean magazineDetailBean);
        void failed(int result);
    }
}
