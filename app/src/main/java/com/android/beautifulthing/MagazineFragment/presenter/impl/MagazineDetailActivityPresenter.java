package com.android.beautifulthing.MagazineFragment.presenter.impl;

import com.android.beautifulthing.MagazineFragment.bean.MagazineDetailBean;
import com.android.beautifulthing.MagazineFragment.model.IMagazineDetailActivityModel;
import com.android.beautifulthing.MagazineFragment.model.impl.MagazineDetailActivityModel;
import com.android.beautifulthing.MagazineFragment.presenter.IMagazineDetailActivityPresenter;
import com.android.beautifulthing.MagazineFragment.view.IMagazineDetailActivityView;

/**
 * Created by keshanpu on 16/9/11.
 */
public class MagazineDetailActivityPresenter implements IMagazineDetailActivityPresenter, IMagazineDetailActivityPresenter.Callback {

    private IMagazineDetailActivityModel iMagazineDetailActivityModel;
    private IMagazineDetailActivityView iMagazineDetailActivityView;


    public MagazineDetailActivityPresenter(IMagazineDetailActivityView iMagazineDetailActivityView){
        this.iMagazineDetailActivityModel = new MagazineDetailActivityModel();
        this.iMagazineDetailActivityView = iMagazineDetailActivityView;
    }
    @Override
    public void getMagazineDetail(int id) {
        iMagazineDetailActivityModel.queryMagazineDetail(id, this);
    }

    @Override
    public void success(MagazineDetailBean magazineDetailBean) {
        iMagazineDetailActivityView.refreshMagazineDetail(magazineDetailBean.getData());
    }

    @Override
    public void failed(int result) {
        iMagazineDetailActivityView.refreshDataFailed(result);
    }
}
