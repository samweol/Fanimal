package com.project.care.main.community;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.CommuDTO;
import com.project.care.dto.FreeCommentDTO;

@WebServlet("/community/freeviewcp.do")
public class FreeViewCp extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		
		String seq = req.getParameter("seq");
		
		String isSearch = req.getParameter("isSearch");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		
		
		CommuDAO dao = new CommuDAO();
		
		CommuDTO dto = dao.get(seq);
		
		// 조회수
		if (session.getAttribute("read") == null || session.getAttribute("read").toString().equals("n")) {
			dao.updateReadcount(seq);
			session.setAttribute("read", "y");
		}
		
		// 태그 비활성화
		dto.setTitle(dto.getTitle().replace("<", "&lt;").replace(">", "&gt;"));
		dto.setField(dto.getField().replace("<", "&lt;").replace(">", "&gt;"));
		
		// 출력 데이터 조작하기
		dto.setField(dto.getField().replace("\r\n", "<br>"));
		
		// 검색어
		if (isSearch.equals("y") && column.equals("field")) {
						
			dto.setField(dto.getField().replace(word, "<span style=\"background-color:yellow;color:red;\">" + word + "</span>"));
			
		}
		
		
		ArrayList<FreeCommentDTO> clist = dao.listComment(seq);
		
		for (FreeCommentDTO cdto : clist) {
			cdto.setContent(cdto.getContent().replace("\r\n", "<br>"));
		}
		
		
		req.setAttribute("dto", dto);	
		
		req.setAttribute("isSearch", isSearch);
		req.setAttribute("column", column);
		req.setAttribute("word", word);
		
		req.setAttribute("clist", clist);		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/freeviewcp.jsp");
		dispatcher.forward(req, resp);
	}

}