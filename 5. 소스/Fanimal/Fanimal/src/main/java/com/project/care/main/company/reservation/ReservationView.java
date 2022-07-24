package com.project.care.main.company.reservation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.reservationDTO;
import com.project.care.dto.visitDTO;

@WebServlet("/company/reservationview.do")
public class ReservationView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		reservationDAO dao = new reservationDAO();
		
		reservationDTO dto = dao.get(seq);

		
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/company/reservationview.jsp");

		dispatcher.forward(req, resp);

	}
}