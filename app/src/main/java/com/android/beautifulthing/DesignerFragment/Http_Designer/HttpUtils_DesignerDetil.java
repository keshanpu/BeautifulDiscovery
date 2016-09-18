package com.android.beautifulthing.DesignerFragment.Http_Designer;

import com.android.beautifulthing.DesignerFragment.url.DataUrl;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public class HttpUtils_DesignerDetil {
    private static HttpService_DesignerDetil mHttpService_designerDetil;
    public static HttpService_DesignerDetil create(){
        if (mHttpService_designerDetil== null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(DataUrl.DESIGNER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mHttpService_designerDetil = retrofit.create(HttpService_DesignerDetil.class);
        }
        return mHttpService_designerDetil;
    }
}
