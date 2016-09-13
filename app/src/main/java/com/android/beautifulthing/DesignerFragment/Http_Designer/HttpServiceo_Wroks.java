package com.android.beautifulthing.DesignerFragment.Http_Designer;

import com.android.beautifulthing.DesignerFragment.bean.DesignerWorksBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public interface HttpServiceo_Wroks {

        // /api/v1/products/designer/100
        @GET("/api/v1/products/designer/{tag}")
        Observable<DesignerWorksBean> queryDesigner4(
            @Path("tag")int tag);

}
