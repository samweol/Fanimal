package com.project.care.main.community;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.CommuDTO;
import com.project.care.dto.UserDTO;


@WebServlet("/community/freedelok.do")
public class FreeDelOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		UserDTO dto1 = (UserDTO) session.getAttribute("auth");

		String seq = req.getParameter("seq");

		CommuDAO dao = new CommuDAO();
		
		
		int temp = 0;
		
		if (dto1.getId() == null) {
			temp = 1;
		} else if (dto1.getId() != null) { 
			
			if (dto1.getId().equals(dao.get(seq).getId())) {
				temp = 2; 
			} else {
				
				if (dto1.getId().toString().equals("admin")) {
					temp = 3; 
				} else {
					temp = 4;
				}
				
			}
			
		}
		
		int result = 0;
		
		if (temp == 2 || temp == 3) {
			

			CommuDTO dto = dao.get(seq);
			
			if (dto.getAttachFile() != null) {
				
				String path = req.getRealPath("images") + "\\" + dto.getAttachFile();
				File file = new File(path);
				file.delete();
			}
			
	
			result = dao.del(seq);
		}
		

		req.setAttribute("result", result);		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/freedelok.jsp");
		dispatcher.forward(req, resp);
	}
	
}
