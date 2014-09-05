package com.lyq.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyq.model.User;
/**
 * 用户退出Servlet
 * 
 * @author Li Yong Qiang
 */
public class UserExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1599366365079846238L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取session
		HttpSession session = request.getSession();
		// 获取用户对象
		User user = (User)session.getAttribute("user");
		// 判断用户是否有效
		if(user != null){
			// 将用户对象逐出session
			session.removeAttribute("user");
			// 设置提示信息
			request.setAttribute("info", user.getUsername() + " 已成功退出！");
		}
		// 转发到message.jsp页面
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}

}
