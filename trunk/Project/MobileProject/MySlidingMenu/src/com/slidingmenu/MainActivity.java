package com.slidingmenu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import fragment.ContentFragment;
import fragment.MenuFragment;

//import com.doctor.R;
//import com.doctor.global.MyApplication;
//import com.doctor.menu.ContentFragment;
//import com.doctor.menu.MenuFragment;
//import com.doctor.menu.SlidingMenu;

/**
 * 名称：PersonalDetailActivity.java
 * 
 * 描述：个人详情管理界面
 * 
 * @author Yu Farong - yfr5734@gmail.com
 * @date：2013-8-21 下午5:01:00
 * @version v1.0
 */
public class MainActivity extends FragmentActivity {

	public SlidingMenu menu;
	private MyApplication application = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 使用自定义标题栏
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏

		// set the Above View
		setContentView(R.layout.content_frame);
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);
//
//		ImageView backImage = (ImageView) findViewById(R.id.backImage);
//		ImageView familyCenter = (ImageView) findViewById(R.id.familyCenter);
//		backImage.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				finish();
//			}
//		});

//		familyCenter.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
////				startActivity(new Intent(MainActivity.this, PersonalInfoDetailActivity.class));
//				Toast.makeText(MainActivity.this, "RightTitle", 5).show();
//			}
//		});

		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new ContentFragment()).commit();
		// configure the SlidingMenu
		menu = new SlidingMenu(this);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new MenuFragment()).commit();
		application = (MyApplication) getApplication();
		application.attachExample = MainActivity.this;

	}

	/**
	 * 描述：显示内容界面
	 * 
	 * @throws
	 * @date：2013-8-20 下午5:40:40
	 * @version v1.0
	 */
	public void isShowMenu() {
		menu.showContextMenu();
	}

	@Override
	public void onBackPressed() {
		if (menu.isMenuShowing()) {
			menu.showContent();
		} else {
			super.onBackPressed();
		}
	}

	public SlidingMenu getSlidingMenu() {
		return menu;
	}

}
