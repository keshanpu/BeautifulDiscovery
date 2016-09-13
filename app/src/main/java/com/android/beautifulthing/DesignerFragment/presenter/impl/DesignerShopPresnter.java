package com.android.beautifulthing.DesignerFragment.presenter.impl;

import com.android.beautifulthing.DesignerFragment.bean.DesignerShopBean;
import com.android.beautifulthing.DesignerFragment.model.IDesignerShopModel;
import com.android.beautifulthing.DesignerFragment.model.impl.DesignerShopModel;
import com.android.beautifulthing.DesignerFragment.presenter.IDesignerShopPreseter;
import com.android.beautifulthing.DesignerFragment.view.IDesignerShop2View;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class DesignerShopPresnter implements IDesignerShopPreseter,IDesignerShopPreseter.CallBack3 {
    public DesignerShopPresnter(IDesignerShop2View iDesignerShop2View) {
        this.iDesignerShop2View = iDesignerShop2View;
    }

    IDesignerShop2View iDesignerShop2View;

    IDesignerShopModel iDesignerShopModel = new DesignerShopModel(this);
    @Override
    public void getDesignerShopList(String base_Path2, String url_path2) {
        iDesignerShopModel.queryDesignerShoplList(base_Path2, url_path2);
    }

    @Override
    public void success3(DesignerShopBean designerShopBean) {
        if (designerShopBean != null) {
            iDesignerShop2View.refreshListView2(designerShopBean);
        }
    }
}
