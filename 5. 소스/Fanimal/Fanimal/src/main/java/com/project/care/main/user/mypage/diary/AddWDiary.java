package com.project.care.main.user.mypage.diary;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/wdiary/add.do")
public class AddWDiary extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String wdiaryPath = "/WEB-INF/views/user/mypage/wdiary";
		
		String aseq = req.getParameter("aSeq");

		System.out.println(req.getQueryString());
		
		System.out.println("AddWDiary doGet aseq : "+ aseq);

		req.setAttribute("aseq", aseq);

		RequestDispatcher dispatcher = req.getRequestDispatcher(wdiaryPath+"/add.jsp");
		dispatcher.forward(req, resp);

	}
}