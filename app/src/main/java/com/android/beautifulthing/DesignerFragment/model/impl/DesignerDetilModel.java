package com.android.beautifulthing.DesignerFragment.model.impl;

import com.android.beautifulthing.DesignerFragment.Http_Designer.HttpUtils_DesignerDetil;
import com.android.beautifulthing.DesignerFragment.bean.DesignerDetilBean;
import com.android.beautifulthing.DesignerFragment.model.IDesignerDetilModel;
import com.android.beautifulthing.DesignerFragment.presenter.IDesignerDetilPresent;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public class DesignerDetilModel implements IDesignerDetilModel {
    @Override
    public void queryDesignerDetilList(int tag, final IDesignerDetilPresent.CallBack4 callBack4) {
        HttpUtils_DesignerDetil.create().queryDesigner(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DesignerDetilBean>() {
                    @Override
                    public void call(DesignerDetilBean designerDetilBean) {
                        callBack4.success4(designerDetilBean);
                    }
                });
    }
}
