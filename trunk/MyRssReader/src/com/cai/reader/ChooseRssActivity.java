package com.cai.reader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cai.reader.adapter.ChooseRssAdapter;
import com.cai.reader.entity.RssAddressInfo;
import com.cai.reader.util.ChooseRssData;

public class ChooseRssActivity extends Activity {

	private AlertDialog dialog;
	private ListView lvMainReader;
	private ChooseRssAdapter adapter;
	private Button btnChooseRss, btnPlayGame, btnReaderList;

	private void initView() {
		btnChooseRss = (Button) findViewById(R.id.btn_top_choose_rss);
		btnPlayGame = (Button) findViewById(R.id.btn_top_game);
		btnReaderList = (Button) findViewById(R.id.btn_top_show);
		btnChooseRss.setClickable(false);
		btnChooseRss.setBackgroundResource(R.drawable.empty_bg);
		btnPlayGame.setBackgroundResource(R.drawable.bot_shadow);
		btnReaderList.setBackgroundResource(R.drawable.bot_shadow);
		lvMainReader = (ListView) findViewById(R.id.lv_main_list);
		adapter = new ChooseRssAdapter(this, null);
		lvMainReader.setAdapter(adapter);

	}

	private void initData() {
		Builder builder = new Builder(this);
		TextView tvTitle = new TextView(this);
		TextView tvAddress = new TextView(this);
		tvTitle.setText("请输入地址标题：");
		tvAddress.setText("请输入RSS具体地址：");
		final EditText etRssTitle = new EditText(this);
		final EditText etRssAddress = new EditText(this);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setBackgroundColor(Color.GRAY);
		layout.addView(tvTitle);
		layout.addView(etRssTitle);
		layout.addView(tvAddress);
		layout.addView(etRssAddress);
		builder.setView(layout).setTitle("新增RSS");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				RssAddressInfo info = new RssAddressInfo();
				info.address = etRssAddress.getText().toString();
				info.title = etRssTitle.getText().toString();
				ChooseRssData.addRssAddress(ChooseRssActivity.this, info);
				adapter.chanageDataSet(ChooseRssData
						.getChooseRssListData(ChooseRssActivity.this));
			}
		});
		dialog = builder.create();
	}

	private void initListener() {
		btnReaderList.setOnClickListener(listener);
		btnPlayGame.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.btn_top_show:
				finish();
				break;
			case R.id.btn_top_game:
				Intent intent = new Intent(ChooseRssActivity.this,
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
		adapter.chanageDataSet(ChooseRssData.getChooseRssListData(this));
	}

	private static final int MENU_ADD = 1;
	private static final int MENU_RESET = 2;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_ADD, 0, "新增");
		menu.add(0, MENU_RESET, 1, "恢复默认");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ADD:
			dialog.show();
			return true;
		case MENU_RESET:
			adapter.chanageDataSet(ChooseRssData.initChooseRssData(this));
			return true;

		}
		return super.onOptionsItemSelected(item);
	}
}
