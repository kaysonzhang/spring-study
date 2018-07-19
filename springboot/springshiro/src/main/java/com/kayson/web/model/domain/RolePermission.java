package com.kayson.web.model.domain;

import java.io.Serializable;

public class RolePermission  implements Serializable {

    private static final long serialVersionUID = 1169997465646077919L;

    private long rid;
    private long pid;

    private String pName;
    private String pUrl;

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



    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

}
