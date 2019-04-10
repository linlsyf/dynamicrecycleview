package com.easysoft.dyview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easy.recycleview.inter.IAddressItemBean;
import com.easy.recycleview.inter.IItemView;

import java.util.Random;

/**
 * Created by ldh on 2017/5/11.
 */

public class dyCardView extends  TextView implements IItemView {

    static boolean index=false;
    public dyCardView(Context context) {
        super(context);
        initUI(context);
    }

    public dyCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public void initUI(Context context) {
        Random random1 = new Random();
//       int radom= random1.nextInt(Math.abs(500))+400;
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
//        if (index){
//            setBackgroundColor(0xccff00ff);
//
//        }else{
            setBackgroundColor(0xaa00ff00);
//        }
        index=!index;
    }


    @Override
    public void initData(IAddressItemBean map) {

    }

}

