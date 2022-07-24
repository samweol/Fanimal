package com.project.care.main.community;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.CommuDTO;



@WebServlet("/community/freeedit.do")
public class FreeEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		//System.out.println(seq);
		String isSearch = req.getParameter("isSearch");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		
		CommuDAO dao = new CommuDAO();
		
		CommuDTO dto = dao.get(seq);
		
		req.setAttribute("dto", dto);
		
		req.setAttribute("isSearch", isSearch);
		req.setAttribute("column", column);
		req.setAttribute("word", word);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/freeedit.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}
