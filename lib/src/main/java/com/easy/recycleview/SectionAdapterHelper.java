package com.easy.recycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.easy.recycleview.bean.Section;
import com.easy.recycleview.inter.IAddressItemBean;
import com.easy.recycleview.inter.IItemView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *创建者：林党宏
 *时间：2017/4/17
 *注释：分组数据界面
 */
public class SectionAdapterHelper {
    /**adapter 数据源集合 */
    private ArrayList<IAddressItemBean> mDataArrayList = new ArrayList<IAddressItemBean>();
    /** section  集合*/
    private List<Section> mSectionList=new ArrayList<Section>();
    /**adapter */
    SectionedListViewAdapter mSectionedExpandableGridAdapter;
    /** 线性管理 recycleview */
    RecyclerView.LayoutManager  mRecycleViewManger;
//    /**多选辅助工具 */
//    IMutiTypeSelectUtils mSelectUtils;
   /**显示recycleview */
    RecyclerView mRecyclerView;
    private updateListener mUpdateListener;
    /**空数据显示界面 */
//    private View mEmptyView;
    /**上下文环境 */
     Context mContext;
    /** 控件可实现 从写item*/
    private IAddItemView mIAddItemView;

//    private List<LayoutHelper> layoutHelpers=new ArrayList<>();

    public void init(Context context,RecyclerView recyclerView){
        mContext=context;
        mRecyclerView=recyclerView;

//        mRecycleViewManger = new LinearLayoutManager(context);
        mRecycleViewManger= new GridLayoutManager(recyclerView.getContext(), 6, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mRecycleViewManger);
        mSectionedExpandableGridAdapter = new SectionedListViewAdapter(context, mDataArrayList);
        mRecyclerView.setAdapter(mSectionedExpandableGridAdapter);

    }

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

    public SectionedListViewAdapter getAdapter() {
        return mSectionedExpandableGridAdapter;
    }

    /**
     *创建者：林党宏
     *时间：2017/1/20
     *注释：添加一个分组
     */
    public void addNewSection(Section section) {
        mSectionList.add(section);
    }
    public void addSection(Section section) {
        boolean index = checkIsExitSection(section);
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
            notifyDataSetChanged();
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
        notifyDataSetChanged();
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
            List<IAddressItemBean> removeSectionMap = mSectionList.get(indexSectionRemove).getDataMaps();
            int itemRemove = -1;
            for (int i = 0; i < removeSectionMap.size(); i++) {
                IAddressItemBean itemMap = removeSectionMap.get(i);
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
            notifyDataSetChanged();
            if (mUpdateListener != null && deleteSection) {
                mUpdateListener.deleteSection(sectionId);
            }
        }
    }
    public void removeItem(int postion){
        if (postion<=mDataArrayList.size()-1){
            IAddressItemBean addressItemBean =   mDataArrayList.get(postion);
            removeItem(addressItemBean.getSection(), addressItemBean.getId());
        }
    }

    /**
     *创建者：林党宏
     *时间：2017/2/8
     *注释：只更新部分字段
     */
    public void updateItem(String sectionId,IAddressItemBean newDataMap){
        int updateSectionIndex=-1;
        String id=newDataMap.getId();
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);
            if (sectionId .equals(itemSection.getId())){
                updateSectionIndex=i;
            }
        }
        if (updateSectionIndex>-1){
            List<IAddressItemBean>  removeSectionMap=  mSectionList.get(updateSectionIndex).getDataMaps();
            for(int i=0;i<removeSectionMap.size();i++){
                IAddressItemBean itemMap=removeSectionMap.get(i);
                if (id.equals(itemMap.getId())){
                    removeSectionMap.set(i,newDataMap);
                    break;
                }
            }
        }
        notifyDataSetChanged();
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
            List<IAddressItemBean>  removeSectionMap=  mSectionList.get(updateSectionIndex).getDataMaps();

            List<IAddressItemBean>  newSectionMap=new ArrayList<IAddressItemBean>();
            for(int i=0;i<removeSectionMap.size();i++){
                IAddressItemBean itemMap=removeSectionMap.get(i);
                if (!deleteId.equals(itemMap.getId())){
                    newSectionMap.add(itemMap);
                }
            }
            mSectionList.get(updateSectionIndex).setDataMaps(newSectionMap);
            notifyDataSetChanged();
        }
    }
    public IAddressItemBean getItem(String sectionId, String id){
        IAddressItemBean resultBean=null;
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);
            if (itemSection.getId().equals(sectionId)){
                for (int j=0;j<itemSection.getDataMaps().size();j++){
                    IAddressItemBean item=itemSection.getDataMaps().get(j);
                    if (item.getId().equals(id)){
                        return item;
                    }
                }
            }
        }
        return resultBean;
    }
    public void refreshCanSelect(String sectionId,boolean isCanSelect){
        for (int i=0;i<mDataArrayList.size();i++){
            IAddressItemBean itemBean=mDataArrayList.get(i);
            if (itemBean.getSelectType().equals(sectionId)){
                itemBean.setShowLeftCheckBox(isCanSelect);
            }
        }
        mSectionedExpandableGridAdapter.notifyDataSetChanged();
    }


