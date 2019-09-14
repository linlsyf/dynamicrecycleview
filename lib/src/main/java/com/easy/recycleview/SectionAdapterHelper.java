package com.easy.recycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.bean.Section;
import com.easy.recycleview.custom.baseview.utils.StringUtils;
import com.easy.recycleview.inter.IDyItemBean;
import com.easy.recycleview.inter.IItemView;
import com.easy.recycleview.utils.RecyDiffCallback;
import com.easy.recycleview.utils.TopSmoothScroller;
import com.easy.recycleview.view.RecyclerViewSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *时间：2017/4/17
 *注释：分组数据界面
 */
public class SectionAdapterHelper {
    /**adapter 数据源集合 */
    private ArrayList<IDyItemBean> mDataArrayList = new ArrayList<IDyItemBean>();
    /** section  集合*/
    private List<Section> mSectionList=new ArrayList<Section>();
    /**adapter */
    SectionedListViewAdapter mSectionedExpandableGridAdapter;
    /** 线性管理 recycleview */
//    LinearLayoutManager mRecycleViewManger;
    GridLayoutManager  mRecycleViewManger;
//    /**多选辅助工具 */
//    IMutiTypeSelectUtils mSelectUtils;
   /**显示recycleview */
   RecyclerViewSupport mRecyclerView;
    private updateListener mUpdateListener;
    /**空数据显示界面 */
//    private View mEmptyView;
    /**上下文环境 */
     Context mContext;
    /** 控件可实现 从写item*/
    private IAddItemView mIAddItemView;
    private boolean mIsSortSection =false;
    private ArrayList<IDyItemBean> mOldDataList;

    public void init(Context context,RecyclerViewSupport recyclerView){
        mContext=context;
        mRecyclerView=recyclerView;
//        mRecycleViewManger= new LinearLayoutManager(recyclerView.getContext());
        mRecycleViewManger= new GridLayoutManager(recyclerView.getContext(), 6, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mRecycleViewManger);
        mSectionedExpandableGridAdapter = new SectionedListViewAdapter(context);
        mRecyclerView.setAdapter(mSectionedExpandableGridAdapter);



    }

//    public GridLayoutManager getRecycleViewManger() {
//        return mRecycleViewManger;
//    }



    public void  setSpanCount(int  spanCount){
        mRecycleViewManger= new GridLayoutManager(mRecyclerView.getContext(), spanCount, GridLayoutManager.VERTICAL, false);
         mRecyclerView.setLayoutManager(mRecycleViewManger);
    }
    public void setEmptyView(View emptyView) {
//        this.mEmptyView = emptyView;
    }
//    public LinearLayoutManager getRecycleViewManger() {
//        return mRecycleViewManger;
//    }

    public SectionedListViewAdapter getSectionAdapter() {


        return mSectionedExpandableGridAdapter;
    }

    public void  loadMoreRefreshing(){
       int postion= mRecycleViewManger.findLastCompletelyVisibleItemPosition()+1;
        mSectionedExpandableGridAdapter.notifyDataSetChanged();
       // mRecyclerView.smoothScrollToPosition(postion);

    }

    public void notifyDataSetChanged(){
        mSectionedExpandableGridAdapter.notifyDataSetChanged();

    }

    public GridLayoutManager getRecycleViewManger() {
        return mRecycleViewManger;
    }



    public boolean isIsSortSection() {
        return mIsSortSection;
    }

    public void setIsSortSection(boolean mIsSortSection) {
        this.mIsSortSection = mIsSortSection;
    }



    public  void refreshDiff(){
     mSectionedExpandableGridAdapter.getItemCount();
        DiffUtil.DiffResult diffResult = DiffUtil
                .calculateDiff(new RecyDiffCallback(mOldDataList, mDataArrayList),true);

        diffResult.dispatchUpdatesTo(mSectionedExpandableGridAdapter);

    }

    public ArrayList<IDyItemBean> getOldDataList() {
        return mOldDataList;
    }

//    public  void smoothToPosition(int position){
//      mRecyclerView.smoothScrollToPosition(position);
//
//    }



