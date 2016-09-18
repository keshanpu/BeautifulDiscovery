package com.android.beautifulthing.MagazineFragment.presenter.impl;

import com.android.beautifulthing.MagazineFragment.bean.MagazineBean;
import com.android.beautifulthing.MagazineFragment.model.IMagazineFragmentModel;
import com.android.beautifulthing.MagazineFragment.model.impl.MagazineFragmentModel;
import com.android.beautifulthing.MagazineFragment.presenter.IMagazineFragmentPresenter;
import com.android.beautifulthing.MagazineFragment.view.IMagazineFragmentView;

/**
 * Created by keshanpu on 16/9/6.
 */
public class MagazineFragmentPresenter implements IMagazineFragmentPresenter ,IMagazineFragmentPresenter.Callback{

    private IMagazineFragmentModel iMagazineFragmentModel;
    private IMagazineFragmentView iMagazineFragmentView;

    public MagazineFragmentPresenter(IMagazineFragmentView iMagazineFragmentView) {

        this.iMagazineFragmentModel = new MagazineFragmentModel();
        this.iMagazineFragmentView = iMagazineFragmentView;
    }

    @Override
    public void getMagazine(int page, int pagesize) {
        iMagazineFragmentModel.queryMagazine(page,pagesize,this);

    }

    @Override
    public void success(MagazineBean magazineBean) {
        iMagazineFragmentView.refreshMagazine(magazineBean.getData().getArticles());

    }

    @Override
    public void dofailed(int result) throws InterruptedException {
        iMagazineFragmentView.refreshDataFailed(result);
    }
}
