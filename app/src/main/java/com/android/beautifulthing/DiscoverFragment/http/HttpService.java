package com.android.beautifulthing.DiscoverFragment.http;

import com.android.beautifulthing.DiscoverFragment.bean.DailyBean;
import com.android.beautifulthing.DiscoverFragment.bean.DetailBean;
import com.android.beautifulthing.DiscoverFragment.bean.CommonBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ydy on 2016/9/6.
 */
public interface HttpService {

    // Daily页 /api/v1/products/daily/?timestamp=1472918400000
    @GET("/api/v1/products/daily/")
    Call<DailyBean> getDailyDatas(
            @Query("timestamp")long timestamp);

    // Daily页详情  /api/v1/product/1097/
    @GET("/api/v1/product/{product_id}")
    Call<DetailBean> getDetailDatas(
            @Path("product_id") int product_id);

    // Jewelry页 /api/v1/products/category/3/?page=1&page_size=10
    @GET("/api/v1/products/category/{tag}/")
    Call<CommonBean> getCommonDatas(
            @Path("tag")int tag,
            @Query("page")int page,
            @Query("page_size")int page_size);

}
