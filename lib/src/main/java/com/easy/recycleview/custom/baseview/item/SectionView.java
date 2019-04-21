package com.easy.recycleview.custom.baseview.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easy.recycleview.bean.Section;
import com.easy.recycleview.custom.baseview.base.BaseLinearLayout;
import com.easy.recycleview.custom.bean.AddressItemBean;
import com.easy.recycleview.inter.IAddressItemBean;
import com.easy.recycleview.inter.IItemView;
import com.easysoft.dynamicrecycleview.R;


/**
 *创建者：林党宏
 *时间：2017/1/17
 *注释：分组界面
 */

public class SectionView extends BaseLinearLayout implements  IItemView{
    /** 分组名字*/
    TextView mTitleView;
    Context mContext;
    RelativeLayout mRightDeletLayout;
    public SectionView(Context context) {
        super(context);
        initUI(context);
    }

    public SectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);

    }

    protected void initUI(Context context) {
        mContext=context;
     View rootView=   LayoutInflater.from(mContext).inflate( R.layout.view_section, this, true);
        mTitleView= (TextView)rootView.findViewById(R.id.tvSection);
        mRightDeletLayout= (RelativeLayout) rootView.findViewById(R.id.rightDeletLayout);

        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void initData(final AddressItemBean map){
        mTitleView.setText(map.getTitle());
        if (map.isSectionShowDelete()){
            mRightDeletLayout.setVisibility(View.VISIBLE);
        }else{
            mRightDeletLayout.setVisibility(View.GONE);
        }
        if (map.getOnItemListener()!=null){
            mRightDeletLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map .getOnItemListener().onItemClick(ClickTypeEnum.SECTION_DELETE,map);
                }
            });
        }
    }

    public void initData(Section section) {
        mTitleView.setText(section.getName());
        mRightDeletLayout.setVisibility(View.GONE);
    }




    @Override
    public void initData(IAddressItemBean map) {

    }
}