    /**
     *创建者：林党宏
     *时间：2017/1/20
     *注释：添加一个分组
     */
    public void addNewSection(Section section) {
        mSectionList.add(section);
    }
    public void addSection(Section section) {
        boolean index = checkIsExitSection(section)>0?true:false;
        if (!index){
            mSectionList.add(section);
        }

    }
    /**
     *创建者：林党宏
     *时间：2017/1/20
     *注释：清除一个分组
     */
    public void removeSection(String id){
        int indexRemove=-1;
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);
            if (itemSection.getId().equals(id)){
                indexRemove=i;
            }
        }
        if (indexRemove>-1){
            mSectionList.remove(indexRemove);
            refreshDataSetChanged();
        }
    }
    /**
     *创建者：林党宏
     *时间：2017/4/19
     *注释:移除包含该字段的section
     */
    public void removeSectionByContainId(String id){
        List<Section> newSectionList=new ArrayList<Section>();
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);
            if (!itemSection.getId().contains(id)){
                newSectionList.add(itemSection);
            }
        }
        mSectionList=newSectionList;
        refreshDataSetChanged();
    }

    public void removeItem(String sectionId,String id){
        boolean deleteSection=false;
        int indexSectionRemove=-1;
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);
            if (itemSection.getId().equals(sectionId)){
                indexSectionRemove=i;
            }
        }
        if (indexSectionRemove>-1) {
            List<IDyItemBean> removeSectionMap = mSectionList.get(indexSectionRemove).getDataMaps();
            int itemRemove = -1;
            for (int i = 0; i < removeSectionMap.size(); i++) {
                IDyItemBean itemMap = removeSectionMap.get(i);
                if (id.equals(itemMap.getId())) {
                    itemRemove = i;
                }
            }
            int removeSpliteAndItemIndex = itemRemove - 1;
            if (itemRemove > -1) {
                removeSectionMap.remove(itemRemove);
                if (removeSpliteAndItemIndex > -1) {
                    int viewType = removeSectionMap.get(removeSpliteAndItemIndex).getViewType();
                    if (viewType == IItemView.ViewTypeEnum.SPLITE.value()) {
                        removeSectionMap.remove(removeSpliteAndItemIndex);
                        if (removeSpliteAndItemIndex-1>-1){
                            int topSecondType = removeSectionMap.get(removeSpliteAndItemIndex-1).getViewType();
                            if (topSecondType == IItemView.ViewTypeEnum.SECTION.value()) {
                                if (removeSectionMap.size() == 1) {
                                    removeSectionMap.remove(removeSpliteAndItemIndex-1);
                                    deleteSection = true;
                                }
                            }
                        }

                    } else if (viewType == IItemView.ViewTypeEnum.SECTION.value()) {
                        if (removeSectionMap.size() == 1) {
                            removeSectionMap.remove(0);
                            mSectionList.remove(indexSectionRemove);
                            deleteSection = true;
                        }
                    }
                }
                if (!deleteSection){
                    mSectionList.get(indexSectionRemove).setDataMaps(removeSectionMap);
                }
            }
            refreshDataSetChanged();
            if (mUpdateListener != null && deleteSection) {
                mUpdateListener.deleteSection(sectionId);
            }
        }
    }
    public void removeItem(int postion){
        if (postion<=mDataArrayList.size()-1){
            IDyItemBean addressItemBean =   mDataArrayList.get(postion);
            removeItem(addressItemBean.getSection(), addressItemBean.getId());
        }
    }

    public  View  getItemView( IDyItemBean iDyItemBean  ){
        int  i=1;
        int  position=-1;

        String id=iDyItemBean.getId();
        String title=iDyItemBean.getTitle();
        for (IDyItemBean itemBean:mDataArrayList) {
             if (itemBean.getId()==id||itemBean.getTitle()==title){
                 position=i;
                 break;
             }
             i=i+1;

        }

        if (position==-1){
            return null;
        }
        position=position-1;
        return mRecycleViewManger.findViewByPosition(position);

    }

    /**
     *创建者：林党宏
     *时间：2017/2/8
     *注释：只更新部分字段
     */
    public void updateItem(String sectionId,IDyItemBean newDataMap){
        int updateSectionIndex=-1;
        String id=newDataMap.getId();
              if (StringUtils.isEmpty(sectionId)){
                  for ( IDyItemBean  itemBean  :  mDataArrayList ) {
                        if (itemBean.getId()==newDataMap.getId()){
                            sectionId=itemBean.getSection();
                            break;

                        }

                  }

              }

        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);
            if (sectionId .equals(itemSection.getId())){
                updateSectionIndex=i;
            }
        }



        if (updateSectionIndex>-1){
            List<IDyItemBean>  removeSectionMap=  mSectionList.get(updateSectionIndex).getDataMaps();
            for(int i=0;i<removeSectionMap.size();i++){
                IDyItemBean itemMap=removeSectionMap.get(i);
                if (id.equals(itemMap.getId())){
                    removeSectionMap.set(i,newDataMap);
                    break;
                }
            }
        }
        refreshDataSetChanged();
    }
    /** 删除意向*/
    public void deleteItem(String sectionId,String deleteId){
        int updateSectionIndex=-1;
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);
            if (sectionId .equals(itemSection.getId())){
                updateSectionIndex=i;
            }
        }
        if (updateSectionIndex>-1){
            List<IDyItemBean>  removeSectionMap=  mSectionList.get(updateSectionIndex).getDataMaps();

            List<IDyItemBean>  newSectionMap=new ArrayList<IDyItemBean>();
            for(int i=0;i<removeSectionMap.size();i++){
                IDyItemBean itemMap=removeSectionMap.get(i);
                if (!deleteId.equals(itemMap.getId())){
                    newSectionMap.add(itemMap);
                }
            }
            mSectionList.get(updateSectionIndex).setDataMaps(newSectionMap);
            refreshDataSetChanged();
        }
    }
    public IDyItemBean getItem(String sectionId, String id){
        IDyItemBean resultBean=null;
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);
            if (itemSection.getId().equals(sectionId)){
                for (int j=0;j<itemSection.getDataMaps().size();j++){
                    IDyItemBean item=itemSection.getDataMaps().get(j);
                    if (item.getId().equals(id)){
                        return item;
                    }
                }
            }
        }
        return resultBean;
    }

    @Deprecated
    public void refreshCanSelect(String sectionId,boolean isCanSelect){
        for (int i=0;i<mDataArrayList.size();i++){
            IDyItemBean itemBean=mDataArrayList.get(i);
            if (itemBean.getSelectType().equals(sectionId)){
                itemBean.setShowLeftCheckBox(isCanSelect);
            }
        }
        mSectionedExpandableGridAdapter.notifyDataSetChanged();
    }



    /**
     *创建者：林党宏
     *时间：2017/1/20
     *注释：清除指定id 外的所有分组
     */
    public void removeAllExceptById(String id){
        List<Section> mNewSectionList=new ArrayList<Section>();
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);
            if (itemSection.getId().equals(id)){
                mNewSectionList.add(itemSection);
                break;
            }
        }
        mSectionList=mNewSectionList;
        refreshDataSetChanged();


    }
    public void clean(){
        mSectionList=new ArrayList<Section>();
        refreshDataSetChanged();
    }

        /**
         *创建者：林党宏
         *时间：2017/1/20
         *注释：更新或者添加
         */
    public void updateSection(Section section){
       int oldCount=checkIsExitSection(section);
        boolean index = oldCount>0?true:false;
        if (!index||(index&&!section.isLoadMore())){
            wrappingList(section);
            addNewSection(section);//20190831
            refreshDataSetChanged();
        }
        else{
            wrappingList(section);
            List<IDyItemBean> subjects=section.getDataMaps();
            refresh(false,oldCount,subjects.size());
        }
    }

        private void   refresh(boolean all, int startPosition,int endPosition){
        if (all){
            notifyData();
            mSectionedExpandableGridAdapter.notifyDataSetChanged();
        }else{
            notifyData();//此处为刷新数据
           // mSectionedExpandableGridAdapter.notifyItemRangeInserted(startPosition,endPosition);

        }

        }
       public  void  notifyItemRangeInserted( int startPosition,int endPosition){
        mSectionedExpandableGridAdapter.notifyItemRangeInserted(startPosition,endPosition);
      }

    public static  void  wrappingList(Section section){
        int i=0;
        String   sectionId=section.getId();
        List<IDyItemBean>  newSectionList=new CopyOnWriteArrayList<>();//线程安全的ArrayList
        List<IDyItemBean>  sectionList=section.getDataMaps();
        for (IDyItemBean itemBean:sectionList ) {
            if (i!=0&&section.isAutoAddSpliteLine()){
                DyItemBean itemSpliteBean=new DyItemBean();
                itemSpliteBean.setViewType(IItemView.ViewTypeEnum.SPLITE.value());
                    itemSpliteBean.setSection(sectionId);

                newSectionList.add(itemSpliteBean);
            }

            if (StringUtils.isEmpty(itemBean.getSection())){
                itemBean.setSection(sectionId);
            }
            newSectionList.add(itemBean);
            i=i+1;
        }

        section.setDataMaps(newSectionList);
    }

    private int  checkIsExitSection(Section section) {
        List<Section> mNewSectionList=new ArrayList<Section>();
         List<IDyItemBean>  oldDataList=new ArrayList<>();
         int oldDataCount=0;
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);


            if (itemSection.getId().equals(section.getId())){
                oldDataCount=itemSection.getDataMaps().size();

                  if (section.isLoadMore()){
                      itemSection.getDataMaps().addAll(section.getDataMaps());
                      mNewSectionList.add(itemSection);
                  }else{
                      mNewSectionList.add(section);

                  }
                break;
            }else{
                mNewSectionList.add(itemSection);
            }
        }
        this.mSectionList=mNewSectionList;
        return oldDataCount;
    }

    /**
     *创建者：林党宏
     *时间：2017/1/20
     *注释：获取指定分组
     */
    public Section getSection(String id){
        Section getSection=null;
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);
            if (itemSection.getId().equals(id)){
                getSection= itemSection;
                break;
            }
        }
        return getSection;
    }

    /**
     *创建者：林党宏
     *时间：2017/1/20
     *注释：获取list 行数
     */
    public int getCount(){
        int count=mDataArrayList.size();
        return count;
    }

    /**
     *创建者：林党宏
     *时间：2017/1/20
     *注释：滑动到指定位置
     */
    public void scrollToPositionWithOffset(int postion) {
//        if (mRecycleViewManger extends LinearLayoutManager){
//
//            ( (LinearLayoutManager) mRecycleViewManger).scrollToPositionWithOffset(postion , 0);
//        }
    }

    public void smoothscrollToPositionWithOffset(int postion,int y){

    }
    public void smoothscrollToPosition(int postion){
        LinearSmoothScroller s1 = new TopSmoothScroller(mContext);
        s1.setTargetPosition(postion);
        mRecycleViewManger.startSmoothScroll(s1);
    }
     public void smoothscrollToTopNew(){
         int oldPostion=mOldDataList.size();

       smoothscrollToPosition(oldPostion-1);


     }




    /**
     *创建者：林党宏
     *时间：2017/1/20
     *注释：获取指定id对应的index
     */
    public int getSectionIndex(String sectionString){
        int  index=0;
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);

            if (itemSection.getId().equals(sectionString)){
                break;
            }
            index=index+itemSection.getDataMaps().size();
        }
        return index;
    }
