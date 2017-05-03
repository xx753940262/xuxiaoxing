package com.xiaoxing.module.address.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ab.R;
import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.dialog.loadingDialog.LoadingDialogUtil;
import com.xiaoxing.common.util.AbStrUtil;
import com.xiaoxing.common.util.ToastUtil;
import com.xiaoxing.common.view.clear_edit_text.ClearEditText;
import com.xiaoxing.module.address.model.AddressSet;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/16 10:44
 * 邮箱：2235445233@qq.com
 */
public class EditOrAddNewAddressActivity extends BaseActivity {

    private TextView tv_jie_dao;
    private ClearEditText mCeUsername;
    private ClearEditText mCeMobile;
    private TextView mDiQu;
    private ClearEditText mCeAddress;

    private CheckBox checkBox;

    private Button btn_save;


    private String districtId; //区id
    private Bundle mBundle = new Bundle();

    private String mProvName, mCityName, mDistrictName, mJieDaoName;
    private String mAddressId = "";
    private String mAddressType;

    @Override
    public int bindLayout() {
        return R.layout.activity_add_new_address;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        tv_jie_dao = (TextView) view.findViewById(R.id.tv_jie_dao);
        mDiQu = (TextView) view.findViewById(R.id.tv_di_qu);
        mCeUsername = (ClearEditText) view.findViewById(R.id.ce_username);
        mCeMobile = (ClearEditText) view.findViewById(R.id.ce_mobile);
        mCeAddress = (ClearEditText) view.findViewById(R.id.ce_address);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        btn_save = (Button) view.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        setHeaderBack();
        getBundleValue();
        getEditAddressBundle();
    }

    private void getBundleValue() {

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            mAddressType = bundle.getString("mAddressType");
        }
    }

    private void getEditAddressBundle() {
        String username = "";
        String phone = "";
        String address = "";
        String street = "";
        String detail = "";
        setHeaderTitle(R.string.add_shou_huo_address);
        Bundle mEditAddressBundle = getIntent().getExtras();
        if (mEditAddressBundle != null) {
            setHeaderTitle(R.string.edit_new_address);
            username = mEditAddressBundle.getString("username");
            phone = mEditAddressBundle.getString("phone");
            street = mEditAddressBundle.getString("street");
            address = mEditAddressBundle.getString("address");
            detail = mEditAddressBundle.getString("detail");
            mAddressId = mEditAddressBundle.getString("addressId");

            mCeUsername.setText(username);
            mCeMobile.setText(phone);
            mCeAddress.setText(detail);
            mDiQu.setText(address);
            tv_jie_dao.setText(street);
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_area) {
            mBundle.putString("districtId", "");
            getOperation().startActivityForResult(mBundle, 100, AreaSelectActivity.class);

        } else if (i == R.id.ll_jie_dao) {
            if (!getTextViewValue(mDiQu).equals("")) {
                mBundle.putString("districtId", districtId);
                getOperation().startActivityForResult(mBundle, 101, AreaSelectActivity.class);
            } else {
                ToastUtil.showMessage(this, "请先选择所在地区");
            }

            submitData();

        } else if (i == R.id.btn_save) {
            submitData();

        }

    }

    private void submitData() {
        String ceUsername = getEidtTextValue(mCeUsername);
        String ceMobile = getEidtTextValue(mCeMobile);
        String ceAddress = getEidtTextValue(mCeAddress);
        String cesDiqu = getTextViewValue(mDiQu);
        String ceJiedao = getTextViewValue(tv_jie_dao);
        String uid = sHelper.getString(BaseConstant.UID);
        String token = sHelper.getString(BaseConstant.TOKEN);

        if (AbStrUtil.isEmpty(ceUsername)) {
            ToastUtil.showMessage(this, "收货人姓名不能为空");
            return;
        } else if (AbStrUtil.isEmpty(ceMobile)) {
            ToastUtil.showMessage(this, "联系人电话不能为空");
            return;
        } else if (AbStrUtil.isEmpty(getTextViewValue(mDiQu))) {
            ToastUtil.showMessage(this, "所在区不能为空");
            return;
        } else if (AbStrUtil.isEmpty(ceAddress)) {
            ToastUtil.showMessage(this, "详细地址不能为空");
            return;
        } else if (!AbStrUtil.isMobileNo(ceMobile)) {
            ToastUtil.showMessage(this, "联系人电话格式错误");
            return;
        }

        if (mAddressType.equals("addAddress")) {
            LoadingDialogUtil.showGifdialog(this);
            BaseApi.addressSet(this, token, uid, ceUsername, "", ceMobile, cesDiqu, ceJiedao, ceAddress, checkBox.isChecked() == true ? "1" : "0");

        } else if (mAddressType.equals("updateAddress")) {

            LoadingDialogUtil.showGifdialog(this);
            BaseApi.addressUpdate(this, token, uid, ceUsername, "", ceMobile, cesDiqu, ceJiedao, ceAddress, checkBox.isChecked() == true ? "1" : "0", mAddressId);


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 选择地区返回
         */
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            final Map map = (Map) data.getSerializableExtra("addressInfo");
            mProvName = getString(map, "provName", "");
            mCityName = getString(map, "cityName", "");
            mDistrictName = getString(map, "districtName", "");

            String areaName = String.format("%s %s %s", mProvName, mCityName, mDistrictName);
            mDiQu.setText(areaName);
            districtId = String.format("%s", getString(map, "districtId", ""));

        }
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            final Map map = (Map) data.getSerializableExtra("addressInfo");
            mJieDaoName = getString(map, "jieDaoName", "");
            String jieDaoName = String.format("%s", mJieDaoName);
            tv_jie_dao.setText(jieDaoName);
        }

    }

    private Pattern intPattern = Pattern.compile("^[-\\+]?[\\d]*\\.0*$");

    public String getString(Map map, String key, String defaultValue) {
        Object obj = map.get(key);
        return obj == null ? defaultValue : (obj instanceof Number && intPattern.matcher(obj.toString()).matches() ? String.valueOf(Long.valueOf(((Number) obj).longValue())) : obj.toString());
    }


    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {

        if (url.equals(BaseConstant.ADDRESS_SET) && jo != null) {
            AddressSet addressSet = new AddressSet(jo.toString());

            if (addressSet != null) {


                if (addressSet.getCode().equals("200")) {

                    ToastUtil.showMessage(this, "添加成功");
                    Intent mIntent = new Intent();
                    // 设置结果，并进行传送
                    setResult(101, mIntent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                    finish();//此处一定要调用finish()方法

                } else {
                    ToastUtil.showMessage(this, addressSet.getMsg());
                }
            }

        }
        if (url.equals(BaseConstant.ADDRESS_UPDATE) && jo != null) {
            AddressSet addressSet = new AddressSet(jo.toString());

            if (addressSet != null) {


                if (addressSet.getCode().equals("200")) {

                    ToastUtil.showMessage(this, "修改成功");
                    Intent mIntent = new Intent();
                    // 设置结果，并进行传送
                    setResult(101, mIntent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                    finish();//此处一定要调用finish()方法

                } else {
                    ToastUtil.showMessage(this, addressSet.getMsg());
                }
            }

        }

    }

}
