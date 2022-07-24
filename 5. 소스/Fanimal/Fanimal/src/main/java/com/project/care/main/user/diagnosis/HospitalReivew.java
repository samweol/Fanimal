package com.project.care.main.user.diagnosis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.HosReviewDTO;


@WebServlet("/diagnosis/hospitalreview.do")
public class HospitalReivew extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HosReviewDTO dto = new HosReviewDTO();
		dto.setHpseq(req.getParameter("hpseq"));
		dto.setHrseq(req.getParameter("hrseq"));
		
		
		DiagnosisDAO dao = new DiagnosisDAO();
		
		ArrayList<HosReviewDTO> list = dao.getReview(dto);
		
		
		//ArrayList<BuseoDTO> -> JSON 변환
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		
		PrintWriter writer = resp.getWriter();
		
		String temp = "";
		temp += "[";
		for (HosReviewDTO r : list) {
			temp += "{";
			temp += String.format("\"hrseq\": \"%s\",", r.getHrseq());
			temp += String.format("\"id\": \"%s\",", r.getId());
			temp += String.format("\"nickname\": \"%s\",", r.getNickname());
			temp += String.format("\"review\": \"%s\",", r.getReview());
			temp += String.format("\"star\": \"%s\",", r.getStar());
			temp += String.format("\"redate\": \"%s\"", r.getRedate().substring(0,16));
	
			temp += "},";
		}
		temp = temp.substring(0, temp.length()-1); //마지막 , 제거
		temp += "]";
		
		writer.print(temp);
		
		writer.close();
		
	}

}
