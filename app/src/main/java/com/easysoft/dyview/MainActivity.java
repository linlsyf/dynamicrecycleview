package com.easysoft.dyview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.core.recycleview.AddressRecycleView;

import com.core.recycleview.RecycleViewManage;
import com.core.recycleview.item.AddressItemBean;
import com.core.recycleview.item.IItemView;
import com.core.recycleview.item.bean.AddressHeadImgeSettings;
import com.core.recycleview.sectionview.Section;
import com.easysoft.dyview.R;

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

//        recycleView.setIloadImage(ImageUtils.getInStance());

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
        newSectionList.add(newItemBean);
        Section  newSection=new Section(SECTION_NEW);
        newSection.setPosition(0);

        newSection.setName("其他");
        newSection.setDataMaps(newSectionList);
        recycleView.updateSection(newSection);

    }
}
