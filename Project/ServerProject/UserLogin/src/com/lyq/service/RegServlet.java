package com.lyq.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyq.model.User;
import com.lyq.model.dao.UserDao;
/**
 * �û�ע���Servlet��
 * @author Li Yong Qiang
 */
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 5280356329609002908L;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�û���
		String username = request.getParameter("username");
		// ��ȡ����
		String password = request.getParameter("password");
		// ��ȡ�Ա�
		String sex = request.getParameter("sex");
		// ��ȡͷ��
		String photo = request.getParameter("photo");
		// ��ȡ��ϵ�绰
		String tel = request.getParameter("tel");
		// ��ȡ��������
		String email = request.getParameter("email");
		// ʵ����UserDao����
		UserDao userDao = new UserDao();
		if(username != null && !username.isEmpty()){
			if(userDao.userIsExist(username)){
				// ʵ����һ��User����
				User user = new User();		
				// ���û������е����Ը�ֵ
				user.setUsername(username);	
				user.setPassword(password);
				user.setSex(sex);
				user.setPhoto(photo);
				user.setTel(tel);
				user.setEmail(email);
				// �����û�ע����Ϣ
				userDao.saveUser(user);	
				request.setAttribute("info", "��ϲ��ע��ɹ���<br>");
			}else{
				request.setAttribute("info", "���󣺴��û����Ѵ��ڣ�");
			}
		}
		// ת����message.jspҳ��
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}

}
