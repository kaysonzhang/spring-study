package com.kayson.web.model.domain;

import java.io.Serializable;
import java.util.List;

public class UserRole  implements Serializable {

    private static final long serialVersionUID = 2940068525893885701L;

    private long uid;
    private long rid;

    private String roleName;
    private String roleFlag;

    public String getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(String roleFlag) {
        this.roleFlag = roleFlag;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }


}
