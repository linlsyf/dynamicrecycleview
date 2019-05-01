package com.easy.recycleview.custom.baseview.inter;

import com.easy.recycleview.inter.IDyItemBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/10 0010.
 */

public interface IMutiTypeSelectUtils {
    boolean isCheckSelectedById(String sectionId, String id);

    void initSelectDatas(String type, List<IDyItemBean> datas);

    void setIntentSelectedCanEdit(boolean isCanEdit);

    boolean select(boolean leftCheckBoxIsChecked, IDyItemBean newDataMap);

    Map<String,List<IDyItemBean>> getSelectedMap();

    void setIsMutiSelect(String type, boolean isMutiSelect);

    boolean getItemCanSelectEdit(String id);
}
