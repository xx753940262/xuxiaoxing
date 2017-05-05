package com.xiaoxing.module.address.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.R;
import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.Operation;
import com.xiaoxing.common.dialog.loadingDialog.LoadingDialogUtil;
import com.xiaoxing.common.dialog.materialDialog.MaterialDialogUtil;
import com.xiaoxing.common.image.abimage.AbImageLoader;
import com.xiaoxing.common.util.AbViewHolder;
import com.xiaoxing.common.util.SharedPreferencesHelper;
import com.xiaoxing.module.address.activity.EditOrAddNewAddressActivity;

import java.util.List;
import java.util.Map;

/**
 * 描述：改变地址
 * 作者：xiaoxing on 17/4/16 09:51
 * 邮箱：2235445233@qq.com
 */

public class Adapter_Address_Change extends BaseAdapter {

    private Activity mContext;
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

    private String mUid;
    private String mToken;
    private Operation mOperation;
    private SharedPreferencesHelper sHelper;

    /**
     * 构造方法
     *
     * @param context
     * @param data     列表展现的数据
     * @param resource 单行的布局
     * @param from     Map中的key
     * @param to       view的id
     */
    public Adapter_Address_Change(Activity context, List data,
                                  int resource, String[] from, int[] to, String uid, String token, Operation operation, SharedPreferencesHelper sHelper) {
        this.mContext = context;
        this.mData = data;
        this.mResource = resource;
        this.mFrom = from;
        this.mTo = to;
        this.mUid = uid;
        this.mToken = token;
        this.mOperation = operation;
        this.sHelper = sHelper;
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

//        ImageView itemsIcon = AbViewHolder.get(convertView, mTo[0]);
        TextView itemsName = AbViewHolder.get(convertView, mTo[0]);
        TextView itemsPhone = AbViewHolder.get(convertView, mTo[1]);
        TextView itemsAddress = AbViewHolder.get(convertView, mTo[2]);
        TextView itemsDeleteAddress = AbViewHolder.get(convertView, mTo[3]);
        TextView itemsTvMoren = AbViewHolder.get(convertView, mTo[4]);
        TextView itemsEdit = AbViewHolder.get(convertView, mTo[5]);
        ImageView itemsImgMoren = AbViewHolder.get(convertView, mTo[6]);
        LinearLayout itemsLLMoren = AbViewHolder.get(convertView, mTo[7]);

        //获取该行的数据
        final Map<String, Object> obj = (Map<String, Object>) mData.get(position);
//        String imageUrl = (String) obj.get("itemsIcon");
        final String username = (String) obj.get("itemsName");
        final String phone = (String) obj.get("itemsPhone");
        final String address = (String) obj.get("itemsAddress");
        final String detail = (String) obj.get("itemsDetail");
        final String addressId = (String) obj.get("itemsAddressId");
        final String userAddressId = (String) obj.get("itemsUserAddressId");
        final String moren = (String) obj.get("itemsMoren");
        final String street = (String) obj.get("itemsStreet");

        itemsName.setText(username);
        itemsPhone.setText(phone);
        itemsAddress.setText(address + detail);
        if (moren.equals("1")) {
            itemsImgMoren.setImageDrawable(mContext.getResources().getDrawable(R.drawable.selected));
            itemsTvMoren.setTextColor(mContext.getResources().getColor(R.color.morendizhi));
        } else {
            itemsImgMoren.setImageDrawable(mContext.getResources().getDrawable(R.drawable.unselected));
            itemsTvMoren.setTextColor(mContext.getResources().getColor(R.color.black));
        }


        itemsDeleteAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDialogUtil.showDialogTwoButton(mContext, "确定删除地址？", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MaterialDialogUtil.dismissDialogButton();
                        LoadingDialogUtil.showGifdialog(mContext);
                        BaseApi.addressDel(mContext, mToken, addressId);
                    }
                });
            }
        });

        itemsLLMoren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = mContext.getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                bundle.putString("phone", phone);
                bundle.putString("address", address);
                bundle.putString("street", street);
                bundle.putString("detail", detail);
                bundle.putString("addressId", addressId);
                bundle.putString("mAddressType", "updateAddress");
                intent.putExtra("data", bundle);
                Intent mIntent = new Intent();
                // 设置结果，并进行传送
//                mOperation.startActivityForResult(bundle, 100, QueRenDingDanActivity.class);
                //设置当前Activity的结果码
                mContext.setResult(105, intent);
                mContext.finish();


                BaseApi.addressSetDefault(mContext, mToken, mUid, addressId);
//                sHelper.putString("country_id", itemsCountry_id);
//                sHelper.putString("province_id", itemsProvince_id);
//                sHelper.putString("city_id", itemsCity_id);
//                sHelper.putString("district_id", itemsDistrict_id);
//                sHelper.putString("shouhuoren", username);
//                sHelper.putString("shouhuodizhi", provice + city + district + address);
//                sHelper.putString("dizhiid", addressId);

//                Intent intent1 = new Intent(mContext, Activity_QueRenDingDan.class);
//                intent1.putExtra("M_QueRenDingDanGoods", mQueRenDingDanGoods);
//                mContext.startActivity(intent1);
//                mContext.finish();

            }
        });
        itemsEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                bundle.putString("phone", phone);
                bundle.putString("address", address);
                bundle.putString("street", street);
                bundle.putString("detail", detail);
                bundle.putString("addressId", addressId);
                bundle.putString("mAddressType", "updateAddress");
                mOperation.startActivityForResult(bundle, 100, EditOrAddNewAddressActivity.class);
            }
        });
//        //设置加载中的View
//        View loadingView = convertView.findViewById(R.id.progressBar);
//        //图片的下载
//        mAbImageLoader.display(itemsIcon, loadingView, imageUrl, 150, 150);

        return convertView;
    }

}
