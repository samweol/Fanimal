package com.project.care.main.company.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.CompanyDTO;
import com.project.care.main.CompanyDAO;

@WebServlet("/company/updatecompanypage.do")
public class UpdateCompanyPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/company/updatecompanypage.jsp");

		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//2. ajax로 보낸 데이터 받기
		String id = req.getParameter("id");
		String info = req.getParameter("info");
		
		CompanyDTO dto = new CompanyDTO();
		dto.setId(id);
		dto.setInfo(info);
		
		CompanyDAO dao = new CompanyDAO();
		
		//아이디 가지고 hpseq 찾아오기
		int hpseq = dao.findHpseq(dto);
		
		int result = dao.updateHospital(hpseq, info);
		
		//보내기
		PrintWriter writer = resp.getWriter();
		
		writer.printf("{\"result\" : %d}", result);

        writer.close();
	}
}
