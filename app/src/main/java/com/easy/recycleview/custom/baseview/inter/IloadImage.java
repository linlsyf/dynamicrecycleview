package com.easy.recycleview.custom.baseview.inter;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by lindanghong on 2018/4/20.
 */

public interface IloadImage {


    void load(final String url, final ImageView myImageView);
    void load(final Bitmap bitmap, final ImageView myImageView);

    void loadPath(final String path, final ImageView myImageView);

    void loadResourceId(int id, ImageView myImageView);
}