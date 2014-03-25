package com.cai.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ShowDescriptionActivity extends Activity {
	private String title = null;
	private String pubdate = null;
	private String description = null;
	private String link = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdescription);
		String content = null;
		Intent intent = getIntent();
		if(intent != null) {
			Bundle bundle = intent.getBundleExtra("com.rss.data.RssFeed");
			title = bundle.getString("title");
			pubdate = bundle.getString("pubdate");
			description = bundle.getString("description");
			link = bundle.getString("link");
			if(bundle != null){
			 
				content ="description:\n" + description.replace('\n', ' ')
				+ "\n\nlink:\n" + link
				+ "\n\npubDate: " + pubdate;
			}
		}else {
			content = "程序设计有错误";
		}
		
		ShowDescriptionActivity.this.setTitle(title);
		
		TextView textView = (TextView)findViewById(R.id.content);
		textView.setText(content.toString());
		
		Button backButton = (Button)findViewById(R.id.back);
		Button storeButton = (Button)findViewById(R.id.store);
		
		storeButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//storeDataRss();			
				Toast.makeText(ShowDescriptionActivity.this, "收藏成功！", Toast.LENGTH_LONG).show();
			}

		});
		
		backButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
					finish();			
			}
		});
		

	}

	/*protected void storeDataRss() {
		ContentResolver cr = getContentResolver();
		ContentValues values = new ContentValues();
		values.put(RssProvider.RSS_TITLE, title);
		values.put(RssProvider.RSS_DESCRIPTION, description);
		values.put(RssProvider.RSS_PUBDATE, pubdate);
		values.put(RssProvider.RSS_LINK, link);
		
		cr.insert(RssProvider.RSS_URI, values);
		
	}	*/
}
