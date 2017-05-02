package com.xiaoxing.module.login.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ab.R;
import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.dialog.loadingDialog.LoadingDialogUtil;
import com.xiaoxing.common.http.OnMessageResponse;
import com.xiaoxing.common.util.AbStrUtil;
import com.xiaoxing.common.util.TimeCountUtil;
import com.xiaoxing.common.util.ToastUtil;
import com.xiaoxing.common.view.clear_edit_text.ClearEditText;
import com.xiaoxing.module.login.model.Register;
import com.xiaoxing.module.login.model.SendSMS;
import com.xiaoxing.module.login.model.UpdatePwd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：设置密码
 * 作者：xiaoxing on 17/4/7 15:11
 * 邮箱：2235445233@qq.com
 */
public class SetPwdActivity extends BaseActivity implements OnMessageResponse,View.OnClickListener {

    ClearEditText cdtYanZhengMa;
    ClearEditText cetPwd;
    TextView btnCountdown;


    private String mTel, mVerityCode, mPwd, mType;

    @Override
    public int bindLayout() {
        return R.layout.activity_set_pwd;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        cdtYanZhengMa = (ClearEditText) view.findViewById(R.id.cdt_yan_zheng_ma);
        cetPwd = (ClearEditText) view.findViewById(R.id.cet_pwd);
        btnCountdown = (ClearEditText) view.findViewById(R.id.btn_countdown);
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_kai_qi) {
            register();

        } else if (i == R.id.btn_countdown) {
            reCountDown();

        }
    }

    /**
     * 执行注册
     */
    private void register() {
        if (checkCodePwd()) {

//            Api.verityCode(this, mTel, mVerityCode);

            if (mType.equals("register")) {

                LoadingDialogUtil.showGifdialog(this);
                BaseApi.register(this, mTel, mPwd, mVerityCode);
            } else if (mType.equals("forgot")) {

                LoadingDialogUtil.showGifdialog(this);
                BaseApi.updatePassword(this, mTel, mPwd, mVerityCode);
            }
        }
    }

    /**
     * 检查验证码和密码
     *
     * @return
     */
    private boolean checkCodePwd() {
        mVerityCode = getEidtTextValue(cdtYanZhengMa);
        mPwd = getEidtTextValue(cetPwd);

        if (AbStrUtil.isEmpty(mVerityCode)) {
            ToastUtil.showMessage(this, "验证码不能为空");
            return false;
        } else if (AbStrUtil.isEmpty(mPwd)) {
            ToastUtil.showMessage(this, "密码不能为空");
            return false;
        } else if (mPwd.length() < 6) {
            ToastUtil.showMessage(this, "密码不能少于6位");
            return false;
        }

        return true;
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        setHeaderBack();
        setHeaderTitle(R.string.set_pwd);
        getBundleValue();

    }

    /**
     * 获取bundleValue
     */
    private void getBundleValue() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mTel = bundle.getString("tel");
            mType = bundle.getString("type");
            countDown();
        }

    }

    /**
     * 开始计时
     */
    private void countDown() {
        new TimeCountUtil(btnCountdown, BaseConstant.SECURITY_CODE_TIME, 1000).start();
    }
    /**
     * 重新发送
     */
    private void reCountDown() {
        new TimeCountUtil(btnCountdown, BaseConstant.SECURITY_CODE_TIME, 1000).start();
        LoadingDialogUtil.showGifdialog(this);
        BaseApi.sendCheckCode(this, mTel);
    }

    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {
        if (url.equals(BaseConstant.REGISTER) && jo != null) {
            Register register = new Register(jo.toString());

            if (register != null) {


                if (register.getCode().equals("200")) {

                    ToastUtil.showMessage(this, "注册成功");

                    sHelper.putString(BaseConstant.USERNAME, mTel);
                    startActivity(LoginActivity.class);
                    finish();
                }else {

                    ToastUtil.showMessage(this, register.getMsg());

                }
            }

        }
        if (url.equals(BaseConstant.UPDATE_PASSWORD) && jo != null) {
            UpdatePwd updatePwd = new UpdatePwd(jo.toString());

            if (updatePwd != null) {


                if (updatePwd.getCode().equals("200")) {

                    ToastUtil.showMessage(this, "密码修改成功");

                    startActivity(LoginActivity.class);
                    finish();
                    sHelper.removeString(BaseConstant.PASSWORD);
                }else {

                    ToastUtil.showMessage(this, updatePwd.getMsg());

                }
            }

        }
        if (url.equals(BaseConstant.SENE_CHECK_CODE) && jo != null) {
            SendSMS sendSMS = new SendSMS(jo.toString());

            if (sendSMS != null) {


                if (sendSMS.getCode().equals("200")) {

                    ToastUtil.showMessage(this, "验证码发送成功");
                } else {

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
