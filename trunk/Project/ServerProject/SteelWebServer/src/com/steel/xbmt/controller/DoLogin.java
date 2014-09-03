package com.steel.xbmt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.steel.xbmt.biz.SteelBiz;
import com.steel.xbmt.dao.SteelInfoDao;
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
		outWriter.print("登录成功_get");

		outWriter.flush();
		outWriter.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doPost(req, resp);
		System.out.println("This is do post !");
		// 获得客户端请求参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		PrintWriter outWriter = resp.getWriter();
		// UserInfo info = new UserInfo();
		
		
//		SteelInfoDao webInfo = new SteelInfoDao();
//		UserInfo userInfo = webInfo.userLogin(username, password);
		UserInfo userInfo=biz.login(username, password);
		if (userInfo != null) {
			resp.sendRedirect("/AndroidJspSevletMysqlServer/success.jsp");
		} else {
			outWriter.print("Login failed Please try again !");
		}

		// http://localhost:8080/SteelWebServer/register.jsp
		// resp.sendRedirect("/SteelWebServer/register.jsp");
		// outWriter.print("Login successful !  Welcome to visit !");

		
		
	}
}
