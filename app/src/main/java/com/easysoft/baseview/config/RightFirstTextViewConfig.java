package com.easysoft.baseview.config;

import android.view.View;

import com.easysoft.baseview.item.ContentItemView;
import com.easysoft.baseview.utils.StringUtils;
import com.easysoft.bean.AddressItemBean;
import com.easysoft.dyview.R;

/**
 * Created by Chant on 2018/6/17.
 */

public class RightFirstTextViewConfig {
    public static  void load(ContentItemView itemView, AddressItemBean dataItemBean) {
        //设置右侧是否显示
        if (dataItemBean.getRightFirstTvColor() == 0) {
            itemView.mRightFirstTextView.setTextColor(itemView.getResources().getColor(R.color.transparent));
        } else { //必须由资源文件里面的定义颜色
            itemView.mRightFirstTextView.setTextColor(itemView.getResources().getColor(dataItemBean.getRightFirstTvColor()));
        }
        if (StringUtils.isNotEmpty(dataItemBean.getRightFirstText())) {
                itemView.mRightFirstTextView.setText(dataItemBean.getRightFirstText());
                itemView.mRightFirstTextView.setVisibility(View.VISIBLE);
        }
        else {
            itemView.mRightFirstTextView.setVisibility(View.GONE);
        }
    }
}
