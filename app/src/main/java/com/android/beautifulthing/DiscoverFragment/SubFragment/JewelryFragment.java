package com.android.beautifulthing.DiscoverFragment.SubFragment;

import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.beautifulthing.DiscoverFragment.adapter.CategoryAdapter;
import com.android.beautifulthing.DiscoverFragment.adapter.CommonAdapter;
import com.android.beautifulthing.DiscoverFragment.bean.CategoryBean;
import com.android.beautifulthing.DiscoverFragment.bean.CommonBean;
import com.android.beautifulthing.DiscoverFragment.custom.MyGridView;
import com.android.beautifulthing.DiscoverFragment.presenter.ICategoryPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.ICommonPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.impl.CategoryPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.impl.CommonPresenter;
import com.android.beautifulthing.DiscoverFragment.view.ICategoryView;
import com.android.beautifulthing.DiscoverFragment.view.ICommonView;
import com.android.beautifulthing.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydy on 2016/9/6
 * SubFragment---首饰
 */
public class JewelryFragment extends Fragment implements ICommonView ,ICategoryView{

    private int CATEGORY_TAG = 3;
    private int PAGER = 1;
    private int PAGER_SIZE = 20;

    private int statusBarHeight;
    private int actionBarHeight;

    private Context mContext;
    private PullToRefreshListView mRefreshListView;
    private ICommonPresenter commonPresenter;
    private List<CommonBean.DataBean.ProductsBean> products = new ArrayList<>();
    private CommonAdapter mCommonAdapter;
    private MyGridView mCategoryGridView;
    private PopupWindow mPopupWindow;
    private ICategoryPresenter categoryPresenter;
    private CategoryAdapter mCategoryAdapter;
    private List<CategoryBean.DataBean.CategoriesBean.SubCategoriesBean> sub_categories = new ArrayList<>();
    private TextView mCategoryName;
    private ProgressDialog mProgressDialog;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mRefreshListView.onRefreshComplete();
            mCommonAdapter.notifyDataSetChanged();
        }
    };

    public static JewelryFragment newInstance(){
        return new JewelryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("努力加载中...");
        mProgressDialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subfragment_common_main_view, container, false);
        mCategoryName = (TextView) view.findViewById(R.id.category_selected_name_tv);
        mRefreshListView = (PullToRefreshListView) view.findViewById(R.id.subfragment_common_refresh_listview);
        //主界面数据源
        initLoadDatas(CATEGORY_TAG);
        //分类的数据源
        initCategoryDatas();
        //分类界面初始化
        initCategoryView(view);
        return view;
    }

    /**
     * 分类数据源
     */
    private void initCategoryDatas() {
        categoryPresenter = new CategoryPresenter(this);
        categoryPresenter.getCategoryDatas();
    }

    /**
     * 主界面数据源
     */
    private void initLoadDatas(int tag) {
        commonPresenter = new CommonPresenter(this);
        commonPresenter.getDatas(tag, PAGER, PAGER_SIZE);
    }

    /**
     * 分类视图刷新
     */
    public void refreshView(int tag, String name){
        products.clear();
        initLoadDatas(tag);
        mRefreshListView.setRefreshing(true);
        mCategoryName.setText(name);
        mPopupWindow.dismiss();
        mHandler.sendEmptyMessageDelayed(1,1000);
    }

    /**
     * 分类界面初始化
     */
    private void initCategoryView(View view) {
        RelativeLayout mRelativeLayout = (RelativeLayout) view.findViewById(R.id.mtest);
        //点击分类
        ImageButton mBtn = (ImageButton) view.findViewById(R.id.start_category_btn);
        TextView mSelectedName = (TextView) view.findViewById(R.id.category_selected_name_tv);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加载弹出窗口
                initPopupWindow();
            }
        });
    }

    /**
     * 分类弹出窗口
     */
    private void initPopupWindow() {
        //创建PopupWindow内容
        View categoryView = LayoutInflater.from(mContext).inflate(R.layout.subfragment_category_view, null, false);
        mCategoryGridView = (MyGridView) categoryView.findViewById(R.id.category_grid_view);
        //适配器创建、绑定
        mCategoryAdapter = new CategoryAdapter(mContext, sub_categories, this);
        mCategoryGridView.setAdapter(mCategoryAdapter);
        //创建PopupWindow对象
        mPopupWindow = new PopupWindow(categoryView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置参数，点击屏幕空白区域，使PopupWindow消失
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        //PopupWindow偏移高度
        initOffsetHeight();
        //显示PopupWindow
        mPopupWindow.showAtLocation(categoryView, Gravity.TOP, 0, actionBarHeight + statusBarHeight);
    }

    /**
     * PopupWindow偏移高度
     */
    private void initOffsetHeight() {
        //计算actionBar的高度
        TypedArray actionbarSizeTypedArray = mContext.obtainStyledAttributes(new int[] {
                android.R.attr.actionBarSize
        });
        actionBarHeight = (int) actionbarSizeTypedArray.getDimension(0, 0);
        //计算statusBar的高度
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
    }

    //主界面数据返回
    @Override
    public void dataResult(final CommonBean.DataBean dataBean) {
        products.addAll(dataBean.getProducts());
        mProgressDialog.dismiss();
        mCommonAdapter = new CommonAdapter(mContext, products);
        mRefreshListView.setAdapter(mCommonAdapter);
        //刷新事件
        mRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                products.clear();
                commonPresenter.getDatas(CATEGORY_TAG, PAGER, PAGER_SIZE);
                mHandler.sendEmptyMessageDelayed(1,1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (dataBean.getHas_next() != 0){
                    PAGER += 1;
                    commonPresenter.getDatas(CATEGORY_TAG, PAGER, PAGER_SIZE);
                } else {
                    Toast.makeText(mContext, "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
                mHandler.sendEmptyMessageDelayed(1,1000);
            }
        });
    }

    //分类数据返回
    @Override
    public void categoryDatas(CategoryBean.DataBean data) {
        sub_categories = data.getCategories().get(0).getSub_categories();
    }
}
