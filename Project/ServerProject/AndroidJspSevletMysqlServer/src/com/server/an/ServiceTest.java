package com.server.an;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author ����־ ��Ӧ Android�ͻ��˷���������
 */
@SuppressWarnings("serial")
public class ServiceTest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do Get");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		UserDaoImpl dao = new UserDaoImpl();
		// ��ÿͻ����������
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User u = dao.login(username, password);
		if (u != null) {
			// ��Ӧ�ͻ������ݣ���¼�ɹ�
			// out.print("��¼�ɹ�");
			out.print("��¼�ɹ�");
		} else {
			// ��Ӧ�ͻ������ݣ���¼ʧ��
			out.print("��¼ʧ��");
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do Post");
		String username = request.getParameter("username");
		PrintWriter out = response.getWriter();
		UserDaoImpl dao = new UserDaoImpl();
		// ��ÿͻ����������
		String password = request.getParameter("password");

		User u = dao.login(username, password);
		if (u != null) {
			// ��Ӧ�ͻ������ݣ���¼�ɹ�
			// out.print("��¼�ɹ�");
			response.sendRedirect("/AndroidJspSevletMysqlServer/success.jsp");
		} else {
			// ��Ӧ�ͻ������ݣ���¼ʧ��
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
