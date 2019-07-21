package com.easy.recycleview.bean;

import com.easy.recycleview.inter.IDyItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 *创建者：林党宏
 *时间：2017/1/19
 *注释：分组信息
 */
public class Section {
    /**分组名称*/
    private  String name;
    /**分组id */
    private  String id;
    /** 显示分组分割*/
    private  boolean isShowSection=true;
    /**是否显示右侧英文导航*/
    private  boolean isAddSideBar=true;
    /**是否自动添加分割线*/
    private  boolean isAutoAddSpliteLine=true;
    /**是否设置分组id*/
    private  boolean isAutoAddSection=true;
    /** 是否显示删除布局*/
    private  boolean isSectionShowDelete=false;
    /** 插入位置 当大于0时指定位置*/
    private int position=0;
    /** 插入位置 当大于0时指定位置*/
    private  boolean loadMore=false ;
    private List<IDyItemBean> dataMaps=new ArrayList<IDyItemBean>();


    public Section(String id) {
        this.id = id;
    }

    public Section(String id, String name, List<IDyItemBean> dataMaps) {
        this.id=id;
        this.name = name;
        this.dataMaps=dataMaps;
    }

    public String getName() {
        return name;
    }

    public List<IDyItemBean> getDataMaps() {
        return dataMaps;
    }

    public void setDataMaps(List<IDyItemBean> dataMaps) {
        this.dataMaps = dataMaps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isShowSection() {
        return isShowSection;
    }

    public void setShowSection(boolean showSection) {
        isShowSection = showSection;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAddSideBar() {
        return isAddSideBar;
    }

    public void setAddSideBar(boolean addSideBar) {
        isAddSideBar = addSideBar;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSectionShowDelete() {
        return isSectionShowDelete;
    }

    public void setSectionShowDelete(boolean sectionShowDelete) {
        isSectionShowDelete = sectionShowDelete;
    }

    public boolean isAutoAddSpliteLine() {
        return isAutoAddSpliteLine;
    }

    public void setAutoAddSpliteLine(boolean autoAddSpliteLine) {
        isAutoAddSpliteLine = autoAddSpliteLine;
    }

    public boolean isAutoAddSection() {
        return isAutoAddSection;
    }

    public void setAutoAddSection(boolean autoAddSection) {
        isAutoAddSection = autoAddSection;
    }

    public boolean isLoadMore() {
        return loadMore;
    }

    public void setLoadMore(boolean loadMore) {
        this.loadMore = loadMore;
    }
}
