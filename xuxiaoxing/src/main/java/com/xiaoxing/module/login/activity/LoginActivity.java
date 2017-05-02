package com.xiaoxing.module.login.activity;

import android.content.Context;
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
import com.xiaoxing.module.login.model.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：登录
 * 作者：xiaoxing on 17/4/7 13:03
 * 邮箱：2235445233@qq.com
 */
public class LoginActivity extends BaseActivity implements OnMessageResponse,View.OnClickListener {


    ClearEditText cedTel;
    ClearEditText cetPwd;
    private String mTel, mPwd;

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        cedTel = (ClearEditText) view.findViewById(R.id.ced_tel);
        cetPwd = (ClearEditText) view.findViewById(R.id.cet_pwd);
    }


    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);

        setHeaderBack();
        setHeaderTitle(R.string.login);
        autoLogin();
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_login) {
            login();

        } else if (i == R.id.tv_forgot_pwd) {
            startActivity(ForgotPwdActivity.class);

        } else if (i == R.id.tv_reg) {
            startActivity(RegisterActivity.class);

        }
    }

    /**
     * 检查用户名和密码是否为空
     */
    private boolean checkTelPwd() {

        mTel = getEidtTextValue(cedTel);
        mPwd = getEidtTextValue(cetPwd);

        if (AbStrUtil.isEmpty(mTel)) {
            ToastUtil.showMessage(this, "手机号不能为空");
            return false;
        } else if (!AbStrUtil.isMobileNo(mTel)) {
            ToastUtil.showMessage(this, "手机号格式错误");
            return false;
        } else if (AbStrUtil.isEmpty(mPwd)) {
            ToastUtil.showMessage(this, "密码不能为空");
            return false;
        }
        return true;
    }

    /**
     * 登录
     */
    private void login() {
        if (checkTelPwd()) {
            loginPost();
        }
    }

    /**
     * 提交登登录信息
     */
    private void loginPost() {

        LoadingDialogUtil.showGifdialog(this);
        BaseApi.login(this, mTel, mPwd);
    }

    /**
     * 自动登陆
     */
    public void autoLogin() {
        String username = sHelper.getString(BaseConstant.USERNAME);
        String pwd = sHelper.getString(BaseConstant.PASSWORD);
        if (username != null && pwd != null) {

            if (BaseConstant.AUTO_LOGIN) {
                cedTel.setText(username);
                cetPwd.setText(pwd);
                login();
            } else {
                cedTel.setText(sHelper.getString(BaseConstant.USERNAME));
            }
        }
    }


    /**
     * 保存登陆的信息
     */
    private void saveLoginInfo(Login m_login) {

        sHelper.putString(BaseConstant.USERNAME, mTel);
        sHelper.putString(BaseConstant.PASSWORD, mPwd);
        sHelper.putBoolean(BaseConstant.IS_LOGIN, true);
        sHelper.putString(BaseConstant.UID, m_login.getData().getUid());
        sHelper.putString(BaseConstant.TOKEN, m_login.getData().getAccess_token());
        sHelper.putString(BaseConstant.COVER, m_login.getData().getCover());
    }


    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {

        if (url.equals(BaseConstant.LOGIN) && jo != null) {
            Login m_login = new Login(jo.toString());

            if (m_login != null) {


                if (m_login.getCode().equals("200")) {

                    ToastUtil.showMessage(this, "登录成功");
                    saveLoginInfo(m_login);
//                    startActivity(MainActivity.class);
//                    finish();
                } else {
                    ToastUtil.showMessage(this, m_login.getMsg());
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
