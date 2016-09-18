package com.android.beautifulthing.DesignerFragment.designerDetilFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.beautifulthing.CommonActivity.WebViewActivity;
import com.android.beautifulthing.DesignerFragment.bean.DesignerShopBean;
import com.android.beautifulthing.DesignerFragment.presenter.IDesignerShopPreseter;
import com.android.beautifulthing.DesignerFragment.presenter.impl.DesignerShopPresnter;
import com.android.beautifulthing.DesignerFragment.url.DataUrl;
import com.android.beautifulthing.DesignerFragment.view.IDesignerShop2View;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/7 0007
 */
public class BuyFragment extends Fragment  implements IDesignerShop2View {
    private Context mContext;
    private IDesignerShopPreseter iDesignerPresent;
    private int id;
    private DesignerShopBean.DataBean dataBean;
    private String shopUrl;
    @BindView(R.id.buyfragment_iv)
    ImageView imageView;
    @BindView(R.id.buyfragment_name)
    TextView name;
    @BindView(R.id.buyfragment_rl)
    RelativeLayout mRl;
    public static BuyFragment newInstance(int id){
        BuyFragment buyFragment = new BuyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        buyFragment.setArguments(bundle);
        return buyFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =getContext();
        Bundle arguments = getArguments();
        id = arguments.getInt("id");
        iDesignerPresent = new DesignerShopPresnter(this);
        iDesignerPresent.getDesignerShopList(DataUrl.DESTIGNER_DETAILS2_URL,""+id);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.buyfragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void refreshListView2(DesignerShopBean designerShopBean) {
        dataBean = designerShopBean.getData();
        Picasso.with(mContext).load(dataBean.getOnline_shop_image()).into(imageView);
        final String shop_name = dataBean.getOnline_shops().get(0).getName();
        name.setText(shop_name);
        shopUrl = dataBean.getOnline_shops().get(0).getLink_url();
        mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("shop_url",shopUrl);
                intent.putExtra("shop_name",shop_name);
                mContext.startActivity(intent);
            }
        });
    }
}
