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
        IDesignerPresent.CallBack
       {
//    IDesignerWorkView iDesignerWorkView;
//    public DesignerPresenter(IDesignerWorkView iDesignerWorkView) {
//        this.iDesignerWorkView = iDesignerWorkView;
//    }

//    IDesignerShopView iDesignerShopView;
//
//    public DesignerPresenter(IDesignerShopView iDesignerShopView) {
//        this.iDesignerShopView = iDesignerShopView;
//    }

//    IDesignerDetilView iDesignerDetilView;
//
//    public DesignerPresenter(IDesignerDetilView iDesignerDetilView) {
//        this.iDesignerDetilView = iDesignerDetilView;
//
//    }


    IDesignerView iDesignerView;
    public DesignerPresenter(IDesignerView iDesignerView) {
        this.iDesignerView = iDesignerView;
    }



    @Override
    public void getDesignerList(int page, int size) {
        iDesignerModel.queryDesignerList(page, size);
    }

    IDesignerModel iDesignerModel = new DesignerModel(this);

    @Override
    public void success(DesignerBean designerBean) {
        if (designerBean != null) {
            iDesignerView.refreshListView(designerBean.getData().getDesigners());
        }
    }

//    @Override
//    public void getDesignerList(String base_Path, String url_path) {
//        iDesignerModel.queryDesignerDetilList(base_Path, url_path);
//    }

//    @Override
//    public void getDesignerShopList(String base_Path2, String url_path2) {
//        iDesignerModel.queryDesignerShoplList(base_Path2, url_path2);
//    }

//    @Override
//    public void getDesignerWrokList(String base_Path3, String url_path3) {
//        iDesignerModel.queryDesignerWorklList(base_Path3, url_path3);
//    }

//    @Override
//    public void success2(DesignerDetilBean designerDetilBean) {
//        if (designerDetilBean != null) {
//            iDesignerDetilView.refreshListView(designerDetilBean);
//        }
//    }

//    @Override
//    public void success3(DesignerShopBean designerShopBean) {
//        if (designerShopBean != null) {
//            iDesignerShopView.refreshListView(designerShopBean);
//        }
//    }

//    @Override
//    public void success4(DesignerWorksBean designerWorksBean) {
//        iDesignerWorkView.refreshListView(designerWorksBean);
//    }
}
