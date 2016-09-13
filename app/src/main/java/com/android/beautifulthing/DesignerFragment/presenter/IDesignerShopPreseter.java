package com.android.beautifulthing.DesignerFragment.presenter;

import com.android.beautifulthing.DesignerFragment.bean.DesignerShopBean;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public interface IDesignerShopPreseter {
    void getDesignerShopList(String base_Path2,String url_path2);
    interface CallBack3{
        void success3(DesignerShopBean designerShopBean);
    }
}
