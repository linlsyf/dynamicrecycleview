/**
* <p>Title: </p>
* <p>desc: 
* <p>Copyright: Copyright(c)Sinosoft 2013</p>
* @author 卢健生<a href=">
* @time 上午9:26:04
* @version 1.0
* @since 
*/
package com.core.utils;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author Anson
 *
 */
public class DensityUtil {
    /** 
     * 根据设备的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
    /** 
     * 根据设备的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static float getdensity(Context context) {  
    	final float scale = context.getResources().getDisplayMetrics().density;  
    	return scale;  
    }  
  
    
    /** 
     * 根据设备的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
    public static int getWindowWidth (Activity activity)
    {
    	WindowManager windowManager = activity.getWindowManager(); 
        Display display = windowManager.getDefaultDisplay(); 

        
        
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
//        int height = size.y;
        
        
        
        return width; 
    }
    
    public static int getWindowHeight (Activity activity)
    {
    	WindowManager windowManager = activity.getWindowManager(); 
        Display display = windowManager.getDefaultDisplay(); 

        
//        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
//        int width = size.x;
        int height = size.y;
        return height; 
    }
    public static int dip2pxIntNonCompat(Context context, float dpValue) {
        try {
            Field field = DisplayMetrics.class.getField("noncompatDensity");
            float scale = field.getFloat(context.getResources().getDisplayMetrics());
            return (int) (dpValue * scale + 0.5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getNonCompatDensityDpi(Context context) {
        try {
            Field field = DisplayMetrics.class.getField("noncompatDensityDpi");
            return field.getInt(context.getResources().getDisplayMetrics());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }



    /** 
     * 用对角线尺寸判断是否为平板 
     *  
     * @return 
     */  
    public static boolean isPad(Context context) {  
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);  
        Display display = wm.getDefaultDisplay();  
        
        
        
        Point size = new Point();
        display.getSize(size);
//        int width = size.x;
//        int height = size.y;
//        
//        
        // 屏幕宽度  
//        float screenWidth = display.getWidth();  
//        // 屏幕高度  
//        float screenHeight = display.getHeight();  
        DisplayMetrics dm = new DisplayMetrics();  
        display.getMetrics(dm);  
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);  
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);  
        // 屏幕尺寸  
        double screenInches = Math.sqrt(x + y);  
        // 大于6尺寸则为Pad  
        if (screenInches >= 6.0) {  
            return true;  
        }  
        return false;  
    }

    
   
}
