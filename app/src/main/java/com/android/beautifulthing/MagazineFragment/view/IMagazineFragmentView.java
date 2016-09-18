package com.android.beautifulthing.MagazineFragment.view;

import com.android.beautifulthing.MagazineFragment.bean.MagazineBean;

import java.util.List;

/**
 * Created by keshanpu on 16/9/6.
 */
public interface IMagazineFragmentView {

    void refreshMagazine(List<MagazineBean.DataBean.ArticlesBean> articlesBeanList);
    void refreshDataFailed(int result) throws InterruptedException;
}
