package com.wxxr.mobile.callhelper.service.support;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wxxr.mobile.callhelper.utils.Tools;

/**
 * 列表头像
 * 
 * @author yinzhen
 * 
 */
public class ItemPortraitRule {

	private enum portraitValue {
		DEFAULT, // 默认头像
		EMPTY, // 背景空
		HAVE// 有头像
	}

	private Map<String, portraitValue> portraitMaps = new HashMap<String, portraitValue>();
	private Map<String, String> portraitLastChars = new HashMap<String, String>();
	private Map<String, Bitmap> portraitBitmaps = new HashMap<String, Bitmap>();
	private Context mContext;
	private static ItemPortraitRule instance;

	private ItemPortraitRule(Context mContext) {
		this.mContext = mContext;
	}

	public final static ItemPortraitRule getInstance(Context mContext) {
		if (instance == null) {
			instance = new ItemPortraitRule(mContext);
		}
		return instance;
	}

	public Bitmap getItemPortrait(String sms_name, String telphone) {
		String lastChar = null;
		Bitmap bitmap = null;
		// 设置头像
		if (!portraitMaps.containsKey(sms_name)) {
			lastChar = Tools.getLastChar(sms_name);
			bitmap = Tools.getContactsHeadBitmap(mContext, telphone);

			if (null == bitmap) {
				// 没有头像
				if (!TextUtils.isEmpty(lastChar)) {
					// 有汉字
					portraitMaps.put(sms_name, portraitValue.EMPTY);
					portraitLastChars.put(sms_name, lastChar);
				} else {
					// 默认头像
					portraitMaps.put(sms_name, portraitValue.DEFAULT);
				}
			} else {
				portraitMaps.put(sms_name, portraitValue.HAVE);
				portraitBitmaps.put(sms_name, bitmap);
			}
		}
		return portraitBitmaps.get(sms_name);

//		if (portraitValue.EMPTY.equals(portraitMaps.get(sms_name))) {
//			// 文字
//			rl.setVisibility(View.VISIBLE);
//			iv.setVisibility(View.GONE);
//			rl.setBackgroundResource(R.drawable.gd_item_portrait_empty);
//			tv.setText(portraitLastChars.get(sms_name));
//
//		} else if (portraitValue.HAVE.equals(portraitMaps.get(sms_name))) {
//			// 头像
//			rl.setVisibility(View.GONE);
//			iv.setVisibility(View.VISIBLE);
//			iv.setBackgroundDrawable(new BitmapDrawable(toRoundCorner(portraitBitmaps.get(sms_name), 7)));
//
//		} else {
//			// 默认头像
//			rl.setVisibility(View.GONE);
//			iv.setVisibility(View.VISIBLE);
//			iv.setBackgroundResource(R.drawable.gd_callrecord_item_portrait);
//		}
	}

	/**
	 * 设置成圆角
	 * @param bitmap
	 * @param pixels
	 * @return
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;

	}

	public void clearPortraitMaps() {
		portraitMaps.clear();
		portraitLastChars.clear();
		portraitBitmaps.clear();
	}
}
