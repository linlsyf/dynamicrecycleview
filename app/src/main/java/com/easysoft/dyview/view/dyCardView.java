package com.easysoft.dyview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.IItemView;
import com.easy.recycleview.recycleview.sectionview.MutiTypeSelectUtils;

import java.util.Random;

/**
 * Created by ldh on 2017/5/11.
 */

public class dyCardView extends  android.support.v7.widget.AppCompatTextView implements IItemView {

    static int index=0;
    public dyCardView(Context context) {
        super(context);
        initUI(context);
    }

    public dyCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    private void initUI(Context context) {
        Random random1 = new Random();
       int radom= random1.nextInt(Math.abs(300))+200;
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, radom));
       requestLayout();
        if (index==0){
            setBackgroundColor(0xccff00ff);

        }else if (index==1){
            setBackgroundColor(0xaa00ff00);

        }else{
            setBackgroundColor(0x66cc0000 );

        }
        index=index+1;
    }

    @Override
    public void initSelectUtils(MutiTypeSelectUtils selectUtils) {

    }

    @Override
    public void initData(final AddressItemBean map) {



    }
}

