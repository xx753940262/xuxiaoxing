package com.xiaoxing.module.recycler.swipe.activity;

/**
 * 描述： 继承 BaseRecyclerActivity 实现列表的示例展示，只显示列表的 Activity 继承它就好了。
 * 实现更多功能，如侧滑菜单，Item 拖拽，滑动删除 Item 等，mSwipeMenuRecyclerView 设置更多的方法即可。
 * 详见与此类同包的其他 activity。
 * 作者：xiaoxing on 17/5/5 11:36
 * 邮箱：2235445233@qq.com
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ab.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xiaoxing.common.base.BaseRecyclerActivity;
import com.xiaoxing.module.recycler.swipe.model.ListItemInfo;

import java.util.ArrayList;
import java.util.List;

public class RvMultiFuncActivity extends BaseRecyclerActivity<ListItemInfo> {
    private String title;


    @Override
    public int bindLayout() {
        return R.layout.activity_rv_demo;
    }


    @Override
    protected void initItemLayout() {
        setLayoutResId(R.layout.item_main);
        setListType(LINEAR_LAYOUT_MANAGER, true, true, new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void initView(View view) {
        super.initView(view);
        setHeaderTitle("BaseRecyclerActivity");
        mSwipeMenuRecyclerView.addOnItemTouchListener(onItemClickListener);
    }


    @Override
    public void initData() {
        super.initData();
        addData();

    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
    }

    @Override
    protected void MyHolder(BaseViewHolder baseViewHolder, ListItemInfo listItemInfo) {
        baseViewHolder.setText(R.id.tv_title, listItemInfo.getTitle());
        baseViewHolder.setText(R.id.tv_des, listItemInfo.getSubTitle());
    }

    private void addData() {
        List<ListItemInfo> list = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.recycler_item_title);
        String[] titlesDes = getResources().getStringArray(R.array.recycler_item_des);
        for (int i = 0; i < titles.length; i++) {
            list.add(new ListItemInfo(titles[i], titlesDes[i]));
        }
        mAdapter.addData(list);
        mAdapter.notifyDataSetChanged();
    }

    private RecyclerView.OnItemTouchListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    bundle.putString("title", mAdapter.getItem(position).getTitle());
                    startBundleActivity(bundle, SwipeRVDemoActivity.class);
                    break;
                case 1:
                    break;
            }
        }
    };
}
