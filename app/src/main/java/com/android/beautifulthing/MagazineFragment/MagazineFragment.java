package com.android.beautifulthing.MagazineFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.beautifulthing.MagazineFragment.bean.MagazineBean;
import com.android.beautifulthing.MagazineFragment.presenter.IMagazineFragmentPresenter;
import com.android.beautifulthing.MagazineFragment.presenter.impl.MagazineFragmentPresenter;
import com.android.beautifulthing.MagazineFragment.view.IMagazineFragmentView;
import com.android.beautifulthing.R;
import com.appeaser.deckview.views.DeckChildView;
import com.appeaser.deckview.views.DeckView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ydy on 2016/9/5.
 */
public class MagazineFragment extends Fragment implements IMagazineFragmentView {

    private static final String TAG = "androidxx";
    private Context mContext;
    private IMagazineFragmentPresenter mMagazineFragmentPresenter;
    private List<MagazineBean.DataBean.ArticlesBean> articlesBeanList = new ArrayList<>();

    private int page;
    private int pagesize;

    public static MagazineFragment newInstance(){
        MagazineFragment magazineFragment = new MagazineFragment();
        return magazineFragment;
    }


    //=======================

    // View that stacks its children like a deck of cards
    DeckView<Datum> mDeckView;

    Drawable mDefaultHeaderIcon;
    ArrayList<Datum> mEntries;

    // Placeholder for when the image is being downloaded
    Bitmap mDefaultTarget_image;
    Bitmap mDefaultTargetIcon;

    // Retain position on configuration change
    // imageSize to pass to http://lorempixel.com
    int scrollToChildIndex = -1, imageSize = 500;

    // SavedInstance bundle keys
    final String CURRENT_SCROLL = "current.scroll", CURRENT_LIST = "current.list";

    //=========================

    //设为public,传给model层 ;
    private ProgressDialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

        mEntries = new ArrayList<>();

        loadDatas();

    }

    private void loadDatas() {
        mMagazineFragmentPresenter = new MagazineFragmentPresenter(this);
        page = 1;
        pagesize = 30;
        dialog = new ProgressDialog(mContext);
        mMagazineFragmentPresenter.getMagazine(page,pagesize);
        dialog.setMessage("正在加载...");
//        dialog.show();
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mDeckView.notifyDataSetChanged();
            if(dialog != null){
                dialog.dismiss();
            }
        }
    };

    @Override
    public void refreshMagazine(final List<MagazineBean.DataBean.ArticlesBean> articlesBeanList) {
        Collections.reverse(articlesBeanList);
        this.articlesBeanList.addAll(articlesBeanList);
//        Log.d(TAG, "refreshMagazine: "+articlesBeanList);
//        Log.d(TAG, "onCreateView: "+articlesBeanList.size());
        //由于这里数据量运算;采用handler更新;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0, j = articlesBeanList.size(); i < j; i++) {
                    Datum datum = new Datum();
                    MagazineBean.DataBean.ArticlesBean articlesBean = articlesBeanList.get(i);
                    datum.id = articlesBean.getId();//generateUniqueKey();
                    datum.iconTitle = articlesBean.getAuthor().getSign();
                    datum.title = articlesBean.getTitle();
                    datum.subTitle = articlesBean.getSub_title();
                    datum.image_url = articlesBean.getImage_url();
                    datum.avatar_url = articlesBean.getAuthor().getAvatar_url();
                    mEntries.add(datum);
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();
//        mDeckView.notifyDataSetChanged();
    }

    @Override
    public void refreshDataFailed(int result) throws InterruptedException {
        if (result == 1000){
            if(dialog != null) {
                dialog.dismiss();
            }
            Toast.makeText(mContext,"网络迷路了,需要重新加载",Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_magazine_main_view,container,false);
        /**
         * 点击刷新*/
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDatas();
            }
        });
        mDeckView = (DeckView) view.findViewById(R.id.deckview);
        mDefaultTarget_image = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_mine_portrait);
        mDefaultTargetIcon  = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_mine_portrait);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(CURRENT_LIST)) {
                mEntries = savedInstanceState.getParcelableArrayList(CURRENT_LIST);
            }

            if (savedInstanceState.containsKey(CURRENT_SCROLL)) {
                scrollToChildIndex = savedInstanceState.getInt(CURRENT_SCROLL);
            }
        }


        // Callback implementation
        DeckView.Callback<Datum> deckViewCallback = new DeckView.Callback<Datum>() {
            @Override
            public ArrayList<Datum> getData() {
                return mEntries;
            }

            @Override
            public void loadViewData(WeakReference<DeckChildView<Datum>> dcv, Datum item) {
                loadViewDataInternal(item, dcv);
            }

            @Override
            public void unloadViewData(Datum item) {
                Picasso.with(mContext).cancelRequest(item.target_image);
                Picasso.with(mContext).cancelRequest(item.target_avatar_icon);
            }

            /**改方法在底层已屏蔽;
             * @param item
             */
            @Override
            public void onViewDismissed(Datum item) {
                mEntries.remove(item);
                mDeckView.notifyDataSetChanged();
            }

            @Override
            public void onItemClick(Datum item) {
//                Toast.makeText(mContext,
//                        "Item with id: '" + item.id + "' clicked",
//                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, MagazineDetailActivity.class);
                intent.putExtra("id",item.id);
                mContext.startActivity(intent);
            }

            @Override
            public void onNoViewsToDeck() {
                Toast.makeText(mContext,
                        "No views to show",
                        Toast.LENGTH_SHORT).show();
            }
        };

        mDeckView.initialize(deckViewCallback);

        if (scrollToChildIndex != -1) {
            mDeckView.post(new Runnable() {
                @Override
                public void run() {
                    // Restore scroll position
                    mDeckView.scrollToChild(scrollToChildIndex);
                }
            });
        }


        return view;
    }

    /**
     * @param datum
     * @param weakView
     */
    void loadViewDataInternal(final Datum datum,
                              final WeakReference<DeckChildView<Datum>> weakView) {

        // datum.target can be null
        Picasso.with(mContext).cancelRequest(datum.target_image);
        Picasso.with(mContext).cancelRequest(datum.target_avatar_icon);

        /**
         * 这里设置大图;
         * */
        datum.target_image = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // Pass loaded Bitmap to view
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum,bitmap,
                            datum.iconTitle, datum.title, datum.subTitle);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                // Loading failed. Pass default thumbnail instead
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum,mDefaultTarget_image,
                            "Failed", "Failed", "Failed");
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                // Pass the default thumbnail for now. It will
                // be replaced once the target Bitmap has been loaded
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, mDefaultTarget_image,
                            "", "","");
                }
                //by kesp,不显示好看一点;
            }
        };
        /**
         * 这里设置小头像,icon_image.
         * */
        datum.target_avatar_icon = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded2(datum,bitmap);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded2(datum,mDefaultTargetIcon);
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded2(datum,mDefaultTargetIcon);
                }
            }
        };

        // Begin loading
        Picasso.with(mContext).load(datum.image_url).into(datum.target_image);
        Picasso.with(mContext).load(datum.avatar_url).into(datum.target_avatar_icon);
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Save current scroll and the list
        int currentChildIndex = mDeckView.getCurrentChildIndex();
        outState.putInt(CURRENT_SCROLL, currentChildIndex);
        outState.putParcelableArrayList(CURRENT_LIST, mEntries);

        super.onSaveInstanceState(outState);
    }

    // Generates a key that will remain unique
    // during the application's lifecycle
//    private static int generateUniqueKey() {
//        return ++KEY;
//    }
//    private static int KEY = 0;

}
