package com.easy.recycleview.inter;

/**
 * Created by Administrator on 2019/4/10 0010.
 */

public interface IDyItemBean {
    String getId();

    String getTitle();

    int getViewType();

    String getSection();

    String getSelectType();

    void setShowLeftCheckBox(boolean isCanSelect);

    boolean isShowLeftCheckBox();

    boolean isLeftCheckBoxIsChecked();

    void setLeftCheckBoxIsChecked(boolean leftCheckBoxIsChecked);

    int getSpanSize();

    void setPosition(int position);

}
