package com.android.beautifulthing.MagazineFragment.view;

import com.android.beautifulthing.MagazineFragment.bean.MagazineDetailBean;

/**
 * Created by keshanpu on 16/9/11.
 */
public interface IMagazineDetailActivityView {

    void refreshMagazineDetail(MagazineDetailBean.DataBean dataBean);

    void refreshDataFailed(int result);

}
