package com.xiaoxing.module.shopcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoxing.common.image.abimage.AbImageLoader;
import com.xiaoxing.common.util.AbViewHolder;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/15 19:15
 * 邮箱：2235445233@qq.com
 */
public class Adapter_QueRenDingDanGoods_ErJi_List extends BaseAdapter {

    private Context mContext;
    //xml转View对象
    private LayoutInflater mInflater;
    //单行的布局
    private int mResource;
    //列表展现的数据
    private List mData;
    //Map中的key
    private String[] mFrom;
    //view的id
    private int[] mTo;
    //图片下载器
    private AbImageLoader mAbImageLoader = null;

    /**
     * 构造方法
     *
     * @param context
     * @param data     列表展现的数据
     * @param resource 单行的布局
     * @param from     Map中的key
     * @param to       view的id
     */
    public Adapter_QueRenDingDanGoods_ErJi_List(Context context, List data,
                                                int resource, String[] from, int[] to) {
        this.mContext = context;
        this.mData = data;
        this.mResource = resource;
        this.mFrom = from;
        this.mTo = to;
        //用于将xml转为View
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //图片下载器
        mAbImageLoader = AbImageLoader.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //使用自定义的list_items作为Layout
            convertView = mInflater.inflate(mResource, parent, false);
        }

        ImageView itemsIcon = AbViewHolder.get(convertView, mTo[0]);
        TextView itemsTitle = AbViewHolder.get(convertView, mTo[1]);
        TextView itemsColorSize = AbViewHolder.get(convertView, mTo[2]);
        TextView itemsPrice = AbViewHolder.get(convertView, mTo[3]);
        TextView itemsDiscountPrice = AbViewHolder.get(convertView, mTo[4]);
        TextView itemsBuyNum = AbViewHolder.get(convertView, mTo[5]);

        //获取该行的数据
        final Map<String, Object> obj = (Map<String, Object>) mData.get(position);
        String imageUrl = (String) obj.get("itemsImg");
        itemsTitle.setText((String) obj.get("itemsTitle"));
        itemsColorSize.setText((String) obj.get("itemsColorSize"));
        itemsPrice.setText("￥"+obj.get("itemsPrice") + "");
        itemsDiscountPrice.setText(obj.get("itemsDiscountPrice") + "");
        itemsBuyNum.setText(obj.get("itemsBuyNum") + "");
//        //设置加载中的View
//        View loadingView = convertView.findViewById(R.id.progressBar);
        //图片的下载
        mAbImageLoader.display(itemsIcon, imageUrl, 150, 150);

        return convertView;
    }

}
