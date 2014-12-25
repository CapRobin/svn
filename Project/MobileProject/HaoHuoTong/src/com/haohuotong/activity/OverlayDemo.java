package com.haohuotong.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.Ground;
import com.baidu.mapapi.map.GroundOverlay;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.MyLocationOverlay.LocationMode;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.haohuotong.R;
import com.haohuotong.global.MyApplication;
import com.haohuotong.util.BMapUtil;

/**
 * 演示覆盖物的用法
 */
public class OverlayDemo extends Activity {

	/**
	 * MapView 是地图主控件
	 */
	private MapView mMapView = null;
	/**
	 * 用MapController完成地图控制
	 */
	private MapController mMapController = null;
	private MyOverlay mOverlay = null;
	private PopupOverlay pop = null;
	private ArrayList<OverlayItem> mItems = null;
	private TextView popupText = null;
	private View viewCache = null;
	private View popupInfo = null;
	private View popupLeft = null;
	private View popupRight = null;
	private Button button = null;
	private MapView.LayoutParams layoutParam = null;
	private OverlayItem mCurItem = null;
	private GeoPoint geoPoint = null;

	/**
	 * overlay 位置坐标
	 */

	// private double mLon1 = 116.400244;
	// private double mLat1 = 39.963175;
	// private double mLon2 = 116.369199;
	// private double mLat2 = 39.942821;
	// private double mLon3 = 116.425541;
	// private double mLat3 = 39.939723;
	// private double mLon4 = 116.401394;
	// private double mLat4 = 39.906965;
	// private double mLon5 = 116.380338;
	// private double mLat5 = 39.92235;
	// private double mLon6 = 116.414977;
	// private double mLat6 = 39.947246;

	private double mLon1 = 106.2127639352326;
	private double mLat1 = 38.44272215607542;
	private double mLon2 = 106.2253166734223;
	private double mLat2 = 38.444696868649906;
	private double mLon3 = 106.231745928431;
	private double mLat3 = 38.43845323107454;
	private double mLon4 = 106.22414186587378;
	private double mLat4 = 38.435959406598094;
	private double mLon5 = 106.21821418395086;
	private double mLat5 = 38.437264105632835;
	private double mLon6 = 106.22883841385885;
	private double mLat6 = 38.44411496428228;

	// ground overlay
	// private GroundOverlay mGroundOverlay;
	// private Ground mGround;

