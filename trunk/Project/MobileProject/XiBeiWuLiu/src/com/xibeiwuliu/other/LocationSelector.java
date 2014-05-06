package com.xibeiwuliu.other;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xibeiwuliu.activity.MainActivity;
import com.xibeiwuliu.activity.R;
import com.xibeiwuliu.database.SqliteDaoArea;
import com.xibeiwuliu.entity.AreaInfo;
import com.xibeiwuliu.global.MyApplication;

public class LocationSelector extends Fragment {

	private ViewGroup mView;

	private ExpandableGridView mGridView;
	private LinearLayout selectBut = null;
	private TextView startOrStopAddress = null;

	private ArrayList<Button> mBtnArr;
	private ArrayList<Integer> mBtnIdArr;
	private ArrayList<Integer> mBtnStrArr;
	private int mBtnIdx;

	private int MAX_BTN_CNT = 3;
	private int MIN_BTN_CNT = 1;
	private int mBtnCnt = this.MAX_BTN_CNT;
	private StringBuilder sb = null;
	private SqliteDaoArea daoArea = null;
	private Context myContext = null;

	@Override
	public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
		super.onInflate(activity, attrs, savedInstanceState);
		TypedArray a = activity.obtainStyledAttributes(attrs, R.styleable.LocationSelector);
		int btnCnt = a.getInt(R.styleable.LocationSelector_button_count, this.MAX_BTN_CNT);
		if (this.MIN_BTN_CNT <= btnCnt && btnCnt <= this.MAX_BTN_CNT)
			this.mBtnCnt = btnCnt;
		a.recycle();
	}

	public LocationSelector(Context context) {
		myContext = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		daoArea = SqliteDaoArea.getInstance(myContext);
		mBtnIdx = 0;
		mView = (ViewGroup) inflater.inflate(R.layout.select_locations, null);
		mGridView = (ExpandableGridView) mView.findViewById(R.id.locations);
		selectBut = (LinearLayout) mView.findViewById(R.id.selectBut);
		startOrStopAddress = (TextView) mView.findViewById(R.id.startOrStopAddress);
		mGridView.setExpanded(true);

		startOrStopAddress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startOrStopAddress.setVisibility(View.GONE);
				selectBut.setVisibility(View.VISIBLE);
			}
		});

		mBtnIdArr = new ArrayList<Integer>();
		mBtnIdArr.add(R.id.province_btn);
		mBtnIdArr.add(R.id.city_btn);
		mBtnIdArr.add(R.id.area_btn);

		mBtnStrArr = new ArrayList<Integer>();
		mBtnStrArr.add(R.string.province_btn);
		mBtnStrArr.add(R.string.city_btn);
		mBtnStrArr.add(R.string.area_btn);

		mBtnArr = new ArrayList<Button>();
		for (int i = 0; i < this.MAX_BTN_CNT; i++) {
			if (i < this.mBtnCnt) {
				mBtnArr.add((Button) mView.findViewById(mBtnIdArr.get(i)));
			} else {
				((Button) mView.findViewById(mBtnIdArr.get(i))).setVisibility(View.GONE);
			}
		}

		for (int idx = 0; idx < mBtnArr.size(); idx++) {
			Button btn = mBtnArr.get(idx);
			
			//ÆÁ±Î¡£¡£¡£
//			btn.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					mGridView.setBackgroundDrawable(getResources().getDrawable(R.drawable.input_box_n));
//					int index = mBtnIdArr.indexOf(v.getId());
//					if (index > 0 && mBtnArr.get(index - 1).getTag() == null) {
//						String warning = LocationSelector.this.getActivity().getString(R.string.loc_warning);
//						String area = LocationSelector.this.getActivity().getString(mBtnStrArr.get(index - 1));
//						Toast.makeText(LocationSelector.this.getActivity(), warning + area, Toast.LENGTH_SHORT).show();
//
//						return;
//					}
//
//					if (index > 0) {
//						LocationEntity le = (LocationEntity) mBtnArr.get(index - 1).getTag();
//						if (null != le) {
//							setGridViewContent(le.getMsg_ID());
//						}
//
//					} else {
//						setGridViewContent(0);
//					}
//
//					for (int i = index + 1; i < mBtnArr.size(); i++) {
//						Button btn = (Button) mBtnArr.get(i);
//						btn.setTag(null);
//						btn.setText(mBtnStrArr.get(i));
//
//						Drawable d = btn.getBackground();
//						btn.invalidateDrawable(d);
//						d.clearColorFilter();
//					}
//
//					mBtnIdx = index;
//				}
//
//			});
		}

		//ÆÁ±Î¡£¡£¡£¡£
