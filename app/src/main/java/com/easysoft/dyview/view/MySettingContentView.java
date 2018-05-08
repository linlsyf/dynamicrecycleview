package com.easysoft.dyview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.easy.recycleview.recycleview.AddressRecycleView;
import com.easy.recycleview.recycleview.item.IItemView;


/**
 * 个人设置内容布局
 */

public class MySettingContentView extends AddressRecycleView {

    public MySettingContentView(Context context) {
        super(context);
    }

    public MySettingContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public View addItemView(int viewType) {
        View  itemView;
        if (viewType== IItemView.ViewTypeEnum.INFO_CARD_VIEW.value()){
            itemView=new InfoCardView(getContext());
            return itemView;
        }

        return super.addItemView(viewType);
    }
}
