package com.server.an;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 郭宏志 响应 Android客户端发来的请求
 */
@SuppressWarnings("serial")
public class ServiceTest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do Get");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		UserDaoImpl dao = new UserDaoImpl();
		// 获得客户端请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User u = dao.login(username, password);
		if (u != null) {
			// 响应客户端内容，登录成功
			// out.print("登录成功");
			out.print("登录成功");
		} else {
			// 响应客户端内容，登录失败
			out.print("登录失败");
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do Post");
		String username = request.getParameter("username");
		PrintWriter out = response.getWriter();
		UserDaoImpl dao = new UserDaoImpl();
		// 获得客户端请求参数
		String password = request.getParameter("password");

		User u = dao.login(username, password);
		if (u != null) {
			// 响应客户端内容，登录成功
			// out.print("登录成功");
			response.sendRedirect("/AndroidJspSevletMysqlServer/success.jsp");
		} else {
			// 响应客户端内容，登录失败
			out.print("Login failed Please try again !");
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

	public ServiceTest() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
