package com.android.beautifulthing.MagazineFragment.model.impl;

import com.android.beautifulthing.MagazineFragment.tools.http.HttpUtils;
import com.android.beautifulthing.MagazineFragment.bean.MagazineBean;
import com.android.beautifulthing.MagazineFragment.model.IMagazineFragmentModel;
import com.android.beautifulthing.MagazineFragment.presenter.IMagazineFragmentPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by keshanpu on 16/9/6.
 */
public class MagazineFragmentModel implements IMagazineFragmentModel{

    @Override
    public void queryMagazine(int page, int pagesize, final IMagazineFragmentPresenter.Callback callback) {
//        Log.d("onResponse", "onResponse: "+page+" "+pagesize);
        HttpUtils.newInstance().queryMagazineBean(page,pagesize)
                .enqueue(new Callback<MagazineBean>() {
                    @Override
                    public void onResponse(Call<MagazineBean> call, Response<MagazineBean> response) {
                        MagazineBean magazineBean = response.body();
//                        Log.i("onResponse", "onResponse: "+magazineBean.getData().getArticles());
//                        Log.i("onResponse", "onResponse: "+magazineBean.getData().getArticles().size());
                        callback.success(magazineBean);

                    }

                    @Override
                    public void onFailure(Call<MagazineBean> call, Throwable t) {
                        try {
                            callback.dofailed(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
