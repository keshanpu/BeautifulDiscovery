package com.android.beautifulthing.DiscoverFragment.presenter;

import com.android.beautifulthing.DiscoverFragment.bean.CommonBean;

/**
 * Created by ydy on 2016/9/6.
 */
public interface ICommonPresenter {

    void getDatas(int tag, int page,int page_size);

    interface Callback{
        void success(CommonBean.DataBean dataBean);
    }

}
