package com.net56888.logistics;

import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.net56888.logistics.data.Entity;
import com.net56888.logistics.data.UserEntity;

public class Logistics extends FragmentActivity implements DataSource.Callback {
	private final String TAG = "LogisticsManagementActivity";
	TabHost mTabHost;
	TabManager mTabManager;

	Dialog splashDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_tabs);
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);
		setupTab(mTabManager, this.getResources().getString(R.string.tab_browser), R.drawable.tab_browser, BrowserTab.class);
		setupTab(mTabManager, this.getResources().getString(R.string.tab_search), R.drawable.tab_search, SearchTab.class);
		setupTab(mTabManager, this.getResources().getString(R.string.tab_publish), R.drawable.tab_publish, PublishTab.class);
		setupTab(mTabManager, this.getResources().getString(R.string.tab_more), R.drawable.tab_more, MoreTab.class);

		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}

		showSplashScreen(3000, R.drawable.splash);

		UserEntity ue = App.getLogisticsDB().getUserTable().fetch();
		if (null != ue) {
			Config.getInstance().setUser(ue);
		}

		DataSource.getInstance().checkConnectionAsync(this);

		(new Updater(this)).check();
	}

	private void setupTab(TabManager tm, String tag, int icon, Class<?> klass) {
		View tabView = createTabView(mTabHost.getContext(), tag, icon);
		tm.addTab(mTabHost.newTabSpec(tag).setIndicator(tabView), klass, null);
	}

	private View createTabView(Context context, String tag, int icon) {
		View view = LayoutInflater.from(context).inflate(R.layout.tab, null);
		TextView tv = (TextView) view.findViewById(R.id.tabLable);
		tv.setText(tag);
		ImageView iv = (ImageView) view.findViewById(R.id.tabImage);
		iv.setImageResource(icon);
		return view;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("tab", mTabHost.getCurrentTabTag());
	}

	/**
	 * This is a helper class that implements a generic mechanism for
	 * associating fragments with the tabs in a tab host. It relies on a trick.
	 * Normally a tab host has a simple API for supplying a View or Intent that
	 * each tab will show. This is not sufficient for switching between
	 * fragments. So instead we make the content part of the tab host 0dp high
	 * (it is not shown) and the TabManager supplies its own dummy view to show
	 * as the tab content. It listens to changes in tabs, and takes care of
	 * switch to the correct fragment shown in a separate content area whenever
	 * the selected tab changes.
	 */
	public static class TabManager implements TabHost.OnTabChangeListener {
		private final String TAG = "TabManager";
		private final FragmentActivity mActivity;
		private final TabHost mTabHost;
		private final int mContainerId;
		private final HashMap<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
		TabInfo mLastTab;

		static final class TabInfo {
			private final String tag;
			private final Class<?> clss;
			private final Bundle args;
			private Fragment fragment;

			TabInfo(String _tag, Class<?> _class, Bundle _args) {
				tag = _tag;
				clss = _class;
				args = _args;
			}
		}

		static class DummyTabFactory implements TabHost.TabContentFactory {
			private final Context mContext;

			public DummyTabFactory(Context context) {
				mContext = context;
			}

			@Override
			public View createTabContent(String tag) {
				View v = new View(mContext);
				v.setMinimumWidth(0);
				v.setMinimumHeight(0);
				return v;
			}
		}

		public TabManager(FragmentActivity activity, TabHost tabHost, int containerId) {
			mActivity = activity;
			mTabHost = tabHost;
			mContainerId = containerId;
			mTabHost.setOnTabChangedListener(this);
		}

		public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
			tabSpec.setContent(new DummyTabFactory(mActivity));
			String tag = tabSpec.getTag();

			TabInfo info = new TabInfo(tag, clss, args);

			// Check to see if we already have a fragment for this tab, probably
			// from a previously saved state. If so, deactivate it, because our
			// initial state is that a tab isn't shown.
			info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
			/*
			 * if (info.fragment != null && !info.fragment.isDetached()) {
			 * FragmentTransaction ft = mActivity.getSupportFragmentManager()
			 * .beginTransaction(); ft.detach(info.fragment); ft.commit(); }
			 */
			if (info.fragment != null && !info.fragment.isHidden()) {
				FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
				ft.hide(info.fragment);
				ft.commit();
			}

			mTabs.put(tag, info);
			mTabHost.addTab(tabSpec);
		}

		@Override
		public void onTabChanged(String tabId) {
			TabInfo newTab = mTabs.get(tabId);
			if (mLastTab != newTab) {
				FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
				if (mLastTab != null) {
					if (mLastTab.fragment != null) {
						// ft.detach(mLastTab.fragment);
						ft.hide(mLastTab.fragment);
					}
				}
				if (newTab != null) {
					if (newTab.fragment == null) {
						newTab.fragment = Fragment.instantiate(mActivity, newTab.clss.getName(), newTab.args);
						ft.add(mContainerId, newTab.fragment, newTab.tag);
					} else {
						// ft.attach(newTab.fragment);
						ft.show(newTab.fragment);
					}
				}

				mLastTab = newTab;
				ft.commit();
				mActivity.getSupportFragmentManager().executePendingTransactions();
			}
		}
	}

	/**
	 * Shows the splash screen over the full Activity
	 */
	protected void showSplashScreen(int time, int splashscreen) {
		// Get reference to display
		Display display = getWindowManager().getDefaultDisplay();

		// Create the layout for the dialog
		FrameLayout root = new FrameLayout(this);
		root.setMinimumHeight(display.getHeight());
		root.setMinimumWidth(display.getWidth());
		// root.setOrientation(LinearLayout.VERTICAL);
		root.setBackgroundColor(Color.BLACK);
		root.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.0F));
		root.setBackgroundResource(splashscreen);

		/*
		 * Create spinner ProgressBar pb = new ProgressBar(this);
		 * FrameLayout.LayoutParams lp = new
		 * FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
		 * LayoutParams.WRAP_CONTENT); lp.gravity = Gravity.CENTER_HORIZONTAL |
		 * Gravity.CENTER_VERTICAL; pb.setLayoutParams(lp); root.addView(pb);
		 */

		// Create and show the dialog
		splashDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
		splashDialog.setContentView(root);
		splashDialog.setCancelable(false);
		splashDialog.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					splashDialog.dismiss();
					splashDialog = null;
					finish();
					return true;
				}
				return false;
			}
		});
		splashDialog.show();

		// Set Runnable to remove splash screen just in case
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				removeSplashScreen();
			}
		}, time);
	}

	/**
	 * Removes the Dialog that displays the splash screen
	 */
	public void removeSplashScreen() {
		if (splashDialog != null) {
			splashDialog.dismiss();
			splashDialog = null;
		}
	}

	@Override
	public void onLogin(Entity entity) {
		// do nothing
	}

	@Override
	public void onPublish(String result) {
		// do nothing
	}

	@Override
	public void onCheckConnection(String result, int statusCode) {
		if (200 == statusCode && 0 == result.compareToIgnoreCase("TestOK"))
			return;

		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				new AlertDialog.Builder(Logistics.this).setIcon(R.drawable.logo).setTitle(R.string.no_network).setMessage(R.string.no_network_tips)
						.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								return;
							}
						}).setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
							}
						}).show();

			}
		});

	}

	@Override
	public void onRegisterUser(String result) {
		// do nothing
	}

	@Override
	public void onCheckUpdate(String result) {
		// do nothing
	}
}