//		mGridView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
//				LocationEntity le = (LocationEntity) adapter.getItemAtPosition(position);
//
//				Button btn = (Button) mBtnArr.get(mBtnIdx);
//				btn.setTag(le);
//				btn.setText(le.getMsg_Location());
//
//				Drawable d = btn.getBackground();
//				PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
//				d.setColorFilter(filter);
//
//				if (++mBtnIdx < mBtnCnt) {
//					LocationSelector.this.setGridViewContent(le.getMsg_ID());
//				} else {
//					LocationSelector.this.setGridViewContent(-1);
//				}
//				if (mBtnIdx == 1) {
//					sb = new StringBuilder();
//				}
//				sb.append(String.valueOf(le.getMsg_Location()));
//				if (mBtnIdx == 3) {
//
//					mGridView.setBackgroundColor(getResources().getColor(R.color.transparent));
//					startOrStopAddress.setVisibility(View.VISIBLE);
//					selectBut.setVisibility(View.GONE);
//					startOrStopAddress.setText(String.valueOf(sb));
//				}
//			}
//
//		});

		return mView;
	}

	private void setGridViewContent(int parentId) {
		// LogisticsDB logisticsDB = new LogisticsDB(context);
		// logisticsDB.open().close(); // to initialize
		Context context = LocationSelector.this.getActivity();
		//ÆÁ±Î
//		MyApplication.SetMyContext();
//		EntitySet es = MyApplication.getLogisticsDB().getLocationTable().fetchByParentID(parentId);
		
		List<AreaInfo> strList = daoArea.getAreaInfo(0);
//		Toast.makeText(MainActivity.this, strList.get(0), 5).show();
//		Toast.makeText(MainActivity.this, strList.get(0).getCcityName(), 5).show();
		mGridView.setAdapter(new LocationAdapter(context, strList));
	}

//	public EntitySet getLocations() {
//		EntitySet es = new EntitySet();
//		for (int i = 0; i < mBtnArr.size(); i++) {
//			Button btn = (Button) mBtnArr.get(i);
//			LocationEntity e = (LocationEntity) btn.getTag();
//			if (null != e)
//				es.add(e);
//		}
//		return es;
//	}

	public String getLocationString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mBtnArr.size(); i++) {
			Button btn = (Button) mBtnArr.get(i);
			//ÆÁ±Î¡£¡£¡£
//			LocationEntity e = (LocationEntity) btn.getTag();
//			if (null != e)
//				sb.append(e.getMsg_Location());
		}
		return sb.toString();
	}

	public void reset() {

		for (int i = 0; i < mBtnArr.size(); i++) {
			Button btn = (Button) mBtnArr.get(i);
			btn.setTag(null);
			btn.setText(mBtnStrArr.get(i));

			Drawable d = btn.getBackground();
			btn.invalidateDrawable(d);
			d.clearColorFilter();
		}

		setGridViewContent(-1);
	}

	public class LocationAdapter extends BaseAdapter {
		private Context mContext;
//		private EntitySet mLocations;
		private List<AreaInfo> areaInfoList = null;

		public LocationAdapter(Context c, List<AreaInfo> locations) {
			mContext = c;
			areaInfoList = locations;
		}

		@Override
		public int getCount() {
			return areaInfoList.size();
		}

		@Override
		public Object getItem(int position) {
			return areaInfoList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView;
			if (convertView == null) {
				textView = new TextView(mContext);
				textView.setTextColor(Color.parseColor("#000000"));
				textView.setPadding(16, 16, 16, 16);
			} else {
				textView = (TextView) convertView;
			}

//			LocationEntity le = (LocationEntity) mLocations.get(position);
			String cityname = areaInfoList.get(position).getCcityName();
			textView.setText(cityname);
			return textView;
		}

	}
}
