package com.android.beautifulthing.DesignerFragment.presenter;

import com.android.beautifulthing.DesignerFragment.bean.DesignerWorksBean;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public interface IDesgnerWorkPresent {

    void getDesignerWrokList(int tag);

    interface CallBack4{
        void success4(DesignerWorksBean designerWorksBean);
    }

}
