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
 *         �ٶȵ�������
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
		// ����֪ͨ���:iMaxSecond - ���֪ͨ���,��λ:��;iMinSecond - ��С֪ͨ���,��λ:��
		mapManager.getLocationManager().setNotifyInternal(20, 5);

		mapManager.getLocationManager().requestLocationUpdates(
				new MyLocationListener());
		mapManager.start();
		// --------------��ȡ��ǰ��λ��ַ----------------->>

		qdAdress = (EditText) findViewById(R.id.qdAdress);
		zdAdress = (EditText) findViewById(R.id.zdAdress);
		naviBut = (Button) findViewById(R.id.naviBut);
		relayout_03 = (RelativeLayout) findViewById(R.id.relayout_03);
		// ��ȡ����ַ
		qdAdress.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "��ȡ����ַ��", 5).show();
				;
			}
		});

		// ��ȡ�յ��ַ
		zdAdress.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "��ȡ�յ��ַ��", 5).show();
				;
			}
		});

		// ��ʼ����
		naviBut.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "��ʼ������", 5).show();
				;
			}
		});
	}

	// �����¼���������������ͨ�������������Ȩ��֤�����
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
			tv1.setText("���ȣ�" + jindu + ",γ�ȣ�" + weidu);

			MKSearch search = new MKSearch();
			search.init(mapManager, new MyMKSearchListener());
			search.reverseGeocode(new GeoPoint(jindu, weidu));
		}

	}

	class MyMKSearchListener implements MKSearchListener {

		@Override
		public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
			if (arg0 == null) {
				tv2.setText("û�л�ȡ��Ҫ��λ��");
			} else {
				GeoPoint point = arg0.geoPt;
				startAddress = arg0.strAddr;
				tv2.setText("��ַ��" + startAddress + ",���꣺"
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
