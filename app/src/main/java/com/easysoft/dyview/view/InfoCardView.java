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

    static int index=0;
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


       if (index==0){
           setBackgroundColor(0xccff00ff);

       }else if (index==1){
           setBackgroundColor(0xaa00ff00);

       }else{
         setBackgroundColor(0x66cc0000 );

       }
        index=index+1;
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));

    }

    @Override
    public void initSelectUtils(MutiTypeSelectUtils selectUtils) {

    }

    @Override
    public void initData(final AddressItemBean map) {



    }
}

