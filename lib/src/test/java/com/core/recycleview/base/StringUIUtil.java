package com.core.recycleview.base;

import android.graphics.Paint;

public class StringUIUtil {

	/**
	 * 获取画笔要画出的文字的宽高
	 * @param paint 画笔对象
	 * @param text 需要画出的字符串
	 * @return ｛宽，高｝
	 */
	public static float[] measureText(Paint paint, String text){
		float width = paint.measureText(text);
		float height = measureTextHeight(paint);
		return new float[]{width, height};
	}

	public static float measureTextHeight(Paint paint) {
		return (float) Math.ceil(paint.descent() - paint.ascent());
	}

//	public static void copyToClipBoard(String text) {
//		ClipboardManager manager = (ClipboardManager) CoreApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
//		manager.setPrimaryClip(ClipData.newPlainText(null, text));
//	}
}
