package com.haohuotong.util;

import com.haohuotong.activity.PhotoActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name��PhotoTools.java
 * @Describe�����չ�����
 * @Author FaRong����yfr5734@gmail.com
 * @Date��2013-12-11 ����2:37:13
 * @Version v1.0
 */
public class PhotoTools {

	/**
	 * @Describe������ͼƬ
	 * @param act
	 * @param viewId
	 * @param imageId
	 * @param preferenceName
	 * @Throws
	 * @Date��2013-12-11 ����2:40:13
	 * @Version v1.0
	 */
	public static void setGuidImage(Activity act, View viewId, int imageId, String preferenceName) {
		SharedPreferences preferences = act.getSharedPreferences(preferenceName, act.MODE_PRIVATE);
		final SharedPreferences.Editor editor = preferences.edit();
		final String key = act.getClass().getName() + "_firstLo" + "" + "" + "" + "gin";
		if (!preferences.contains(key)) {
			editor.putBoolean(key, true);
			editor.commit();
		}
		ViewParent viewParent = viewId.getParent();
		// ��������ҳ��
		if (viewParent instanceof FrameLayout) {
			final FrameLayout frameLayout = (FrameLayout) viewParent;
			final ImageView guideImage = new ImageView(act.getApplication());
			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			guideImage.setLayoutParams(params);
			guideImage.setScaleType(ScaleType.FIT_XY);
			guideImage.setImageResource(imageId);
			final PhotoActivity activity = (PhotoActivity) act;
			activity.mCaptureButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// Toast.makeText(activity, "��ģ�����ڿ����У����Ժ�...", 5).show();
					activity.mCaptureButton.setEnabled(false);
					// ������ڻ�ȡһ��ͼƬ
					activity.mCameraSurPreview.takePicture(activity);
				}
			});
			frameLayout.addView(guideImage);// �������ͼƬ
			// activity.setOnClick();
		}
	}
}
