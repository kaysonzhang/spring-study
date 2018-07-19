package com.kayson.web.model.domain;

import java.io.Serializable;

public class Role  implements Serializable {

    private static final long serialVersionUID = 5557795593293173263L;

    private long id;
    private String name;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String flag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
