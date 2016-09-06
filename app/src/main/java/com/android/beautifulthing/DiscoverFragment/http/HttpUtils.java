package com.android.beautifulthing.DiscoverFragment.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ydy on 2016/9/6.
 */
public class HttpUtils {

    private static HttpService mHttpService;
    public static final String BASE_URL = "http://design.zuimeia.com";

    public static HttpService init() {
        if (mHttpService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mHttpService = retrofit.create(HttpService.class);
        }
        return mHttpService;
    }

}
