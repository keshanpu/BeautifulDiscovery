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
//    void getDesignerShopList(String base_Path2,String url_path2);
//    interface CallBack3{
//        void success3(DesignerShopBean designerShopBean);
//    }
//    void getDesignerWrokList(String base_Path3,String url_path3);
//    interface CallBack4{
//        void success4(DesignerWorksBean designerWorksBean);
//    }
}
