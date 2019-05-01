package com.easy.recycleview.custom.bean;

import java.io.Serializable;

/**
 * Created by ldh on 2017/3/21.
 */

public class SelectBean implements Serializable {

    private  String id="";
    private  Object  linkObject;
    /**标题*/
    private  String title="";

    private String   type="";

    public SelectBean() {}

    public SelectBean(String id, String title, String type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getLinkObject() {
        return linkObject;
    }

    public void setLinkObject(Object linkObject) {
        this.linkObject = linkObject;
    }
}