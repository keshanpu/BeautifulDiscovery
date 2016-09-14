package com.android.beautifulthing.DesignerFragment.Http_Designer;

import com.android.beautifulthing.DesignerFragment.Url.url;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class HttpUtils_Works  {
        private static HttpServiceo_Wroks mHttpService_Wroks;
        public static HttpServiceo_Wroks create(){
        if (mHttpService_Wroks == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url.DESIGNER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mHttpService_Wroks = retrofit.create(HttpServiceo_Wroks.class);
        }
        return mHttpService_Wroks;
    }
}
