package com.zmr.xuxiaoxing.ui.recycler.swipe.adapter;

/**
 * 描述：
 * 作者：xiaoxing on 17/5/5 12:30
 * 邮箱：2235445233@qq.com
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ab.R;
import com.xiaoxing.common.third.recyclerview.swipe.SwipeMenuAdapter;
import com.zmr.xuxiaoxing.ui.recycler.swipe.listener.OnItemClickListener;
import com.zmr.xuxiaoxing.ui.recycler.swipe.model.ChannelBean;

import java.util.List;


public class SwipeRVAdapter extends SwipeMenuAdapter<SwipeRVAdapter.DefaultViewHolder> {

    public List<ChannelBean> mArray;

    private Context context;

    private OnItemClickListener mOnItemClickListener;

    public SwipeRVAdapter(Context context, List<ChannelBean> mArray) {
        this.context = context;
        this.mArray = mArray;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mArray == null ? 0 : mArray.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.srv_item_demo, parent, false);
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(mArray.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(ChannelBean channelBean) {
            this.tvTitle.setText(channelBean.getName());
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
