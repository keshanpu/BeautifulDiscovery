package com.android.beautifulthing.DesignerFragment.presenter;

import com.android.beautifulthing.DesignerFragment.bean.DesignerBean;
import com.android.beautifulthing.DesignerFragment.bean.DesignerDetilBean;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public interface IDesignerPresent {
    void getDesignerList(int page,int size);
    interface CallBack{
        void success(DesignerBean designerBean);
    }
    void getDesignerList(String base_Path,String url_path);
    interface CallBack2{
        void success2(DesignerDetilBean designerDetilBean);
    }
}