//    public SimpleArrayMap<Integer, String> getSectionIndex(){
//        SimpleArrayMap<Integer, String> map=new SimpleArrayMap<Integer, String>();
//
//        for (int i=0;i<mDataArrayList.size();i++){
//            DyItemBean itemSimpleMap=mDataArrayList.get(i);
//            if (itemSimpleMap.getViewType()==IItemView.ViewTypeEnum.SECTION.value()){
//
//                if (itemSimpleMap.isAddSideBar()){
//                    map.put(i,itemSimpleMap.getTitle());
//                }
//            }
//        }
//        return map;
//    }
    /**
     *创建者：林党宏
     *时间：2017/1/20
     *注释：更新adapter数据源并刷新界面
     */
    public void refreshDataSetChanged() {
        refresh(true,0,0);


    }


    private void notifyData() {


        mOldDataList  =   new  ArrayList();
        Collections.addAll(mOldDataList,  new  IDyItemBean[mDataArrayList.size()]);
        Collections.copy(mOldDataList, mDataArrayList);

        mDataArrayList.clear();

        if(mIsSortSection){
            Collections.sort(mSectionList, new SectinComparator());
        }
        for (int i=0;i<mSectionList.size();i++){
            mDataArrayList.addAll(mSectionList.get(i).getDataMaps());
        }
    }

    public boolean isSectionEmpty() {
        /*假设为空*/
        boolean isEmpty = true;
        for (Section section : mSectionList) {
            /*只要有一组不为空，则不为空*/
            if (!section.getDataMaps().isEmpty()) {
                isEmpty = false;
            }
        }
        return isEmpty;
    }


