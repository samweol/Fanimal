package com.project.care.main.user.mypage.diary;

import java.io.IOException;




import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cdiary/add.do")
public class AddCDiary extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String cdiaryPath = "/WEB-INF/views/user/mypage/cdiary";
		
		String pSeq = req.getParameter("pSeq");
		String aSeq = req.getParameter("aSeq");
		
		
		
		req.setAttribute("pSeq", pSeq);
		req.setAttribute("aSeq", aSeq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(cdiaryPath+"/add.jsp");
		dispatcher.forward(req, resp);

	}
}
