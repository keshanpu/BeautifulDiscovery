package com.android.beautifulthing.DesignerFragment.Http_Designer;


import com.android.beautifulthing.DesignerFragment.url.DataUrl;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/9/6
 */
public class HttpUtils_Designer {
    private static HttpService_Designer mHttpService_designer;
    public static HttpService_Designer create(){
        if (mHttpService_designer == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(DataUrl.DESIGNER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mHttpService_designer = retrofit.create(HttpService_Designer.class);
        }
        return mHttpService_designer;
    }
    public static HttpService_Designer create2(){
        if (mHttpService_designer == null) {
            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl(DataUrl.DESIGNER_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
            mHttpService_designer = retrofit2.create(HttpService_Designer.class);
        }
        return mHttpService_designer;
    }
    public static HttpService_Designer create3(){
        if (mHttpService_designer == null) {
            Retrofit retrofit3 = new Retrofit.Builder()
                    .baseUrl(DataUrl.DESIGNER_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
            mHttpService_designer = retrofit3.create(HttpService_Designer.class);
        }
        return mHttpService_designer;
    }

}
