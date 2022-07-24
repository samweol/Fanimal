package com.project.care.main.user.mypage.calendar;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.UserDTO;
import com.project.care.main.user.mypage.animal.AnimalDAO;

@WebServlet("/user/mypage/addcalendar.do")
public class AddCalendar extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		
		UserDTO dto = (UserDTO) session.getAttribute("auth");
		
		String memo = req.getParameter("memo");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		
		//System.out.println(end);
		
		String[] calend = end.split("-");
		
		System.out.println(Arrays.toString(calend));
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, Integer.parseInt(calend[0]));
		cal.set(Calendar.MONTH, (Integer.parseInt(calend[1]) - 1));
		cal.set(Calendar.DAY_OF_MONTH, (Integer.parseInt(calend[2]) + 1));
		
		System.out.printf("%tF", cal);
		
		
		String day = String.format("%tF", cal);
		
		System.out.println("1" + day);
		
		AnimalDAO dao = new AnimalDAO();
		
		String id = dto.getId();
		
		int result = dao.addCal(day, memo, start, id);
		
		System.out.println("result:" + result);
		
		
		if (result != 0) {
			resp.sendRedirect("/fanimal/user/mypage/calendar.do");
		} else {
			resp.sendRedirect("/fanimal/user/mypage/listanimal.do");
		}
		
				
		

	}

}
