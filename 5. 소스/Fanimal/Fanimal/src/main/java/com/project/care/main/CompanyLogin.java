package com.project.care.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.CompanyDTO;
import com.project.care.dto.LoginDTO;

@WebServlet("/main/companylogin.do")
public class CompanyLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/companylogin.jsp");

		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Post로 보내서 인코딩부터 해야함
		req.setCharacterEncoding("UTF-8");
		
		//id와 password 받아오기
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		//포장하기(처리해야할 데이터가 2개 이상이면 dto로 포장하기)
		LoginDTO dto = new LoginDTO();
		dto.setId(id);
		dto.setPassword(password);
		
		CompanyDAO dao = new CompanyDAO();
		
		//로그인 후 회원정보를 저장한 dto
		CompanyDTO companydto = dao.companyLogin(dto);
		
		//로그인 할 때 기업 승인여부 확인하기 위한 절차
		CompanyDTO cdto = dao.companyState(dto);
		
		//로그인 성공
		if(companydto != null) {
			
			//세션 만들기
			HttpSession session = req.getSession();
			
			//해당 회원의 정보를 담은 dto를 auth에 저장
			session.setAttribute("auth", companydto);
			req.setAttribute("cdto", cdto);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/company/companyindex.jsp");
			dispatcher.forward(req, resp);
			//다음 페이지로 보내기
			//resp.sendRedirect("/fanimal/company/companyindex.do");
			
		} else { //로그인 실패
			//로그인 실패시 loginError 데이터를 가지고 다시 로그인 창으로 보냄
			req.setAttribute("loginError", "y");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/companylogin.jsp");
			dispatcher.forward(req, resp);
		}
		
		
	}
}
