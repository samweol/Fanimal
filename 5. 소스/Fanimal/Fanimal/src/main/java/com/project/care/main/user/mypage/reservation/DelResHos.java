package com.project.care.main.user.mypage.reservation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.main.user.mypage.animal.AnimalDAO;

@WebServlet("/user/mypage/delreshos.do")
public class DelResHos extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		
		//String uaseq = req.getParameter("uaseq");
		String rhseq = req.getParameter("rhseq");
		
		//System.out.println("del_uaseq: " + uaseq);
		System.out.println("del_rhseq: " + rhseq);
		
		AnimalDAO dao = new AnimalDAO();
		
		int result = 0;
		
		result = dao.delreshos(rhseq);
		
		
		System.out.println("result: " + result);
		
		if (result != 0) {
			resp.sendRedirect(String.format("/fanimal/user/mypage/reshos.do"));
		} else {
			resp.sendRedirect(String.format("/fanimal/user/mypage/viewreshos.do?rhseq=%s", rhseq));
			
		}
		
	}

}