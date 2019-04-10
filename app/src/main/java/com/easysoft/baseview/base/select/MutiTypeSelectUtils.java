package com.easysoft.baseview.base.select;

import android.content.Context;

import com.easy.recycleview.bean.SelectBean;
import com.easy.recycleview.inter.IAddressItemBean;
import com.easysoft.baseview.utils.StringUtils;
import com.easysoft.baseview.utils.ToastUtils;
import com.easysoft.bean.AddressItemBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
   *<br> 创建者：林党宏
   *<br>时间：2016/12/5 11:47
   *<br>注释：多选数据管理类
   */
public class MutiTypeSelectUtils  {
    /**分类选中集合 */
  protected Map<String,List<AddressItemBean>> mSelectedMap =new HashMap<String,List<AddressItemBean>>();
    /** type 类型对应是否可以多选*/
  protected Map<String,Boolean> mTypeMutiMap =new HashMap<String,Boolean>();

    /**已选提示 */
    private String mHasSelectNoticeText="";
       Context mContext;
  /**传入已选的数据是否可以去除 */
    private boolean intentSelectedCanEdit=true;
  /**传入的已选数据 */
    private   Map<String ,AddressItemBean> intentSelectedMap=new HashMap<String,AddressItemBean>();
    public MutiTypeSelectUtils(Context context) {
        mContext=context;
  }


  public Map<String, List<AddressItemBean>> getSelectedMap() {
      return mSelectedMap;
  }



  public boolean select(boolean setIsSelect, AddressItemBean mItemMap){
      String selectType="";

      if (!intentSelectedCanEdit){
          if (intentSelectedMap.containsKey(mItemMap.getId())){
              return true;//如果传入的数据不可选那么直接返回选中状态为已选
          }
      }
      selectType=mItemMap.getSelectType();
      if (selectType==""){
          selectType=mItemMap.getSection();
          mItemMap.setSelectType(selectType);
      }
      List<AddressItemBean> typeListMap=null;
      boolean mIsMutiUserSelect=true;
     //从多类型中获取数据源
       typeListMap=mSelectedMap.get(selectType);
    //判读是否为多选
      if (mTypeMutiMap.containsKey(selectType)){
          mIsMutiUserSelect=mTypeMutiMap.get(selectType);
      }else{
          mTypeMutiMap.put(selectType,true);//默认为多选
      }
      if (!mIsMutiUserSelect){
          if (!setIsSelect){
              reomveSelect(typeListMap,mItemMap);
              return false;//确认点击为单选取消选中
          }else {
              if (typeListMap!=null&&typeListMap.size()>0){
                  if (StringUtils.isNotEmpty(mHasSelectNoticeText)){
                        ToastUtils.showShort(mHasSelectNoticeText);
                  }
                  return false;//确认点击为单选选中无效
              }else{
                  if (typeListMap==null){
                      typeListMap=new ArrayList<AddressItemBean>();
                  }
                  typeListMap.add(mItemMap);
                  mSelectedMap.put(mItemMap.getSelectType(),typeListMap);
                  return true;//确认点击为单选选中
              }
          }
      }
      else{
          if (!setIsSelect){
              reomveSelect(typeListMap,mItemMap);
              return false;//确认点击为多选取消选中
          }else  {
              if (typeListMap==null){
                  typeListMap=new ArrayList<AddressItemBean>();
              }
              typeListMap.add(mItemMap);
              mSelectedMap.put(mItemMap.getSelectType(),typeListMap);
              return true;//确认点击为多选选中
          }
      }
  }
    /**
     *创建者：林党宏
     *时间：2017/1/23
     *注释：移除已选
     */
    public void reomveSelect(List<AddressItemBean> typeListMap, AddressItemBean removeMap){
        int index=-1;
        for (int i=0; i<typeListMap.size();i++){
            AddressItemBean itemMap=typeListMap.get(i);
            if (itemMap.getId().equals(removeMap.getId())){
                index=i;
                break;
            }
        }
        if (index>-1){
            typeListMap.remove(index);//确认点击为单选取消选中
            mSelectedMap.put(removeMap.getSelectType(),typeListMap);
        }

    }



public void setHasSelectNoticeText(String text){
  mHasSelectNoticeText=text;
}

    public void setIsMutiSelect(String type, boolean isMutiSelect) {
        mTypeMutiMap.put(type,isMutiSelect);
    }
    public void  initSelectDatas(String type,List<AddressItemBean> datas)  {
        List<AddressItemBean> newSelectList=new ArrayList<AddressItemBean>();

        intentSelectedMap.clear();
        for (int i=0;i<datas.size();i++){
            AddressItemBean  itembean=datas.get(i);
            intentSelectedMap.put(itembean.getId(),itembean);
            newSelectList.add(itembean);
        }
        mSelectedMap.put(type,newSelectList);


    }


    /**
     *创建者：林党宏
     *时间：2017/3/2
     *注释：传入数据是否可以编辑
     */
    public void setIntentSelectedCanEdit(boolean intentSelectedCanEdit) {
        this.intentSelectedCanEdit = intentSelectedCanEdit;
    }
    public boolean getItemCanSelectEdit(String id){
        return !(!intentSelectedCanEdit & intentSelectedMap.containsKey(id));
    }

     public boolean  isCheckSelectedById(String sectionId,String id){
         boolean isContain=false;
         List<AddressItemBean> dataList=   mSelectedMap.get(sectionId);
          if (dataList==null){
              return false;
          }
          if (dataList.size()==0){
              return false;
          }
          for (AddressItemBean itemBean:dataList){
              if (itemBean.getId().equals(id)){
                  isContain=true;
                  break;
              }
          }
         return isContain;
     }

    public List<SelectBean> getSelect(String selectType){
        Map<String, List<AddressItemBean>>  selectMap=getSelectedMap();

        List<AddressItemBean> departmentList=new ArrayList<AddressItemBean>();
        int departmentSize=0;
        if (selectMap.containsKey(selectType)){
            departmentList=   selectMap.get(selectType);
            departmentSize= departmentList.size();
        }

        List<SelectBean> resultDepartmentList=new ArrayList<SelectBean>();
        for (int i=0;i<departmentSize;i++){
            SelectBean itembean=new SelectBean();
            IAddressItemBean selectItembean=departmentList.get(i);
            itembean.setId(selectItembean.getId());
            itembean.setTitle(selectItembean.getTitle());
            resultDepartmentList.add(itembean);
        }
        return resultDepartmentList;
    }


//    public void changeSelectSelectItem(List<Section> mSectionList,String sectionId,AddressItemBean newDataMap){
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
//                       select(newDataMap.isLeftCheckBoxIsChecked(),itemMap);
//                    }
//                    break;
//                }
//            }
//            if (updateItemIndex==-1){//如果当前列表没有那么 找选中记录中消除
//                select(newDataMap.isLeftCheckBoxIsChecked(),newDataMap);
//            }
//        }else{
//          select(newDataMap.isLeftCheckBoxIsChecked(),newDataMap);
//        }
////        notifyDataSetChanged();
//    }


}
