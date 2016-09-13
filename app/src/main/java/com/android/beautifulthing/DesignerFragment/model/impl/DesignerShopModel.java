package com.android.beautifulthing.DesignerFragment.model.impl;

import com.android.beautifulthing.DesignerFragment.Http_Designer.HttpUtils_Designer;
import com.android.beautifulthing.DesignerFragment.bean.DesignerShopBean;
import com.android.beautifulthing.DesignerFragment.model.IDesignerShopModel;
import com.android.beautifulthing.DesignerFragment.presenter.impl.DesignerShopPresnter;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class DesignerShopModel implements IDesignerShopModel {
    public DesignerShopModel(DesignerShopPresnter designerShopPresnter) {
        this.designerShopPresnter = designerShopPresnter;
    }

    private DesignerShopPresnter designerShopPresnter;
    @Override
    public void queryDesignerShoplList(String base_Path2, String url_path2) {
        HttpUtils_Designer.create3().queryDesigner3(base_Path2,url_path2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DesignerShopBean>() {
                    @Override
                    public void call(DesignerShopBean designerShopBean) {
                        designerShopPresnter.success3(designerShopBean);
                    }
                });
    }
}
