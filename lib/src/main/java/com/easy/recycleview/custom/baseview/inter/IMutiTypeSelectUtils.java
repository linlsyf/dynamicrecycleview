package com.easy.recycleview.custom.baseview.inter;

import com.easy.recycleview.inter.IAddressItemBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/10 0010.
 */

public interface IMutiTypeSelectUtils {
    boolean isCheckSelectedById(String sectionId, String id);

    void initSelectDatas(String type, List<IAddressItemBean> datas);

    void setIntentSelectedCanEdit(boolean isCanEdit);

    boolean select(boolean leftCheckBoxIsChecked, IAddressItemBean newDataMap);

    Map<String,List<IAddressItemBean>> getSelectedMap();

    void setIsMutiSelect(String type, boolean isMutiSelect);

    boolean getItemCanSelectEdit(String id);
}
