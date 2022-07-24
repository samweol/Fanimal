package com.project.care.main;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main/findid.do")
public class FindId extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/findid.jsp");

		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//인코딩
		req.setCharacterEncoding("UTF-8");
		
		//이름, 전화번호 받아오기
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		
		//포장하기
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("name", name);
		map.put("tel", tel);
		
		UserDAO userdao = new UserDAO();
		CompanyDAO companydao = new CompanyDAO();
		
		//회원 테이블에서 먼저 찾기
		String id = userdao.findId(map);
		String type = "u";
		// 여기서 나오면 id가 null 이 아님 만약 null 이면 아래 기업 테이블 실행
		if(id == null || id == "") { //회원 테이블에 일치하는 정보가 없는 경우
			id = companydao.findId(map); //기업 테이블에서 찾기
			type = "c";
		}
		
		//다 찾은 후 아이디가 있는 경우
		if(id != null) {
			req.setAttribute("id", id); //아이디 저장하기
			req.setAttribute("type", type);
			//jsp에 넘기기
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/findid.jsp");
			dispatcher.forward(req, resp);
		} else { // 아이디가 없는 경우
			req.setAttribute("noId", "y");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/findid.jsp");
			dispatcher.forward(req, resp);
		}
		
		
		
	}
}