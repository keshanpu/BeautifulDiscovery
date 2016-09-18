package com.android.beautifulthing.MagazineFragment.Tools.http;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by keshanpu on 16/8/31.
 */
public class HttpUtils {

    private HttpUtils(){

    }

    private static HttpService httpService;
    public static HttpService newInstance(){
        if (null == httpService){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URLPath.MAGAZINE_DOMAIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())//结合Rxjava
                    .build();
            httpService = retrofit.create(HttpService.class);
        }

        return httpService;

    }
}
