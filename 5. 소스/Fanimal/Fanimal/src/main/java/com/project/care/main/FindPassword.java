package com.project.care.main;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main/findpassword.do")
public class FindPassword extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/findpassword.jsp");

		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//인코딩
		req.setCharacterEncoding("UTF-8");
		
		//이름, 전화번호 받아오기
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		String id = req.getParameter("id");
		
		//포장하기
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("name", name);
		map.put("tel", tel);
		map.put("id", id);
		
		UserDAO userdao = new UserDAO();
		CompanyDAO companydao = new CompanyDAO();
		
		String password = userdao.findPassword(map);
		
		if(password == null || password == "") { //회원 테이블에 일치하는 정보가 없는 경우
			password = companydao.findPassword(map); //기업 테이블에서 찾기
		}
		
		//다 찾은 후 비밀번호가 있는 경우
		if(password != null) {
			req.setAttribute("id", id); //아이디 넘겨야함 > 비밀번호 업데이트 하기 위해서
			//jsp에 넘기기
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/changepassword.jsp");
			dispatcher.forward(req, resp);
		} else { // 비밀번호가 없는 경우
			req.setAttribute("noPassword", "y");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/findpassword.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
