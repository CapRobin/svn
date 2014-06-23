package com.test.demo;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * SeekFile�ࣺ�ڸ���Ŀ¼���������������ؼ��ֵ��ļ����ļ���
 */
public class SeekFile/* implements Filter */
{
	private String pathname; // ָ��Ŀ¼
	private CharSequence charSequence; // ָ���ؼ���
	private Vector<File> listFitFileArray = new Vector<File>(); // ����Ҫ����ļ���

	public SeekFile() {
	}

	public SeekFile(String pathname, CharSequence charSequence) {
		this.pathname = pathname;
		this.charSequence = charSequence;
	}

	// �õ�����Ҫ���·��������
	public Vector<File> getTheFitFileName() {
		BreadthFirstSearch(); // ���ù��ѷ���

		return listFitFileArray; // �������ս��
	}

	// ����
	private void BreadthFirstSearch() {
		Queue<String> queue = new LinkedList<String>();

		queue.add(pathname);

		while (0 != queue.size()) {
			File newPathname = new File(queue.remove()); // ��ǰĿ¼
			File[] listPathNameArray = newPathname.listFiles(); // �ѵ�ǰĿ¼�µ�file��directory�浽����

			SelectTheFitFileNameWithSuffix(listPathNameArray); // ��ѡ����Ҫ���file��·����

			// Ŀ¼���
			for (int index = 0; index < listPathNameArray.length; index++) {
				if (!listPathNameArray[index].isHidden() // ɸѡĿ¼�ҷ�����
						&& listPathNameArray[index].isDirectory()) // �Ƿ���һ��Ŀ¼
				{
					queue.offer(listPathNameArray[index].toString()); // ���һֱ�����쳣�������
				}
			}
		}
	}

	// ��ѡ����Ҫ���·����
	private void SelectTheFitFileNameWithSuffix(File[] listPathNameArray) {
		for (int index = 0; index < listPathNameArray.length; index++) {
			if (listPathNameArray[index].isFile() // ��һ����׼�ļ�
					&& !listPathNameArray[index].isHidden() // ������
					&& listPathNameArray[index].toString().contains(charSequence)) // �����ؼ���
			{
				listFitFileArray.add(listPathNameArray[index]);
			}
		}
	}

}