package fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.slidingmenu.MenuItemAdapter;
import com.slidingmenu.MyApplication;
import com.slidingmenu.R;

/**
 * 
 * 名称：ContentFragment.java
 * 
 * 描述：个人详情内容县市区
 * 
 * @author Yu Farong - yfr5734@gmail.com
 * @date：2013-8-21 下午5:00:24
 * @version v1.0
 */
public class ContentFragment extends Fragment {
	private List<String> myList = null;
	private MyApplication application = null;
	private ListView contentListView = null;
	private TextView contentTitle = null ;
	private String getContentTitle = null ;

	public ContentFragment() {
	}

	public ContentFragment(List<String> stringList, String selectTitle) {
		this.myList = stringList;
		this.getContentTitle = selectTitle;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// inflater the layout
		View view = inflater.inflate(R.layout.content_layout, null);
		Button contentBut = (Button) view.findViewById(R.id.menu_show);
		contentTitle = (TextView) view.findViewById(R.id.selectTitle);
		contentListView = (ListView) view.findViewById(R.id.contentListView);
		application = (MyApplication) getActivity().getApplication();

		contentBut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				application.attachExample.menu.showMenu();
			}
		});

		//设置标题
		if (getContentTitle != null) {
			contentTitle.setText(getContentTitle);
		}
		
		//设置显示数据
		if (myList != null && myList.size() > 0) {
			MenuItemAdapter adapter = new MenuItemAdapter(getActivity(), myList);
			contentListView.setAdapter(adapter);

			contentListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Toast.makeText(getActivity(), myList.get(position), 5).show();
				}
			});
		}
		return view;
	}

	@Override
	public void onDestroy() {
		Log.e("Krislq", "onDestroy:" + getContentTitle);
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.e("Krislq", "onDetach:" + getContentTitle);
		super.onDetach();
	}

	@Override
	public void onPause() {
		Log.e("Krislq", "onPause:" + getContentTitle);
		super.onPause();
	}

	@Override
	public void onResume() {
		Log.e("Krislq", "onResume:" + getContentTitle);
		super.onResume();
	}

	@Override
	public void onStart() {
		Log.e("Krislq", "onStart:" + getContentTitle);
		super.onStart();
	}

	@Override
	public void onStop() {
		Log.e("Krislq", "onStop:" + getContentTitle);
		super.onStop();
	}

}
