package com.core.recycleview.item;

import java.io.Serializable;

import com.core.recycleview.sectionview.MutiTypeSelectUtils;

/**
 *创建者：林党宏
 *时间：2017/1/18
 *注释：adapter通用界面接口
 */
public interface IItemView {

     enum ViewTypeEnum {
        ITEM(0),
        SECTION(1),
        SPLITE(2),
        DELETE_BUTTON(3),//删除人员界面
        GROUP_SETTING_GRIDVIEW(4),//系统设置群成员列表界面
        SPACE_VIEW(5),//间隔view 可设置高度
        CENTER_CONTENT(6),//中间内容itemview
        INPUT_VIEW(7),//中间内容itemview
        INFO_CARD_VIEW(8),//中间内容itemview
        TOP_VIEW(9);//头部itemview
        private int value=0;
        private ViewTypeEnum(int value) {    //    必须是private的，否则编译错误
            this.value = value;
        }
        public int value() {
            return this.value;
        }
    }

    enum ClickTypeEnum {
        ITEM(0),
        RIGHTBUTTION(1),
        RIGHT_FIRST_IMG(2),
        RIGHT_SCALE_CENTER_IMG(3),
        ITEM_LONG(4),
        SECTION_DELETE(5),
        RIGHT_SECOND_IMG(6);
        private int value=0;
        private ClickTypeEnum(int value) {    //    必须是private的，否则编译错误
            this.value = value;
        }
        public int value() {
            return this.value;
        }
    }

     void initSelectUtils(MutiTypeSelectUtils selectUtils);
    void initData(AddressItemBean map);

    interface onItemClick extends Serializable{

        void onItemClick(ClickTypeEnum typeEnum, AddressItemBean bean);

    }

  }
