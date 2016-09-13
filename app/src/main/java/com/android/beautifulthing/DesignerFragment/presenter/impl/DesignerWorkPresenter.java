package com.android.beautifulthing.DesignerFragment.presenter.impl;

import com.android.beautifulthing.DesignerFragment.bean.DesignerWorksBean;
import com.android.beautifulthing.DesignerFragment.model.IDesignerWorkModel;
import com.android.beautifulthing.DesignerFragment.model.impl.DesignerWorkModel;
import com.android.beautifulthing.DesignerFragment.presenter.IDesgnerWorkPresent;
import com.android.beautifulthing.DesignerFragment.view.IDesignerWorkView;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class DesignerWorkPresenter implements IDesgnerWorkPresent,IDesgnerWorkPresent.CallBack4{

    IDesignerWorkModel iDesignerWorkModel = new DesignerWorkModel();
    IDesignerWorkView iDesignerWorkView;

    public DesignerWorkPresenter(IDesignerWorkView iDesignerWorkView) {
        this.iDesignerWorkView = iDesignerWorkView;
    }

    @Override
    public void getDesignerWrokList(int tag) {
       iDesignerWorkModel.queryDesignerWorklList(tag, this);
    }

    @Override
    public void success4(DesignerWorksBean designerWorksBean) {
        if (designerWorksBean != null) {
            iDesignerWorkView.refreshListView(designerWorksBean);
        }
    }
}
