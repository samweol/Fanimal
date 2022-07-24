package com.project.care.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.LoginDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/main/userlogin.do")
public class UserLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/userlogin.jsp");

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
		
		UserDAO dao = new UserDAO();
		
		//아이디, 비밀번호가 일치하는 회원이 있으면 정보 받아오기
		UserDTO userdto = dao.userLogin(dto);
		
		//로그인 성공
		if(userdto != null) {
			
			//세션 만들기
			HttpSession session = req.getSession();
			//해당 회원의 정보를 담은 dto를 auth에 저장
			session.setAttribute("auth", userdto);
			//다음 페이지로 보내기
			resp.sendRedirect("/fanimal/user/mypage/listanimal.do");
		} else { //로그인 실패
			//로그인 실패시 loginError 데이터를 가지고 다시 로그인 창으로 보냄
			req.setAttribute("loginError", "y");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/userlogin.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
