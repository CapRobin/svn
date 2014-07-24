package com.steellogistics.util;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title: 字符串操作助手类 fileName: StringUtils.java Description: 一系列的常用方法
 * 
 * @Copyright: PowerData Software Co.,Ltd. Rights Reserved.
 * @Company:深圳市博安达软件开发有限公司
 * @author: 康庆
 * @version:1.0 create date:2009-4-25
 */
public class StringUtils {

	public static final String trim(String str) {
		return str == null ? "" : str.trim();
	}

	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	public static boolean isNotEmpty(String str) {
		return isEmpty(str) ? false : true;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @return
	 */
	public static String getFileFormatter(String filename) {
		String fileFormat = "";
		if (!StringUtils.isEmpty(filename)) {
			int x = filename.lastIndexOf(".");
			if (x > 0) {
				fileFormat = filename.substring(x);
			}
		}
		return fileFormat;
	}

	/**
	 * 根据文件路径解析文件名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String parseFileName(String filePath) {
		String fileName = filePath;
		if (!StringUtils.isEmpty(filePath)) {
			filePath = filePath.replace(File.separator, "/");
			String[] parts = filePath.split("/");
			if (parts != null && parts.length > 0) {
				fileName = parts[parts.length - 1];
			}
		}
		return fileName;
	}

	public static String parseFileSingleName(String filePath) {
		return parseFileName(filePath).replace(getFileFormatter(filePath), "");
	}

	/**
	 * 将Byte数组转成字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String toHexString(byte b[]) {
		return Base64.encode(b);
	}

	/**
	 * 获取文本内容能够产生的行数
	 * 
	 * @param content
	 * @param maxSize
	 * @return
	 */
	public static int getPrintLines(String content, int maxSize) {
		int contentsline = 0;
		if (!StringUtils.isEmpty(content)) {
			contentsline = content.length() / maxSize;
			contentsline = (contentsline * maxSize < content.length()) ? (contentsline + 1)
					: contentsline;
		}
		return contentsline;
	}

	/**
	 * 获取字母或者数字的数量
	 * 
	 * @param content
	 * @return
	 */
	public static int getNumberCount(String content) {
		int count = 0;
		if (!StringUtils.isEmpty(content)) {
			for (int i = 0; i < content.length(); i++) {
				char temp = content.charAt(i);
				if (String.valueOf(temp).matches("[0-9]")) {
					count++;
				} else if (String.valueOf(temp).matches("[a-zA-Z]")) {
					count++;
				}
			}
			count = count / 2;// 为什么要去一半
		}
		return count;
	}

	/**
	 * 将字符串转换成双精度
	 * 
	 * @param num
	 *            数字字符串
	 * @return Double
	 * @author 李东喜 2009-3-25
	 */
	public static double parseDouble(String num) {
		double val = 0D;
		if (StringUtils.isEmpty(num)) {
			return val;
		}
		String ss = StringUtils.trim(num);
		try {
			val = Double.parseDouble(num);
			return val;
		} catch (NumberFormatException e) {
			try {
				Pattern pattern = Pattern.compile("[\\d]+[.]?[\\d]*");
				Matcher matcher = pattern.matcher(ss);
				if (matcher.find()) {
					val = Double.parseDouble(matcher.group());
				}
			} catch (NumberFormatException nfe) {
				val = 0D;
			}
		}
		return val;
	}

	/**
	 * 将字符串转换成整型
	 * 
	 * @param num
	 *            数字字符串
	 * @return Integer
	 * @author 李东喜 2009-3-25
	 */
	public static int parseInteger(String num) {
		int val = 0;
		String ss = StringUtils.trim(num);
		try {
			Pattern pattern = Pattern.compile("[\\d]*");
			Matcher matcher = pattern.matcher(ss);
			if (matcher.find()) {
				val = Integer.parseInt(matcher.group());
			}
		} catch (NumberFormatException nfe) {
			val = 0;
		}
		return val;
	}

	/**
	 * 将字符串转换成长整型
	 * 
	 * @param num
	 *            数字字符串
	 * @return Long
	 * @author 李东喜 2009-3-25
	 */
	public static long parseLong(String num) {
		long val = 0L;
		String ss = StringUtils.trim(num);
		try {
			Pattern pattern = Pattern.compile("[\\d]*");
			Matcher matcher = pattern.matcher(ss);
			if (matcher.find()) {
				val = Long.parseLong(matcher.group());
			}
		} catch (NumberFormatException nfe) {
			val = 0L;
		}
		return val;
	}

	public static int[] double2dufenmiao(double source) {
		int[] result = new int[3];
		String s = String.valueOf(source);
		if (s.indexOf(".") > 0) {
			String[] strs = s.split("\\.");
			result[0] = Integer.parseInt(strs[0]);
			double fen = Double.parseDouble("0." + strs[1]);
			String fenmiao = String.valueOf(fen * 60);
			result[1] = Integer.parseInt(fenmiao.split("\\.")[0]);
			double miao = Double.parseDouble("0." + fenmiao.split("\\.")[1]);
			String m = String.valueOf(miao * 60);
			result[2] = Integer.parseInt(m.split("\\.")[0]);
		} else {
			result[0] = (int) source;
			result[1] = 0;
			result[2] = 0;
		}
		return result;
	}

	public static String[] getStrArray(String str, String regex) {
		if (StringUtils.isNotEmpty(str) && StringUtils.isNotEmpty(regex)) {
			return str.split(regex);
		} else {
			return null;
		}

	}
}
