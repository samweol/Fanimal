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

@WebServlet("/company/reservationlist.do")
public class ReservationList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		reservationDAO dao = new reservationDAO();
		
		ArrayList<reservationDTO> list = dao.list();
		

		//시분초 자르기
		for (reservationDTO dto : list) {
			dto.setResdate(dto.getResdate().substring(0, 10));
		}
		
		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/company/reservationlist.jsp");

		dispatcher.forward(req, resp);

	}
}