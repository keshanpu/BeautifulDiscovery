package com.android.beautifulthing.DesignerFragment.model.impl;

import com.android.beautifulthing.DesignerFragment.Http_Designer.HttpUtils_Works;
import com.android.beautifulthing.DesignerFragment.bean.DesignerWorksBean;
import com.android.beautifulthing.DesignerFragment.model.IDesignerWorkModel;
import com.android.beautifulthing.DesignerFragment.presenter.IDesgnerWorkPresent;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class DesignerWorkModel implements IDesignerWorkModel {

    @Override
    public void queryDesignerWorklList(int tag, final IDesgnerWorkPresent.CallBack4 callBack4) {
        HttpUtils_Works.create().queryDesigner4(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DesignerWorksBean>() {
                    @Override
                    public void call(DesignerWorksBean designerWorksBean) {
                        callBack4.success4(designerWorksBean);
                    }
                });
    }

}
