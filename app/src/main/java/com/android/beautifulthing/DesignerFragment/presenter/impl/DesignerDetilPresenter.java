package com.android.beautifulthing.DesignerFragment.presenter.impl;

import com.android.beautifulthing.DesignerFragment.bean.DesignerDetilBean;
import com.android.beautifulthing.DesignerFragment.model.IDesignerDetilModel;
import com.android.beautifulthing.DesignerFragment.model.impl.DesignerDetilModel;
import com.android.beautifulthing.DesignerFragment.presenter.IDesignerDetilPresent;
import com.android.beautifulthing.DesignerFragment.view.IDesignerDetilView;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public class DesignerDetilPresenter implements IDesignerDetilPresent,IDesignerDetilPresent.CallBack4 {
   IDesignerDetilModel iDesignerDetilModel = new DesignerDetilModel();

    IDesignerDetilView iDesignerDetilView;

    public DesignerDetilPresenter(IDesignerDetilView iDesignerDetilView) {
        this.iDesignerDetilView = iDesignerDetilView;
    }
    @Override
    public void success4(DesignerDetilBean designerDetilBean) {
        if (designerDetilBean != null) {
            iDesignerDetilView.refreshListView(designerDetilBean);
        }
    }

    @Override
    public void getDesignerDetilList(int tag) {
    iDesignerDetilModel.queryDesignerDetilList(tag,this);
    }
}
