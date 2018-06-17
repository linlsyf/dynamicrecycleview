package com.easy.recycleview.recycleview.item.config;

import android.view.View;

import com.easy.recycleview.recycleview.EdittextLayoutView;
import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.ContentItemView;

/**
 * Created by Chant on 2018/6/17.
 */

public class EditLayoutConfig {
    public static  void load(ContentItemView itemView,final  AddressItemBean dataItemBean) {
            itemView.mTitleTextView.setText(dataItemBean.getTitle());  if (dataItemBean.getEidtSettings().isShowEdittext()) {//输入框
            itemView.mContentEditLayout.setCallback(new EdittextLayoutView.CallbackListener() {
                @Override
                public void onCallback(String text) {
                    dataItemBean.getEidtSettings().setEditContent(text);
                }
            });
            itemView.mContentEditLayout.setVisibility(View.VISIBLE);
            if (!dataItemBean.getEidtSettings().isEdittextCanEdit()) {
                itemView.mContentEditLayout.setCanUserInput(false);
            } else {
                itemView.mContentEditLayout.setCanUserInput(true);
            }
            itemView.mContentEditLayout.setShowCleanImg(dataItemBean.isShowCleanImg());
            itemView.mContentEditLayout.setHint(dataItemBean.getEidtSettings().getEditHint());
            itemView.mContentEditLayout.setText(dataItemBean.getEidtSettings().getEditContent());
            itemView.mContentEditLayout.setOpenKeybord(dataItemBean.getEidtSettings().isOpenKeybord());
            dataItemBean.getEidtSettings().setOpenKeybord(false);
            itemView.mContentEditLayout.setInputType(dataItemBean.getEidtSettings().getInputType());
        } else {
            itemView.mContentEditLayout.setVisibility(View.GONE);
        }

    }
}
