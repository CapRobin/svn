package com.baidu.test;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;

/**
 * 
 * @author zhanglei
 * 
 *         百度导航测试
 */
public class MainActivity extends Activity {

	private TextView tv1, tv2;
	private BMapManager mapManager;
	private EditText qdAdress, zdAdress;
	private Button naviBut;
	private RelativeLayout relayout_03;

	private String startAddress = "";
	private String endAddress = "";
	private double start_latitude = 38.4382552803335;
	private double start_longitude = 106.22407820905688;
	private double end_latitude = 38.4843932029292;
	private double end_longitude = 106.24659669484141;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv1 = (TextView) this.findViewById(R.id.result1);
		tv2 = (TextView) this.findViewById(R.id.result2);

		mapManager = new BMapManager(this);
		mapManager.init("EDB67AD764D300895C95ABA02A4DDC58D5485CCD",
				new MyMKGeneralListener());
		// 设置通知间隔:iMaxSecond - 最大通知间隔,单位:秒;iMinSecond - 最小通知间隔,单位:秒
		mapManager.getLocationManager().setNotifyInternal(20, 5);

		mapManager.getLocationManager().requestLocationUpdates(
				new MyLocationListener());
		mapManager.start();
		// --------------获取当前定位地址----------------->>

		qdAdress = (EditText) findViewById(R.id.qdAdress);
		zdAdress = (EditText) findViewById(R.id.zdAdress);
		naviBut = (Button) findViewById(R.id.naviBut);
		relayout_03 = (RelativeLayout) findViewById(R.id.relayout_03);
		// 获取起点地址
		qdAdress.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "获取起点地址！", 5).show();
				;
			}
		});

		// 获取终点地址
		zdAdress.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "获取终点地址！", 5).show();
				;
			}
		});

		// 开始导航
		naviBut.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "开始导航！", 5).show();
				;
			}
		});
	}

	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	class MyMKGeneralListener implements MKGeneralListener {

		public void onGetNetworkState(int arg0) {

		}

		public void onGetPermissionState(int arg0) {

		}

	}

	class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location arg0) {
			int jindu = (int) (arg0.getLatitude() * 1000000);
			int weidu = (int) (arg0.getLongitude() * 1000000);
			tv1.setText("经度：" + jindu + ",纬度：" + weidu);

			MKSearch search = new MKSearch();
			search.init(mapManager, new MyMKSearchListener());
			search.reverseGeocode(new GeoPoint(jindu, weidu));
		}

	}

	class MyMKSearchListener implements MKSearchListener {

		@Override
		public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
			if (arg0 == null) {
				tv2.setText("没有获取想要的位置");
			} else {
				GeoPoint point = arg0.geoPt;
				startAddress = arg0.strAddr;
				tv2.setText("地址：" + startAddress + ",坐标："
						+ point.getLatitudeE6() + "," + point.getLongitudeE6());
				qdAdress.setText(startAddress);
			}
		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {

		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {

		}

		@Override
		public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {

		}

		@Override
		public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {

		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {

		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {

		}

	}
}
