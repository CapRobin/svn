package com.steel.xbmt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.TextUI;

import com.steel.xbmt.biz.SteelBiz;
import com.steel.xbmt.entity.UserInfo;

@SuppressWarnings("serial")
public class DoLogin extends HttpServlet {

	private SteelBiz biz = new SteelBiz();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doGet(req, resp);
		System.out.println("This is do get !");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		PrintWriter outWriter = resp.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserInfo userInfo=biz.login(username, password);
		if (userInfo != null) {
			outWriter.print("Get����ɹ�! ��ȡ�û���ʵ���� = "+userInfo.getRealName()+"������ = "+userInfo.getAge()+"���绰 = "+userInfo.getMobile());
		}else {
			outWriter.print("Get����ʧ�ܣ�");
		}
//		if (!username.isEmpty() & !password.isEmpty()) {
//			outWriter.print("Get����ɹ�! �������Ϊ��username = "+username+"��password = "+password);
//		}else {
//			outWriter.print("Get����ʧ�ܣ�");
//		}
		
		outWriter.flush();
		outWriter.close();
		
//		System.out.println("do Get");
//		response.setContentType("text/html");
//		response.setCharacterEncoding("utf-8");
//		PrintWriter out = response.getWriter();
//		UserDaoImpl dao = new UserDaoImpl();
//		// ��ÿͻ����������
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//
//		User u = dao.login(username, password);
//		if (u != null) {
//			// ��Ӧ�ͻ������ݣ���¼�ɹ�
//			// out.print("��¼�ɹ�");
//			out.print("��¼�ɹ�");
//		} else {
//			// ��Ӧ�ͻ������ݣ���¼ʧ��
//			out.print("��¼ʧ��");
//		}
//		out.flush();
//		out.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doPost(req, resp);
		System.out.println("This is do post !");
		// ��ÿͻ����������
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		PrintWriter outWriter = resp.getWriter();
		// UserInfo info = new UserInfo();
		
		
//		SteelInfoDao webInfo = new SteelInfoDao();
//		UserInfo userInfo = webInfo.userLogin(username, password);
		UserInfo userInfo=biz.login(username, password);
		if (userInfo != null) {
			resp.sendRedirect("/SteelWebServer/success.jsp");
		} else {
			outWriter.print("Login failed Please try again !");
		}

		// http://localhost:8080/SteelWebServer/register.jsp
		// resp.sendRedirect("/SteelWebServer/register.jsp");
		// outWriter.print("Login successful !  Welcome to visit !");

		
		
	}
}
