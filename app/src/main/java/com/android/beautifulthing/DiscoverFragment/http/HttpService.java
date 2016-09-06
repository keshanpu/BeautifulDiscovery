package com.android.beautifulthing.DiscoverFragment.http;

import com.android.beautifulthing.DiscoverFragment.bean.DailyBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ydy on 2016/9/6.
 */
public interface HttpService {

    // /api/v1/products/daily/?timestamp=1472918400000

    @GET("/api/v1/products/daily/")
    Call<DailyBean> getDailyDatas(@Query("timestamp")String timestamp);

}
