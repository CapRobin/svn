package org.lsp.todaynews.fragment;

import org.lsp.todaynews.R;
import org.lsp.todaynews.activity.MainActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class NewsListFragment extends Fragment implements OnClickListener {
	// private ListView listView;
	private RelativeLayout content_view;
	private TextView contentText;
	private TextView title_tv;
	private Handler handler = new Handler();
	private ProgressDialog progressDialog = null;
	// private String url = "";
	private String title = "";

	// List<NewsItem> dataList = null;
	// private MyAdapter adapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 接收传过来的bundle值
		Bundle b = getArguments();
		// url = b.getString("URL");
		title = b.getString("TITLE");
		Toast.makeText(getActivity(), title, 5).show();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 初始化内容显示区
		View view = inflater.inflate(R.layout.news_list, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// 初始化显示区的View
		initView(view);
		// 加载数据
		loaderData();
	}

	/**
	 * 
	 * 描述：初始化显示区的View
	 * 
	 * @param view
	 * @throws
	 * @date：2014年7月25日 上午3:08:58
	 * @version v1.0
	 */
	private void initView(View view) {
		content_view = (RelativeLayout) view.findViewById(R.id.content_view);
		contentText = (TextView) view.findViewById(R.id.contentText);
		title_tv = (TextView) view.findViewById(R.id.title_tv);
		view.findViewById(R.id.btn_main_left_menu).setOnClickListener(this);
		view.findViewById(R.id.btn_main_refresh).setOnClickListener(this);
		title_tv.setText(title);
//		 listView.setOnItemClickListener(new OnItemClickListener() {
//		
//		 @Override
//		 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//		 long arg3) {
//		 NewsItem item = dataList.get(arg2);
//		 Intent intent = new Intent(getActivity(),NewDetailActivity.class);
//		 intent.putExtra("link", item.getLink());
//		 intent.putExtra("title", item.getTitle());
//		 startActivity(intent);
//		 getActivity().overridePendingTransition(R.anim.alpha_in,
//		 R.anim.alpha_out);
//		
//		
//		 }
//		 });
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_main_left_menu:
			if (MainActivity.slidingMenuView.getCurrentScreen() == 1) {
				MainActivity.slidingMenuView.snapToScreen(0);
			} else {
				MainActivity.slidingMenuView.snapToScreen(1);
			}
			break;
		case R.id.btn_main_refresh:
			Toast.makeText(getActivity(), "刷新", 5).show();
			 loaderData();
			break;
		default:
			break;
		}
	}

	/**
	 * 加载数据 描述：TODO
	 * 
	 * @throws
	 * @date：2014年7月25日 上午3:09:22
	 * @version v1.0
	 */
	public void loaderData() {
		contentText.setText(title);
	}
}
