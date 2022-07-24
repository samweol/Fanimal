package com.project.care.main.user.diagnosis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.HosReviewDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/diagnosis/hospitalreviewadd.do")
public class HospitalReviewAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		UserDTO auth = (UserDTO)session.getAttribute("auth");
		

		HosReviewDTO dto = new HosReviewDTO();
		dto.setId(auth.getId());
		dto.setStar(req.getParameter("star"));
		dto.setReview(req.getParameter("review"));
		dto.setHpseq(req.getParameter("hpseq"));
		
		DiagnosisDAO dao = new DiagnosisDAO();
		int result = dao.addReview(dto);
		HosReviewDTO rdto = null;
		
		if(result == 1) {
			rdto  = dao.getTheReview();
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			
			PrintWriter writer = resp.getWriter();
			
			writer.print("{");
			writer.print(String.format("\"result\": \"%s\",", result));
			writer.print(String.format("\"hrseq\": \"%s\",", rdto.getHrseq()));
			writer.print(String.format("\"star\": \"%s\",", rdto.getStar()));
			writer.print(String.format("\"review\": \"%s\",", rdto.getReview()));
			writer.print(String.format("\"redate\": \"%s\",", rdto.getRedate().substring(0, 16)));
			writer.print(String.format("\"id\": \"%s\"", rdto.getId()));
			writer.print("}");
			
			writer.close();
		
		}
	}

}
