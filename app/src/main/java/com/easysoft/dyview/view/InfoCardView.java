package com.easysoft.dyview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easy.recycleview.inter.IAddressItemBean;
import com.easy.recycleview.inter.IItemView;

/**
 * Created by ldh on 2017/5/11.
 */

public class InfoCardView extends  TextView implements IItemView {

    static boolean index=false;
    public InfoCardView(Context context) {
        super(context);
        initUI(context);
    }

    public InfoCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public void initUI(Context context) {
       View textView= new TextView(context);


//       if (index==0){
//           setBackgroundColor(0xccff00ff);
//
//       }else if (index==1){
//           setBackgroundColor(0xaa00ff00);
//
//       }else{
//         if (index){
//             setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//         }else{
//             setBackgroundColor(0x66cc0000 );
//         }

//       }
        index=!index;
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));

    }

    @Override
    public void initSelectUtils(IMutiTypeSelectUtils selectUtils) {

    }

    @Override
    public void initData( IAddressItemBean map) {



    }
}

