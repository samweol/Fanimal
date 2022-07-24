package com.project.care.main.community;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.FreeCommentDTO;

@WebServlet("/community/freecommenteditok.do")
public class FreeCommentEditOk extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		

		String caseq = req.getParameter("seq");
		System.out.println(caseq);
		String content = req.getParameter("content");
		
		String comuseq = req.getParameter("commuseq");
		System.out.println(comuseq);
		String isSearch = req.getParameter("isSearch");
		String column = req.getParameter("column");
		String word = req.getParameter("word");

		FreeCommentDTO dto = new FreeCommentDTO();
		
		dto.setCaseq(caseq);
		dto.setContent(content);
		
		CommuDAO dao = new CommuDAO();
		
		int result = dao.editComment(dto);

		if (result == 1) {
			
			resp.sendRedirect(String.format("/fanimal/community/freeview.do?seq=%s&isSearch=%s&column=%s&word=%s", comuseq, isSearch, column, URLEncoder.encode(word, "UTF-8")));
			
		} else {
			
			PrintWriter writer = resp.getWriter();
			
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<script>");
			writer.println("alert('failed');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");
			
			writer.close();		
		}
	}

}
