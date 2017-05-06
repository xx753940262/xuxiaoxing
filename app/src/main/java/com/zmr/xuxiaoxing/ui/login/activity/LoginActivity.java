package com.zmr.xuxiaoxing.ui.login.activity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.util.AbStrUtil;
import com.xiaoxing.common.util.ToastUtil;
import com.xiaoxing.common.view.clear_edit_text.ClearEditText;
import com.zmr.xuxiaoxing.R;
import com.zmr.xuxiaoxing.ui.login.model.Login;
import com.zmr.xuxiaoxing.ui.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 描述：登录
 * 作者：xiaoxing on 17/4/7 13:03
 * 邮箱：2235445233@qq.com
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.ced_tel)
    ClearEditText mCedTel;
    @BindView(R.id.cet_pwd)
    ClearEditText mCetPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_forgot_pwd)
    TextView mTvForgotPwd;
    @BindView(R.id.tv_reg)
    TextView mTvReg;


    private String mTel, mPwd;

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        setHeaderTitle(R.string.login);
    }


    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        autoLogin();
    }


    @OnClick({R.id.btn_login, R.id.tv_forgot_pwd, R.id.tv_reg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_forgot_pwd:
                startActivity(ForgotPwdActivity.class);
                break;
            case R.id.tv_reg:
                startActivity(RegisterActivity.class);
                break;
        }
    }

    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {
        super.onMessageResponse(url, jo);

        httpResultLogin(url, jo);
    }

    /**
     * 登录api返回数据处理
     *
     * @param url
     * @param jo
     */
    private void httpResultLogin(String url, JSONObject jo) {

        if (url.equals(BaseConstant.LOGIN) && jo != null) {
            try {
                Login m_login = new Login(jo.toString());

                if (m_login != null) {

                    if (m_login.getCode().equals(BaseConstant.HTTP_RESULT_OK)) {

                        ToastUtil.showMessage(this, "登录成功");
                        saveLoginInfo(m_login.getData());
//                        startActivity(MainActivity.class);
                        startActivity(MainActivity.class);
                        finish();
                    } else {
                        ToastUtil.showMessage(this, m_login.getMsg());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 检查用户名和密码是否为空
     */
    private boolean checkTelPwd() {

        mTel = getEidtTextValue(mCedTel);
        mPwd = getEidtTextValue(mCetPwd);

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

        showGifdialog();
        BaseApi.login(this, mTel, mPwd);
    }

    /**
     * 自动登陆
     */
    public void autoLogin() {
        String username = sHelperGetString(BaseConstant.USERNAME);
        String pwd = sHelperGetString(BaseConstant.PASSWORD);
        if (username != null && pwd != null) {

            if (BaseConstant.AUTO_LOGIN) {
                mCedTel.setText(username);
                mCetPwd.setText(pwd);
                login();
            } else {
                mCedTel.setText(sHelperGetString(BaseConstant.USERNAME));
            }
        }
    }


    /**
     * 保存登陆的信息
     */
    private void saveLoginInfo(Login.DataBean dataBean) {

        sHelperPutString(BaseConstant.USERNAME, mTel);
        sHelperPutString(BaseConstant.PASSWORD, mPwd);
        sHelperPutBoolen(BaseConstant.IS_LOGIN, true);
        sHelperPutString(BaseConstant.UID, dataBean.getUid());
        sHelperPutString(BaseConstant.TOKEN, dataBean.getAccess_token());
        sHelperPutString(BaseConstant.COVER, dataBean.getCover());
    }

}
