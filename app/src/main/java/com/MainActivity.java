package com;

import android.app.Activity;
import android.os.Bundle;

import com.easy.recycleview.DyLayout;
import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.bean.Section;
import com.easy.recycleview.custom.bean.CentLayoutConfig;
import com.easy.recycleview.inter.IDyItemBean;
import com.easy.recycleview.outinter.RecycleConfig;
import com.easysoft.dyview.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final java.lang.String SECTION_NEW = "new";
    private static final java.lang.String SECTION_GRID = "grid";
    private static final java.lang.String SECTION_DY = "dy";
    DyLayout recycleView;
//    MutiTypeSelectUtils mutiTypeSelectUtils=new MutiTypeSelectUtils();
       int  i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView= (DyLayout) findViewById(R.id.recycleView);



        RecycleConfig.getInstance().setThemeConfig(new ThemeConfig().setTitleColorResId(getResources().getColor(R.color.common_title_color)));
//        RecycleConfig.getInstance().setIloadImage(new I);

        List<IDyItemBean> newSectionList=new ArrayList<IDyItemBean>();



        final DyItemBean newItemBean=new DyItemBean();




        newItemBean.setCentLayoutConfig(new CentLayoutConfig().setName("中间标题"));

        newSectionList.add(newItemBean);


         DyItemBean  secondItemBean=new DyItemBean();
        String url="https://avatar.csdn.net/C/3/4/3_liufatao.jpg";

        secondItemBean.setCentLayoutConfig(new CentLayoutConfig().setImgRadius(100).setImgResId(R.drawable.empty_photo));


        newSectionList.add(secondItemBean);


        Section  newSection=new Section(SECTION_NEW);

        newSection.setDataMaps(newSectionList);
          newSection.setAutoAddSpliteLine(false);
         recycleView.updateSection(newSection);


    }
}
