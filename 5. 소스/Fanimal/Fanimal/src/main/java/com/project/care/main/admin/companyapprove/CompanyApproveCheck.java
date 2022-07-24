package com.project.care.main.admin.companyapprove;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.CompanyapproveDTO;


@WebServlet("/admin/companyapprove.do")
public class CompanyApproveCheck extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 데이터 가져오기
		String seq = req.getParameter("seq");

		companyapproveDAO dao = new companyapproveDAO();

		CompanyapproveDTO dto = new CompanyapproveDTO();
		
		// 4.
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/companyapprove.jsp");

		dispatcher.forward(req, resp);

	}
}
