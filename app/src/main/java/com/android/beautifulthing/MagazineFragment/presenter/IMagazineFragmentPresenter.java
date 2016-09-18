package com.android.beautifulthing.MagazineFragment.presenter;

import com.android.beautifulthing.MagazineFragment.bean.MagazineBean;

/**
 * Created by keshanpu on 16/9/6.
 */
public interface IMagazineFragmentPresenter {

    void getMagazine(int page, int pagesize);

    interface Callback{

        void success(MagazineBean magazineBean);
        void dofailed(int result) throws InterruptedException;
    }
}
