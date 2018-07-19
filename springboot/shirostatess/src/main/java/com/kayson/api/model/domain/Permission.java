package com.kayson.api.model.domain;

import java.io.Serializable;

public class Permission  implements Serializable {

    private static final long serialVersionUID = 9096637402585555587L;

    private long id;
    private String name;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
