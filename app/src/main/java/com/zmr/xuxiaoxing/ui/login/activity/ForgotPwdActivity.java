package com.zmr.xuxiaoxing.ui.login.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.util.AbStrUtil;
import com.xiaoxing.common.util.ToastUtil;
import com.xiaoxing.common.view.clear_edit_text.ClearEditText;
import com.zmr.xuxiaoxing.R;
import com.zmr.xuxiaoxing.base.AppBaseActivity;
import com.zmr.xuxiaoxing.ui.login.model.SendSMS;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 描述：忘记密码
 * 作者：xiaoxing on 17/4/7 15:07
 * 邮箱：2235445233@qq.com
 */
public class ForgotPwdActivity extends AppBaseActivity {

   @BindView(R.id.cet_tel)
    ClearEditText mCetTel;
   @BindView(R.id.btn_next_step)
    Button mBtnNextStep;

    private String mTel;

    @Override
    public int bindLayout() {
        return R.layout.activity_forgot_pwd;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        setHeaderTitle(R.string.find_pwd);

    }

    @OnClick(R.id.btn_next_step)
    public void onClick() {
        next();
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
    }


    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {

        httpResultSendCheckCode(url, jo);

    }

    /**
     * 发送验证码
     *
     * @param url
     * @param jo
     */
    private void httpResultSendCheckCode(String url, JSONObject jo) {
        if (url.equals(BaseConstant.SENE_CHECK_CODE) && jo != null) {
            try {
                SendSMS sendSMS = new SendSMS(jo.toString());

                if (sendSMS != null) {


                    if (sendSMS.getCode().equals("200")) {

                        ToastUtil.showMessage(this, "验证码发送成功");
                        Bundle bundle = new Bundle();
                        bundle.putString("tel", mTel);
                        bundle.putString("type", "forgot");

                        startBundleActivity(bundle, SetPwdActivity.class);
                    } else {

                        ToastUtil.showMessage(this, sendSMS.getMsg());

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 下一步
     */
    private void next() {

        if (checkTel()) {
            sendCheckCode();

        }
    }

    /**
     * 发送验证码
     */
    private void sendCheckCode() {
        showGifdialog();
        BaseApi.sendCheckCode(this, mTel);
    }

    /**
     * 验证手机号
     *
     * @return
     */
    private boolean checkTel() {
        mTel = getEidtTextValue(mCetTel);

        if (AbStrUtil.isEmpty(mTel)) {
            ToastUtil.showMessage(this, "手机号不能为空");
            return false;
        } else if (!AbStrUtil.isMobileNo(mTel)) {
            ToastUtil.showMessage(this, "手机号格式错误");
            return false;
        }
        return true;
    }

}
