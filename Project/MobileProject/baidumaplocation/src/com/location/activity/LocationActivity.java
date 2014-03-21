package com.location.activity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.android.map.MyMapOverlay;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKLocationManager;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.Overlay;

public class LocationActivity extends MapActivity implements LocationListener {

	private MapView mapView;
	private MapController mMapCtrl;
	private List<Overlay> mapOverlays;
	public GeoPoint locPoint;
	private MyMapOverlay mOverlay;
	private TextView desText;
	private String lost_tips;
	private int point_X;
	private int point_Y;

	public final int MSG_VIEW_LONGPRESS = 10001;
	public final int MSG_VIEW_ADDRESSNAME = 10002;
	public final int MSG_GONE_ADDRESSNAME = 10003;
	private Intent mIntent;
	private int mLatitude;
	private int mLongitude;
	private String name;
	private BMapManager mapManager;
	private MKLocationManager mLocationManager = null;
	private boolean isLoadAdrr = true;
	private MKSearch mMKSearch;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		initMap();
		mIntent = getIntent();
		mLatitude = mIntent.getIntExtra("latitude", 0);
		mLongitude = mIntent.getIntExtra("longitude", 0);
		name = mIntent.getStringExtra("name");
		mapView = (MapView) findViewById(R.id.map_view);
		desText = (TextView) this.findViewById(R.id.map_bubbleText);
		lost_tips = getResources().getString(R.string.load_tips);
		if (mLatitude != 0 && mLongitude != 0) {
			locPoint = new GeoPoint((int) (mLatitude * 1E6),
					(int) (mLongitude * 1E6));
			desText.setText(name);
		}
		mapView.setBuiltInZoomControls(true);
		mapView.setClickable(true);
		mMapCtrl = mapView.getController();
		point_X = this.getWindowManager().getDefaultDisplay().getWidth() / 2;
		point_Y = this.getWindowManager().getDefaultDisplay().getHeight() / 2;
		mOverlay = new MyMapOverlay(point_X, point_Y) {
			@Override
			public void changePoint(GeoPoint newPoint, int type) {
				if (type == 1) {
					mHandler.sendEmptyMessage(MSG_GONE_ADDRESSNAME);
				} else {
					locPoint = newPoint;
					mHandler.sendEmptyMessage(MSG_VIEW_LONGPRESS);
				}

			}
		};
		mapOverlays = mapView.getOverlays();
		if (mapOverlays.size() > 0) {
			mapOverlays.clear();
		}
		mapOverlays.add(mOverlay);
		mMapCtrl.setZoom(20);

	}

	private void initMap() {

		// 初始化MapActivity
		mapManager = new BMapManager(getApplication());
		// init方法的第一个参数需填入申请的API Key
		mapManager.init("C66C0501D0280744759A6957C42543AE38F5D540", null);
		super.initMapActivity(mapManager);
		// 实例化搜索地址类
		mMKSearch = new MKSearch();
		// 初始化搜索地址实例
		mMKSearch.init(mapManager, new MySearchListener());
		mLocationManager = mapManager.getLocationManager();
		// 注册位置更新事件
		mLocationManager.requestLocationUpdates(this);
		// 使用GPS定位
		mLocationManager
				.enableProvider((int) MKLocationManager.MK_GPS_PROVIDER);
	}

	@Override
	protected void onResume() {
		if (mapManager != null) {
			mapManager.start();
		}
		super.onResume();

	}

	@Override
	protected void onPause() {
		isLoadAdrr = false;
		if (mapManager != null) {
			mapManager.stop();
		}
		super.onPause();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * 通过经纬度获取地址
	 * 
	 * @param point
	 * @return
	 */
	private String getLocationAddress(GeoPoint point) {
		String add = "";
		Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
		try {
			List<Address> addresses = geoCoder.getFromLocation(
					point.getLatitudeE6() / 1E6, point.getLongitudeE6() / 1E6,
					1);
			Address address = addresses.get(0);
			int maxLine = address.getMaxAddressLineIndex();
			if (maxLine >= 2) {
				add = address.getAddressLine(1) + address.getAddressLine(2);
			} else {
				add = address.getAddressLine(1);
			}
		} catch (IOException e) {
			add = "";
			e.printStackTrace();
		}
		return add;
	}


	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_VIEW_LONGPRESS:// 处理长按时间返回位置信息
			{
				if (null == locPoint)
					return;
				mMKSearch.reverseGeocode(locPoint);
				desText.setVisibility(View.VISIBLE);
				desText.setText(lost_tips);
				mMapCtrl.animateTo(locPoint);
				mapView.invalidate();
			}
				break;
			case MSG_VIEW_ADDRESSNAME:
				desText.setText((String) msg.obj);
				desText.setVisibility(View.VISIBLE);
				break;
			case MSG_GONE_ADDRESSNAME:
				desText.setVisibility(View.GONE);
				break;
			}
		}
	};

	// 关闭程序也关闭定位
	@Override
	protected void onDestroy() {
		if (mapManager != null) {
			mapManager.destroy();
			mapManager = null;
		}
		super.onDestroy();
	}

	/**
	 * 根据MyLocationOverlay配置的属性确定是否在地图上显示当前位置
	 */
	@Override
	protected boolean isLocationDisplayed() {
		return false;
	}

	/**
	 * 当位置发生变化时触发此方法
	 * 
	 * @param location
	 *            当前位置
	 */
	public void onLocationChanged(Location location) {
		if (location != null) {
			locPoint = new GeoPoint((int) (location.getLatitude()* 1E6),
					(int) (location.getLongitude()* 1E6));
			mHandler.sendEmptyMessage(MSG_VIEW_LONGPRESS);
		}
	}

	/**
	 * 内部类实现MKSearchListener接口,用于实现异步搜索服务
	 * 
	 * @author liufeng
	 */
	public class MySearchListener implements MKSearchListener {
		/**
		 * 根据经纬度搜索地址信息结果
		 * 
		 * @param result
		 *            搜索结果
		 * @param iError
		 *            错误号（0表示正确返回）
		 */
		public void onGetAddrResult(MKAddrInfo result, int iError) {
			if (result == null) {
				return;
			}
			Message msg = new Message();
			msg.what = MSG_VIEW_ADDRESSNAME;
			msg.obj = result.strAddr;
			mHandler.sendMessage(msg);

		}

		/**
		 * 驾车路线搜索结果
		 * 
		 * @param result
		 *            搜索结果
		 * @param iError
		 *            错误号（0表示正确返回）
		 */
		public void onGetDrivingRouteResult(MKDrivingRouteResult result,
				int iError) {
		}

		/**
		 * POI搜索结果（范围检索、城市POI检索、周边检索）
		 * 
		 * @param result
		 *            搜索结果
		 * @param type
		 *            返回结果类型（11,12,21:poi列表 7:城市列表）
		 * @param iError
		 *            错误号（0表示正确返回）
		 */
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {
		}

		/**
		 * 公交换乘路线搜索结果
		 * 
		 * @param result
		 *            搜索结果
		 * @param iError
		 *            错误号（0表示正确返回）
		 */
		public void onGetTransitRouteResult(MKTransitRouteResult result,
				int iError) {
		}

		/**
		 * 步行路线搜索结果
		 * 
		 * @param result
		 *            搜索结果
		 * @param iError
		 *            错误号（0表示正确返回）
		 */
		public void onGetWalkingRouteResult(MKWalkingRouteResult result,
				int iError) {
		}

		public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}
	}

}