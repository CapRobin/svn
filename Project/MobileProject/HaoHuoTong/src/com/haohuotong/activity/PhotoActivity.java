package com.haohuotong.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.haohuotong.R;
import com.haohuotong.util.PhotoSurfacePreview;
import com.haohuotong.util.PhotoTools;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：PhotoActivity.java
 * @Describe：拍摄个人照片
 * @Author FaRong——yfr5734@gmail.com
 * @Date：2013-12-11 下午2:50:41
 * @Version v1.0
 */
public class PhotoActivity extends Activity implements OnClickListener, PictureCallback {
	public PhotoSurfacePreview mCameraSurPreview = null;
	public Button mCaptureButton = null;
	private String TAG = "Dennis";
	private LinearLayout mainLayout = null;
	private Bitmap cameraBitmap = null;
	private String filePath = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);

		// 初始化视图
		mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		mCaptureButton = (Button) findViewById(R.id.button_capture);

		mCameraSurPreview = new PhotoSurfacePreview(this);
		preview.addView(mCameraSurPreview);

		// 设置引导视图
		PhotoTools.setGuidImage(this, mainLayout, R.drawable.cover_view, "FirstLogin");
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {

		// 保存图片到SD卡
		File pictureFile = getOutputMediaFile();
		if (pictureFile == null) {
			Log.d(TAG, "Error creating media file, check storage permissions: ");
			return;
		}

		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			fos.write(data);
			fos.close();

			cameraBitmap = BitmapFactory.decodeStream(new FileInputStream(pictureFile));

			// 得到文件的路径
			filePath = pictureFile.getAbsolutePath();

			// 获取图片跳转
			Intent it = new Intent();
			it.putExtra("filePath", filePath);
			setResult(Activity.RESULT_OK, it);
			finish();

			Toast.makeText(this, "Image has been saved to " + filePath, Toast.LENGTH_LONG).show();

		} catch (FileNotFoundException e) {
			Log.d(TAG, "File not found: " + e.getMessage());
		} catch (IOException e) {
			Log.d(TAG, "Error accessing file: " + e.getMessage());
		}

		// Restart the preview and re-enable the shutter button so that we can
		// take another picture
		camera.startPreview();

		// See if need to enable or not
		mCaptureButton.setEnabled(true);
	}

	@Override
	public void onClick(View v) {
		mCaptureButton.setEnabled(false);

		// get an image from the camera
		mCameraSurPreview.takePicture(this);

		if (cameraBitmap != null) {
			Intent it = new Intent();
			Bundle bundle = new Bundle();
			bundle.putParcelable("bitmap", cameraBitmap);
			it.putExtra("bundle", bundle);
			setResult(Activity.RESULT_OK, it);
			this.finish();
		}
	}

	/**
	 * 
	 * @Describe：从媒体文件中获取图片文件
	 * @return
	 * @Throws
	 * @Date：2013-12-11 下午2:49:30
	 * @Version v1.0
	 */
	private File getOutputMediaFile() {
		// get the mobile Pictures directory
		File picDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

		// get the current time
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		return new File(picDir.getPath() + File.separator + "IMAGE_" + timeStamp + ".jpg");
	}
}
