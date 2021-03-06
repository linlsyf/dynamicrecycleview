package com.easysoft.dyview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.easy.recycleview.DyLayout;


/**
 * 个人设置内容布局
 */

public class MySettingContentView extends DyLayout {

    public MySettingContentView(Context context) {
        super(context);
    }

    public MySettingContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public View addItemView(int viewType) {
        View  itemView;
        if (viewType==3){
            itemView=new InfoCardView(getContext());
            return itemView;
        }
        if (viewType==4){
            itemView=new dyCardView(getContext());
            return itemView;
        }

        return super.addItemView(viewType);
    }
}
