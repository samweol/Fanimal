package com.project.care.main.company.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.CompanyDTO;
import com.project.care.main.CompanyDAO;

@WebServlet("/company/reservationchart.do")
public class ReservationChart extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		CompanyDTO dto = (CompanyDTO) session.getAttribute("auth");
		
		//DB 다녀오기
		
		// 카운트 받아오기
		ArrayList<Integer> count = new ArrayList<Integer>();
		CompanyDAO dao = new CompanyDAO();
		count = dao.countMonth(dto);
		
		// 월 받아오기
		ArrayList<String> month = new ArrayList<String>();
		month = dao.getMonth(dto);
		
		System.out.println("count : " + count);
		System.out.println("month : " + month);
		
		req.setAttribute("count", count);
		req.setAttribute("month", month);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/company/reservationchart.jsp");

		dispatcher.forward(req, resp);
	}
}
