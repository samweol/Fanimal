package com.project.care.main.community;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.CommuDTO;

@WebServlet("/community/freelist.do")
public class FreeList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doTemp(req, resp);
		
	}
	
	private void doTemp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String division = req.getParameter("division");
		
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String isSearch = "n";
		
		if ((column == null || word == null)
				|| (column == "" || word == "")) {
			isSearch = "n";
		} else {
			isSearch = "y";
		}
		
		//구분
		if (division == null || division.equals("")) {
			division = "1";
		}
		
		//System.out.println(division);
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		map.put("column", column);
		map.put("word", word);
		map.put("isSearch", isSearch);
		
		map.put("division", division);
		
		//페이징
		int nowPage = 0; 	//현재 페이지 번호(= page)
		int begin = 0;
		int end = 0;
		int pageSize = 10;	//한페이지 당 출력할 게시물 수
		
		int totalCount = 0; //총 게시물 수
		int totalPage = 0;	//총 페이지 수
		
		String page = req.getParameter("page");
		
		if (page == null || page == "") nowPage = 1;
		else nowPage = Integer.parseInt(page);

		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		
		map.put("begin", begin + "");
		map.put("end", end + "");	
		
		HttpSession session = req.getSession();
		
		CommuDAO dao = new CommuDAO();
		
		ArrayList<CommuDTO> list = dao.list(map);
		
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		//String strNow = String.format("%tF", now);
		
		
		String nowday = format.format(cal.getTime());
		//System.out.println(nowday);
		
		
		for (CommuDTO dto : list) {
			
			dto.setPostDay(dto.getPostDay().substring(0, 10));
			
			if (dto.getTitle().length() > 25) {
				dto.setTitle(dto.getTitle().substring(0, 25) + "..");
			}

			dto.setTitle(dto.getTitle().replace("<", "&lt;").replace(">", "&gt;"));
			
			//최신글 new
			if(dto.getPostDay().equals(nowday)) {
				dto.setPostDay(dto.getPostDay() + "   " + "new");

			}
			
			
		}
			
		totalCount = dao.getTotalCount(map);
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		
		String pagebar = "";
		
		int blockSize = 10;	
		int n = 0;			
		int loop = 0;
		
		pagebar = "";
		
		
		loop = 1;
		n = ((nowPage - 1) / blockSize) * blockSize + 1;
	
		
		pagebar += "<ul class=\"pagination\">";
		
	
		if (n == 1) {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"#!\" aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "		      </a>\r\n"
					+ "		    </li> "
					);
		} else {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"/fanimal/community/freelist.do?page=%d\" aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "		      </a>\r\n"
					+ "		    </li> "
					, n - 1
					);
		}
		
		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (n == nowPage) {
				pagebar += String.format(" <li class=\"page-item active\"><a class=\"page-link\" href=\"#!\">%d</a></li> "
						, n);
			} else {
				pagebar += String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/fanimal/community/freelist.do?page=%d\">%d</a></li> "
						, n
						, n);
			}
			
			loop++;
			n++;
		}
		

		if (n > totalPage) {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"#!\" aria-label=\"Next\">\r\n"
					+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "		      </a>\r\n"
					+ "		    </li> "
					);
		} else {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"/fanimal/community/freelist.do?page=%d\" aria-label=\"Next\">\r\n"
					+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "		      </a>\r\n"
					+ "		    </li> "
					, n
					);
		}
		
		
		pagebar += "</ul>";

		
		session.setAttribute("read", "n");
		
		req.setAttribute("list", list);

		req.setAttribute("map", map);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("pagebar", pagebar);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/freelist.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}
