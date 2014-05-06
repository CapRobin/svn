package net.blogjava.mobile;

import java.util.ArrayList;
import java.util.List;

import net.blogjava.mobile.MyLocationAdapter.HeadView;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends Activity {
	private HeadView viewHead;

	private String[] str = new String[] { "北京", "上海", "南京", "西安", "成都", "杭州", "大连", "银川", "中卫", "中宁中", "苏州", "厦门", "上海", "南京", "西安", "成都", "杭州",
			"大连", "银川", "中卫", "中宁", "苏州", "厦门", "上海", "南京南", "西安", "成都", "杭州", "大连大", "银川" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_view);
		GridView gridView = (GridView) findViewById(R.id.gridview);

		// 加载数据列表
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
		}
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		// gridView.setSelector(R.drawable.view_selector);
		MyLocationAdapter locationAdapter = new MyLocationAdapter(this, list);
		gridView.setAdapter(locationAdapter);
		gridView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// view.setBackgroundResource(R.drawable.view_item_bg);
				Toast.makeText(LocationActivity.this, str[position], 5).show();

				// // 设置获取CheckBox中的事件
				// // if (((GridView) parent).getTag() != null) {
				// // viewHead = (HeadView) ((View) ((GridView)
				// parent).getTag()).getTag();
				// //
				// // viewHead.layout.setOnClickListener(new OnClickListener() {
				// //
				// // @Override
				// // public void onClick(View v) {
				// //
				// viewHead.layout.setBackgroundResource(R.drawable.view_item_bg);
				// // }
				// // });
				// //
				// // }else {
				// //
				// // }
				//
				// ((GridView) parent).setTag(view);
				// viewHead = (HeadView) view.getTag();
				//
				// // 触发事件
				// viewHead.itemText.setOnClickListener(new OnClickListener() {
				//
				// @Override
				// public void onClick(View v) {
				// viewHead.itemText.setBackgroundResource(R.drawable.view_item_bg);
				// }
				// });
			}
		});

	}
}