package com.ab.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.ab.R;
import com.ab.task.AbTask;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.ListUtils;
import com.ab.view.pullview.AbPullToRefreshView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xuxing on 2016/3/30 0030.
 */
public class BaseActivityList<T extends BaseAdapter> extends BaseActivity implements AdapterView.OnItemClickListener, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener {

    protected List<Map<String, Object>> list = null;
    protected AbPullToRefreshView mAbPullToRefreshView = null;
    protected ListView mListView = null;
    protected int currentPage = 1;
    protected List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
    protected int mLoadType = 0;
    protected MyHandler myHandler;
    protected T myListViewAdapter = null;


    @Override
    public void initView(View view) {
        super.initView(view);
        // ListView数据
        list = new ArrayList<Map<String, Object>>();

    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        myHandler = new MyHandler();

        // 设置监听器
        mAbPullToRefreshView.setOnHeaderRefreshListener(this);
        mAbPullToRefreshView.setOnFooterLoadListener(this);
        mListView.setOnItemClickListener(this);

        // 设置进度条的样式
        mAbPullToRefreshView.getHeaderView().setHeaderProgressBarDrawable(
                this.getResources().getDrawable(R.drawable.progress_circular));
        mAbPullToRefreshView.getFooterView().setFooterProgressBarDrawable(
                this.getResources().getDrawable(R.drawable.progress_circular));

    }

    @Override
    public void initData() {
        currentPage = 1;
        loading();
    }

    @Override
    public void getMoreData() {
        loading();
    }

    private void loading() {
        AbDialogUtil.showProgressDialog(this, 0, "数据加载中...");
    }


    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        mLoadType = 2;
        getMoreData();
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        mLoadType = 1;
        getMoreData();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    protected class MyHandler extends Handler {
        public MyHandler() {
        }

        public MyHandler(Looper L) {
            super(L);
        }

        // 子类必须重写此方法，接受数据
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    refreshTask();
                    break;
                case 1:
                    headerLoadMoreTask();
                    break;
                case 2:
                    footerLoadMoreTask();
                    break;

            }

        }
    }

    public void refreshTask() {
        AbTask mAbTask = AbTask.newInstance();
        final AbTaskItem item = new AbTaskItem();
        item.setListener(new AbTaskListListener() {
            @Override
            public List<?> getList() {
                return newList;
            }

            @Override
            public void update(List<?> paramList) {
                List<Map<String, Object>> newList = (List<Map<String, Object>>) paramList;
                list.clear();
                if (newList != null && newList.size() > 0) {
                    list.addAll(newList);
                    myListViewAdapter.notifyDataSetChanged();
                    newList.clear();
                }

                mAbPullToRefreshView.onHeaderRefreshFinish();
            }

        });

        mAbTask.execute(item);
    }

    /**
     * 下拉加载
     */
    private void headerLoadMoreTask() {
        AbTask mAbTask = AbTask.newInstance();
        final AbTaskItem item = new AbTaskItem();
        item.setListener(new AbTaskListListener() {

            @Override
            public List<?> getList() {

                return newList;
            }

            @Override
            public void update(List<?> paramList) {

                List<Map<String, Object>> newList = (List<Map<String, Object>>) paramList;
                if (newList != null && newList.size() > 0) {
                    list.addAll(newList);
                    ListUtils.removeDuplicate(list);
                    myListViewAdapter.notifyDataSetChanged();
                    newList.clear();
                }

                mAbPullToRefreshView.onHeaderRefreshFinish();


            }


        });

        mAbTask.execute(item);
    }

    /**
     * 上拉加载
     */
    private void footerLoadMoreTask() {
        AbTask mAbTask = AbTask.newInstance();
        final AbTaskItem item = new AbTaskItem();
        item.setListener(new AbTaskListListener() {

            @Override
            public List<?> getList() {
                return newList;
            }

            @Override
            public void update(List<?> paramList) {

                List<Map<String, Object>> newList = (List<Map<String, Object>>) paramList;
                if (newList != null && newList.size() > 0) {
                    list.addAll(newList);
                    ListUtils.removeDuplicate(list);
                    myListViewAdapter.notifyDataSetChanged();
                    newList.clear();
                }

                mAbPullToRefreshView.onFooterLoadFinish();

            }
        });

        mAbTask.execute(item);
    }

    protected void currentPageAdd(List<Map<String, Object>> newList) {
        if (newList.size() % 10 == 0 && newList.size() > 0) {
            currentPage++;
        }
    }

    protected void closeAbPullToRefreshView(int mLoadType) {
        switch (mLoadType) {
            case 1:
                mAbPullToRefreshView.onHeaderRefreshFinish();
                break;
            case 2:
                mAbPullToRefreshView.onFooterLoadFinish();
                break;

        }
    }


}