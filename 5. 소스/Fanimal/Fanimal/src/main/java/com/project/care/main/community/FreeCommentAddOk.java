package com.project.care.main.community;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.FreeCommentDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/community/freecommentaddok.do")
public class FreeCommentAddOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	HttpSession session = req.getSession();
	UserDTO dto1 = (UserDTO) session.getAttribute("auth");
	
	req.setCharacterEncoding("UTF-8");
	
	String content = req.getParameter("content");
	String comuseq = req.getParameter("commuseq");
	
	String isSearch = req.getParameter("isSearch");
	String column = req.getParameter("column");
	String word = req.getParameter("word");
	
	FreeCommentDTO dto = new FreeCommentDTO();
	
	dto.setContent(content);
	dto.setComuseq(comuseq);
	dto.setId((String)dto1.getId());
			
	
	CommuDAO dao = new CommuDAO();
	
	int result = dao.addComment(dto);
	
	
	req.setAttribute("result", result);
	req.setAttribute("isSearch", isSearch);
	req.setAttribute("column", column);
	req.setAttribute("word", word);
	req.setAttribute("comuseq", comuseq);
	
	
	RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/freecommentaddok.jsp");
	dispatcher.forward(req, resp);
	
	}
	
}
