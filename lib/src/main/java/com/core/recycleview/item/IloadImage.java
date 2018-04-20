package com.core.recycleview.item;

import android.widget.ImageView;

/**
 * Created by lindanghong on 2018/4/20.
 */

public interface IloadImage {


    public void load(final String url, final ImageView myImageView);

    public void loadPath(final String path, final ImageView myImageView);

    public void loadResourceId(int id, ImageView myImageView);
}