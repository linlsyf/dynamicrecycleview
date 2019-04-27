package com.easy.recycleview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easy.recycleview.inter.IEmptyView;
import com.easysoft.dynamicrecycleview.R;

public class EmptyView extends LinearLayout implements IEmptyView {

    ImageView emptyImg;
    TextView  emptyIv;
    public EmptyView(Context context) {
        super(context);
        initUI(context);

    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    protected void initUI(Context context) {
        View rootView=   LayoutInflater.from(context).inflate( R.layout.view_adress_list_emty, this, false);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyImg=(ImageView)rootView.findViewById(R.id.iv_empty);
        emptyIv=(TextView) rootView.findViewById(R.id.tv_empty);

    }

//    public   void setVisibility(int visibility){
//
//        emptyImg.setVisibility(visibility);
//        emptyIv.setVisibility(visibility);
//
//    }

    public void clearAnimation(){

    }


}
