package com.project.care.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.AdminDTO;
import com.project.care.dto.LoginDTO;

@WebServlet("/main/adminlogin.do")
public class AdminLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/adminlogin.jsp");

		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		LoginDTO dto = new LoginDTO();
		dto.setId(id);
		dto.setPassword(password);
		
		AdminDAO dao = new AdminDAO();
		
		AdminDTO admindto = dao.adminLogin(dto);
		
		//로그인 성공
		if(admindto != null) {
			
			//세션 만들기
			HttpSession session = req.getSession();
			//해당 회원의 정보를 담은 dto를 auth에 저장
			session.setAttribute("auth", admindto);
			//다음 페이지로 보내기
			resp.sendRedirect("/fanimal/admin/adminindex.do");
		} else { //로그인 실패
			//로그인 실패시 loginError 데이터를 가지고 다시 로그인 창으로 보냄
			req.setAttribute("loginError", "y");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/adminlogin.jsp");
			dispatcher.forward(req, resp);
		}
		
		
		
	}
}
