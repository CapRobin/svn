package com.fit.DoLogin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoLogin
 */
public class DoLogin_text extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLogin_text() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//          String user_id=request.getParameter("user_id");
//          String user_pwd=request.getParameter("user_pwd");
//          String []hoby=request.getParameterValues("hoby");
//          response.setContentType("text/html;charset=utf-8");
//          PrintWriter out=response.getWriter();
//        //  out.print("<content='text/html;charset=utf-8'>");
//          out.print("�˺���:"+user_id+"����������:"+user_pwd);
//          for (int i = 0; i < hoby.length; i++) {
//			out.print("����������:"+hoby[i]);
//		}
//          out.flush();
//          out.close();
//		
		//�����ת����
		request.getRequestDispatcher("dologin_1").forward(request, response);
		//������ض���
//		response.sendRedirect("dologin_1");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
