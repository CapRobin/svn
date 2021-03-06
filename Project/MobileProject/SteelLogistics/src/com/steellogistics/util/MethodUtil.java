package com.steellogistics.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.Xml;
import android.view.inputmethod.InputMethodManager;

import com.steellogistics.R;
import com.steellogistics.entity.BuyInfo;
import com.steellogistics.entity.SupplyInfo;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：MethodUtil.java
 * @Describe：常用方法
 * @Author Administrator yfr5734@gmail.com
 * @Date：2013-11-19 上午11:13:25
 * @Version v1.0
 */
public class MethodUtil {
	/**
	 * 
	 * 描述：获取网络状态
	 * 
	 * @return
	 * @throws
	 * @date：2012-8-30 上午11:01:52
	 * @version v1.0
	 */
	public static boolean getNetworkState(Context context) {
		// 检测网络
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWork = connectivity.getActiveNetworkInfo();
		if (netWork != null) {
			return netWork.isAvailable();
		}
		return false;
	}

	/**
	 * 
	 * 描述：判断GPS定位功能是否开启
	 * 
	 * @return
	 * @throws
	 * @date：2013-11-19 上午10:37:39
	 * @version v1.0
	 */
	public static boolean isGpsEnable(Context context) {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * 描述：手机号格式验证
	 * 
	 * @param mobile
	 * @return
	 * @throws
	 * @date：2012-7-20 上午9:07:58
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
	 * 描述：邮箱格式验证
	 * 
	 * @param mail
	 * @return
	 * @throws
	 * @date：2012-7-20 上午9:09:58
	 * @version v1.0
	 */
	public static boolean isEmail(String mail) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mail);
		return m.find();
	}

	/**
	 * 获取字符串的长度，如果有中文，则每个中文字符计为2位
	 * 
	 * @param value
	 *            指定的字符串
	 * @return 字符串的长度
	 */
	public static int chineseLength(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
			String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
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
	 * @Describe：Pull解析Xml文件
	 * @param is
	 * @return
	 * @throws Exception
	 * @Throws
	 * @Date：2013-12-19 下午3:30:01
	 * @Version v1.0
	 */
	public static String parse(InputStream is) throws Exception {
		String getResultStr = null;
		String resultStr = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式

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
	 * 描述：发布货源信息参数
	 * 
	 * @param string
	 * @param lprId
	 * @throws
	 * @date：2013年12月27日 上午3:37:10
	 * @version v1.0
	 */
	// private void setParameterList(String key, String value) {
	// Parameter parameter = null;
	// parameter = new Parameter();
	// parameter.setParameterKey(key);
	// parameter.setParameterValue(value);
	// parameterList.add(parameter);
	// }

	/**
	 * 
	 * @Describe：参数转换
	 * @param portName
	 * @param parameterList
	 * @return
	 * @Throws
	 * @Date：2013-12-23 下午5:08:55
	 * @Version v1.0
	 */
	// public static String getParameterString(String portName,
	// List<Parameter> parameterList) {
	// int size = parameterList.size();
	// StringBuilder sb = new StringBuilder();
	// for (int i = 0; i < size; i++) {
	// String string2 = "<" + parameterList.get(i).getParameterKey() + ">"
	// + parameterList.get(i).getParameterValue() + "</"
	// + parameterList.get(i).getParameterKey() + ">";
	// sb.append(string2);
	// }
	// return "<Request><data code='" + portName + "'><no>" + sb
	// + "</no></data></Request>";
	// }

	/**
	 * 导入数据库
	 * 
	 * @param context
	 * @param dbfile
	 * @return
	 */
	public static boolean importDatabase(Context context, File dbfile) {
		int BUFFER_SIZE = 1024;
		InputStream is = null;
		FileOutputStream fos = null;
		boolean flag = false;
		try {
			// 判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
			if (!dbfile.exists()) {
				// 欲导入的数据库
				if (!dbfile.getParentFile().exists()) {
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
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * @Describe：Unicode解码
	 * @param theString
	 * @return
	 * @Throws:
	 * @Date：2014年5月26日 上午11:32:26
	 * @Version v1.0
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	/**
	 * 获取assets文件中的Json字符串
	 * 
	 */
	public static String getLocalInfo(Context context, String fileName) {
		String getInfo = null;
		try {
			InputStream mStream = context.getAssets().open(fileName);
			byte[] buffer = new byte[context.getResources().getAssets().open(fileName).available()];
			mStream.read(buffer);
			mStream.close();
			getInfo = new String(buffer);
			Log.d("TAG", "getJson is ---------------------->>" + getInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getInfo;
	}

	/**
	 * 
	 * @Describe：SharedPreferences保存数据
	 * @Throws:
	 * @Date：2014年8月14日 下午4:15:50
	 * @Version v1.0
	 */
	public static void setSharedPreferences(Context context, String fileName, String infoKey, String savaString) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_WORLD_READABLE);
		Editor editor = sharedPreferences.edit();
		editor.putString(infoKey, savaString);
		// editor.putInt("age", new Integer(ageEditText.getText().toString()));
		editor.commit();
	}

	/**
	 * 
	 * @Describe：SharedPreferences读取数据
	 * @Throws:
	 * @Date：2014年8月14日 下午4:15:50
	 * @Version v1.0
	 */
	public static String getSharedPreferences(Context context, String fileName, String infoKey) {

		SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		String userinfo = sharedPreferences.getString(infoKey, "");
		// int age = sharedPreferences.getInt("age", 20);
		// nameEditText.setText(name);
		// ageEditText.setText(String.valueOf(age));
		return userinfo;
	}
	
	/**
	 * 
	 * @Describe：关闭输入法
	 * @Throws:
	 * @Date：2014年8月20日 上午11:58:30
	 * @Version v1.0
	 */
	public static void closeInputMethod(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean isOpen = imm.isActive();

		// isOpen若返回true，则表示输入法打开
		if (isOpen) {
			imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}

	}

	/**
	 * 描述：DoctorAnswerInfo排序
	 * @param list
	 * @param format
	 * @return
	 * @throws 
	 * @date：2012-8-16 下午1:51:28
	 * @version v1.0
	 */
//	public static List<BuyInfo> docSortList(List<BuyInfo> list, String format) {
//
////		List<BuyInfo> mBuyInfoInfoList
//		
//		BuyInfo aq_1 = new BuyInfo();
//		BuyInfo aq_2 = new BuyInfo();
//
//		String first, second;
//		Date firstDate, secondDate;
//		int size = list.size();
//		if (list != null) {
//			for (int i = 0; i < size; i++) {
//				for (int j = 0; j < size - 1; j++) {
//					aq_1 = list.get(j);
//					aq_2 = list.get(j + 1);
//
//					first = aq_1.getCreatTime();
//					second = aq_2.getCreatTime();
//					firstDate = Date.getDateByFormat(first, format);
//					secondDate = DateUtil.getDateByFormat(second, format);
//
//					if (secondDate.after(firstDate)) {
//						list.set(j, aq_2);
//						list.set(j + 1, aq_1);
//					}
//				}
//			}
//		}
//
//		return list;
//	}
	
	
	/**
	 * 描述：按照发布求购信息时间排序
	 * @param list
	 * @param format
	 * @return
	 * @throws 
	 * @date：2013-3-11 下午3:18:39
	 * @version v1.0
	 */
	public static List<BuyInfo> sortBuyList(List<BuyInfo> list, String format) {

		BuyInfo aq_1 = new BuyInfo();
		BuyInfo aq_2 = new BuyInfo();
		String first, second;
		Date firstDate, secondDate;
		int size = list.size();
		if (list != null) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size - 1; j++) {
					aq_1 = list.get(j);
					aq_2 = list.get(j + 1);
					first = aq_1.getCreatTime();
					second = aq_2.getCreatTime();
					firstDate = getDateByFormat(first, format);
					secondDate = getDateByFormat(second, format);
					
					if (secondDate.after(firstDate)) {
						
						list.set(j + 1, aq_1);
						list.set(j, aq_2);
					}
				}
			}
		}
		return list;
	}
	

	/**
	 * 描述：按照发布求购信息时间排序
	 * @param list
	 * @param format
	 * @return
	 * @throws 
	 * @date：2013-3-11 下午3:18:39
	 * @version v1.0
	 */
	public static List<SupplyInfo> sortSupplyList(List<SupplyInfo> list, String format) {

		SupplyInfo aq_1 = new SupplyInfo();
		SupplyInfo aq_2 = new SupplyInfo();
		String first, second;
		Date firstDate, secondDate;
		int size = list.size();
		if (list != null) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size - 1; j++) {
					aq_1 = list.get(j);
					aq_2 = list.get(j + 1);
					first = aq_1.getCreatTime();
					second = aq_2.getCreatTime();
					firstDate = getDateByFormat(first, format);
					secondDate = getDateByFormat(second, format);
					
					if (secondDate.after(firstDate)) {
						list.set(j + 1, aq_1);
						list.set(j, aq_2);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * String转Date
	 * @param strDate  String
	 * @param format  "yyyy-MM-dd"
	 * @return
	 */
	public static Date getDateByFormat(String strDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return (sdf.parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
