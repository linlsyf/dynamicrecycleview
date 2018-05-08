package com.easysoft.dyview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.IItemView;
import com.easy.recycleview.recycleview.sectionview.MutiTypeSelectUtils;

/**
 * Created by ldh on 2017/5/11.
 */

public class InfoCardView extends  android.support.v7.widget.AppCompatTextView implements IItemView {

    public InfoCardView(Context context) {
        super(context);
        initUI(context);
    }

    public InfoCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    private void initUI(Context context) {
       View textView= new TextView(context);

        setBackgroundColor(0xaa00ff00);
        setLayoutParams(new ViewGroup.LayoutParams(200, 200));

    }

    @Override
    public void initSelectUtils(MutiTypeSelectUtils selectUtils) {

    }

    @Override
    public void initData(final AddressItemBean map) {



    }
}

