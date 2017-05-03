package com.xiaoxing.module.login.activity;

import android.content.Context;
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
import com.xiaoxing.common.http.OnMessageResponse;
import com.xiaoxing.common.util.AbStrUtil;
import com.xiaoxing.common.util.ToastUtil;
import com.xiaoxing.common.view.clear_edit_text.ClearEditText;
import com.xiaoxing.module.login.model.SendSMS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.ab.R.id.tv_xie_yi;

/**
 * 描述：注册
 * 作者：xiaoxing on 17/4/7 15:32
 * 邮箱：2235445233@qq.com
 */
public class RegisterActivity extends BaseActivity implements OnMessageResponse {
    private ClearEditText cetTel;
    private TextView tvXieYi;
    private CheckBox cbAgree;
    private Button btn_next_step;

    private String mTel;

    @Override
    public int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        cetTel = (ClearEditText) view.findViewById(R.id.cet_tel);
        tvXieYi = (TextView) view.findViewById(tv_xie_yi);
        cbAgree = (CheckBox) view.findViewById(R.id.cb_agree);
        btn_next_step = (Button) view.findViewById(R.id.btn_next_step);

        tvXieYi.setOnClickListener(this);
        btn_next_step.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        setHeaderBack();
        setHeaderTitle(R.string.register);
    }


    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.btn_next_step) {
            next();

        } else if (i == tv_xie_yi) {
            startActivity(XieYiActivity.class);

        }
    }

    /**
     * 下一步
     */
    private void next() {

        if (checkTelCheckBox()) {

            LoadingDialogUtil.showGifdialog(this);
            BaseApi.sendSms(this, mTel);


        }
    }

    private boolean checkTelCheckBox() {
        mTel = getEidtTextValue(cetTel);

        if (AbStrUtil.isEmpty(mTel)) {
            ToastUtil.showMessage(this, "手机号不能为空");
            return false;
        } else if (!AbStrUtil.isMobileNo(mTel)) {
            ToastUtil.showMessage(this, "手机号格式错误");
            return false;
        } else if (!cbAgree.isChecked()) {
            ToastUtil.showMessage(this, "请先阅读并同意学习乐使用条款及隐私协议");
            return false;
        }
        return true;
    }

    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {

        if (url.equals(BaseConstant.SEND_SMS) && jo != null) {
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

        }

    }

    @Override
    public void onMessageResponse(String url, JSONArray jo) throws JSONException {

        ToastUtil.showMessage(this, "JSONArray");
    }

    @Override
    public void onMessageResponse(String url, String str) throws Exception {
        ToastUtil.showMessage(this, "str");
    }
}
