package com.core.recycleview.sectionview;

import java.util.ArrayList;
import java.util.List;

import com.core.recycleview.item.AddressItemBean;
import com.core.recycleview.item.IItemView;

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
    /** 是否显示删除布局*/
    private  boolean isSectionShowDelete=false;
    /** 插入位置 当大于0时指定位置*/
    private int position=0;
    List<AddressItemBean> dataMaps=new ArrayList<AddressItemBean>();

    IItemView.onItemClick onItemListener;

    public Section(String id) {
        this.id = id;
    }

    public Section(String id, String name, List<AddressItemBean> dataMaps) {
        this.id=id;
        this.name = name;
        this.dataMaps=dataMaps;
    }

    public String getName() {
        return name;
    }

    public List<AddressItemBean> getDataMaps() {
        return dataMaps;
    }

    public void setDataMaps(List<AddressItemBean> dataMaps) {
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

    public IItemView.onItemClick getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(IItemView.onItemClick onItemListener) {
        this.onItemListener = onItemListener;
    }
}
