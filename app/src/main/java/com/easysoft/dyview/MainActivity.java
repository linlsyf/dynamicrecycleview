package com.easysoft.dyview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.easy.recycleview.AddressRecycleView;
import com.easy.recycleview.bean.Section;
import com.easy.recycleview.inter.IAddressItemBean;
import com.easy.recycleview.inter.IItemView;
import com.easysoft.baseview.item.ContentItemView;
import com.easysoft.baseview.utils.ToastUtils;
import com.easysoft.bean.AddressHeadImgeSettings;
import com.easysoft.bean.AddressItemBean;
import com.easysoft.dyview.view.InfoCardView;
import com.easysoft.dyview.view.dyCardView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final java.lang.String SECTION_NEW = "new";
    private static final java.lang.String SECTION_GRID = "grid";
    private static final java.lang.String SECTION_DY = "dy";
    AddressRecycleView recycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView= (AddressRecycleView) findViewById(R.id.recycleView);
        recycleView.initCustomViewCallBack(new AddressRecycleView.CustomViewCallBack() {
            @Override
            public View getCustomView(Context context, int viewType) {
                View  itemView;
                if (viewType==3){
                    itemView=new InfoCardView(context);
                    return itemView;
                }
                if (viewType==4){
                    itemView=new dyCardView(context);
                    return itemView;
                }
                return null;
            }
        });




        List<IAddressItemBean> newSectionList=new ArrayList<IAddressItemBean>();

        AddressItemBean newItemBean=new AddressItemBean();
//          newItemBean.setViewType(4);
        newItemBean.setTitle("助手小Q");
        newItemBean.setHint("this is hint");
        newItemBean.setHintShow(true);
        newItemBean.setShowLeftCheckBox(true);
        newItemBean.setContentBgResid(R.drawable.corners_bg);
//        newItemBean.setOnItemAllClickAble(false);
        newItemBean.setOnItemClickAble(false);
        newItemBean.setOnItemListener(new IItemView.onItemClick() {
            @Override
            public void onItemClick(IItemView.ClickTypeEnum typeEnum, IAddressItemBean bean) {
                ToastUtils.show(getApplication(),"点击事件触发了");


            }
        });
        AddressHeadImgeSettings headImgeSettings=new AddressHeadImgeSettings();
        String url="https://avatar.csdn.net/C/3/4/3_liufatao.jpg";
        headImgeSettings.setHeadImgRadius(100);
        headImgeSettings.setHeadImgUrl(url);
        newItemBean.setHeadImgeSettings(headImgeSettings);



//        RightSecondImgSettings addressRightSecondImgSettings =new RightSecondImgSettings();
//        addressRightSecondImgSettings.setRightSecondImgRadius(50);
//        addressRightSecondImgSettings.setRightSecondImgURL(url);
//        newItemBean.setRightSecondImgSettings(addressRightSecondImgSettings);
//        String msg="广州，简称穗，别称羊城、花城，是广东省省会、副省级市、国家中心城市、超大城市、国际大都市、国际商贸中心、国际综合交通枢纽、国家综合性门户城市，首批沿海开放城市，也是南部战区司令部驻地。广州地处广东省中南部，珠江三角洲北缘，濒临南海，邻近香港、澳门，是中国通往世界的南大门，是粤港澳";
//        newItemBean.setRightFirstText(msg);
        newSectionList.add(newItemBean);


        Section  newSection=new Section(SECTION_NEW);
        newSection.setPosition(0);

        newSection.setName("其他");
        newSection.setDataMaps(newSectionList);
        recycleView.updateSection(newSection);


        Section  gridViewSection=new Section(SECTION_GRID);
        gridViewSection.setPosition(1);

        gridViewSection.setName("网格布局");

//        recycleView.getSectionAdapterHelper().setc
//        recycleView.initIMutiTypeSelectUtils(new MutiTypeSelectUtils);
        recycleView.initCustomViewCallBack(new AddressRecycleView.CustomViewCallBack() {
            @Override
            public View getCustomView(Context context, int viewType) {
                View itemView=null;
//              if (viewType==IItemView.ViewTypeEnum.ITEM.value()){
             itemView=new ContentItemView(MainActivity.this)  ;
//               }
//              else if(viewType==IItemView.ViewTypeEnum.SECTION.value()){
//            itemView=new SectionView(MainActivity.this);
//                 }
//                else if(viewType==IItemView.ViewTypeEnum.SPLITE.value()){
//            itemView=new SpliteView(MainActivity.this);
//                   }
                return itemView;
            }
        });

    }
}
