package com.easy.recycleview.recycleview.item.config;

import android.view.View;

import com.easy.recycleview.recycleview.button.IOSSwitchButton;
import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.ContentItemView;
import com.easy.recycleview.recycleview.item.IItemView;

/**
 * Created by Chant on 2018/6/17.
 */

public class ListenerConfig {

    public static  void load(final ContentItemView itemView) {
        if (itemView.mBindItemBean != null && itemView.mBindItemBean.getOnRightCheckBoxListener() != null) {
            itemView.mSwitchButton.setOnStateChangeListener(new IOSSwitchButton.OnStateChangeListener() {
                @Override
                public void onStateChanged(boolean isOn) {
                    itemView.mBindItemBean.setRightCheckBoxSelect(isOn);
                    if (itemView.mBindItemBean.getOnRightCheckBoxListener() != null) {
                        itemView.mBindItemBean.getOnRightCheckBoxListener().onStateChanged(isOn);
                    }
                }
            });
        }

        if (itemView.mBindItemBean == null || itemView.mBindItemBean.getOnItemListener() == null) {
            itemView.mRightFirstButton.setOnClickListener(null);
            itemView.mRightCenterScaleImgeLayout.setOnClickListener(null);
            itemView.mRightSecondImgeView.setOnClickListener(null);
            itemView.mRightFirstImageView.setOnClickListener(null);
            itemView.mRootlayout.setOnClickListener(null);
            itemView.mRootlayout.setOnLongClickListener(null);
            itemView.mRightFirstTextView.setOnClickListener(null);
            return;
        }
        final IItemView.onItemClick onItemListener = itemView.mBindItemBean.getOnItemListener();
        itemView.mRightFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onItemClick(IItemView.ClickTypeEnum.RIGHTBUTTION, itemView.mBindItemBean);
            }
        });
        itemView.mRightCenterScaleImgeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onItemClick(IItemView.ClickTypeEnum.RIGHT_SCALE_CENTER_IMG, itemView.mBindItemBean);
            }
        });
        itemView.mRightSecondImgeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onItemClick(IItemView.ClickTypeEnum.RIGHT_SECOND_IMG, itemView.mBindItemBean);
            }
        });
        itemView.mRightFirstImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onItemClick(IItemView.ClickTypeEnum.RIGHT_FIRST_IMG, itemView.mBindItemBean);
            }
        });

        itemView.mRootlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemView.mBindItemBean.isOnItemAllClickAble()){
                    onItemClick(itemView,onItemListener);

                }
            }
        });

        itemView.mRootlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemListener != null&&itemView.mBindItemBean.isOnItemClickAble()) {
                    onItemListener.onItemClick(IItemView.ClickTypeEnum.ITEM_LONG, itemView.mBindItemBean);
                }
                return true;
            }
        });

        itemView.mRightFirstTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(itemView,onItemListener);
            }
        });
    }


    public static  void onItemClick( ContentItemView itemView, IItemView.onItemClick onItemListener) {
        if (itemView.mBindItemBean.isShowLeftCheckBox()) {
            itemView.mLeftCheckBox.setVisibility(View.VISIBLE);
            boolean isChecked = itemView.mBindItemBean.isLeftCheckBoxIsChecked();
            if (itemView.mSelectUtils != null) {
                boolean selectReuslt = itemView.mSelectUtils.select(!isChecked, itemView.mBindItemBean);
                itemView.mBindItemBean.setLeftCheckBoxIsChecked(selectReuslt);
                itemView.mChangeSelectRefresh = true;
                 itemView. initData(itemView.mBindItemBean);
            }
        }
        if (onItemListener != null&&  itemView.mBindItemBean.isOnItemClickAble()) {
            onItemListener.onItemClick(IItemView.ClickTypeEnum.ITEM, itemView.mBindItemBean);
        }
    }
}
