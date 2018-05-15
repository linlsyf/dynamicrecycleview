package com.easysoft.dyview;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.alibaba.android.vlayout.LayoutHelper;
//import com.alibaba.android.vlayout.VirtualLayoutAdapter;
//import com.alibaba.android.vlayout.VirtualLayoutManager;
//import com.alibaba.android.vlayout.layout.DefaultLayoutHelper;
//import com.alibaba.android.vlayout.layout.FixLayoutHelper;
//import com.alibaba.android.vlayout.layout.GridLayoutHelper;
//import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.easy.recycleview.recycleview.AddressRecycleView;
import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.IItemView;
import com.easy.recycleview.recycleview.item.bean.AddressHeadImgeSettings;
import com.easy.recycleview.recycleview.sectionview.Section;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VlayoutActivity extends AppCompatActivity {
    private static final String SECTION_NEW = "new";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        recyclerView= (RecyclerView) findViewById(R.id.recycleView);

//        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
//
//        recyclerView.setLayoutManager(layoutManager);
//
//        //layoutManager.setReverseLayout(true);
//
//        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                outRect.set(1, 1, 1, 1);
//            }
//        });
//
//        final List<LayoutHelper> helpers = new LinkedList<>();
//
//        final GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
//        gridLayoutHelper.setItemCount(25);
//
//
//        final ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(FixLayoutHelper.TOP_RIGHT, 100, 100);
//
//        helpers.add(DefaultLayoutHelper.newHelper(7));
//        helpers.add(scrollFixLayoutHelper);
//        helpers.add(DefaultLayoutHelper.newHelper(2));
//        helpers.add(gridLayoutHelper);
//
//        layoutManager.setLayoutHelpers(helpers);
//
//        recyclerView.setAdapter(
//                new VirtualLayoutAdapter(layoutManager) {
//                    @Override
//                    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                        return new MainViewHolder(new TextView(getApplicationContext()));
//                    }
//
//                    @Override
//                    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(
//                                ViewGroup.LayoutParams.MATCH_PARENT, 300);
//                        holder.itemView.setLayoutParams(layoutParams);
//
//                        ((TextView) holder.itemView).setText(Integer.toString(position));
//
//                        if (position == 7) {
//                            layoutParams.height = 60;
//                            layoutParams.width = 60;
//                        } else if (position > 35) {
//                            layoutParams.height = 200 + (position - 30) * 100;
//                        }
//
//                        if (position > 35) {
//                            holder.itemView.setBackgroundColor(0x66cc0000 + (position - 30) * 128);
//                        } else if (position % 2 == 0) {
//                            holder.itemView.setBackgroundColor(0xaa00ff00);
//                        } else {
//                            holder.itemView.setBackgroundColor(0xccff00ff);
//                        }
//                    }
//
//                    @Override
//                    public int getItemCount() {
//                        List<LayoutHelper> helpers = getLayoutHelpers();
//                        if (helpers == null) {
//                            return 0;
//                        }
//                        int count = 0;
//                        for (int i = 0, size = helpers.size(); i < size; i++) {
//                            count += helpers.get(i).getItemCount();
//                        }
//                        return count;
//                    }
//                });
//
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                recyclerView.scrollToPosition(7);
//                recyclerView.getAdapter().notifyDataSetChanged();
//            }
//        }, 6000);
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }
    }
}