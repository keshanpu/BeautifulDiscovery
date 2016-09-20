package com.android.beautifulthing.DesignerFragment.presenter.impl;

import com.android.beautifulthing.DesignerFragment.bean.DesignerBean;
import com.android.beautifulthing.DesignerFragment.model.IDesignerModel;
import com.android.beautifulthing.DesignerFragment.model.impl.DesignerModel;
import com.android.beautifulthing.DesignerFragment.presenter.IDesignerPresent;
import com.android.beautifulthing.DesignerFragment.view.IDesignerView;

/**
 * Created by Administrator on 2016/9/6
 */
public class DesignerPresenter implements IDesignerPresent,
        IDesignerPresent.CallBack {

   IDesignerModel iDesignerModel;
   IDesignerView iDesignerView;

    public DesignerPresenter(IDesignerView iDesignerView) {
        iDesignerModel = new DesignerModel(this);
        this.iDesignerView = iDesignerView;
    }

    @Override
    public void getDesignerList(int page, int size) {
        iDesignerModel.queryDesignerList(page, size);
    }

    @Override
    public void success(DesignerBean designerBean) {
        if (designerBean != null) {
            iDesignerView.refreshListView(designerBean.getData().getDesigners());
        }
    }
}
