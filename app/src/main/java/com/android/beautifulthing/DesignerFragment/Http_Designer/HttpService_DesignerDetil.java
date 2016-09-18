package com.android.beautifulthing.DesignerFragment.Http_Designer;

import com.android.beautifulthing.DesignerFragment.bean.DesignerDetilBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public interface HttpService_DesignerDetil {

    @GET("/api/v1/designer/{tag}")
    Observable<DesignerDetilBean>  queryDesigner(@Path("tag") int tag);

}
