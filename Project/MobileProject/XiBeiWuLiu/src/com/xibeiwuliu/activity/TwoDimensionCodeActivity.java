package com.xibeiwuliu.activity;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * 
 * Copyright (c) 2012 All rights reserved 名称：TwoDimensionCodeActivity.java
 * 描述：我的二维码
 * 
 * @author Administrator - yfr5734@gmail.com
 * @date：2014年5月13日 上午1:48:36
 * @version v1.0
 */
public class TwoDimensionCodeActivity extends BaseActivity {
	private ImageView result = null;
	private Button save = null;
	private Button share = null;
	private Bitmap bmp = null;
	private String getMsg, content = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setAbContentView(R.layout.two_dimension_code_show);
		getMsg = getIntent().getStringExtra("msg");
		content = getIntent().getStringExtra("content");
		initTitleLayout(getMsg, false);
		initView();
	}

	/**
	 * 
	 * 描述：初始化View
	 * 
	 * @throws
	 * @date：2014年5月13日 上午1:52:49
	 * @version v1.0
	 */
	private void initView() {
		save = (Button) findViewById(R.id.save);
		share = (Button) findViewById(R.id.share);
		result = (ImageView) findViewById(R.id.img);
		try {
			bmp = createBitmap(Create2DCode(content));
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		result.setImageBitmap(bmp);
		ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.PNG, 100, baos1);
		try {
			Writetemp(baos1.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		save.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
				try {
					Write(baos.toByteArray());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		share.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("image/png");
				File f = new File("/mnt/sdcard/zibuyu/temp/temp.jpg");
				Uri u = Uri.fromFile(f);
				intent.putExtra(Intent.EXTRA_SUBJECT, "share");
				intent.putExtra(Intent.EXTRA_TEXT, "二维码说说:一切尽在图片中！");
				intent.putExtra(Intent.EXTRA_STREAM, u);
				intent.putExtra("sms_body", "二维码说说:一切尽在图片中！");
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(Intent.createChooser(intent, "分享图片到："));
			}
		});
	}

	/**
	 * 
	 * 描述：生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
	 * 
	 * @param str
	 * @return
	 * @throws WriterException
	 * @throws UnsupportedEncodingException
	 * @throws
	 * @date：2014年5月13日 上午1:54:25
	 * @version v1.0
	 */
	public Bitmap Create2DCode(String str) throws WriterException, UnsupportedEncodingException {
		BitMatrix matrix = new MultiFormatWriter().encode(new String(str.getBytes("GBK"), "ISO-8859-1"), BarcodeFormat.QR_CODE, 300, 300);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		// 二维矩阵转为一维像素数组,也就是一直横着排了
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = 0xff000000;
				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		// 通过像素数组生成bitmap,具体参考api
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	public void Write(byte[] b) throws IOException {
		File cacheFile = null;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File sdCardDir = Environment.getExternalStorageDirectory();

			long time = Calendar.getInstance().getTimeInMillis();
			String fileName = time + ".png";
			File dir = new File(sdCardDir.getCanonicalPath() + "/zibuyu/");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			cacheFile = new File(dir, fileName);
		}
		Toast toast = Toast.makeText(getApplicationContext(), "图片保存在了内存卡下yfr文件夹下，请查看！", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView = (LinearLayout) toast.getView();
		ImageView imageCodeProject = new ImageView(getApplicationContext());
		imageCodeProject.setImageResource(R.drawable.app_icon);
		toastView.addView(imageCodeProject, 0);
		toast.show();
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(cacheFile));
			bos.write(b, 0, b.length);
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Writetemp(byte[] b) throws IOException {
		File cacheFile = null;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File sdCardDir = Environment.getExternalStorageDirectory();
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			String fileName = "temp.jpg";
			File dir = new File(sdCardDir.getCanonicalPath() + "/yfr/temp");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			cacheFile = new File(dir, fileName);
		}
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(cacheFile));
			bos.write(b, 0, b.length);
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Bitmap createBitmap(Bitmap src) {
		if (src == null) {
			return null;
		}
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		int w = 300;
		int h = 300;
		Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		Canvas cv = new Canvas(newb);
		cv.drawColor(Color.WHITE);
		cv.drawBitmap(src, 0, 0, null);
		cv.save(Canvas.ALL_SAVE_FLAG);
		cv.restore();// 存储
		return newb;

	}
}
