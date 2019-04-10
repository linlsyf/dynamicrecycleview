package com.easy.recycleview.recycleview.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easy.recycleview.base.BaseLinearLayout;
import com.easy.recycleview.recycleview.item.inter.IItemView;
import com.easy.recycleview.recycleview.sectionview.MutiTypeSelectUtils;
import com.easysoft.dynamicrecycleview.R;

public class SpliteView extends BaseLinearLayout implements IItemView {
    public SpliteView(Context context) {
        super(context);
        initUI(context);

    }

    public SpliteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    protected void initUI(Context context) {
        View rootView=   LayoutInflater.from(context).inflate( R.layout.view_splite, this, true);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    @Override
    public void initData(AddressItemBean map) {

    }

    @Override
    public void initSelectUtils(MutiTypeSelectUtils selectUtils) {

    }




}
