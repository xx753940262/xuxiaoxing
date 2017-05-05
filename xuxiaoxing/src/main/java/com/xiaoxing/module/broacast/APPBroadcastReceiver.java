package com.xiaoxing.module.broacast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.xiaoxing.common.base.BaseConstant;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/26 12:03
 * 邮箱：2235445233@qq.com
 */
public class APPBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String extra = intent.getStringExtra(BaseConstant.BROADCAST_FILTER.EXTRA_CODE);
        if (extra.equals(BaseConstant.INTENT_KEY.LOAD_MORE)) {//首页加载更多
        } else if (extra.equals(BaseConstant.INTENT_KEY.UPDATE_CART)) {//添加到购物车时，更新购物车列表数据
//            CarActivity.instance.cartList();
        } else if (extra.equals(BaseConstant.INTENT_KEY.QUE_REN_DING_DAN)) {//确认订单
//            Activity_QueRenDingDan.instance.queRenDingDan(intent);
        } else if (extra.equals(BaseConstant.INTENT_KEY.UPDATE_USER_INFO)) {//更新用戶的信息
//            Fragment4.instance.updateHeadImage();
        }
    }

    /**
     * 发送广播
     *
     * @param context
     * @param extra
     */
    public static void sendAppBroadcastReceiverMessage(Context context, String extra) {
        Intent intent = new Intent();
        intent.setAction(BaseConstant.BROADCAST_FILTER.FILTER_CODE);
        intent.putExtra(BaseConstant.BROADCAST_FILTER.EXTRA_CODE, extra);
        context.sendBroadcast(intent);
    }


    /**
     * 确认订单
     *
     * @param context
     * @param extra
     * @param supplierShipping
     * @param yunFei
     */
    public static void sendAppBroadcastReceiverMessageQueRenDingDan(Context context, String extra, String supplierShipping, String yunFei) {
        Intent intent = new Intent();
        intent.setAction(BaseConstant.BROADCAST_FILTER.FILTER_CODE);
        intent.putExtra(BaseConstant.BROADCAST_FILTER.EXTRA_CODE, extra);
        intent.putExtra("supplierShipping", supplierShipping);
        //intent.putExtra("itemFee", contentBeanList.get(position).getItem_fee());
        intent.putExtra("itemFee", yunFei);
        context.sendBroadcast(intent);

    }

}
