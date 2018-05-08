package com.easysoft.dyview;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.DefaultLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.easy.recycleview.recycleview.AddressRecycleView;

import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.IItemView;
import com.easy.recycleview.recycleview.item.bean.AddressHeadImgeSettings;
import com.easy.recycleview.recycleview.item.bean.RightSecondImgSettings;
import com.easy.recycleview.recycleview.sectionview.Section;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final java.lang.String SECTION_NEW = "new";
    private static final java.lang.String SECTION_GRID = "grid";
    AddressRecycleView recycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView= findViewById(R.id.recycleView);

        List<AddressItemBean> newSectionList=new ArrayList<AddressItemBean>();

        AddressItemBean newItemBean=new AddressItemBean();
        newItemBean.setTitle("助手小Q");
        newItemBean.setOnItemListener(new IItemView.onItemClick() {
            @Override
            public void onItemClick(IItemView.ClickTypeEnum typeEnum, AddressItemBean bean) {

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
        String msg="广州，简称穗，别称羊城、花城，是广东省省会、副省级市、国家中心城市、超大城市、国际大都市、国际商贸中心、国际综合交通枢纽、国家综合性门户城市，首批沿海开放城市，也是南部战区司令部驻地。广州地处广东省中南部，珠江三角洲北缘，濒临南海，邻近香港、澳门，是中国通往世界的南大门，是粤港澳";
        newItemBean.setRightFirstText(msg);
        newSectionList.add(newItemBean);




        Section  newSection=new Section(SECTION_NEW);
        newSection.setPosition(0);

        newSection.setName("其他");
        newSection.setDataMaps(newSectionList);
        recycleView.updateSection(newSection);

        Section  gridViewSection=new Section(SECTION_GRID);
        gridViewSection.setPosition(1);

        gridViewSection.setName("网格布局");


        List<AddressItemBean> gridectionList=new ArrayList<AddressItemBean>();

        for (int i=0;i<2;i++){
            AddressItemBean gridItemBean=new AddressItemBean();
            gridItemBean.setViewType(IItemView.ViewTypeEnum.INFO_CARD_VIEW.value());
            gridItemBean.setTitle("name"+i);
            gridectionList.add(gridItemBean);
        }

        gridViewSection.setDataMaps(gridectionList);
        recycleView.updateSection(gridViewSection);

        final GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setItemCount(gridectionList.size()-1);
        final List<LayoutHelper> helpers = new LinkedList<>();
        helpers.add(DefaultLayoutHelper.newHelper(newSectionList.size()+1));
        helpers.add(gridLayoutHelper);
        recycleView.setLayoutHelpers(helpers);




//        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                outRect.set(1, 1, 1, 1);
//            }
//        });
////        Intent  testIntent=new Intent(MainActivity.this,VlayoutActivity.class);
//        startActivity(testIntent);

    }
}
