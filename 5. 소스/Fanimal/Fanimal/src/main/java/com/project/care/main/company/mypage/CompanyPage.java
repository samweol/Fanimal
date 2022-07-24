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

@WebServlet("/company/companypage.do")
public class CompanyPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//병원이름, 인허가번호, 운영시간, 소개 및 설명을 받아와야함
		
		HttpSession session = req.getSession();
		CompanyDTO companydto = (CompanyDTO) session.getAttribute("auth");
		
		CompanyDAO dao = new CompanyDAO();
		
		CompanyDTO dto = dao.getHospitalInfo(companydto);
		
		//System.out.println(dto);
		
		//요일 받아오기
		ArrayList<String> mlist = dao.getOpenDate(companydto);
		//System.out.println(mlist);
		
		
		//데이터 jsp에 넘기기
		req.setAttribute("dto", dto);
		req.setAttribute("mlist", mlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/company/companypage.jsp");

		dispatcher.forward(req, resp);
	}
}
