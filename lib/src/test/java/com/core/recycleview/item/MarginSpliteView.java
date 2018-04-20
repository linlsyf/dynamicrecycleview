package com.core.recycleview.item;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.core.recycleview.sectionview.MutiTypeSelectUtils;
import com.easysoft.costumes.R;

public class MarginSpliteView extends LinearLayout  implements IItemView{
    public MarginSpliteView(Context context) {
        super(context);
        initUI(context);

    }

    public MarginSpliteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    private void initUI(Context context) {
        View rootView=   LayoutInflater.from(context).inflate( R.layout.view_magin_splite, this, true);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    @Override
    public void initData(AddressItemBean map) {

    }

    @Override
    public void initSelectUtils(MutiTypeSelectUtils selectUtils) {

    }



}
