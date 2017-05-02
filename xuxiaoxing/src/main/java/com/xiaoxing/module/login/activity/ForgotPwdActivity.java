package com.xiaoxing.module.login.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ab.R;
import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.dialog.loadingDialog.LoadingDialogUtil;
import com.xiaoxing.common.http.OnMessageResponse;
import com.xiaoxing.common.util.AbStrUtil;
import com.xiaoxing.common.util.ToastUtil;
import com.xiaoxing.common.view.clear_edit_text.ClearEditText;
import com.xiaoxing.module.login.model.SendSMS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：忘记密码
 * 作者：xiaoxing on 17/4/7 15:07
 * 邮箱：2235445233@qq.com
 */
public class ForgotPwdActivity extends BaseActivity implements OnMessageResponse,View.OnClickListener {

    ClearEditText cetTel;

    private String mTel;

    @Override
    public int bindLayout() {
        return R.layout.activity_forgot_pwd;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        cetTel = (ClearEditText) view.findViewById(R.id.cet_tel);
    }


    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.btn_next_step) {
            next();

        }
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        setHeaderBack();
        setHeaderTitle(R.string.find_pwd);
    }

    /**
     * 下一步
     */
    private void next() {

        if (checkTel()) {
            LoadingDialogUtil.showGifdialog(this);
            BaseApi.sendCheckCode(this, mTel);



        }
    }

    /**
     * 验证手机号
     * @return
     */
    private boolean checkTel() {
        mTel = getEidtTextValue(cetTel);

        if (AbStrUtil.isEmpty(mTel)) {
            ToastUtil.showMessage(this, "手机号不能为空");
            return false;
        } else if (!AbStrUtil.isMobileNo(mTel)) {
            ToastUtil.showMessage(this, "手机号格式错误");
            return false;
        }
        return true;
    }

    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {
        if (url.equals(BaseConstant.SENE_CHECK_CODE) && jo != null) {
            SendSMS sendSMS = new SendSMS(jo.toString());

            if (sendSMS != null) {


                if (sendSMS.getCode().equals("200")) {

                    ToastUtil.showMessage(this, "验证码发送成功");
                    Bundle bundle = new Bundle();
                    bundle.putString("tel", mTel);
                    bundle.putString("type", "forgot");

                    startBundleActivity(bundle, SetPwdActivity.class);
                }else {

                    ToastUtil.showMessage(this, sendSMS.getMsg());

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
