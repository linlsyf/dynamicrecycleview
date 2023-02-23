package com;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easy.recycleview.inter.IDyItemBean;
import com.easy.recycleview.inter.IItemView;

public class TestCustomView extends TextView implements IItemView {
    public TestCustomView(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    @Override
    public void initData(IDyItemBean map) {
        setText(map.getTitle());
        setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
    }
}
