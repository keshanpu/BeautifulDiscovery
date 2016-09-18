package com.android.beautifulthing.CommonActivity.http;

import com.android.beautifulthing.DiscoverFragment.http.HttpService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ydy on 2016/9/18.
 */
public class UpdateUtils {

    private static UpdateService mUpdateService;
    public static final String BASE_URL = "http://zuimeia.com";

    public static UpdateService init() {
        if (mUpdateService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mUpdateService = retrofit.create(UpdateService.class);
        }
        return mUpdateService;
    }
}
