package com.android.beautifulthing.CommonActivity.http;

import com.android.beautifulthing.CommonActivity.bean.ApkUpdateBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ydy on 2016/9/18.
 */
public interface UpdateService {

    //apk更新 /common/app/update/?package_name=com.zuiapps.zuiworld&openUDID=133524238649809&appVersion=1.1.8_2&appVersionCode=10182&systemVersion=17&resolution=675x1200&platform=android&channel=com.zuiapps.upgrade&lang=zh-cn
    @GET("/common/app/update/?package_name=com.zuiapps.zuiworld&openUDID=133524238649809&appVersion=1.1.8_2&appVersionCode=10182&systemVersion=17&resolution=675x1200&platform=android&channel=com.zuiapps.upgrade&lang=zh-cn")
    Call<ApkUpdateBean> getUpdateMsg();
}
