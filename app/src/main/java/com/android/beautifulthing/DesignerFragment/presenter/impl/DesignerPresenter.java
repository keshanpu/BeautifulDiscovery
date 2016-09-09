package com.android.beautifulthing.DesignerFragment.presenter.impl;

import com.android.beautifulthing.DesignerFragment.bean.DesignerBean;
import com.android.beautifulthing.DesignerFragment.bean.DesignerDetilBean;
import com.android.beautifulthing.DesignerFragment.model.IDesignerModel;
import com.android.beautifulthing.DesignerFragment.model.impl.DesignerModel;
import com.android.beautifulthing.DesignerFragment.presenter.IDesignerPresent;
import com.android.beautifulthing.DesignerFragment.view.IDesignerDetilView;
import com.android.beautifulthing.DesignerFragment.view.IDesignerView;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class DesignerPresenter implements IDesignerPresent,IDesignerPresent.CallBack,IDesignerPresent.CallBack2{
    IDesignerModel iDesignerModel = new DesignerModel(this);
    IDesignerView iDesignerView;
    IDesignerDetilView  iDesignerDetilView;

    public DesignerPresenter(IDesignerDetilView iDesignerDetilView) {
        this.iDesignerDetilView = iDesignerDetilView;
    }

    public DesignerPresenter(IDesignerView iDesignerView) {
        this.iDesignerView = iDesignerView;
    }

    @Override
    public void getDesignerList(int page ,int size) {
        iDesignerModel.queryDesignerList(page,size);
    }

    @Override
    public void success(DesignerBean designerBean) {
        if (designerBean != null) {
            iDesignerView.refreshListView(designerBean.getData().getDesigners());
        }
    }

    @Override
    public void getDesignerList(String base_Path, String url_path) {
        iDesignerModel.queryDesignerDetilList(base_Path,url_path);
    }

    @Override
    public void success2(DesignerDetilBean designerDetilBean) {
        if (designerDetilBean != null) {
            iDesignerDetilView.refreshListView(designerDetilBean);
        }
    }
}
