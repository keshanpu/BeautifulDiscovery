package com.android.beautifulthing.DesignerFragment.designerDetilFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.beautifulthing.CommonActivity.DetailActivity;
import com.android.beautifulthing.DesignerFragment.bean.DesignerWorksBean;
import com.android.beautifulthing.DesignerFragment.presenter.IDesgnerWorkPresent;
import com.android.beautifulthing.DesignerFragment.presenter.impl.DesignerWorkPresenter;
import com.android.beautifulthing.DesignerFragment.view.IDesignerWorkView;
import com.android.beautifulthing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class WroksFragment extends Fragment implements IDesignerWorkView{
    public  static WroksFragment newInstance(int id){
        WroksFragment wroksFragment = new WroksFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        wroksFragment.setArguments(bundle);
        return wroksFragment;
    }
    private RecyclerView mRecyclerView;
    private IDesgnerWorkPresent iDesgnerWorkPresent;
    private DesignerWorksBean.DataBean dataBean;
    private Context mContext;
    private List<String> urllist = new ArrayList<>();
    private List<Integer> idlist = new ArrayList<>();
    private int id;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =getContext();
        Bundle arguments = getArguments();
         id = arguments.getInt("id");
        iDesgnerWorkPresent = new DesignerWorkPresenter(this);
        iDesgnerWorkPresent.getDesignerWrokList(id);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =LayoutInflater.from(mContext).inflate(R.layout.worksfragment,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
    }

    @Override
    public void refreshListView(DesignerWorksBean designerWorksBean) {
        urllist.clear();
        dataBean = designerWorksBean.getData();
        Log.d("TAG0000", "refreshListView: "+dataBean.toString());
        for (int i = 0; i <dataBean.getProducts().size() ; i++) {
            urllist.add(dataBean.getProducts().get(i).getCover_images().get(0));
            idlist.add(dataBean.getProducts().get(i).getId());
        }
        Log.d("2222", "refreshListView: "+urllist.size());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyclerView.setAdapter(new MyAdapter());
    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
           iv = (ImageView) itemView.findViewById(R.id.works_gridview_item);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            TextView tv = new TextView(getActivity());
//            tv.setTextSize(30);
            View view =LayoutInflater.from(mContext).inflate(R.layout.works_gridview_item,parent,false);

            return new MyViewHolder(view);
        }

        @Override
                public void onBindViewHolder(MyViewHolder holder, final int position) {
//            holder.mTextView.setText("Item:"+position);
            Picasso.with(mContext).load(urllist.get(position)).into(holder.iv);
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("id",idlist.get(position));
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return urllist.size();
        }
    }
}
