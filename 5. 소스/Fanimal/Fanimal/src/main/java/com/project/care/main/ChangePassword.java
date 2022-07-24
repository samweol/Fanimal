package com.project.care.main;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main/changepassword.do")
public class ChangePassword extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//findpassword에서 password찾을 때 썻던 id를 받아서 jsp로 넘김
		String id = req.getParameter("id");
		req.setAttribute("id", id);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/changepassword.jsp");

		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id"); //hidden 으로 넘긴 id 받아오기
		String password = req.getParameter("password");
		String passwordcheck = req.getParameter("passwordcheck");

		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("password", password);
		map.put("passwordcheck", passwordcheck);
		map.put("id", id);
		
		//아이디로 회원/기업 정보 찾은 후 비밀번호 갱신하기
		UserDAO userdao = new UserDAO();
		CompanyDAO companydao = new CompanyDAO();
		int result = 0;
		//새비밀번호와 확인비밀번호가 일치하면
		if(password.equals(passwordcheck)) {
			result = userdao.updatePassword(map);
			if(result == 1) {
				req.setAttribute("result", result);
				req.setAttribute("type", "u");				
			} else if(result == 0) { //회원 테이블에서 찾지 못했다면
				result = companydao.updatePassword(map);
				req.setAttribute("result", result);
				req.setAttribute("type", "c");
			}
		} else {
			req.setAttribute("error", "비밀번호가 일치하지 않습니다.");
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/changepassword.jsp");
        dispatcher.forward(req, resp);
		
		
		
	}
}
