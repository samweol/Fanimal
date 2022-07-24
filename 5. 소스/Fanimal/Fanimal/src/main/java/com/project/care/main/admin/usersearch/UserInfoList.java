package com.project.care.main.admin.usersearch;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.UserDTO;
import com.project.care.dto.reservationDTO;

@WebServlet("/admin/userinfolist.do")
public class UserInfoList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		userinfoDAO dao = new userinfoDAO();
		
		ArrayList<UserDTO> list = dao.list();
		
		for (UserDTO dto : list) {
			dto.setJoindate(dto.getJoindate().substring(0, 10));
		}
		
		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/userinfolist.jsp");

		dispatcher.forward(req, resp);

	}
}
