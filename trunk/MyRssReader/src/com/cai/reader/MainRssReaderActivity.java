package com.cai.reader;

import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cai.reader.adapter.MyAdapter;
import com.cai.reader.parser.RssHandler;
import com.cai.reader.util.RssFeed;

public class MainRssReaderActivity extends Activity {

	public static final String INTENT_EXTRA_RSS_NAME = "rss_name";
	public static final String INTENT_EXTRA_RSS_TITLE = "rss_title";
	private RssFeed feed;
	private ListView lvMainReader;
	private Button btnChooseRss, btnPlayGame, btnReaderList;
	private List<Map<String, Object>> mList;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(MainRssReaderActivity.this,
						"RSS解析错误,请检查网络或重新选择Rss地址", Toast.LENGTH_LONG).show();
				Intent chooseRssIntent = new Intent(MainRssReaderActivity.this,
						ChooseRssActivity.class);
				startActivity(chooseRssIntent);
				finish();
				break;

			case 2:
				mList = (List<Map<String, Object>>) msg.obj;
				if (mList != null && mList.size() > 0) {
					MyAdapter adapter = new MyAdapter(
							MainRssReaderActivity.this, mList, feed);
					lvMainReader.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}
				break;
			}
			super.handleMessage(msg);
		}
	};

	private void initView() {
		btnChooseRss = (Button) findViewById(R.id.btn_top_choose_rss);
		btnPlayGame = (Button) findViewById(R.id.btn_top_game);
		btnReaderList = (Button) findViewById(R.id.btn_top_show);
		btnReaderList.setClickable(false);
		btnReaderList.setBackgroundResource(R.drawable.empty_bg);
		btnPlayGame.setBackgroundResource(R.drawable.bot_shadow);
		btnChooseRss.setBackgroundResource(R.drawable.bot_shadow);

		lvMainReader = (ListView) findViewById(R.id.lv_main_list);
		MyAdapter adapter = new MyAdapter(this, null, null);
		lvMainReader.setAdapter(adapter);

	}

	private void initData() {
		new Thread() {
			public void run() {
				feed = getFeed(getIntent()
						.getStringExtra(INTENT_EXTRA_RSS_NAME));
				if (feed == null) {
					handler.sendEmptyMessage(1);
				} else {
					Message msg = Message.obtain();
					msg.what = 2;
					List<Map<String, Object>> list = feed
							.getAllItemsForListView();
					msg.obj = list;
					handler.sendMessage(msg);
				}
			}
		}.start();
	}

	private RssFeed getFeed(String urlString) {
		try {
			URL url = new URL(urlString);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader xmlReader = parser.getXMLReader();
			RssHandler rssHandler = new RssHandler();
			xmlReader.setContentHandler(rssHandler);
			InputSource is = new InputSource(url.openStream());
			if (null != is) {
				xmlReader.parse(is);
			}
			return rssHandler.getFeed();
		} catch (Exception e) {
			return null;
		}
	}

	private void initListener() {
		btnChooseRss.setOnClickListener(listener);
		btnPlayGame.setOnClickListener(listener);

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.btn_top_choose_rss:
				intent = new Intent(MainRssReaderActivity.this,
						ChooseRssActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_top_game:
				intent = new Intent(MainRssReaderActivity.this,
						ScrawlGameActivity.class);
					startActivity(intent);
			finish();
			break;
			}
		
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		initData();
		initListener();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

}