package com.android.beautifulthing.DesignerFragment.presenter;

import com.android.beautifulthing.DesignerFragment.bean.DesignerDetilBean;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public interface IDesignerDetilPresent {
    void getDesignerDetilList(int tag);

    interface CallBack4{
        void success4(DesignerDetilBean designerDetilBean);
    }
}
