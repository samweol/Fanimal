package com.project.care.main.user.diagnosis.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.AnimalListDTO;
import com.project.care.dto.HospitalDTO;
import com.project.care.dto.HospitalListDTO;
import com.project.care.dto.PurposeDTO;
import com.project.care.dto.UserDTO;

@WebServlet("/reservation/reservation.do")
public class Reservation extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		HttpSession session = req.getSession();
		//UserDTO auth = (UserDTO)session.getAttribute("auth");
		
		//-----------임시-----------
		UserDTO auth = new UserDTO();
		auth.setId("hong1234");
		auth.setNickname("길길동이");
		//---------------------------
		
		//1
		HospitalListDTO dto = new HospitalListDTO();
		dto.setSi(req.getParameter("si"));
		dto.setGu(req.getParameter("gu"));
		dto.setPage(req.getParameter("page"));
		dto.setAlign(req.getParameter("align"));
		dto.setSearch(req.getParameter("search"));
		String hpseq = req.getParameter("hpseq");
		
		
		//반려동물 리스트 
		ReservationDAO dao = new ReservationDAO();
		ArrayList<AnimalListDTO> animals = dao.getAnimal(auth.getId());
		
		//오늘기준 한달 내의 일정 중 예약 가능한 일정을 찾아 달력에 표시한다 
		ArrayList<String> days = dao.getDay(hpseq); 
		HospitalDTO time = dao.getTime(hpseq);
		
		//방문 목적 종류
		ArrayList<PurposeDTO> purposes = dao.getPurpose();
		
		
		
		//요일
		String[] opendays = new String[7];
		
		for (String d : days) {
			switch(d) {
				case "일" : opendays[0] = d;  break;
				case "월" : opendays[1] = d;  break;
				case "화" : opendays[2] = d;  break;
				case "수" : opendays[3] = d;  break;
				case "목" : opendays[4] = d;  break;
				case "금" : opendays[5] = d;  break;
				case "토" : opendays[6] = d;  break;
			}
		}
		
		String restdays = "";
		
		for(int i=0; i<opendays.length; i++) {
			if (opendays[i] == null) {
				restdays += "day != " + i + " && ";
			}
		}
		restdays = restdays.substring(0, restdays.length()-3);
		
		
		//시간
		int starthour = Integer.parseInt(time.getStarttime().split(":")[0]);
		int startmin =  Integer.parseInt(time.getStarttime().split(":")[1]);
		int endhour =  Integer.parseInt(time.getEndtime().split(":")[0]);
		int endmin =  Integer.parseInt(time.getEndtime().split(":")[1]);
		
		ArrayList<String> restime = new ArrayList<String>();
		
		Calendar temp = Calendar.getInstance(); //시작시간 부터 30분씩 증가
		
		if (startmin == 0) {
			temp.set(Calendar.HOUR_OF_DAY, starthour);
			temp.set(Calendar.MINUTE, 0);
		} else if (startmin <= 30) {
			temp.set(Calendar.HOUR_OF_DAY, starthour);
			temp.set(Calendar.MINUTE, 30);
		} else {
			temp.set(Calendar.HOUR_OF_DAY, starthour + 1);
			temp.set(Calendar.MINUTE, 30);
		} 
		
		Calendar end = Calendar.getInstance(); //종료시간
		
		if (endmin < 30) {
			end.set(Calendar.HOUR_OF_DAY, endhour - 1);
			end.set(Calendar.MINUTE, 30);
		} else {
			end.set(Calendar.HOUR_OF_DAY, endhour);
			end.set(Calendar.MINUTE, 00);
		}
		
		
		while(temp.compareTo(end) <= 0) {

			//휴게시간이 아니면 예약시간으로 출력
			if(temp.get(Calendar.HOUR_OF_DAY) != 13) { 
				restime.add(String.format("%tH:%tM", temp, temp));
			}
			
			temp.add(Calendar.MINUTE, 30);
		}
		
		//test
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("hpseq", hpseq);
		req.setAttribute("animals", animals);
		req.setAttribute("purposes", purposes);
		req.setAttribute("hosname", time.getHosname());
		req.setAttribute("restdays", restdays);
		req.setAttribute("restime", restime);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main/user/diagnosis/reservation/reservation.jsp");

		dispatcher.forward(req, resp);
	}

}

