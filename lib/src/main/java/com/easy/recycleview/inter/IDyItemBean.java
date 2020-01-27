package com.easy.recycleview.inter;

import com.easy.recycleview.bean.AddressEditSettings;

/**
 * Created by Administrator on 2019/4/10 0010.
 */

public interface IDyItemBean extends  IRecycleItemBean{


    String getHint();
     Object getBindObject();
    void setBindObject(Object bindObject);
    public AddressEditSettings getEidtSettings();

}
