package com.test.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 功能：给定目录下搜索设定文件格式中含有指定的关键字符(递归方式)
 * 
 * @author sunfei
 * @since 2013.9.9
 * 
 */

public class SearchString {

	public static String keywords = "管理系统";
	public static String keywords2 = "keyword2";
	public static boolean isFind = false;

//	public static String filePath = "F:\\Projects\\trunk\\ib\\com\\ib\\client";
//	public static String filePath = "D:\\yyoa\\yyoa";
	public static String filePath = "D:\\OA软件脚本\\UFseeyon";
//	public static String filePath = "C:\\Users\\Administrator\\Desktop\\XiBeiWuLiu";
	public static String fileFormat = ".java;.jsp;.xml;.js;.css;";

	public static void main(String[] args) {

		File file = new File(filePath);
		searchFile(file, fileFormat);
		if (!isFind) {
			Print("在给定的文件中没有找到关键字：" + keywords);
		}
	}

	/**
	 * 功能：在指定的文件或目录中搜索关键字
	 * 
	 * @param file
	 *            指定的路径的文件或目录对象
	 * @param fileFormat
	 *            以分号(;)连接的文件格式字符串
	 */
	public static void searchFile(File file, String fileFormat) {
		if (file.isFile()) {
			searchWord(file, fileFormat);
		} else {
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isFile()) {
					searchWord(f, fileFormat);
				} else {
					searchFile(f, fileFormat);
				}
			}
		}
	}

	/**
	 * 功能：在指定的文件中搜索关键字
	 * 
	 * @param file
	 *            指定的路径的文件对象
	 * @param fileFormat
	 *            以分号(;)连接的文件格式字符串
	 */
	public static void searchWord(File file, String fileFormat) {
		String name = file.getName();
		String[] formats = fileFormat.split(";");
		// 该文件是否是指定的文件格式
		boolean isGivedFormat = false;
		for (String format : formats) {
			isGivedFormat = isGivedFormat || name.endsWith(format);
		}

		if (isGivedFormat) {
			BufferedReader br = null;
			FileReader fr = null;
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				for (String line = null; (line = br.readLine()) != null;) {
					String linestr = line;
					if (linestr.contains(keywords)) {// ||	// linestr.contains(keywords2)
						Print("在该文件中找到" + keywords + "字符串:" + file.getPath());
//						System.out.println("关键字所在路径为："+file.getPath());
//						System.out.println(file.getPath());
						isFind = true;
						
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 功能：向控制台输出字符串
	 * 
	 * @param str
	 *            输出的字符串
	 */
	public static void Print(String str) {
		System.out.println(str);
	}
}