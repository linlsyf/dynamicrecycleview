package com.easy.recycleview.inter;

import java.io.Serializable;

/**
 *创建者：林党宏
 *时间：2017/1/18
 *注释：adapter通用界面接口
 */
public interface IItemView {

    void initData(IDyItemBean map);
//
    enum ViewTypeEnum {
        ITEM(0),
        SECTION(1),
        SPLITE(2);
        private int value=0;
        ViewTypeEnum(int value) {    //    必须是private的，否则编译错误
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
        RIGHT_SECOND_IMG(6),
        HEAD_IMG(7),
        CUSTOM(100);
        private int value=0;
        ClickTypeEnum(int value) {    //    必须是private的，否则编译错误
            this.value = value;
        }
        public int value() {
            return this.value;
        }
    }

    interface onItemClick extends Serializable {

        void onItemClick(ClickTypeEnum typeEnum, IDyItemBean bean);

    }

  }
