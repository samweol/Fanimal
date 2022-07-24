package com.project.care.main.user.mypage.animal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.AniKindDTO;

@WebServlet("/ajax/anikind.do")
public class AnimalKindAjax extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String tseq = req.getParameter("tseq");
		
		System.out.println(tseq);
		
		AnimalDAO dao = new AnimalDAO();
		
		ArrayList<AniKindDTO> kindlist = new ArrayList<AniKindDTO>();
		
		kindlist = dao.anikind(tseq);
				
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		
		
		
		int i = 0;
		
		writer.print("[");
		
		for (AniKindDTO item : kindlist) {
			writer.print("{");
			writer.printf("\"kseq\": \"%s\",", item.getKseq());
			writer.printf("\"tseq\": \"%s\","   , item.getTseq());
			writer.printf("\"kind\": \"%s\"", item.getKind());
			writer.print("}");
			
			if (i < kindlist.size() - 1) {
				writer.print(",");
			}
			
			i++;
		}
		
		writer.print("]");
		
		
		writer.close();
		
		

	}

}
