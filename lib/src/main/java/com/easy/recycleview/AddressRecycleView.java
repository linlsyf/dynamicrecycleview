package com.easy.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.easy.recycleview.bean.Section;
import com.easy.recycleview.inter.IAddressItemBean;
import com.easy.recycleview.inter.IEmptyView;
import com.easy.recycleview.outinter.DefaultViewFactory;
import com.easy.recycleview.view.EmptyView;
import com.easysoft.dynamicrecycleview.R;

import java.lang.reflect.Constructor;

/**
 *创建者：林党宏
 *时间：2017/4/17
 *注释：通用列表界面
 *  继承 重写
 *
 */

public class AddressRecycleView extends RelativeLayout implements SectionAdapterHelper.IAddItemView {
    IEmptyView mIEmptyView;
    RecyclerView mRefreshRecyclerView;
    /** 分组工具类*/
    SectionAdapterHelper mSectionAdapterHelper;
    private CustomViewCallBack customViewCallBack;
    private RelativeLayout rootView;
    //DefaultViewFactory defaultViewFactory;
    public AddressRecycleView(Context context) {
        super(context);
        initUI(context);
    }

    public AddressRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);

    }

    protected void initUI(Context context) {
        rootView= (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.view_address_recycle, this, true);
         mRefreshRecyclerView=(RecyclerView) rootView.findViewById(R.id.refreshRecycleView);

        mSectionAdapterHelper=new SectionAdapterHelper();
        mSectionAdapterHelper.init(context,mRefreshRecyclerView);


        mIEmptyView =new EmptyView(getContext());
//        mIEmptyView =(EmptyView) rootView.findViewById(R.id.emptyNoticeView);
        mSectionAdapterHelper.setEmptyView(mIEmptyView);

        mSectionAdapterHelper.setIAddItemView(this);
    }
    public void  setEmptyView(View iEmptyView){
//        mIEmptyView.setVisibility(View.GONE);
        mIEmptyView=(IEmptyView)iEmptyView;
        RelativeLayout.LayoutParams  layoutParams=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        rootView.addView(iEmptyView,layoutParams);
        mSectionAdapterHelper.setEmptyView(mIEmptyView);
    }


//    public void  initIMutiTypeSelectUtils(IMutiTypeSelectUtils mSelectUtils){
//        mSectionAdapterHelper.initIMutiTypeSelectUtils(mSelectUtils);
//    }
    public RecyclerView getRefreshRecyclerView() {
        return mRefreshRecyclerView;
    }
//    public List<IAddressItemBean> getSelectList(String sectionId){
//        List<IAddressItemBean>  selectList= mSectionAdapterHelper.getSelectMap().get(sectionId);
//       if (selectList==null){
//           selectList=new ArrayList<IAddressItemBean>();
//       }
//        return selectList;
//    }
    public void initLayoutManager(RecyclerView.LayoutManager layout) {
        mSectionAdapterHelper.initLayoutManager(layout);
    }
    public void updateSection(Section nextSection) {
        mSectionAdapterHelper.updateSection(nextSection);
    }
    public void updateSection(Section nextSection,boolean isRefresh) {
        mSectionAdapterHelper.updateSection(nextSection,isRefresh);
    }

public void  setSpanCount(int  spanCount){
    mSectionAdapterHelper.setSpanCount(spanCount);
}
    public void clean() {
        mSectionAdapterHelper.clean();
    }


    /**
     *创建者：林党宏
     *时间：2017/4/6
     *注释：重写可以添加新的自定义item
     */
    @Override
    public View addItemView(int viewType){
        View itemView=null;
        if(customViewCallBack!=null){
             itemView  =customViewCallBack.getCustomView(getContext(),viewType);
        }

        if (itemView==null){
       Class  classView= DefaultViewFactory.getInstance().getDefaultViewNameMap().get(viewType);
           try {
               Class[] params = {Context.class};//类类型
               Object[] values = {getContext()};//类型值

               Constructor con =classView.getConstructor(params);
               //调用构造方法并创建实例
               Object obj = con.newInstance(values);

               itemView=(View) obj;

           }catch (Exception e){
               System.out.print("class for view"+e.getMessage());
           }

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
    public void updateItem(IAddressItemBean addressItemBean) {
        mSectionAdapterHelper.updateItem(addressItemBean.getSection(),addressItemBean);
    }

    public void deleteItem(String sectionId,String deleteId){
        mSectionAdapterHelper.deleteItem( sectionId,deleteId);
    }

    public void scrollToBottom(){
        mRefreshRecyclerView.scrollToPosition(mSectionAdapterHelper.getCount());
    }


//    public void show(){
//        mSectionAdapterHelper.show();
//    }
   public void addSection(Section section) {
       mSectionAdapterHelper.addSection(section);
   }

}
