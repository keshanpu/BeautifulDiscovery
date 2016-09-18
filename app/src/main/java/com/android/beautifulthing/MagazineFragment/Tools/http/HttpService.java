package com.android.beautifulthing.MagazineFragment.Tools.http;

import com.android.beautifulthing.MagazineFragment.bean.MagazineBean;
import com.android.beautifulthing.MagazineFragment.bean.MagazineDetailBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by keshanpu on 16/8/25.
 */
public interface HttpService {
    //http://design.zuimeia.com/api/v1/articles/daily/simple/?page=1&page_size=30
    @GET("/api/v1/articles/daily/simple/")
    Call<MagazineBean> queryMagazineBean(@Query("page") int page, @Query("page_size") int page_size);

    @GET("/api/v1/article/{pathId}")
    Call<MagazineDetailBean> queryMagazineDetail(@Path("pathId") int pathId);

}

