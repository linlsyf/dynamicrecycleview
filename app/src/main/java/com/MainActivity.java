package com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.easy.recycleview.DyLayout;
import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.bean.Section;
import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.baseview.utils.ToastUtils;
import com.easy.recycleview.custom.bean.AddressEditSettings;
import com.easy.recycleview.custom.bean.AddressHeadImgeSettings;
import com.easy.recycleview.custom.bean.RightCenterScaleImgSettings;
import com.easy.recycleview.inter.IDyItemBean;
import com.easy.recycleview.inter.IItemView;
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

        List<IDyItemBean> newSectionList=new ArrayList<IDyItemBean>();

        DyItemBean sectionBean=new DyItemBean();
        sectionBean.setViewType(IItemView.ViewTypeEnum.SECTION.value());
        sectionBean.setTitle("点击查看例句，长按编辑");
//		sectionBean.setTitle("随机待学"+dictList.size()+"个新词");
        newSectionList.add(sectionBean);


        final DyItemBean newItemBean=new DyItemBean();
        newItemBean.setSelectType(SECTION_NEW);//选择类型
//          newItemBean.setViewType(4);
        newItemBean.setTitle("助手小Q");

        AddressEditSettings eidtSettings=new AddressEditSettings();
        eidtSettings.setEditContent("test");
        eidtSettings.setShowEdittext(true);
        eidtSettings.setEdittextCanEdit(false);
        newItemBean.setEidtSettings(eidtSettings);
        newItemBean.setShowLeftCheckBox(true);
//        newItemBean.setContentBgResid(R.drawable.corners_bg);
//        newItemBean.setOnItemAllClickAble(false);
//        newItemBean.setOnItemClickAble(false);
        newItemBean.setOnItemListener(new IItemView.onItemClick() {
            @Override
            public void onItemClick(IItemView.ClickTypeEnum typeEnum, IDyItemBean bean) {
//                ToastUtils.show(getApplication(),"点击事件触发了");

            }
        });
        AddressHeadImgeSettings headImgeSettings=new AddressHeadImgeSettings();
        String url="https://avatar.csdn.net/C/3/4/3_liufatao.jpg";
        headImgeSettings.setHeadImgRadius(100);
        headImgeSettings.setHeadImgUrl(url);
        newItemBean.setHeadImgeSettings(headImgeSettings);

        newSectionList.add(newItemBean);

        DyItemBean newItemBean2=new DyItemBean();
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
            public void onItemClick(IItemView.ClickTypeEnum typeEnum, IDyItemBean bean) {
                ToastUtils.show(getApplication(),"点击事件触发了");

            }
        });        newSectionList.add(newItemBean2);



        final DyItemBean newItemBean3=new DyItemBean();
        newItemBean3.setId("new3");
//          newItemBean.setViewType(4);
        newItemBean3.setTitle("查看选择数据");
        newItemBean3.setShowLeftCheckBox(false);
        newItemBean3.setRightFirstButtonText("标记已学");
//        AddressRightFistImgeSettings rightFistImgeSettings=new AddressRightFistImgeSettings();
//        rightFistImgeSettings.setRightFirstImgResId(R.drawable.ic_filled_star);
//         rightFistImgeSettings.setRightFirstImgRadius(45);
//        newItemBean3.setRightFistImgeSettings(rightFistImgeSettings);


        RightCenterScaleImgSettings rightSecondImgSettings=new RightCenterScaleImgSettings();

//        int radius= DensityUtil.dip2px(this,30);
//        int radiusWidth= DensityUtil.dip2px(this,50);
//        rightSecondImgSettings.setImgRadius(radius);
        rightSecondImgSettings.setRightCenterScaleImgResId(R.drawable.ic_filled_star);
//        rightSecondImgSettings.setLayoutWidth(radiusWidth);
        newItemBean3.setRightCenterScaleImgSettings(rightSecondImgSettings);


        newItemBean3.setOnItemListener(new IItemView.onItemClick() {
            @Override
            public void onItemClick(IItemView.ClickTypeEnum typeEnum, IDyItemBean bean) {
//                newItemBean3.setTitle("测试改变"+i);
//
//                i=i+1;
//
//                recycleView.updateItem(newItemBean3);

             View itemView=recycleView.getItemView(newItemBean3);
               if (itemView!=null){

                   ContentItemView  contentItemView=(ContentItemView)itemView;
                   contentItemView.mTitleTextView.setText("定点改变");
                   contentItemView.mImageView.setImageResource(R.drawable.empty_photo);
                   contentItemView.mImageView.setVisibility(View.VISIBLE);

//                         contentItemVie.setti

               }



//                ToastUtils.show(getApplication(),"点击事件触发了");

//                List<SelectBean>  selectBeans=  recycleView.getSelect(SECTION_NEW);
//
//                int size=selectBeans.size();
//                ToastUtils.show(getApplication(),"选中数："+size);

            }
        });
        newSectionList.add(newItemBean3);

        Section  newSection=new Section(SECTION_NEW);
        newSection.setPosition(0);

        newSection.setName("其他");
        newSection.setShowSection(true);
        newSection.setDataMaps(newSectionList);
//        Section   section=new Section("");
        recycleView.updateSection(newSection);

    }
}
