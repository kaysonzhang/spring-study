package com.kayson.web.model.domain;

/**
 * @author by kayson
 * @data 2018/7/16 13:25
 * @description 用户-角色-权限关系
 */
public class UserPermission {

    private long uid;

    private long rId;//角色id
    private long pId;//权限id
    private String pName;//权限名称
    private String pUrl;//权限所对应的url

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getrId() {
        return rId;
    }

    public void setrId(long rId) {
        this.rId = rId;
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpUrl() {
        return pUrl;
    }

    public void setpUrl(String pUrl) {
        this.pUrl = pUrl;
    }



}
