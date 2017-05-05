package com.zmr.xuxiaoxing.ui.login.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.dialog.loadingDialog.LoadingDialogUtil;
import com.xiaoxing.common.util.AbStrUtil;
import com.xiaoxing.common.util.TimeCountUtil;
import com.xiaoxing.common.util.ToastUtil;
import com.xiaoxing.common.view.clear_edit_text.ClearEditText;
import com.zmr.xuxiaoxing.R;
import com.zmr.xuxiaoxing.ui.login.model.Register;
import com.zmr.xuxiaoxing.ui.login.model.SendSMS;
import com.zmr.xuxiaoxing.ui.login.model.UpdatePwd;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 描述：设置密码
 * 作者：xiaoxing on 17/4/7 15:11
 * 邮箱：2235445233@qq.com
 */
public class SetPwdActivity extends BaseActivity {

    @Bind(R.id.cdt_yan_zheng_ma)
    ClearEditText mCdtYanZhengMa;
    @Bind(R.id.btn_countdown)
    TextView mBtnCountdown;
    @Bind(R.id.cet_pwd)
    ClearEditText mCetPwd;
    @Bind(R.id.btn_kai_qi)
    Button mBtnKaiQi;


    private String mTel, mVerityCode, mPwd, mType;

    @Override
    public int bindLayout() {
        return R.layout.activity_set_pwd;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        setHeaderTitle(R.string.set_pwd);
    }

    @OnClick({R.id.btn_countdown, R.id.btn_kai_qi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_countdown:
                reCountDown();
                break;
            case R.id.btn_kai_qi:
                register();
                break;
        }
    }

    @Override
    public void getBundleValue(Bundle bundle) {
        super.getBundleValue(bundle);

        mTel = bundle.getString("tel");
        mType = bundle.getString("type");
        countDown();
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);

    }

    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {
        httpResultRegister(url, jo);
        httpResultUpdatePwd(url, jo);
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
     * 更新密码
     *
     * @param url
     * @param jo
     */
    private void httpResultUpdatePwd(String url, JSONObject jo) {
        if (url.equals(BaseConstant.UPDATE_PASSWORD) && jo != null) {
            try {
                UpdatePwd updatePwd = new UpdatePwd(jo.toString());

                if (updatePwd != null) {


                    if (updatePwd.getCode().equals("200")) {

                        ToastUtil.showMessage(this, "密码修改成功");

                        startActivity(LoginActivity.class);
                        finish();
                        sHelper.removeString(BaseConstant.PASSWORD);
                    } else {

                        ToastUtil.showMessage(this, updatePwd.getMsg());

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 注册
     *
     * @param url
     * @param jo
     */
    private void httpResultRegister(String url, JSONObject jo) {
        if (url.equals(BaseConstant.REGISTER) && jo != null) {
            try {
                Register register = new Register(jo.toString());

                if (register != null) {


                    if (register.getCode().equals("200")) {

                        ToastUtil.showMessage(this, "注册成功");

                        sHelper.putString(BaseConstant.USERNAME, mTel);
                        startActivity(LoginActivity.class);
                        finish();
                    } else {

                        ToastUtil.showMessage(this, register.getMsg());

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * 执行注册
     */
    private void register() {
        if (checkCodePwd()) {

            regOrUpdatePwd();
        }
    }

    /**
     * 注册或修改密码
     */
    private void regOrUpdatePwd() {
        showGifdialog();
        if (mType.equals("register")) {

            BaseApi.register(this, mTel, mPwd, mVerityCode);
        } else if (mType.equals("forgot")) {

            BaseApi.updatePassword(this, mTel, mPwd, mVerityCode);
        }
    }

    /**
     * 检查验证码和密码
     *
     * @return
     */
    private boolean checkCodePwd() {
        mVerityCode = getEidtTextValue(mCdtYanZhengMa);
        mPwd = getEidtTextValue(mCetPwd);

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

    /**
     * 开始计时
     */
    private void countDown() {
        new TimeCountUtil(mBtnCountdown, BaseConstant.SECURITY_CODE_TIME, 1000).start();
    }

    /**
     * 重新发送
     */
    private void reCountDown() {
        new TimeCountUtil(mBtnCountdown, BaseConstant.SECURITY_CODE_TIME, 1000).start();
        LoadingDialogUtil.showGifdialog(this);
        BaseApi.sendCheckCode(this, mTel);
    }


}
