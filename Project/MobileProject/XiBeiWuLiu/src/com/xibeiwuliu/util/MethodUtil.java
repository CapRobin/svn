package com.xibeiwuliu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Xml;

import com.xibeiwuliu.activity.R;
import com.xibeiwuliu.entity.Parameter;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name��MethodUtil.java
 * @Describe�����÷���
 * @Author Administrator yfr5734@gmail.com
 * @Date��2013-11-19 ����11:13:25
 * @Version v1.0
 */
public class MethodUtil {
	/**
	 * 
	 * ��������ȡ����״̬
	 * 
	 * @return
	 * @throws
	 * @date��2012-8-30 ����11:01:52
	 * @version v1.0
	 */
	public static boolean getNetworkState(Context context) {
		// �������
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWork = connectivity.getActiveNetworkInfo();
		if (netWork != null) {
			return netWork.isAvailable();
		}
		return false;
	}

	/**
	 * 
	 * �������ж�GPS��λ�����Ƿ���
	 * 
	 * @return
	 * @throws
	 * @date��2013-11-19 ����10:37:39
	 * @version v1.0
	 */
	public static boolean isGpsEnable(Context context) {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * �������ֻ��Ÿ�ʽ��֤
	 * 
	 * @param mobile
	 * @return
	 * @throws
	 * @date��2012-7-20 ����9:07:58
	 * @version v1.0
	 */
	public static Boolean isMobileNo(String mobile) {
		Boolean isMobileNo1 = false;

		String mobile13 = "13\\d{9}";
		String mobile14 = "(145|147)\\d{8}";
		String mobile15 = "15[0-9]{1}\\d{8}";
		String mobile18 = "(180|182|185|186|187|188|189)\\d{8}";

		if (mobile.matches(mobile13)) {
			isMobileNo1 = true;
		}
		if (mobile.matches(mobile14)) {
			isMobileNo1 = true;
		}
		if (mobile.matches(mobile15)) {
			isMobileNo1 = true;
		}
		if (mobile.matches(mobile18)) {
			isMobileNo1 = true;
		}

		return isMobileNo1;
	}

	/**
	 * �����������ʽ��֤
	 * 
	 * @param mail
	 * @return
	 * @throws
	 * @date��2012-7-20 ����9:09:58
	 * @version v1.0
	 */
	public static boolean isEmail(String mail) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mail);
		return m.find();
	}

	/**
	 * ��ȡ�ַ����ĳ��ȣ���������ģ���ÿ�������ַ���Ϊ2λ
	 * 
	 * @param value
	 *            ָ�����ַ���
	 * @return �ַ����ĳ���
	 */
	public static int chineseLength(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* ��ȡ�ֶ�ֵ�ĳ��ȣ�����������ַ�����ÿ�������ַ�����Ϊ2������Ϊ1 */
		for (int i = 0; i < value.length(); i++) {
			/* ��ȡһ���ַ� */
			String temp = value.substring(i, i + 1);
			/* �ж��Ƿ�Ϊ�����ַ� */
			if (temp.matches(chinese)) {
				/* �����ַ�����Ϊ2 */
				valueLength += 2;
			} else {
				/* �����ַ�����Ϊ1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}
	
 	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
 	
	/**
	 * 
	 * @Describe��Pull����Xml�ļ�
	 * @param is
	 * @return
	 * @throws Exception
	 * @Throws 
	 * @Date��2013-12-19 ����3:30:01
	 * @Version v1.0
	 */
	public static String parse(InputStream is) throws Exception {
		String getResultStr = null;
		String resultStr = null;
		XmlPullParser parser = Xml.newPullParser(); // ��android.util.Xml����һ��XmlPullParserʵ��
		parser.setInput(is, "UTF-8"); // ���������� ��ָ�����뷽ʽ

		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("string")) {
					eventType = parser.next();
					getResultStr = parser.getText();
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("string")) {
					resultStr = getResultStr;
				}
				break;
			}
			eventType = parser.next();
		}
		return resultStr;
	}
	
	/**
	 * 
	 * ������������Դ��Ϣ����
	 * @param string
	 * @param lprId
	 * @throws 
	 * @date��2013��12��27�� ����3:37:10
	 * @version v1.0
	 */
//	private void setParameterList(String key, String value) {
//		Parameter parameter = null;
//		parameter = new Parameter();
//		parameter.setParameterKey(key);
//		parameter.setParameterValue(value);
//		parameterList.add(parameter);
//	}
	
	/**
	 * 
	 * @Describe������ת��
	 * @param portName
	 * @param parameterList
	 * @return
	 * @Throws
	 * @Date��2013-12-23 ����5:08:55
	 * @Version v1.0
	 */
	public static String getParameterString(String portName, List<Parameter> parameterList) {
		int size = parameterList.size();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			String string2 = "<" + parameterList.get(i).getParameterKey() + ">" + parameterList.get(i).getParameterValue() + "</"
					+ parameterList.get(i).getParameterKey() + ">";
			sb.append(string2);
		}
		return "<Request><data code='" + portName + "'><no>" + sb + "</no></data></Request>";
	}
 	
	


    /**
     * �������ݿ�
     * @param context
     * @param dbfile
     * @return
     */
    public static boolean importDatabase(Context context,File dbfile) {
		int BUFFER_SIZE = 1024;
		InputStream is = null;
		FileOutputStream fos = null;
		boolean flag = false;
		try {
			//�ж����ݿ��ļ��Ƿ���ڣ�����������ִ�е��룬����ֱ�Ӵ����ݿ�
			if (!dbfile.exists()) {
				//����������ݿ�
				if(!dbfile.getParentFile().exists()){
					dbfile.getParentFile().mkdirs();
				}
				dbfile.createNewFile();
				is = context.getResources().openRawResource(R.raw.logistics); 
				fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
				   fos.write(buffer, 0, count);
				}
				fos.flush();
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (Exception e) {
				}
			}
		}
		return flag;
	}
}
