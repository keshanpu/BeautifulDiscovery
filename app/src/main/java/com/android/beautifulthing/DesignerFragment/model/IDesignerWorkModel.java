package com.android.beautifulthing.DesignerFragment.model;

import com.android.beautifulthing.DesignerFragment.presenter.IDesgnerWorkPresent;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public interface IDesignerWorkModel {
    void queryDesignerWorklList(int tag, IDesgnerWorkPresent.CallBack4 callBack4);
}
