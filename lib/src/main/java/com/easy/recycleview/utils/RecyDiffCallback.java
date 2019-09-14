package com.easy.recycleview.utils;

import android.support.v7.util.DiffUtil;

import com.easy.recycleview.inter.IDyItemBean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/9 0009.
 */

public class RecyDiffCallback  extends DiffUtil.Callback {

    private List<IDyItemBean> newList;
    private List<IDyItemBean> oldList;

    public RecyDiffCallback(List<IDyItemBean> oldList, List<IDyItemBean> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    } @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId().equals(newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        IDyItemBean oldStr = oldList.get(oldItemPosition);
        IDyItemBean newStr = newList.get(newItemPosition);
        return oldStr.equals(newStr);
    }
}
