package com.easysoft.dyview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.easy.recycleview.recycleview.AddressRecycleView;

import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.IItemView;
import com.easy.recycleview.recycleview.item.bean.AddressHeadImgeSettings;
import com.easy.recycleview.recycleview.item.bean.RightSecondImgSettings;
import com.easy.recycleview.recycleview.sectionview.Section;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final java.lang.String SECTION_NEW = "new";
    AddressRecycleView recycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView=(AddressRecycleView)  findViewById(R.id.recycleView);

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


        RightSecondImgSettings addressRightSecondImgSettings =new RightSecondImgSettings();
        addressRightSecondImgSettings.setRightSecondImgRadius(50);
        addressRightSecondImgSettings.setRightSecondImgURL(url);
        newItemBean.setRightSecondImgSettings(addressRightSecondImgSettings);
        newSectionList.add(newItemBean);
        Section  newSection=new Section(SECTION_NEW);
        newSection.setPosition(0);

        newSection.setName("其他");
        newSection.setDataMaps(newSectionList);
        recycleView.updateSection(newSection);

    }
}
