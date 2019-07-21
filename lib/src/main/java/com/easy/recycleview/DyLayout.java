package com.easy.recycleview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.easy.recycleview.bean.Section;
import com.easy.recycleview.custom.baseview.ContentItemView;
import com.easy.recycleview.custom.bean.SelectBean;
import com.easy.recycleview.inter.IDyItemBean;
import com.easy.recycleview.outinter.RecycleConfig;
import com.easy.recycleview.view.EmptyView;
import com.easysoft.dynamicrecycleview.R;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 *创建者：林党宏
 *时间：2017/4/17
 *注释：通用列表界面
 *  继承 重写
 *
 */

public class DyLayout extends RelativeLayout implements SectionAdapterHelper.IAddItemView {
    EmptyView mIEmptyView;
    RecyclerViewSupport mRefreshRecyclerView;
    /** 分组工具类*/
    SectionAdapterHelper mSectionAdapterHelper;
    private CustomViewCallBack customViewCallBack;
    private RelativeLayout rootView;
    private SwipeRefreshLayout mSwipeLayout;
    private int lastVisibleItemPosition;

    //RecycleConfig defaultViewFactory;
    public DyLayout(Context context) {
        super(context);
        initUI(context);
    }

    public DyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);

    }

    protected void initUI(Context context) {
        rootView= (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.view_recycle, this, true);
         mRefreshRecyclerView=(RecyclerViewSupport) rootView.findViewById(R.id.refreshRecycleView);

        //设置SwipeRefreshLayout
        mSwipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeLayout);

        mSectionAdapterHelper=new SectionAdapterHelper();
        mSectionAdapterHelper.init(context,mRefreshRecyclerView);

       View mIEmptyView =rootView.findViewById(R.id.emptyNoticeView);
        mRefreshRecyclerView.setEmptyView(mIEmptyView);

        mSectionAdapterHelper.setIAddItemView(this);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeLayout.setRefreshing(false);
            }
        });
    }

    public RecyclerView getRefreshRecyclerView() {
        return mRefreshRecyclerView;
    }

    public void initLayoutManager(RecyclerView.LayoutManager layout) {
        mSectionAdapterHelper.initLayoutManager(layout);
    }
    public void updateSection(Section nextSection) {
        mSectionAdapterHelper.updateSection(nextSection);
    }


public void  setSpanCount(int  spanCount){
    mSectionAdapterHelper.setSpanCount(spanCount);
}
    public void clean() {
        mSectionAdapterHelper.clean();
    }



       public  void setRefreshingEnd(){
         mSwipeLayout.setRefreshing(false);
       }
     public void  initSwipeLayout(SwipeRefreshLayout.OnRefreshListener listener){

         mSwipeLayout.setColorSchemeColors(Color.BLUE,
                 Color.GREEN,
                 Color.YELLOW,
                 Color.RED);


         // 设置手指在屏幕下拉多少距离会触发下拉刷新
         mSwipeLayout.setDistanceToTriggerSync(300);
         // 设定下拉圆圈的背景
         mSwipeLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
         // 设置圆圈的大小
         mSwipeLayout.setSize(SwipeRefreshLayout.LARGE);

         //设置下拉刷新的监听
         mSwipeLayout.setOnRefreshListener(listener);
     }


     public void  initSwipePullLayout(final OnloadMoreCallBack callBack){
         mRefreshRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                 super.onScrollStateChanged(recyclerView, newState);
                 //判断是否到底部了
                 if (newState ==RecyclerView.SCROLL_STATE_IDLE &&
                         lastVisibleItemPosition + 1 == mSectionAdapterHelper.getAdapter().getItemCount()) {

                     callBack.call();
//                     new Handler().postDelayed(new Runnable() {
//                         @Override
//                         public void run() {
//                             //如果还有数据则加载更多
//                             if(!getIsFinish()){
//                                 iLoadMoreData.loadMoreData();
//                             }
//                         }
//                     },1000);
                 }
             }
             @Override
             public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                 super.onScrolled(recyclerView,dx, dy);
                 //获取最后一个项目位置
                 lastVisibleItemPosition =mSectionAdapterHelper.getRecycleViewManger().findLastVisibleItemPosition();
             }
         });


     }


    public interface OnloadMoreCallBack{
        void call();
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
        Class  classView= RecycleConfig.getInstance().getDefaultViewNameMap().get(viewType);

        if (itemView==null&&classView!=null){
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

        if (itemView==null){
//             if (IItemView.ViewTypeEnum.ITEM.value()==viewType){
//                 itemView=new ContentItemView(getContext());
//             }
//             else if (IItemView.ViewTypeEnum.SECTION.value()==viewType){
//                 itemView=new ContentItemView(getContext());
//             }
//             else if (IItemView.ViewTypeEnum.SPLITE.value()==viewType){
//                 itemView=new SpliteView(getContext());
//             }else{
                 itemView=new ContentItemView(getContext());
//             }
        }
        return itemView;
    }

    public List<SelectBean> getSelect(String section) {

        return RecycleConfig.getInstance().getSelectUtils().getSelect(section);
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
    public void updateItem(IDyItemBean addressItemBean) {
        mSectionAdapterHelper.updateItem(addressItemBean.getSection(),addressItemBean);
    }
    public  View  getItemView(IDyItemBean iDyItemBean){
          return mSectionAdapterHelper.getItemView(iDyItemBean);
    }

    public void deleteItem(String sectionId,String deleteId){
        mSectionAdapterHelper.deleteItem( sectionId,deleteId);
    }

    public void scrollToBottom(){
        mRefreshRecyclerView.scrollToPosition(mSectionAdapterHelper.getCount());
    }

   public void addSection(Section section) {
       mSectionAdapterHelper.addSection(section);
   }

}
