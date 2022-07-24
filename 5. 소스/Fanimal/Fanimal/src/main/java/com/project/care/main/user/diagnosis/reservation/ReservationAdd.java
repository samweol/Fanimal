package com.project.care.main.user.diagnosis.reservation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.care.dto.ResHosDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/reservation/reservationadd.do")
public class ReservationAdd extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		UserDTO auth = (UserDTO)session.getAttribute("auth");
		
//		----- 임시id---------
		auth = new UserDTO();
		auth.setId("hong1234");
//		--------------------
		
		req.setCharacterEncoding("UTF-8");
		
		//2.
		String path = req.getRealPath("/files/reservation"); //TODO 합칠때 경로수정
		int size = 1024 * 1024 * 100;
		
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(
											req, 
											path, 
											size, 
											"UTF-8", 
											new DefaultFileRenamePolicy()
										);
			 
		} catch (Exception e) {
			System.out.println("ReservationAdd.doPost");
			e.printStackTrace();
		}
		
		
		ResHosDTO dto = new ResHosDTO();
		if (auth != null) { dto.setLoginId(auth.getId()); } 
		dto.setResdate(multi.getParameter("resdate") + " " + multi.getParameter("restime"));
		dto.setPseq(multi.getParameter("purpose"));
		dto.setUniqueness(multi.getParameter("uniqueness"));
		dto.setHpseq(multi.getParameter("hpseq"));
		dto.setUaseq(multi.getParameter("uaseq"));
		if (dto.getPicture() == null) {
			dto.setPicture("default");
		} else {
			dto.setPicture("'" + multi.getFilesystemName("picture") + "'");
		}
		
		ReservationDAO dao = new ReservationDAO();

		int result = 0;
		
		if(auth != null && dao.checkUserId(dto)) {
			result = dao.addReservation(dto);
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter(); 
		
		writer.print("{");
		
		if(result == 1) {
			String rhseq = dao.getRhseq();
			writer.printf("\"rhseq\": \"%s\",", rhseq);
		}
		writer.printf("\"result\": %d", result);
		writer.print("}");
		
		
		writer.close();
		
	}

}
