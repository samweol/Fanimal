package com.project.care.main.company.mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.CompanyDTO;
import com.project.care.main.CompanyDAO;

@WebServlet("/company/companymypage.do")
public class CompanyMyPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//세션에서 로그인한 사람 정보 받아오기
		HttpSession session = req.getSession();
		CompanyDTO companydto = (CompanyDTO) session.getAttribute("auth");
		
		CompanyDAO dao = new CompanyDAO();
		CompanyDTO dto = dao.getCompanyInfo(companydto);
		
		req.setAttribute("dto", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/company/companymypage.jsp");

		dispatcher.forward(req, resp);
	}
}