//    public void showError(String error) {
//        setEmptyViewVisibility(true, true);
//        ImageView ivEmpty =(ImageView) mEmptyView.findViewById(R.id.iv_empty);
//        TextView tvEmpty =(TextView) mEmptyView.findViewById(R.id.tv_empty);
//        ivEmpty.setImageDrawable(null);
//        String errorTips = ResourcesUtil.getResourcesString(mContext, R.string.data_loading_error);
//        if (error != null) {
//            errorTips = error + "，" + errorTips;
//        }
//        tvEmpty.setText(errorTips);
//    }

//    public void showLoading() {
//        ImageView ivEmpty = (ImageView) mEmptyView.findViewById(R.id.iv_empty);
//        TextView tvEmpty = (TextView) mEmptyView.findViewById(R.id.tv_empty);
//        tvEmpty.setText(R.string.data_loading);
//        ivEmpty.setImageResource(R.drawable.address_book_list_loading);
//        setEmptyViewVisibility(true, false);
//        RotateAnimation animation = new RotateAnimation(360, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        animation.setDuration(300);
//        animation.setRepeatCount(Animation.INFINITE);
//        ivEmpty.startAnimation(animation);
//    }

    public void setEmptyViewVisibility(boolean visibility, boolean cancelAnimation) {
        if (!visibility || cancelAnimation) {
            cancelEmptyViewAnimation();
        }
//        mEmptyView.setVisibility(visibility ? VISIBLE : GONE);
    }

    private void cancelEmptyViewAnimation() {
//        ImageView ivEmpty =(ImageView) mEmptyView.findViewById(R.id.iv_empty);
//        mEmptyView.clearAnimation();
    }

    public void initLayoutManager(RecyclerView.LayoutManager layout) {
        mRecyclerView.setLayoutManager(layout);
    }

    public void addItemDecoration(SpacesItemDecoration decoration) {
        mRecyclerView.addItemDecoration(decoration);
    }



    private static class SectinComparator implements Comparator<Section> {
        @SuppressLint("DefaultLocale")
        @Override
        public int compare(Section o1, Section o2) {
            int py1 = o1.getPosition();
            int py2 = o2.getPosition();// 判断是否为空""
            int result = py2 - py1;
            if (result > 0) {
                result = -1;//1代表前者排在前面
            } else if (result < 0) {
                result = 1;
            }
            return result;
        }
    }

        public List<IDyItemBean> getDataArrayList() {
        return mDataArrayList;
    }
    public interface updateListener{
         void deleteSection(String sectionId);
    }
    public void setUpdateListener(updateListener updateListener){
        this.mUpdateListener=updateListener;
    }
