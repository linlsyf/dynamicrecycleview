package com;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import com.easy.recycleview.DyLayout;
import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.bean.Section;
import com.easy.recycleview.custom.baseview.utils.ToastUtils;
import com.easy.recycleview.bean.CentLayoutConfig;
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
    private boolean isRefresh;
    private Section newSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView= (DyLayout) findViewById(R.id.recycleView);



        RecycleConfig.getInstance().setThemeConfig(new ThemeConfig().setTitleColorResId(getResources().getColor(R.color.common_title_color)));
//        RecycleConfig.getInstance().setIloadImage(new I);

        List<IDyItemBean> newSectionList=new ArrayList<IDyItemBean>();



        final DyItemBean newItemBean=new DyItemBean();



         DyItemBean  secondItemBean=new DyItemBean();
        String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564203445905&di=4756acf7cbbf0eab10a18b1dffc05ef3&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheji%2F20141219%2Fzhongguofengdaodeliyizhanbanzhijing_3744115.jpg";

        secondItemBean.setCentLayoutConfig(

                new CentLayoutConfig()
                        .setImgRadius(100)
//                .setImgResId(R.drawable.empty_photo)
                        .setImgUrl(url)
//          .setName("test")
        );


        newSectionList.add(secondItemBean);

//            for (int i=0;i<10;i++){
//                DyItemBean itemBean=new DyItemBean();
//                  itemBean.setTitle("index"+i);
//                  newSectionList.add(itemBean);
//            }


            newSection=new Section(SECTION_NEW);

        newSection.setDataMaps(newSectionList);




          newSection.setAutoAddSpliteLine(false);

           recycleView.initSwipePullLayout(new DyLayout.OnloadMoreCallBack() {
               @Override
               public void call() {

//
                     if (isRefresh){
                         return;
                     }
                   isRefresh=true;

                         new Handler().postDelayed(new Runnable() {
                        public void run() {

                            newSection=new Section(SECTION_NEW);
                            for (int i=0;i<5;i++){
                                DyItemBean itemBean=new DyItemBean();
                                itemBean.setTitle("indexNEW===="+i);
                                newSection.getDataMaps().add(itemBean);
                            }
                            newSection.setLoadMore(true);
                            recycleView.updateSection( newSection);

                            int newPostion=recycleView.getRefreshRecyclerView().getChildCount();
//                            recycleView.getRefreshRecyclerView().scrollToPosition(newPostion-4);

//                            smoothMoveToPosition(recycleView.getRfreshRecyclerView(),11);


                            ToastUtils.show(MainActivity.this,"this  add");
                            //  System.out.print("add ing ads");
                        }
                    }, 1000);

               }
           });

//        recycleView.initSwipeLayout(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                //检查是否处于刷新状态
//                if (!isRefresh) {
//                    isRefresh = true;
//                    //模拟加载网络数据，这里设置4秒，正好能看到4色进度条
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//
//                            //显示或隐藏刷新进度条
//                            recycleView.setRefreshingEnd();
//                            //修改adapter的数据
//
//                            ToastUtils.show(MainActivity.this,"this  add");
//                            //  System.out.print("add ing ads");
//                            isRefresh = false;
//                        }
//                    }, 4000);
//                }
//
//            }
//        });

         recycleView.updateSection(newSection);


    }
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
// 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
// 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
// 如果跳转位置在第一个可见位置之前，就smoothScrollToPosition可以直接跳转
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
// 跳转位置在第一个可见项之后，最后一个可见项之前
// smoothScrollToPosition根本不会动，此时调用smoothScrollBy来滑动到指定位置
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        }else {
// 如果要跳转的位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
// 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);

        }
    }


}
