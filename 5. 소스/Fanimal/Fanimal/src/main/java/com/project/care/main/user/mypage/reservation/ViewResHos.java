package com.project.care.main.user.mypage.reservation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.AniResDTO;
import com.project.care.main.user.mypage.animal.AnimalDAO;

@WebServlet("/user/mypage/viewreshos.do")
public class ViewResHos extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String rhseq = req.getParameter("rhseq");
		
		AniResDTO dto = new AniResDTO();
		
		AnimalDAO dao = new AnimalDAO();
		
		dto = dao.viewres(rhseq);
		
		
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("rhseq", rhseq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/viewreshos.jsp");

		dispatcher.forward(req, resp);

	}

}
