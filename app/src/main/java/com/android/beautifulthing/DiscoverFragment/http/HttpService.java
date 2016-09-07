package com.android.beautifulthing.DiscoverFragment.http;

import com.android.beautifulthing.DiscoverFragment.bean.DailyBean;
import com.android.beautifulthing.DiscoverFragment.bean.DetailBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ydy on 2016/9/6.
 */
public interface HttpService {

    // /api/v1/products/daily/?timestamp=1472918400000

    @GET("/api/v1/products/daily/")
    Call<DailyBean> getDailyDatas(@Query("timestamp")String timestamp);

    // http://design.zuimeia.com /api/v1/product/1097/

//    @Headers({
//            "User-Agent: Android"
//    })
    @GET("/api/v1/product/{product_id}")
    Call<DetailBean> getDetailDatas(
            @Path("product_id") int product_id);

}