	// 定位相关
	LocationClient mLocClient;
	LocationData locData = null;
	public MyLocationListenner myListener = new MyLocationListenner();
	// 定位图层
	locationOverlay myLocationOverlay = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * 使用地图sdk前需先初始化BMapManager. BMapManager是全局的，可为多个MapView共用，它需要地图模块创建前创建，
		 * 并在地图地图模块销毁后销毁，只要还有地图模块在使用，BMapManager就不应该销毁
		 */
		MyApplication app = (MyApplication) this.getApplication();
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(this);
			/**
			 * 如果BMapManager没有初始化则初始化BMapManager
			 */
			app.mBMapManager.init(MyApplication.strKey, new MyApplication.MyGeneralListener());
		}
		/**
		 * 由于MapView在setContentView()中初始化,所以它需要在BMapManager初始化之后
		 */
		setContentView(R.layout.activity_overlay);
		mMapView = (MapView) findViewById(R.id.bmapView);
		/**
		 * 获取地图控制器
		 */
		mMapController = mMapView.getController();
		/**
		 * 设置地图是否响应点击事件 .
		 */
		mMapController.enableClick(true);
		/**
		 * 设置地图缩放级别
		 */
		mMapController.setZoom(14);
		/**
		 * 显示内置缩放控件
		 */
		mMapView.setBuiltInZoomControls(true);
		locData = new LocationData();

		initOverlay();

		/**
		 * 设定地图中心点
		 */
		// GeoPoint p = new GeoPoint((int) (38.44181040884739 * 1E6), (int)
		// (106.22488751997992 * 1E6));
		// mMapController.setCenter(p);

		// mMapController.setCenter(new GeoPoint((int) (locData.latitude * 1e6),
		// (int) (locData.longitude * 1e6)));

		// 定位初始化
		mLocClient = new LocationClient(this);
		locData = new LocationData();
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();

		// 定位图层初始化
		myLocationOverlay = new locationOverlay(mMapView);
		// 设置定位数据
		myLocationOverlay.setData(locData);
		// 添加定位图层
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		// 修改定位数据后刷新图层生效
		mMapView.refresh();

		geoPoint = new GeoPoint((int) (38.441858727537856 * 1E6), (int) (106.22488751997992 * 1E6));
		mMapController.setCenter(geoPoint);

	}

	// 继承MyLocationOverlay重写dispatchTap实现点击处理
	public class locationOverlay extends MyLocationOverlay {

		public locationOverlay(MapView mapView) {
			super(mapView);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected boolean dispatchTap() {
			// TODO Auto-generated method stub
			// 处理点击事件,弹出泡泡
			popupText.setBackgroundResource(R.drawable.popup);
			popupText.setText("我的位置");
			pop.showPopup(BMapUtil.getBitmapFromView(popupText), new GeoPoint((int) (locData.latitude * 1e6), (int) (locData.longitude * 1e6)), 8);
			return true;
		}

	}

	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;

			locData.latitude = location.getLatitude();
			locData.longitude = location.getLongitude();
			// 如果不显示定位精度圈，将accuracy赋值为0即可
			locData.accuracy = location.getRadius();
			// 此处可以设置 locData的方向信息, 如果定位 SDK 未返回方向信息，用户可以自己实现罗盘功能添加方向信息。
			locData.direction = location.getDerect();
			// 更新定位数据
			myLocationOverlay.setData(locData);
			// 更新图层数据执行刷新后生效
			mMapView.refresh();
			// 是手动触发请求或首次定位时，移动到定位点
			// if (isRequest || isFirstLoc){
			// 移动地图到定位点
			Log.d("LocationOverlay", "receive location, animate to it");
			geoPoint = new GeoPoint((int) (locData.latitude * 1e6), (int) (locData.longitude * 1e6));
			// mMapController.animateTo(new GeoPoint((int) (locData.latitude *
			// 1e6), (int) (locData.longitude * 1e6)));
			// isRequest = false;
			myLocationOverlay.setLocationMode(LocationMode.NORMAL);
			// requestLocButton.setText("跟随");
			// mCurBtnType = E_BUTTON_TYPE.FOLLOW;
			// }
			// 首次定位完成
			// isFirstLoc = false;
		}

		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}
		}
	}

	public void initOverlay() {
		/**
		 * 创建自定义overlay
		 */
		mOverlay = new MyOverlay(getResources().getDrawable(R.drawable.icon_marka), mMapView);
		/**
		 * 准备overlay 数据
		 */
		GeoPoint p1 = new GeoPoint((int) (mLat1 * 1E6), (int) (mLon1 * 1E6));
		OverlayItem item1 = new OverlayItem(p1, "覆盖物1", "");
		/**
		 * 设置overlay图标，如不设置，则使用创建ItemizedOverlay时的默认图标.
		 */
		item1.setMarker(getResources().getDrawable(R.drawable.icon_marka));

		GeoPoint p2 = new GeoPoint((int) (mLat2 * 1E6), (int) (mLon2 * 1E6));
		OverlayItem item2 = new OverlayItem(p2, "覆盖物2", "");
		item2.setMarker(getResources().getDrawable(R.drawable.icon_markb));

		GeoPoint p3 = new GeoPoint((int) (mLat3 * 1E6), (int) (mLon3 * 1E6));
		OverlayItem item3 = new OverlayItem(p3, "覆盖物3", "");
		item3.setMarker(getResources().getDrawable(R.drawable.icon_markc));

		GeoPoint p4 = new GeoPoint((int) (mLat4 * 1E6), (int) (mLon4 * 1E6));
		OverlayItem item4 = new OverlayItem(p4, "覆盖物4", "");
		item4.setMarker(getResources().getDrawable(R.drawable.icon_markd));
		/**
		 * 将item 添加到overlay中 注意： 同一个itme只能add一次
		 */
		mOverlay.addItem(item1);
		mOverlay.addItem(item2);
		mOverlay.addItem(item3);
		mOverlay.addItem(item4);
		/**
		 * 保存所有item，以便overlay在reset后重新添加
		 */
		mItems = new ArrayList<OverlayItem>();
		mItems.addAll(mOverlay.getAllItem());

		// 初始化 ground 图层
		// mGroundOverlay = new GroundOverlay(mMapView);
		// GeoPoint leftBottom = new GeoPoint((int) (mLat5 * 1E6), (int) (mLon5
		// * 1E6));
		// GeoPoint rightTop = new GeoPoint((int) (mLat6 * 1E6), (int) (mLon6 *
		// 1E6));
		// Drawable d = getResources().getDrawable(R.drawable.ground_overlay);
		// Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
		// mGround = new Ground(bitmap, leftBottom, rightTop);

		/**
		 * 将overlay 添加至MapView中
		 */
		mMapView.getOverlays().add(mOverlay);
		// mMapView.getOverlays().add(mGroundOverlay);
		// mGroundOverlay.addGround(mGround);
		/**
		 * 刷新地图
		 */
		mMapView.refresh();

		/**
		 * 向地图添加自定义View.
		 */

		viewCache = getLayoutInflater().inflate(R.layout.custom_text_view, null);
		popupInfo = (View) viewCache.findViewById(R.id.popinfo);
		popupLeft = (View) viewCache.findViewById(R.id.popleft);
		popupRight = (View) viewCache.findViewById(R.id.popright);
		popupText = (TextView) viewCache.findViewById(R.id.textcache);

		button = new Button(this);
		button.setBackgroundResource(R.drawable.popup);

		/**
		 * 创建一个popupoverlay
		 */
		PopupClickListener popListener = new PopupClickListener() {
			@Override
			public void onClickedPopup(int index) {
				if (index == 0) {
					// 更新item位置
					pop.hidePop();
					GeoPoint p = new GeoPoint(mCurItem.getPoint().getLatitudeE6() + 5000, mCurItem.getPoint().getLongitudeE6() + 5000);
					mCurItem.setGeoPoint(p);
					mOverlay.updateItem(mCurItem);
					mMapView.refresh();
				} else if (index == 2) {
					// 更新图标
					mCurItem.setMarker(getResources().getDrawable(R.drawable.nav_turn_via_1));
					mOverlay.updateItem(mCurItem);
					mMapView.refresh();
				}
			}
		};
		pop = new PopupOverlay(mMapView, popListener);

	}

	/**
	 * 清除所有Overlay
	 * 
	 * @param view
	 */
	// public void clearOverlay(View view) {
	// mOverlay.removeAll();
	// mGroundOverlay.removeGround(mGround);
	// if (pop != null) {
	// pop.hidePop();
	// }
	// mMapView.removeView(button);
	// mMapView.refresh();
	// }

	/**
	 * 重新添加Overlay
	 * 
	 * @param view
	 */
	public void resetOverlay(View view) {
		// clearOverlay(null);
		// // 重新add overlay
		// mOverlay.addItem(mItems);
		// mGroundOverlay.addGround(mGround);
		// mMapView.refresh();

		mMapController.animateTo(new GeoPoint((int) (locData.latitude * 1e6), (int) (locData.longitude * 1e6)));

	}

	@Override
	protected void onPause() {
		/**
		 * MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		 */
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		/**
		 * MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
		 */
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		/**
		 * MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
		 */
		mMapView.destroy();
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mMapView.onRestoreInstanceState(savedInstanceState);
	}

	public class MyOverlay extends ItemizedOverlay {

		public MyOverlay(Drawable defaultMarker, MapView mapView) {
			super(defaultMarker, mapView);
		}

		@Override
		public boolean onTap(int index) {
			OverlayItem item = getItem(index);
			mCurItem = item;
			if (index == 3) {
				button.setText("这是一个系统控件");
				GeoPoint pt = new GeoPoint((int) (mLat4 * 1E6), (int) (mLon4 * 1E6));
				// 弹出自定义View
				pop.showPopup(button, pt, 32);
			} else {
				popupText.setText(getItem(index).getTitle());
				Bitmap[] bitMaps = { BMapUtil.getBitmapFromView(popupLeft), BMapUtil.getBitmapFromView(popupInfo), BMapUtil.getBitmapFromView(popupRight) };
				pop.showPopup(bitMaps, item.getPoint(), 32);
			}
			return true;
		}

		@Override
		public boolean onTap(GeoPoint pt, MapView mMapView) {
			if (pop != null) {
				pop.hidePop();
				mMapView.removeView(button);
			}
			return false;
		}

	}

}
