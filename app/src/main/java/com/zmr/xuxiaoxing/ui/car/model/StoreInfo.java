package com.zmr.xuxiaoxing.ui.car.model;

/**
 * 描述：店铺信息
 * 作者：xiaoxing on 17/4/12 16:03
 * 邮箱：2235445233@qq.com
 */

import java.io.Serializable;


public class StoreInfo implements Serializable {
    protected String Id;
    protected String name;
    protected boolean isChoosed;
    private boolean isEdtor;
    private String supplier_id;

    public boolean isEdtor() {
        return isEdtor;
    }

    public void setIsEdtor(boolean isEdtor) {
        this.isEdtor = isEdtor;
    }

    public StoreInfo(String id, String name, String supplier_id) {
        Id = id;
        this.name = name;
        this.supplier_id = supplier_id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean isChoosed) {
        this.isChoosed = isChoosed;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setEdtor(boolean edtor) {
        isEdtor = edtor;
    }


}
