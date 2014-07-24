package org.lsp.todaynews.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lsp.todaynews.R;
import org.lsp.todaynews.fragment.NewsListFragment;
import org.lsp.todaynews.view.SlidingMenuView;
import org.lsp.todaynews.view.SlidingMenuView.CloseAnimation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	public static SlidingMenuView slidingMenuView;

	private ViewGroup tabcontent;
	private LinearLayout menu_linearLayout = null;
	private FragmentManager fragmentMapger = null;
	private FragmentTransaction ft = null;
	private NewsListFragment newsListFragment = null;
	private ListView listView = null;
	private String url = "http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss";
	private String title = "国内焦点";
	private List<Map<String, String>> dataList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_main);

		slidingMenuView = (SlidingMenuView) findViewById(R.id.main_menu_view);
		tabcontent = (ViewGroup) slidingMenuView.findViewById(R.id.main_body);
		listView = (ListView) slidingMenuView.findViewById(R.id.gorup_listView);
		menu_linearLayout = (LinearLayout) slidingMenuView.findViewById(R.id.menu_linearLayout);
		initView();
		initData();
		listView.setAdapter(new SimpleAdapter(this, dataList, R.layout.group_item, new String[] { "TITLE" }, new int[] { R.id.textView1 }));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Map<String, String> map = dataList.get(arg2);
				url = map.get("LINK");
				title = map.get("TITLE");
				showActivity(url, title);
			}
		});
		listView.setAdapter(new MyAdapter(this, dataList));
		// addMenuItemView();
	}

	public void initData() {
		dataList = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("TITLE", "国内焦点");
		map1.put("LINK", "http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("TITLE", "国际焦点");
		map2.put("LINK", "http://news.baidu.com/n?cmd=1&class=internews&tn=rss");
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("TITLE", "军事焦点");
		map3.put("LINK", "http://news.baidu.com/n?cmd=1&class=mil&tn=rss");
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("TITLE", "财经焦点");
		map4.put("LINK", "http://news.baidu.com/n?cmd=1&class=finannews&tn=rss");
		Map<String, String> map5 = new HashMap<String, String>();
		map5.put("TITLE", "互联网焦点");
		map5.put("LINK", "http://news.baidu.com/n?cmd=1&class=internet&tn=rss");
		Map<String, String> map6 = new HashMap<String, String>();
		map6.put("TITLE", "房产焦点");
		map6.put("LINK", "http://news.baidu.com/n?cmd=1&class=housenews&tn=rss");
		dataList.add(map1);
		dataList.add(map2);
		dataList.add(map3);
		dataList.add(map4);
		dataList.add(map5);
		dataList.add(map6);
	}

	/**
	 * 初始化控件
	 */
	@SuppressLint("NewApi")
	private void initView() {
		// 加载主页
		fragmentMapger = getFragmentManager();
		ft = fragmentMapger.beginTransaction();
		newsListFragment = new NewsListFragment();
		Bundle b = new Bundle();
		b.putString("URL", url);
		b.putString("TITLE", title);
		newsListFragment.setArguments(b);
		ft.add(R.id.main_body, newsListFragment);
		ft.commit();
		tabcontent.setOnClickListener(this);
	}

	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_homepage:// 首页
			showActivity(url, title);
			break;
		case R.id.main_body:
			slidingMenuView.snapToScreen(1);
			break;
		default:
			break;
		}
	}

	/**
	 * 切换Activity的方法
	 * 
	 * @param c
	 *            参数为Activity
	 */
	@SuppressLint("NewApi")
	private void showActivity(String url, String title) {
		tabcontent.removeAllViews();
		if (newsListFragment != null)
			ft.remove(newsListFragment);
		fragmentMapger = getFragmentManager();
		ft = fragmentMapger.beginTransaction();
		newsListFragment = new NewsListFragment();
		Bundle b = new Bundle();
		b.putString("URL", url);
		b.putString("TITLE", title);
		newsListFragment.setArguments(b);
		// Toast.makeText(MainActivity.this, title, 5).show();
		ft.replace(R.id.main_body, newsListFragment);
		ft.commitAllowingStateLoss();

		slidingMenuView.setCloseAnimation(new CloseAnimation() {

			@Override
			public void closeMenuAnimation() {
				// TODO Auto-generated method stub
				if (-slidingMenuView.getScrollX() == getWindowManager().getDefaultDisplay().getWidth()
						- (slidingMenuView.totalWidth - getWindowManager().getDefaultDisplay().getWidth())) {
					slidingMenuView.closeMenu_2(1);
				}
			}
		});

		slidingMenuView.closeMenu_1(1);
	}

	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			if (slidingMenuView.getCurrentScreen() == 1) {

				if ((System.currentTimeMillis() - exitTime) > 2000) {
					Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
					exitTime = System.currentTimeMillis();
				} else {
					finish();
					System.exit(0);
				}
			} else {
				slidingMenuView.snapToScreen(1);
			}

			return true;

		} else if (keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN) {
			if (slidingMenuView.getCurrentScreen() == 1) {
				slidingMenuView.snapToScreen(0);
			} else {
				slidingMenuView.snapToScreen(1);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	class MyAdapter extends BaseAdapter {
		private Context context;
		private List<Map<String, String>> dataList;

		public MyAdapter(Context context, List<Map<String, String>> dataList) {
			this.context = context;
			this.dataList = dataList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return dataList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return dataList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View v, ViewGroup arg2) {
			if (v == null) {
				v = View.inflate(context, R.layout.group_item, null);
			}
			TextView tv = (TextView) v.findViewById(R.id.textView1);
			View line_view = v.findViewById(R.id.line_view);
			Map<String, String> map = dataList.get(position);
			tv.setText(map.get("TITLE"));
			if (position == dataList.size() - 1) {
				line_view.setVisibility(View.GONE);
			}
			if (position == 0) {
				line_view.setVisibility(View.VISIBLE);
			}
			return v;
		}

	}

	public void addMenuItemView() {
		for (int i = 0; i < dataList.size(); i++) {
			View v = View.inflate(this, R.layout.group_item, null);
			TextView tv = (TextView) v.findViewById(R.id.textView1);
			View line_view = v.findViewById(R.id.line_view);
			Map<String, String> map = dataList.get(i);
			tv.setText(map.get("TITLE"));
			if (i == dataList.size() - 1) {
				line_view.setVisibility(View.GONE);
			}
			if (i == 0) {
				line_view.setVisibility(View.VISIBLE);
			}
			v.setTag(i);
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int index = (Integer) v.getTag();
					Map<String, String> map = dataList.get(index);
					url = map.get("LINK");
					title = map.get("TITLE");
					// showActivity(url, title);
					Toast.makeText(MainActivity.this, title, 5).show();
				}
			});
			menu_linearLayout.addView(v);
		}
	}
}
