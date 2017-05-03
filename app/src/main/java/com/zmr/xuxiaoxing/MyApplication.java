package com.zmr.xuxiaoxing;

import com.xiaoxing.common.base.BaseApplication;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.http.HttpUtil;


/**
 * 描述：
 * 作者：xiaoxing on 17/5/3 11:32
 * 邮箱：2235445233@qq.com
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        HttpUtil.setServiceUrl(BaseConstant.DOMAIN_API);

    }
}
