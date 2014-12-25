package com.haohuotong.web;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.ab.global.AbAppException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haohuotong.entity.AuctionInfo;
import com.haohuotong.entity.CargoInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.entity.VehicleInfo;
import com.haohuotong.global.Constant;
import com.haohuotong.other.EntitySet;
import com.haohuotong.other.HttpRequestEx;
import com.haohuotong.other.HttpResultEx;
import com.haohuotong.other.InfoEntity;
import com.haohuotong.util.MethodUtil;

/**
 * ������Ϣ�ӿڵ��� Copyright (c) 2012 All rights reserved ���ƣ�PublicInfoWeb.java ������TODO
 * 
 * @author Yu Farong - yfr5734@gmail.com
 * @date��2013��12��27�� ����3:05:06
 * @version v1.0
 */
public class AppPublicInfoWeb {

	private final String mUrlBase = "http://nmgclient2.net188.net/";
	private HttpRequestEx mHttp;

	/**
	 * @Describe����Դ��Ϣ�б�
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws
	 * @Date��2013-12-27 ����10:10:09
	 * @Version v1.0
	 */
	public static List<CargoInfo> cargoInfoList(String portName, List<Parameter> parameterList) throws AbAppException {
		boolean isUpdate = false;
		String resultString = null;
		String interfaceName = "InvokeForMobile";
		List<CargoInfo> cargoInfoList = new ArrayList<CargoInfo>();
		CargoInfo cargoInfo = null;
		try {
			String xml = MethodUtil.getParameterString(portName, parameterList);
			// ����������Դ��ַ����һ��Http����
			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// ��ӱ���Ĳ���
			params.add(new BasicNameValuePair("Xml", xml));
			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
			params.add(new BasicNameValuePair("Token", "bbb"));
			params.add(new BasicNameValuePair("ArrayTables", "cargoList"));
			// ���ò����ı���
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// �������󲢻�ȡ����
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);

			// �������ص�����
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(httpResponse.getEntity());

				// ����Xml�ļ�����Json�����
				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
				String resultJson = MethodUtil.parse(is);
				Log.d("UserInfoWeb", "UserInfoWeb_UserRegister is ------------>>" + resultJson);

				// �������
				JSONObject jsonObject = new JSONObject(resultJson);
				boolean resultTag = jsonObject.getBoolean("resultTag");
				if (resultTag) {

					JSONObject cargoList = jsonObject.getJSONObject("cargoList");
					JSONArray jsonArray = cargoList.getJSONArray("data");
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject objectItem = jsonArray.getJSONObject(i);
						GsonBuilder gBuilder = new GsonBuilder();
						Gson gson = gBuilder.create();
						cargoInfo = gson.fromJson(String.valueOf(objectItem), CargoInfo.class);
						cargoInfoList.add(cargoInfo);
					}

					isUpdate = resultTag;
				} else {
					String exMsg = jsonObject.getString("msg");
					AbAppException mAppException = new AbAppException(exMsg);
					throw mAppException;
				}
			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return cargoInfoList;
	}
}
