package com.net56888.logistics.ui;

import android.view.View;
import android.view.animation.TranslateAnimation;

public class MoveBg {
	/**
	 * 移动方法
	 * 
	 * @param v
	 *            需要移动的View
	 * @param startX
	 *            起始x坐标
	 * @param toX
	 *            终止x坐标
	 * @param startY
	 *            起始y坐标
	 * @param toY
	 *            终止y坐标
	 */
	public static void moveFrontBg(View v, int startX, int toX, int startY, int toY) {
		TranslateAnimation anim = new TranslateAnimation(startX, toX, startY, toY);
		anim.setDuration(150);		//调整动画切换的速度(值越大 切换速度越慢)
		anim.setFillAfter(true);
		v.startAnimation(anim);
	}
}
