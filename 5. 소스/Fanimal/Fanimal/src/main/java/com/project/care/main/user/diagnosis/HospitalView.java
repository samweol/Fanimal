package com.project.care.main.user.diagnosis;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.HosReviewDTO;
import com.project.care.dto.HospitalDTO;
import com.project.care.dto.HospitalListDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/diagnosis/hospitalview.do")
public class HospitalView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		UserDTO auth = (UserDTO)session.getAttribute("auth");
		
		//1
		HospitalListDTO dto = new HospitalListDTO();
		dto.setSi(req.getParameter("si"));
		dto.setGu(req.getParameter("gu"));
		dto.setPage(req.getParameter("page"));
		dto.setAlign(req.getParameter("align"));
		dto.setSearch(req.getParameter("search"));
		
		//2
		String hpseq = req.getParameter("hpseq");
		
		//3.
		DiagnosisDAO dao = new DiagnosisDAO();
		HospitalDTO info = dao.getHospitalInfo(hpseq); 		//병원정보 
		ArrayList<String> open = dao.getHosDate(hpseq);		//병원운영요일
		
		int reviewNum = dao.getReviewCnt(hpseq);			//총리뷰수
		
		HosReviewDTO rdto = new HosReviewDTO();
		rdto.setHpseq(hpseq);
		ArrayList<HosReviewDTO> review = dao.getReview(rdto); //리뷰
		
		for (HosReviewDTO r : review) {
			r.setRedate(r.getRedate().substring(0, 16));
		}
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("info", info);
		req.setAttribute("open", open);
		req.setAttribute("review", review);
		req.setAttribute("reviewNum", reviewNum);
		req.setAttribute("hpseq", hpseq);
		
		if (auth != null) {
			req.setAttribute("id", auth.getId());
			req.setAttribute("nickname", auth.getNickname());
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/user/diagnosis/hospitalview.jsp");

		dispatcher.forward(req, resp);
	}

}
