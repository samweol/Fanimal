package com.project.care.main.community;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/community/freecommentdelok.do")
public class FreeCommentDelOk extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String caseq = req.getParameter("seq");
		String comuseq = req.getParameter("commuseq");
		String isSearch = req.getParameter("isSearch");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		
		CommuDAO dao = new CommuDAO();
		
		System.out.println("caseq: " + caseq);
		int result = dao.delcomment(caseq);
		
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
