package com.test.demo;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * SeekFile类：在给定目录下搜索包含给定关键字的文件和文件夹
 */
public class SeekFile/* implements Filter */
{
	private String pathname; // 指定目录
	private CharSequence charSequence; // 指定关键字
	private Vector<File> listFitFileArray = new Vector<File>(); // 符合要求的文件名

	public SeekFile() {
	}

	public SeekFile(String pathname, CharSequence charSequence) {
		this.pathname = pathname;
		this.charSequence = charSequence;
	}

	// 得到符合要求的路径名数组
	public Vector<File> getTheFitFileName() {
		BreadthFirstSearch(); // 调用广搜方法

		return listFitFileArray; // 返回最终结果
	}

	// 广搜
	private void BreadthFirstSearch() {
		Queue<String> queue = new LinkedList<String>();

		queue.add(pathname);

		while (0 != queue.size()) {
			File newPathname = new File(queue.remove()); // 当前目录
			File[] listPathNameArray = newPathname.listFiles(); // 把当前目录下的file和directory存到数组

			SelectTheFitFileNameWithSuffix(listPathNameArray); // 挑选符合要求的file的路径名

			// 目录入队
			for (int index = 0; index < listPathNameArray.length; index++) {
				if (!listPathNameArray[index].isHidden() // 筛选目录且非隐藏
						&& listPathNameArray[index].isDirectory()) // 是否是一个目录
				{
					queue.offer(listPathNameArray[index].toString()); // 这句一直引起异常，标记下
				}
			}
		}
	}

	// 挑选符合要求的路径名
	private void SelectTheFitFileNameWithSuffix(File[] listPathNameArray) {
		for (int index = 0; index < listPathNameArray.length; index++) {
			if (listPathNameArray[index].isFile() // 是一个标准文件
					&& !listPathNameArray[index].isHidden() // 非隐藏
					&& listPathNameArray[index].toString().contains(charSequence)) // 包含关键字
			{
				listFitFileArray.add(listPathNameArray[index]);
			}
		}
	}

}