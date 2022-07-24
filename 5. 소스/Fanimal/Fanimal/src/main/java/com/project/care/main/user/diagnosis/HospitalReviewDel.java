package com.project.care.main.user.diagnosis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.HosReviewDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/diagnosis/hospitalreviewdel.do")
public class HospitalReviewDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		UserDTO auth = (UserDTO)session.getAttribute("auth");
		
		HosReviewDTO dto = new HosReviewDTO();
		dto.setId(auth.getId());
		dto.setHrseq(req.getParameter("hrseq"));
		dto.setHpseq(req.getParameter("hpseq"));
		dto.setCnt("1");
		
		DiagnosisDAO dao = new DiagnosisDAO();
		
		int result = 0;
		if(dao.checkId(dto)) {
			result = dao.delReview(dto.getHrseq());
		}
		
		HosReviewDTO rdto = null;
		if(result == 1) {
			dto.setHrseq(req.getParameter("anoHrseq"));
			
			ArrayList<HosReviewDTO> temp = dao.getReview(dto);
			if(temp.size() != 0) { rdto = temp.get(0); }
		}

			
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		
		writer.print("{");
		
		if(rdto != null) {
			writer.print(String.format("\"hrseq\": \"%s\",", rdto.getHrseq()));
			writer.print(String.format("\"star\": \"%s\",", rdto.getStar()));
			writer.print(String.format("\"review\": \"%s\",", rdto.getReview()));
			writer.print(String.format("\"redate\": \"%s\",", rdto.getRedate().substring(0, 16)));
			writer.print(String.format("\"id\": \"%s\",", rdto.getId()));
		}
		
		writer.print(String.format("\"result\": \"%s\"", result));
		
		writer.print("}");
		
		writer.close();
		
		
		
	}

}
