package com.android.beautifulthing.DesignerFragment.model.impl;

import com.android.beautifulthing.DesignerFragment.Http_Designer.HttpUtils_Designer;
import com.android.beautifulthing.DesignerFragment.bean.DesignerBean;
import com.android.beautifulthing.DesignerFragment.model.IDesignerModel;
import com.android.beautifulthing.DesignerFragment.presenter.impl.DesignerPresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/6
 */
public class DesignerModel implements IDesignerModel {
    private DesignerPresenter designerPresenter;

    public DesignerModel(DesignerPresenter designerPresenter) {
        this.designerPresenter = designerPresenter;
    }

    @Override
    public void queryDesignerList(int page,int size) {
        HttpUtils_Designer.create().queryDesigner(page,size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DesignerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DesignerBean designerBean) {
                        designerPresenter.success(designerBean);
                    }
                });

    }

//    @Override
//    public void queryDesignerDetilList(String base_Path, String url_path) {
//        HttpUtils_Designer.create2().queryDesigner2(base_Path,url_path)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<DesignerDetilBean>() {
//                    @Override
//                    public void call(DesignerDetilBean designerDetilBean) {
//                        designerPresenter.success2(designerDetilBean);
//                    }
//                });
//    }

//    @Override
//    public void queryDesignerShoplList(String base_Path2, String url_path2) {
//        HttpUtils_Designer.create3().queryDesigner3(base_Path2,url_path2)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<DesignerShopBean>() {
//                    @Override
//                    public void call(DesignerShopBean designerShopBean) {
//                        designerPresenter.success3(designerShopBean);
//                    }
//                });
//    }

//    @Override
//    public void queryDesignerWorklList(String base_Path3, String url_path3) {
//        HttpUtils_Designer.create4().queryDesigner4(base_Path3, url_path3)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<DesignerWorksBean>() {
//                    @Override
//                    public void call(DesignerWorksBean designerWorksBean) {
//                        designerPresenter.success4(designerWorksBean);
//                    }
//                });
//    }
}
