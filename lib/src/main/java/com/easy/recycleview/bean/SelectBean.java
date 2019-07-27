package com.easy.recycleview.bean;

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

    public SelectBean setId(String id) {
        this.id = id;return  this ;
    }

    public String getTitle() {
        return title;
    }

    public SelectBean setTitle(String title) {
        this.title = title;return  this ;
    }

    public String getType() {
        return type;
    }

    public SelectBean setType(String type) {
        this.type = type;return  this ;
    }

    public Object getLinkObject() {
        return linkObject;
    }

    public SelectBean setLinkObject(Object linkObject) {
        this.linkObject = linkObject;return  this ;
    }
}
