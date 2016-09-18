package com.android.beautifulthing.DesignerFragment.model;


import com.android.beautifulthing.DesignerFragment.presenter.IDesignerDetilPresent;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public interface IDesignerDetilModel {
    void queryDesignerDetilList(int tag, IDesignerDetilPresent.CallBack4 callBack4);
}
