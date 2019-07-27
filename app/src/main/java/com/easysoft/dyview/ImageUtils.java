package com.easysoft.dyview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.easy.recycleview.custom.baseview.inter.IloadImage;
import com.easysoft.CoreApplication;

import java.io.File;


public class ImageUtils  implements IloadImage {
	static ImageUtils  utils;
	 public static ImageUtils getInStance(){
		 if(utils==null){
			 utils=new    ImageUtils();
		 }
		 return  utils;
	 }
	
	public void load(final String url, final ImageView myImageView){
				Glide.with(CoreApplication.getAppContext())
						.load(url)
						.dontAnimate()
						.placeholder(R.drawable.empty_photo)//图片加载出来前，显示的图片
						.listener(new RequestListener<String, GlideDrawable>() {
							@Override
							public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {

								return false;
							}

							@Override
							public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
								return false;
							}
						})
						.into(myImageView);
	}

	@Override
	public void load(Bitmap bitmap, ImageView myImageView) {
		Glide.with(CoreApplication.getAppContext())
				.load(bitmap)
				.dontAnimate()
				.placeholder(R.drawable.empty_photo)//图片加载出来前，显示的图片
				.into(myImageView);
	}

	public void loadPath(final String path, final ImageView myImageView){
				Glide.with(CoreApplication.getAppContext())
						.load(new File(path))
						.dontAnimate()
						.placeholder(R.drawable.empty_photo)//图片加载出来前，显示的图片
						.into(myImageView);
	}
	public void loadResourceId(int id,ImageView myImageView){

		myImageView.setImageResource(id);
		
	}


	

}
