package com.test.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * ���ܣ�����Ŀ¼�������趨�ļ���ʽ�к���ָ���Ĺؼ��ַ�(�ݹ鷽ʽ)
 * 
 * @author sunfei
 * @since 2013.9.9
 * 
 */

public class SearchString {

	public static String keywords = "����ϵͳ";
	public static String keywords2 = "keyword2";
	public static boolean isFind = false;

//	public static String filePath = "F:\\Projects\\trunk\\ib\\com\\ib\\client";
//	public static String filePath = "D:\\yyoa\\yyoa";
	public static String filePath = "D:\\OA����ű�\\UFseeyon";
//	public static String filePath = "C:\\Users\\Administrator\\Desktop\\XiBeiWuLiu";
	public static String fileFormat = ".java;.jsp;.xml;.js;.css;";

	public static void main(String[] args) {

		File file = new File(filePath);
		searchFile(file, fileFormat);
		if (!isFind) {
			Print("�ڸ������ļ���û���ҵ��ؼ��֣�" + keywords);
		}
	}

	/**
	 * ���ܣ���ָ�����ļ���Ŀ¼�������ؼ���
	 * 
	 * @param file
	 *            ָ����·�����ļ���Ŀ¼����
	 * @param fileFormat
	 *            �Էֺ�(;)���ӵ��ļ���ʽ�ַ���
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
	 * ���ܣ���ָ�����ļ��������ؼ���
	 * 
	 * @param file
	 *            ָ����·�����ļ�����
	 * @param fileFormat
	 *            �Էֺ�(;)���ӵ��ļ���ʽ�ַ���
	 */
	public static void searchWord(File file, String fileFormat) {
		String name = file.getName();
		String[] formats = fileFormat.split(";");
		// ���ļ��Ƿ���ָ�����ļ���ʽ
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
						Print("�ڸ��ļ����ҵ�" + keywords + "�ַ���:" + file.getPath());
//						System.out.println("�ؼ�������·��Ϊ��"+file.getPath());
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
	 * ���ܣ������̨����ַ���
	 * 
	 * @param str
	 *            ������ַ���
	 */
	public static void Print(String str) {
		System.out.println(str);
	}
}