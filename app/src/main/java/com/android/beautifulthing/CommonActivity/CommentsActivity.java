package com.android.beautifulthing.CommonActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.beautifulthing.DiscoverFragment.adapter.CommentsAdapter;
import com.android.beautifulthing.DiscoverFragment.bean.CommentBean;
import com.android.beautifulthing.DiscoverFragment.presenter.ICommentPresenter;
import com.android.beautifulthing.DiscoverFragment.presenter.impl.CommentPresenter;
import com.android.beautifulthing.DiscoverFragment.view.ICommentView;
import com.android.beautifulthing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydy on 2016/9/10.
 * 评论界面
 */
public class CommentsActivity extends AppCompatActivity implements ICommentView{

    private Context mContext;
    private int id;
    private ListView mCommentsListView;
    private ICommentPresenter commentPresenter;
    private List<CommentBean.DataBean.CommentsBean> comments = new ArrayList<>();
    private CommentsAdapter mCommentsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_view);
        mContext = this;
        //获取传来的产品ID
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        //初始化视图
        initView();
    }

    /**
     * 点击事件
     */
    public void click(View view) {
        switch (view.getId()){
            case R.id.common_comments_back_btn://退出界面
                finish();
                break;
        }
    }

    /**
     * 初始化视图、加载数据源
     */
    private void initView() {
        mCommentsListView = (ListView) findViewById(R.id.common_comments_listview);
        LinearLayout emptyView = (LinearLayout) findViewById(R.id.comments_empty_view);
        mCommentsListView.setEmptyView(emptyView);
        commentPresenter = new CommentPresenter(this);
        commentPresenter.getCommentDatas(id);
    }

    /**
     * @param data 返回的数据
     */
    @Override
    public void backCommentData(CommentBean.DataBean data) {
        comments = data.getComments();
        mCommentsAdapter = new CommentsAdapter(mContext, comments);
        mCommentsListView.setAdapter(mCommentsAdapter);
    }
}
