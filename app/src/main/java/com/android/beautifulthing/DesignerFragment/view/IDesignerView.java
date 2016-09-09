package com.android.beautifulthing.DesignerFragment.view;

import com.android.beautifulthing.DesignerFragment.bean.DesignerBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public interface IDesignerView  {
    void refreshListView(List<DesignerBean.DataBean.DesignersBean>designersBeanList);
}
