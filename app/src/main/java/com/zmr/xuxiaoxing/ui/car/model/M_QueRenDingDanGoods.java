package com.zmr.xuxiaoxing.ui.car.model;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/14 15:40
 * 邮箱：2235445233@qq.com
 */
public class M_QueRenDingDanGoods implements Serializable {
    private List<StoreInfo> storeInfoList;
    private List<List<GoodsInfo2>> goodsInfo2List;

    public M_QueRenDingDanGoods() {

    }

    public List<StoreInfo> getStoreInfoList() {
        return storeInfoList;
    }

    public void setStoreInfoList(List<StoreInfo> storeInfoList) {
        this.storeInfoList = storeInfoList;
    }

    public List<List<GoodsInfo2>> getGoodsInfo2List() {
        return goodsInfo2List;
    }

    public void setGoodsInfo2List(List<List<GoodsInfo2>> goodsInfo2List) {
        this.goodsInfo2List = goodsInfo2List;
    }
}