//    public void changeSelectSelectItem(String sectionId,IAddressItemBean newDataMap){
//        int updateSectionIndex=-1;
//        String id=newDataMap.getId();
//        for (int i=0;i<mSectionList.size();i++){
//            Section itemSection=mSectionList.get(i);
//            if (itemSection.getId().equals(sectionId)){
//                updateSectionIndex=i;
//            }
//        }
//        if (updateSectionIndex>-1){
//            List<IAddressItemBean>  removeSectionMap=  mSectionList.get(updateSectionIndex).getDataMaps();
//            int updateItemIndex=-1;
//            for(int i=0;i<removeSectionMap.size();i++){
//                IAddressItemBean itemMap=removeSectionMap.get(i);
//                if (id.equals(itemMap.getId())){
//                    if (itemMap.isShowLeftCheckBox()){
//                        itemMap.setLeftCheckBoxIsChecked(newDataMap.isLeftCheckBoxIsChecked());
//                        mSelectUtils.select(newDataMap.isLeftCheckBoxIsChecked(),itemMap);
//                    }
//                    break;
//                }
//            }
//            if (updateItemIndex==-1){//如果当前列表没有那么 找选中记录中消除
//                mSelectUtils.select(newDataMap.isLeftCheckBoxIsChecked(),newDataMap);
//            }
//        }else{
//            mSelectUtils.select(newDataMap.isLeftCheckBoxIsChecked(),newDataMap);
//        }
//        notifyDataSetChanged();
//    }


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
        notifyDataSetChanged();


    }
    public void clean(){
        mSectionList=new ArrayList<Section>();
        notifyDataSetChanged();
    }
    public void updateSection(Section section){
        updateSection(section,false);
    }

        /**
         *创建者：林党宏
         *时间：2017/1/20
         *注释：更新或者添加
         * @param isRefresh  默认只刷新数据不刷新界面
         */
    public void updateSection(Section section,boolean isRefresh){
        boolean index = checkIsExitSection(section);
        if (!index){
            addNewSection(section);
        }
//        if (isRefresh){
            notifyDataSetChanged();
//        }else{
//            notifyData();
//
//        }
    }

    private boolean checkIsExitSection(Section section) {
        List<Section> mNewSectionList=new ArrayList<Section>();
        boolean index=false;
        for (int i=0;i<mSectionList.size();i++){
            Section itemSection=mSectionList.get(i);
            if (itemSection.getId().equals(section.getId())){
                index=true;
                mNewSectionList.add(section);
            }else{
                mNewSectionList.add(itemSection);
            }
        }
        this.mSectionList=mNewSectionList;
        return index;
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
//    /**
//     *创建者：林党宏
//     *时间：2017/2/8
//     *注释：所有分组已选数据
//     */
//    public Map<String, List<IAddressItemBean>> getSelectMap(){
//      return   mSelectUtils.getSelectedMap();
//    }


//    public boolean getItemCanSelectEdit(String id){
//        return   mSelectUtils.getItemCanSelectEdit(id);
//    }
    /**
     *创建者：林党宏
     *时间：2017/1/20
     *注释：获取list 行数
     */
    public int getCount(){
        int count=mDataArrayList.size();
        return count;
    }
//    public void setIsMutiSelect(String type, boolean isMutiSelect) {
//        mSectionedExpandableGridAdapter.setIsMutiSelect(type,isMutiSelect);
//    }
    /**
//     *创建者：林党宏
//     *时间：2017/1/20
//     *注释：添加点击事件
//     */
//    public void setOnItemClick(IItemView.onItemClick listener ){
//        mSectionedExpandableGridAdapter.setOnItemClick(listener);
//    }

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
//            AddressItemBean itemSimpleMap=mDataArrayList.get(i);
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
    public void notifyDataSetChanged() {
            notifyData();
        mSectionedExpandableGridAdapter.notifyDataSetChanged();
        checkIsShowEmpty();
    }

    private void checkIsShowEmpty() {
//        if (mEmptyView != null && getAdapter() != null) {
//            final boolean emptyViewVisible = mDataArrayList.size() == 0;
//            setEmptyViewVisibility(emptyViewVisible, true);
//
//         }
    }

    private void notifyData() {
        mDataArrayList.clear();
        Collections.sort(mSectionList, new SectinComparator());
        for (int i=0;i<mSectionList.size();i++){
            mDataArrayList.addAll(mSectionList.get(i).getDataMaps());
        }
        checkIsShowEmpty();
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

        public List<IAddressItemBean> getDataArrayList() {
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
//        notifyDataSetChanged();
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
//        if (viewType==IItemView.ViewTypeEnum.ITEM.value()){
//            itemView=new ContentItemView(mContext)  ;
//        }
//        else if(viewType==IItemView.ViewTypeEnum.SECTION.value()){
//            itemView=new SectionView(mContext);
//        }
//        else if(viewType==IItemView.ViewTypeEnum.SPLITE.value()){
//            itemView=new SpliteView(mContext);
//        }
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
        private ArrayList<IAddressItemBean> mDataArrayList;

        public SectionedListViewAdapter(Context context, ArrayList<IAddressItemBean> dataArrayList) {

            mContext = context;
            mDataArrayList = dataArrayList;
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
            final IAddressItemBean item=mDataArrayList.get(position);
//            item.setPosition(position);
            IItemView itemView= holder.getItemView();
            itemView.initData(item);
        }

        @Override
        public int getItemCount() {
            return mDataArrayList.size();

        }
        @Override
        public int getItemViewType(int position) {
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
                        return   mDataArrayList.get(position).getSpanSize();
                    }

            });
        }
    }
    }
}
