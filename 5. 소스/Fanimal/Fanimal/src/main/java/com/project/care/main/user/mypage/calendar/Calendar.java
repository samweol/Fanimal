package com.project.care.main.user.mypage.calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.CalendarDTO;
import com.project.care.dto.UserDTO;
import com.project.care.main.user.mypage.animal.AnimalDAO;

@WebServlet("/user/mypage/calendar.do")
public class Calendar extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		
		UserDTO dto = (UserDTO) session.getAttribute("auth");
		
		if (session.getAttribute("auth") == null) {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();

            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('회원만 접근 가능합니다.');");
            writer.println("location.href = '/fanimal/main/userlogin.do';");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");

            writer.close();
            return;
        }
		
		//dto.getId()
		
		//CalendarDTO cdto = new CalendarDTO();
		//CalendarDTO hdto = new CalendarDTO();
		ArrayList<CalendarDTO> clist = new ArrayList<CalendarDTO>();
		
		ArrayList<CalendarDTO> hlist = new ArrayList<CalendarDTO>();
		
		
		AnimalDAO cdao = new AnimalDAO();
		
		
		clist = cdao.callist(dto.getId());
		hlist = cdao.reshoslist(dto.getUseq());
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		
		String today = String.format("%tF", cal);
		
		System.out.println(today);
		
		req.setAttribute("today", today);
		req.setAttribute("clist", clist);
		req.setAttribute("hlist", hlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/calendar.jsp");

		dispatcher.forward(req, resp);

	}

}