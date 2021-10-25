package com.eup.mvvmlivedatarxjava.model;

import android.net.Uri;

public class Android extends BaseModel{
    private String ver;
    private String name;
    private String api;

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    @Override
    public Uri getContentUri() {
        return null;
    }
}
