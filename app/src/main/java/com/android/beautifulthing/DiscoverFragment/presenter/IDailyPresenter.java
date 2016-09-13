package com.android.beautifulthing.DiscoverFragment.presenter;

import com.android.beautifulthing.DiscoverFragment.bean.DailyBean;

import java.util.List;

/**
 * Created by ydy on 2016/9/6.
 */
public interface IDailyPresenter {

    void getDatas(long timestamp);

    interface Callback{
        void success(List<DailyBean.DataBean.ProductsBean> productsBeanList);
    }

}
