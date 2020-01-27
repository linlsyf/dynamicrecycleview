package com.easy.recycleview.inter;

import com.easy.recycleview.bean.AddressEditSettings;
import com.easy.recycleview.bean.CentLayoutConfig;

/**
 * Created by Administrator on 2019/9/14 0014.
 */

public interface IRecycleItemBean {

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

    public void setSection(String section);

    public CentLayoutConfig getCentLayoutConfig();

    public void setTitle(String title);
    public void setHint(String hint);
}
