package com.lyq.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyq.model.User;
import com.lyq.model.dao.UserDao;
/**
 * �û���¼Servlet��
 * @author Li Yong Qiang
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -3009431503363456775L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		
		// ��ȡ�û���
		String username = req.getParameter("username");
		// ��ȡ����
		String password = req.getParameter("password");
		// ʵ����UserDao����
		UserDao userDao = new UserDao();	
		// �����û������ѯ�û�
		User user = userDao.login(username, password);
		PrintWriter printWriter = resp.getWriter();
		// �ж�user�Ƿ�Ϊ��
		if(user != null){
//			// ���û��������session��
//			request.getSession().setAttribute("user", user);
//			// ת����result.jspҳ��
//			request.getRequestDispatcher("message.jsp").forward(request, response);
			
			printWriter.print("��¼�ɹ�");
			
		}else{
			// ��¼ʧ��
//			request.setAttribute("info", "�����û������������");
//			request.getRequestDispatcher("message.jsp").forward(request, response);
			printWriter.print("�����û������������");
		}
		
//		if (!username.isEmpty() & !password.isEmpty()) {
//			outWriter.print("Get����ɹ�! �������Ϊ��username = "+username+"��password = "+password);
//		}else {
//			outWriter.print("Get����ʧ�ܣ�");
//		}
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�û���
		String username = request.getParameter("username");
		// ��ȡ����
		String password = request.getParameter("password");
		// ʵ����UserDao����
		UserDao userDao = new UserDao();	
		// �����û������ѯ�û�
		User user = userDao.login(username, password);
		// �ж�user�Ƿ�Ϊ��
		if(user != null){
			// ���û��������session��
			request.getSession().setAttribute("user", user);
			// ת����result.jspҳ��
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}else{
			// ��¼ʧ��
			request.setAttribute("info", "�����û������������");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	}

}
