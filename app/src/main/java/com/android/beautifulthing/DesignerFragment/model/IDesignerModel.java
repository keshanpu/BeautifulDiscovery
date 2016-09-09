package com.android.beautifulthing.DesignerFragment.model;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public interface IDesignerModel {
    void queryDesignerList(int page,int size);
    void queryDesignerDetilList(String base_Path,String url_path);
}
