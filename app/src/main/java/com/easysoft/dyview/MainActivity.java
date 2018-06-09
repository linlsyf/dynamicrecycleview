package com.easysoft.dyview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.easy.recycleview.recycleview.AddressRecycleView;
import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.IItemView;
import com.easy.recycleview.recycleview.item.bean.AddressHeadImgeSettings;
import com.easy.recycleview.recycleview.sectionview.Section;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final java.lang.String SECTION_NEW = "new";
    private static final java.lang.String SECTION_GRID = "grid";
    private static final java.lang.String SECTION_DY = "dy";
    AddressRecycleView recycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView= (AddressRecycleView) findViewById(R.id.recycleView);

        List<AddressItemBean> newSectionList=new ArrayList<AddressItemBean>();

        AddressItemBean newItemBean=new AddressItemBean();
        newItemBean.setTitle("助手小Q");
        newItemBean.setShowLeftCheckBox(true);
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


//        List<AddressItemBean> gridectionList=new ArrayList<AddressItemBean>();
//
//        for (int i=0;i<2;i++){
//            AddressItemBean gridItemBean=new AddressItemBean();
//            gridItemBean.setViewType(3);
//            gridItemBean.setSpanSize(3);
//            gridItemBean.setTitle("name"+i);
//            gridectionList.add(gridItemBean);
//        }
//
//        gridViewSection.setDataMaps(gridectionList);
//        recycleView.addSection(gridViewSection);



//        Section  dyViewSection=new Section(SECTION_DY);
//        dyViewSection.setPosition(1);
//
//        dyViewSection.setName("动态布局");
//
//
//        List<AddressItemBean> dysectionList=new ArrayList<AddressItemBean>();
//
//        for (int i=0;i<10;i++){
//            AddressItemBean gridItemBean=new AddressItemBean();
//            int type=3;
//             if (i==1){
//                 type=4;
//             }
//             if (i%3==0){
//                type=4;
//             }
//            gridItemBean.setViewType(type);
//
//            gridItemBean.setSpanSize(3);
//            gridItemBean.setTitle("name"+i);
//            dysectionList.add(gridItemBean);
//        }
//
//        dyViewSection.setDataMaps(dysectionList);
//        recycleView.updateSection(dyViewSection);


//        final GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
//        gridLayoutHelper.setItemCount(gridectionList.size()-1);
//        final List<LayoutHelper> helpers = new LinkedList<>();
//        helpers.add(DefaultLayoutHelper.newHelper(newSectionList.size()+1));
//        helpers.add(gridLayoutHelper);
//        recycleView.setLayoutHelpers(helpers);

//          final GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
//        gridLayoutHelper.setItemCount(gridectionList.size()-1);
//        recycleView.addLayoutHelper(DefaultLayoutHelper.newHelper(newSectionList.size()+1));
//        recycleView.addLayoutHelper(gridLayoutHelper,true);


//        int num1 =5;
//
//        int num2 = 7728;
//
//        // 创建一个数值格式化对象
//
//        NumberFormat numberFormat = NumberFormat.getInstance();
//
//        // 设置精确到小数点后2位
//
//        numberFormat.setMaximumFractionDigits(2);
//
//        float result =Float.parseFloat() ;
//
//        System.out.println("num1和num2的百分比为:" + result + "%");

//        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                outRect.set(1, 1, 1, 1);
//            }
//        });
////        Intent  testIntent=new Intent(MainActivity.this,VlayoutActivity.class);
//        startActivity(testIntent);

    }
}
