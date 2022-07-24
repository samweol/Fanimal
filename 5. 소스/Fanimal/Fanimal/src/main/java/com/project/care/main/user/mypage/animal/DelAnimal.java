package com.project.care.main.user.mypage.animal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/mypage/delAnimal.do")
public class DelAnimal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		req.setCharacterEncoding("UTF-8");
		
		String uaseq = req.getParameter("uaseq");
		
				
		AnimalDAO dao = new AnimalDAO();
		
		
		String aseq = null;
		
		aseq = dao.findaniaseq(uaseq);
		
				
		int delinfo = 0;
		int deldis = 0;
		int delcdi = 0;
		int delua = 0;
		int delani = 0;
		
		
		delinfo = dao.delinfo(aseq);
		deldis = dao.deldis(aseq);
		delcdi = dao.delcdi(aseq);
		delua = dao.delua(aseq);
		delani = dao.delani(aseq);
		
		System.out.println("1" + delinfo);
		System.out.println("2" + deldis);
		System.out.println("3" + delcdi);
		System.out.println("4" + delua);
		System.out.println("5" + delani);
		
		
		if ((delinfo != 0) && (deldis != 0) && (delua != 0) && (delani != 0)) {
			resp.sendRedirect("/fanimal/user/mypage/listanimal.do");
		} else {
			resp.sendRedirect(String.format("/fanimal/user/mypage/viewanimal.do?uaseq=%s", uaseq));
		}
		
		
		
		/*
		 * RequestDispatcher dispatcher =
		 * req.getRequestDispatcher("/fanimal/user/mypage/listanimal.do");
		 * 
		 * dispatcher.forward(req, resp);
		 */

	}	

}