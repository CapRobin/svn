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
 * 用户登录Servlet类
 * @author Li Yong Qiang
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -3009431503363456775L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		
		// 获取用户名
		String username = req.getParameter("username");
		// 获取密码
		String password = req.getParameter("password");
		// 实例化UserDao对象
		UserDao userDao = new UserDao();	
		// 根据用户密码查询用户
		User user = userDao.login(username, password);
		PrintWriter printWriter = resp.getWriter();
		// 判断user是否为空
		if(user != null){
//			// 将用户对象放入session中
//			request.getSession().setAttribute("user", user);
//			// 转发到result.jsp页面
//			request.getRequestDispatcher("message.jsp").forward(request, response);
			
			printWriter.print("登录成功");
			
		}else{
			// 登录失败
//			request.setAttribute("info", "错误：用户名或密码错误！");
//			request.getRequestDispatcher("message.jsp").forward(request, response);
			printWriter.print("错误：用户名或密码错误！");
		}
		
//		if (!username.isEmpty() & !password.isEmpty()) {
//			outWriter.print("Get请求成功! 请求参数为：username = "+username+"；password = "+password);
//		}else {
//			outWriter.print("Get请求失败！");
//		}
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户名
		String username = request.getParameter("username");
		// 获取密码
		String password = request.getParameter("password");
		// 实例化UserDao对象
		UserDao userDao = new UserDao();	
		// 根据用户密码查询用户
		User user = userDao.login(username, password);
		// 判断user是否为空
		if(user != null){
			// 将用户对象放入session中
			request.getSession().setAttribute("user", user);
			// 转发到result.jsp页面
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}else{
			// 登录失败
			request.setAttribute("info", "错误：用户名或密码错误！");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	}

}
