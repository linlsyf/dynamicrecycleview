package com.easysoft.dyview;

import android.app.Activity;
import android.os.Bundle;

import com.easy.recycleview.AddressRecycleView;
import com.easy.recycleview.bean.Section;
import com.easy.recycleview.custom.baseview.utils.ToastUtils;
import com.easy.recycleview.custom.bean.AddressHeadImgeSettings;
import com.easy.recycleview.custom.bean.AddressItemBean;
import com.easy.recycleview.custom.bean.SelectBean;
import com.easy.recycleview.inter.IAddressItemBean;
import com.easy.recycleview.inter.IItemView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final java.lang.String SECTION_NEW = "new";
    private static final java.lang.String SECTION_GRID = "grid";
    private static final java.lang.String SECTION_DY = "dy";
    AddressRecycleView recycleView;
//    MutiTypeSelectUtils mutiTypeSelectUtils=new MutiTypeSelectUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView= (AddressRecycleView) findViewById(R.id.recycleView);

        List<IAddressItemBean> newSectionList=new ArrayList<IAddressItemBean>();

        AddressItemBean newItemBean=new AddressItemBean();
        newItemBean.setSelectType(SECTION_NEW);//选择类型
//          newItemBean.setViewType(4);
        newItemBean.setTitle("助手小Q");
        newItemBean.setHint("this is hint");
        newItemBean.setHintShow(true);
        newItemBean.setShowLeftCheckBox(true);
//        newItemBean.setContentBgResid(R.drawable.corners_bg);
//        newItemBean.setOnItemAllClickAble(false);
//        newItemBean.setOnItemClickAble(false);
        newItemBean.setOnItemListener(new IItemView.onItemClick() {
            @Override
            public void onItemClick(IItemView.ClickTypeEnum typeEnum, IAddressItemBean bean) {
//                ToastUtils.show(getApplication(),"点击事件触发了");

            }
        });
        AddressHeadImgeSettings headImgeSettings=new AddressHeadImgeSettings();
        String url="https://avatar.csdn.net/C/3/4/3_liufatao.jpg";
        headImgeSettings.setHeadImgRadius(100);
        headImgeSettings.setHeadImgUrl(url);
        newItemBean.setHeadImgeSettings(headImgeSettings);

        newSectionList.add(newItemBean);

        AddressItemBean newItemBean2=new AddressItemBean();
        newItemBean2.setSelectType(SECTION_NEW);//选择统计类型
//          newItemBean.setViewType(4);
        newItemBean2.setTitle("22222");
        newItemBean2.setHint("this is hint2");
        newItemBean2.setHintShow(true);
        newItemBean2.setShowLeftCheckBox(true);
//        newItemBean2.setContentBgResid(R.drawable.corners_bg);
//        newItemBean2.setOnItemAllClickAble(false);
        newItemBean2.setOnItemListener(new IItemView.onItemClick() {
            @Override
            public void onItemClick(IItemView.ClickTypeEnum typeEnum, IAddressItemBean bean) {
//                ToastUtils.show(getApplication(),"点击事件触发了");

            }
        });        newSectionList.add(newItemBean2);

        AddressItemBean newItemBean3=new AddressItemBean();

//          newItemBean.setViewType(4);
        newItemBean3.setTitle("查看选择数据");
        newItemBean3.setShowLeftCheckBox(false);
        newItemBean3.setOnItemListener(new IItemView.onItemClick() {
            @Override
            public void onItemClick(IItemView.ClickTypeEnum typeEnum, IAddressItemBean bean) {
//                ToastUtils.show(getApplication(),"点击事件触发了");

                List<SelectBean>  selectBeans=  recycleView.getSelect(SECTION_NEW);

                int size=selectBeans.size();
                ToastUtils.show(getApplication(),"选中数："+size);

            }
        });
        newSectionList.add(newItemBean3);

        Section  newSection=new Section(SECTION_NEW);
        newSection.setPosition(0);

        newSection.setName("其他");
        newSection.setDataMaps(newSectionList);
//        Section   section=new Section("");
        recycleView.updateSection(newSection);


        Section  gridViewSection=new Section(SECTION_GRID);
//          gridViewSection.setPosition(1);
        gridViewSection.setName("网格布局");

//        recycleView.initCustomViewCallBack(new AddressRecycleView.CustomViewCallBack() {
//            @Override
//            public View getCustomView(Context context, int viewType) {
//                View itemView=null;
//              if (viewType==IItemView.ViewTypeEnum.ITEM.value()){
//                  ContentItemView contentItemView=new ContentItemView(MainActivity.this)  ;
//                   contentItemView.initSelectUtils(mutiTypeSelectUtils);
//                  itemView=contentItemView;
//
//              }
//              else if(viewType==IItemView.ViewTypeEnum.SECTION.value()){
//            itemView=new SectionView(MainActivity.this);
//                 }
//                else if(viewType==IItemView.ViewTypeEnum.SPLITE.value()){
//            itemView=new SpliteView(MainActivity.this);
//                   }
//                return itemView;
//            }
//        });

    }
}
