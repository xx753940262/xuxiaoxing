package com.zmr.xuxiaoxing.ui.address.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.socks.library.KLog;
import com.xiaoxing.common.base.BaseActivity;
import com.zmr.xuxiaoxing.R;
import com.zmr.xuxiaoxing.ui.address.fragment.Fragment_Area;
import com.zmr.xuxiaoxing.ui.address.model.AreaInfo;
import com.zmr.xuxiaoxing.ui.address.model.QuInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：所在地址、街道选择，通过ShowAPi接口查询地区、街道数据,网址：https://www.showapi.com/api/lookPoint/101
 * 作者：徐小星 on 2016/9/27 0027 13:20
 * 邮箱：xx@yougudongli.com
 */
public class AreaSelectActivity extends BaseActivity implements Fragment_Area.OnFragmentInteractionListener {
    private Fragment oneFragment;
    private Fragment twoFragment;
    private Map map = new HashMap();
    private String mProvinceName;
    private String mDistrictId;

    @Override
    public int bindLayout() {
        return R.layout.activity_area_select;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        setHeaderTitle(R.string.select_address);

    }

    @Override
    public void getBundleValue(Bundle bundle) {
        super.getBundleValue(bundle);

        mDistrictId = bundle.getString("districtId");
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);

        if (mDistrictId.equals("")) {
            oneFragment = Fragment_Area.newInstance("1", "", "");
        } else {
            oneFragment = Fragment_Area.newInstance("4", "", mDistrictId);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, oneFragment).commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                } else {
                    finish();
                }
                break;
        }
        return true;
    }


    /**
     * 处理交互，hide前一个fragment，并且调用addToBackStack让Fragment可以点击back的时候显示前一个fragment
     * 如果是第三级地区则直接返回地区选择数据给上个Activity
     *
     * @param areaInfo 被点击的地区信息
     */
    @Override
    public void onFragmentInteraction(AreaInfo areaInfo, AreaInfo.ShowapiResBodyBean.DataBean dataBean, QuInfo.ShowapiResBodyBean.DataBean.ChildrenBean childrenBean, QuInfo quInfo) {
        if (dataBean == null && childrenBean == null) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int level = 0;
        if (dataBean != null && childrenBean == null) {
            level = dataBean.getLevel();
        } else if (dataBean == null && childrenBean != null) {
            level = childrenBean.getLevel();
        }

        KLog.e("level=" + level);
        switch (level) {
            case 1:
                KLog.e("DataBean1=", level);
                mProvinceName = dataBean.getAreaName();
                map.put("provId", dataBean.getId());
                map.put("provName", dataBean.getAreaName());

                transaction.hide(oneFragment);
                transaction.add(R.id.content, twoFragment = Fragment_Area.newInstance("2", dataBean.getAreaName(), "")).addToBackStack(null).commit();
                break;
            case 2:
                KLog.e("DataBean2=", level);
                map.put("cityId", dataBean.getId());
                map.put("cityName", dataBean.getAreaName());
                transaction.hide(twoFragment);
                transaction.add(R.id.content, twoFragment = Fragment_Area.newInstance("3", mProvinceName, dataBean.getId() + "")).addToBackStack(null).commit();
                break;
            case 3:
                KLog.e("ChildrenBean3=", level);
                map.put("districtId", childrenBean.getId());
                map.put("districtName", childrenBean.getAreaName());
                Intent intent = new Intent();
                intent.putExtra("addressInfo", (Serializable) map);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case 4:
                KLog.e("jieDao=", level);
                map.put("jieDaoId", childrenBean.getId());
                map.put("jieDaoName", childrenBean.getAreaName());
                Intent intent1 = new Intent();
                intent1.putExtra("addressInfo", (Serializable) map);
                setResult(RESULT_OK, intent1);
                finish();
                break;
        }

    }


}
