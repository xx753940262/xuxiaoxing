package com.xiaoxing.module.address.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ab.R;
import com.xiaoxing.common.base.BaseActivityList;
import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.dialog.loadingDialog.LoadingDialogUtil;
import com.xiaoxing.common.http.OnMessageResponse;
import com.xiaoxing.common.util.ToastUtil;
import com.xiaoxing.common.view.pullview.AbPullToRefreshView;
import com.xiaoxing.module.address.adapter.Adapter_Address_Change;
import com.xiaoxing.module.address.model.AddressDelete;
import com.xiaoxing.module.address.model.AddressGet;
import com.xiaoxing.module.address.model.AddressSetDefault;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：描述：改变地址
 * 作者：xiaoxing on 17/4/16 09:45
 * 邮箱：2235445233@qq.com
 */
public class Activity_ChangeAddress extends BaseActivityList implements OnMessageResponse, View.OnClickListener {

    private Button btn_add_new_address;

    @Override
    public int bindLayout() {
        return R.layout.activity_change_address;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        // 获取ListView对象
        mAbPullToRefreshView = (AbPullToRefreshView) this
                .findViewById(R.id.mPullRefreshView);
        mListView = (ListView) this.findViewById(R.id.lv_address);
        btn_add_new_address = (Button) this.findViewById(R.id.btn_add_new_address);
        btn_add_new_address.setOnClickListener(this);

        // 使用自定义的Adapter
        myListViewAdapter = new Adapter_Address_Change(this, list,
                R.layout.item_change_address,
                new String[]{"姓名", "电话", "地址", "删除", "配送至", "编辑", "默认", "ll默认"},
                new int[]{R.id.tv_name, R.id.tv_phone, R.id.tv_address, R.id.tv_delete_address,
                        R.id.tv_moren, R.id.tv_edit, R.id.img_moren, R.id.ll_morendizhi},
                sHelper.getString(BaseConstant.UID), sHelper.getString(BaseConstant.TOKEN), getOperation(), sHelper);
        mListView.setAdapter(myListViewAdapter);
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        setHeaderBack();
        setHeaderTitle(R.string.shouhuo_address);
    }

    @Override
    public void initData() {
        super.initData();
        getAddressList();
    }

    @Override
    public void getMoreData() {
        super.getMoreData();
        getAddressList();
    }

    private void getAddressList() {
        list.clear();
        myListViewAdapter.notifyDataSetChanged();
        LoadingDialogUtil.showGifdialog(this);
        BaseApi.addressGet(this, sHelper.getString(BaseConstant.TOKEN), sHelper.getString(BaseConstant.UID));
    }

    private void getDelAddressList() {
        list.clear();
        myListViewAdapter.notifyDataSetChanged();
        BaseApi.addressGet(this, sHelper.getString(BaseConstant.TOKEN), sHelper.getString(BaseConstant.UID));
    }


    @Override
    public void onItemClick(AdapterView adapterView, View view, int i, long l) {
        super.onItemClick(adapterView, view, i, l);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_add_new_address) {
            Bundle bundle = new Bundle();
            bundle.putString("mAddressType", "addAddress");
            getOperation().startActivityForResult(bundle, 101, Activity_EditOrAddNewAddress.class);
//                finish();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {

            getAddressList();
        }
    }


    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {

        if (url.equals(BaseConstant.ADDRESS_GET) && jo != null) {
            AddressGet addressGet = new AddressGet(jo.toString());

            if (addressGet != null) {


                if (addressGet.getCode().equals("200")) {

                    List<AddressGet.DataBean> contentBeanList = addressGet.getData();

                    if (contentBeanList.size() > 0) {

                        for (AddressGet.DataBean contentBeanFor : contentBeanList) {
                            Map<String, Object> map = null;
                            map = new HashMap<>();
                            map.put("itemsName", contentBeanFor.getAccept_name());
                            map.put("itemsPhone", contentBeanFor.getTelephone());
                            map.put("itemsAddress", contentBeanFor.getAddress());
                            map.put("itemsStreet", contentBeanFor.getStreet());
                            map.put("itemsDetail", contentBeanFor.getDetail());
                            map.put("itemsAddressId", contentBeanFor.getId());
                            map.put("itemsUserAddressId", contentBeanFor.getUser_id());
                            map.put("itemsMoren", contentBeanFor.getDefaultX());

                            newList.add(map);
                        }
                        currentPageAdd(newList);
                        myHandler.sendEmptyMessage(mLoadType);
                    } else {
                        closeAbPullToRefreshView(mLoadType);
                    }
                } else {
                    ToastUtil.showMessage(this, addressGet.getMsg());
                }
            }

        }
        if (url.equals(BaseConstant.ADDRESS_DEL) && jo != null) {
            AddressDelete addressDelete = new AddressDelete(jo.toString());

            if (addressDelete != null) {


                if (addressDelete.getCode().equals("200")) {

                    ToastUtil.showMessage(Activity_ChangeAddress.this, "删除地址成功");
                    getDelAddressList();
                } else {
                    ToastUtil.showMessage(this, addressDelete.getMsg());
                }
            }

        }
        if (url.equals(BaseConstant.ADDRESS_SETDEFAULT) && jo != null) {
            AddressSetDefault addressSetDefault = new AddressSetDefault(jo.toString());

            if (addressSetDefault != null) {


                if (addressSetDefault.getCode().equals("200")) {

                    ToastUtil.showMessage(Activity_ChangeAddress.this, "设置默认地址成功");
//                    getDelAddressList();
                } else {
                    ToastUtil.showMessage(this, addressSetDefault.getMsg());
                }
            }

        }

    }

    @Override
    public void onMessageResponse(String url, JSONArray jo) throws JSONException {

    }

    @Override
    public void onMessageResponse(String url, String str) throws Exception {

    }
}

