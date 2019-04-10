package com.easysoft.baseview.inter;

/**
 * Created by Administrator on 2019/4/10 0010.
 */

public interface IAddressItemBean {
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

    void setIloadImage(IloadImage iloadImage);
}
