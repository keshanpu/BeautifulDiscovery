package com.android.beautifulthing.DesignerFragment.designerDetilFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.beautifulthing.DesignerFragment.bean.DesignerShopBean;
import com.android.beautifulthing.DesignerFragment.presenter.IDesignerShopPreseter;
import com.android.beautifulthing.DesignerFragment.presenter.impl.DesignerShopPresnter;
import com.android.beautifulthing.DesignerFragment.view.IDesignerShop2View;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/7
 */
public class ShopFragment extends Fragment implements IDesignerShop2View {

    private Context mContext;
    private int id;

    public ShopFragment(int id) {
        this.id = id;
    }

    @BindView(R.id.shopfragment_iv)
    ImageView imageView;
    @BindView(R.id.shopfragment_city)
    TextView city;
    @BindView(R.id.shopfragment_name)
    TextView name;
    @BindView(R.id.shopfragment_address)
    TextView address;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        IDesignerShopPreseter iDesignerPresent = new DesignerShopPresnter(this);
        iDesignerPresent.getDesignerShopList(url.DESTIGNER_DETAILS2_URL,id+"");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.shopfragment,container,false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void refreshListView2(DesignerShopBean designerShopBean) {
        DesignerShopBean.DataBean dataBean =designerShopBean.getData();
        dataBean.getShop_image();
        Log.d("++", "refreshListView: "+dataBean.toString());
        if (!dataBean.getShop_image().isEmpty()){
            Picasso.with(mContext).load(dataBean.getShop_image()).into(imageView);
            city.setText(dataBean.getShops().get(0).getCity());
            name.setText(dataBean.getShops().get(0).getName());
            address.setText(dataBean.getShops().get(0).getAddress());
        }
    }
}
