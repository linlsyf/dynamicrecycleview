package com.easy.recycleview.recycleview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.ContentItemView;
import com.easy.recycleview.recycleview.item.IItemView;
import com.easy.recycleview.recycleview.item.IloadImage;
import com.easy.recycleview.recycleview.item.SectionView;
import com.easy.recycleview.recycleview.item.SpliteView;
import com.easy.recycleview.recycleview.item.bean.ItemViewBean;
import com.easy.recycleview.recycleview.sectionview.Section;
import com.easy.recycleview.recycleview.sectionview.SectionAdapterHelper;
import com.easysoft.dynamicrecycleview.R;

/**
 *创建者：林党宏
 *时间：2017/4/17
 *注释：通用列表界面
 *  继承 重写
 *
 */

public class AddressRecycleView extends LinearLayout implements SectionAdapterHelper.IAddItemView {
    View mEmptyView;
    RecyclerView mRefreshRecyclerView;
    /** 分组工具类*/
    SectionAdapterHelper mSectionAdapterHelper;
    private CustomViewCallBack customViewCallBack;

    public AddressRecycleView(Context context) {
        super(context);
        initUI(context);

    }

    public AddressRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);

    }

    private void initUI(Context context) {
       View  rootView= LayoutInflater.from(context).inflate(R.layout.view_address_recycle, this, true);
         mEmptyView=rootView.findViewById(R.id.emptyView);
         mRefreshRecyclerView=(RecyclerView) rootView.findViewById(R.id.refreshRecycleView);
        mSectionAdapterHelper=new SectionAdapterHelper();
        mSectionAdapterHelper.init(context,mRefreshRecyclerView);
        mSectionAdapterHelper.setEmptyView(mEmptyView);
//        mRefreshRecyclerView.setMode(RecyclerMode.NONE);
        mSectionAdapterHelper.setIAddItemView(this);
        setIloadImage(RecycleViewManage.getInStance().getIloadImage());
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
    public void initLayoutManager(RecyclerView.LayoutManager layout) {
        mSectionAdapterHelper.initLayoutManager(layout);
    }
    public void updateSection(Section nextSection) {
        mSectionAdapterHelper.updateSection(nextSection);
    }
    public void updateSection(Section nextSection,boolean isRefresh) {
        mSectionAdapterHelper.updateSection(nextSection,isRefresh);
    }
//    public void setLayoutHelpers(List<LayoutHelper> helpers) {
////        this.mRecycleViewManger.setLayoutHelpers(helpers);
//        mSectionAdapterHelper.setLayoutHelpers(helpers);
//    }
//    public void addLayoutHelper(LayoutHelper layoutHelper){
//       layoutHelpers.add(layoutHelper);
//    }
//    public void addLayoutHelper(LayoutHelper layoutHelper,boolean refresh){
//       layoutHelpers.add(layoutHelper);
//       if (refresh){
//           mSectionAdapterHelper.setLayoutHelpers(layoutHelpers);
//       }
//    }
//    public void refreshLayoutHelpers(){
//        mSectionAdapterHelper.setLayoutHelpers(layoutHelpers);
//    }
public void  setSpanCount(int  spanCount){
    mSectionAdapterHelper.setSpanCount(spanCount);
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
        else  if (null!=customViewCallBack){
            itemView=customViewCallBack.getCustomView(getContext(),viewType);
         }
        return itemView;
    }
      public  interface  CustomViewCallBack{
          View getCustomView(Context context,int type);
      }
      public  void  initCustomViewCallBack(CustomViewCallBack  callback){
           this.customViewCallBack=callback;
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

    public void setIloadImage(IloadImage iloadImage) {
        mSectionAdapterHelper.setIloadImage( iloadImage);
    }
//    public void show(){
//        mSectionAdapterHelper.show();
//    }
   public void addSection(Section section) {
       mSectionAdapterHelper.addSection(section);
   }

}
