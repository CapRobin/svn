package com.lyq.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyq.model.User;
/**
 * �û��˳�Servlet
 * 
 * @author Li Yong Qiang
 */
public class UserExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1599366365079846238L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡsession
		HttpSession session = request.getSession();
		// ��ȡ�û�����
		User user = (User)session.getAttribute("user");
		// �ж��û��Ƿ���Ч
		if(user != null){
			// ���û��������session
			session.removeAttribute("user");
			// ������ʾ��Ϣ
			request.setAttribute("info", user.getUsername() + " �ѳɹ��˳���");
		}
		// ת����message.jspҳ��
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}

}
