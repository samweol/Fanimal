package com.project.care.main.user.mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.CompanyDTO;
import com.project.care.dto.UserDTO;
import com.project.care.main.UserDAO;

@WebServlet("/user/usermypage.do")
public class UserMyPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("auth");
		
		UserDAO dao = new UserDAO();
		
		UserDTO dto = dao.getUserInfo(userdto);
		
		//생일 가공하기
		String birth = (dto.getBirth()).substring(0, 10);
		
		dto.setBirth(birth);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/usermypage.jsp");

		dispatcher.forward(req, resp);
	}
}
