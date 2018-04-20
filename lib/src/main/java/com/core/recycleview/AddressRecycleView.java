package com.core.recycleview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.core.recycleview.item.AddressItemBean;
import com.core.recycleview.item.ContentItemView;
import com.core.recycleview.item.IItemView;
import com.core.recycleview.item.SectionView;
import com.core.recycleview.item.SpliteView;
import com.core.recycleview.sectionview.Section;
import com.core.recycleview.sectionview.SectionAdapterHelper;
import com.easysoft.dynamicrecycleview.R;

/**
 *创建者：林党宏
 *时间：2017/4/17
 *注释：通用列表界面
 *  继承 重写
 *
 */

public class AddressRecycleView extends LinearLayout implements SectionAdapterHelper.IAddItemView {
    @Bind(R.id.emptyView)
    View mEmptyView;
    @Bind(R.id.refreshRecycleView)
    RecyclerView mRefreshRecyclerView;

    /** 分组工具类*/
    SectionAdapterHelper mSectionAdapterHelper;
    public AddressRecycleView(Context context) {
        super(context);
        initUI(context);

    }

    public AddressRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);

    }

    private void initUI(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_address_recycle, this, true);
        ButterKnife.bind(this);
        mSectionAdapterHelper=new SectionAdapterHelper();
        mSectionAdapterHelper.init(context,mRefreshRecyclerView);
        mSectionAdapterHelper.setEmptyView(mEmptyView);
//        mRefreshRecyclerView.setMode(RecyclerMode.NONE);
        mSectionAdapterHelper.setIAddItemView(this);
    }

    public RecyclerView getRefreshRecyclerView() {
        return mRefreshRecyclerView;
    }
    public List<AddressItemBean> getSelectList(String sectionId){
        List<AddressItemBean>  selectList= mSectionAdapterHelper.getSelectMap().get(sectionId);
       if (selectList==null){
           selectList=new ArrayList<AddressItemBean>();
       }
        return selectList;
    }

    public void updateSection(Section nextSection) {
        mSectionAdapterHelper.updateSection(nextSection);
    }

    public void clean() {
        mSectionAdapterHelper.clean();
    }
    public void cleanSelected(){
        mSectionAdapterHelper.getSelectMap().clear();

    }

    /**
     *创建者：林党宏
     *时间：2017/4/6
     *注释：重写可以添加新的自定义item
     */
    @Override
    public View addItemView(int viewType){
        View itemView=null;

        if (viewType==IItemView.ViewTypeEnum.ITEM.value()){
            itemView=new ContentItemView(getContext())  ;
        }
        else if(viewType==IItemView.ViewTypeEnum.SECTION.value()){
            itemView=new SectionView(getContext());
        }
        else if(viewType==IItemView.ViewTypeEnum.SPLITE.value()){
            itemView=new SpliteView(getContext());
        }
        return itemView;
    }


    public SectionAdapterHelper getSectionAdapterHelper() {
        return mSectionAdapterHelper;
    }
    public void updateItem(AddressItemBean addressItemBean) {
        mSectionAdapterHelper.updateItem(addressItemBean.getSection(),addressItemBean);
    }

    public void deleteItem(String sectionId,String deleteId){
        mSectionAdapterHelper.deleteItem( sectionId,deleteId);
    }

    public void scrollToBottom(){
        mRefreshRecyclerView.scrollToPosition(mSectionAdapterHelper.getCount());
    }

}
