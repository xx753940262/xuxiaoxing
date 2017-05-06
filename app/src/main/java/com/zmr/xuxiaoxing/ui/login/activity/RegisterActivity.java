package com.zmr.xuxiaoxing.ui.login.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.util.AbStrUtil;
import com.xiaoxing.common.util.ToastUtil;
import com.xiaoxing.common.view.clear_edit_text.ClearEditText;
import com.zmr.xuxiaoxing.R;
import com.zmr.xuxiaoxing.ui.login.model.SendSMS;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 描述：注册
 * 作者：xiaoxing on 17/4/7 15:32
 * 邮箱：2235445233@qq.com
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.cet_tel)
    ClearEditText mCetTel;
    @BindView(R.id.cb_agree)
    CheckBox mCbAgree;
    @BindView(R.id.tv_xie_yi)
    TextView mTvXieYi;
    @BindView(R.id.btn_next_step)
    Button mBtnNextStep;

    private String mTel;

    @Override
    public int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        setHeaderTitle(R.string.register);
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);

    }


    @OnClick({R.id.tv_xie_yi, R.id.btn_next_step})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_xie_yi:
                startActivity(XieYiActivity.class);
                break;
            case R.id.btn_next_step:
                next();
                break;
        }
    }

    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {

        httpResultSendSMS(url, jo);

    }

    /**
     * 发送短信
     *
     * @param url
     * @param jo
     */
    private void httpResultSendSMS(String url, JSONObject jo) {
        if (url.equals(BaseConstant.SEND_SMS) && jo != null) {
            try {
                SendSMS sendSMS = new SendSMS(jo.toString());

                if (sendSMS != null) {


                    if (sendSMS.getCode().equals("200")) {

                        ToastUtil.showMessage(this, "验证码发送成功");

                        Bundle bundle = new Bundle();
                        bundle.putString("tel", mTel);
                        bundle.putString("type", "register");

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

        if (checkTelCheckBox()) {

            sendSMS();


        }
    }

    /**
     * 发送短信
     */
    private void sendSMS() {
        showGifdialog();
        BaseApi.sendSms(this, mTel);
    }

    /**
     * 验证
     *
     * @return
     */
    private boolean checkTelCheckBox() {
        mTel = getEidtTextValue(mCetTel);

        if (AbStrUtil.isEmpty(mTel)) {
            ToastUtil.showMessage(this, "手机号不能为空");
            return false;
        } else if (!AbStrUtil.isMobileNo(mTel)) {
            ToastUtil.showMessage(this, "手机号格式错误");
            return false;
        } else if (!mCbAgree.isChecked()) {
            ToastUtil.showMessage(this, "请先阅读并同意学习乐使用条款及隐私协议");
            return false;
        }
        return true;
    }


}