//    public void setLayoutHelpers(List<LayoutHelper> helpers) {
////        this.mRecycleViewManger.setLayoutHelpers(helpers);
//        mSectionedExpandableGridAdapter.setLayoutHelpers(helpers);
//    }
//    public void addLayoutHelper(LayoutHelper layoutHelper){
////        this.mRecycleViewManger.setLayoutHelpers(helpers);
//         layoutHelpers= mSectionedExpandableGridAdapter.getLayoutHelpers();
//        if (layoutHelper==null){
//            layoutHelpers=new ArrayList<LayoutHelper>();
//        }
//        layoutHelpers.add(layoutHelper);
//    }
//    public void show(){
//        if (layoutHelpers.size()>0){
//            mSectionedExpandableGridAdapter.setLayoutHelpers(layoutHelpers);
//
//        }
//        refreshDataSetChanged();
//    }
   /**
    *创建者：林党宏
    *时间：2017/4/6
    *注释：添加一项自定义界面
    */
    public View addItemView(int viewType){
        View itemView=null;

        if (mIAddItemView!=null){
          itemView=mIAddItemView.addItemView(viewType);
            return  itemView;
        }

        return itemView;
    }

    public interface IAddItemView{
         View addItemView(int viewType);
    }
    public void setIAddItemView(IAddItemView iAddItemView){
        this.mIAddItemView=iAddItemView;
    }

    class SectionedListViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder>  {
        /**数据源 */
        private static final int VIEW_ITEM = 0;  //普通Item View
        private static final int TYPE_FOOTER = 1;  //底部FootView
        private static final int TYPE_FINISH = -1;  //完成FootView


        public SectionedListViewAdapter(Context context) {

            mContext = context;
        }


        @Override
        public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView=null;
            BaseRecyclerViewHolder itemHolder=null;
            itemView=addItemView(viewType);
            itemHolder=new BaseRecyclerViewHolder(itemView);
            return itemHolder;
        }
        @Override
        public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
            final IDyItemBean item=mDataArrayList.get(position);
//            item.setPosition(position);
            IItemView itemView= holder.getItemView();
            itemView.initData(item);
        }

        @Override
        public int getItemCount() {
            return mDataArrayList.size();

        }

//        public void getDatas(){
//
//        }
        @Override
        public int getItemViewType(int position) {

//            if(position + 1 != getItemCount()){
//                return mDataArrayList.get(position).getViewType();
//            }else{
//                if(getItemCount()-1 ==totalNum){
//                    return TYPE_FINISH;
//                }else{
//                    return TYPE_FOOTER;
//                }
//            }


            return mDataArrayList.get(position).getViewType();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if(manager instanceof GridLayoutManager) {
                final GridLayoutManager gridManager = ((GridLayoutManager) manager);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
//                      IDyItemBean  itemBean=    mDataArrayList.get(position);
//                        int size= itemBean.getSpanSize();
                        return   mDataArrayList.get(position).getSpanSize();
                    }

            });
        }
    }
    }
}
