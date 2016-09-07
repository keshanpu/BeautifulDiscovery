package com.android.beautifulthing.DiscoverFragment.presenter;

import com.android.beautifulthing.DiscoverFragment.bean.DailyBean;
import com.android.beautifulthing.DiscoverFragment.bean.DetailBean;

import java.util.List;

/**
 * Created by ydy on 2016/9/6.
 */
public interface IDetailPresenter {

    void getDetailDatas(int product_id);

    interface Callback{
        void success(DetailBean body);
    }

}
