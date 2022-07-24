package com.project.care.main.user.mypage.reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.AniResDTO;
import com.project.care.dto.UserDTO;
import com.project.care.main.user.mypage.animal.AnimalDAO;


@WebServlet("/user/mypage/reshos.do")
public class ResHos extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		// 유저의 세션 쓰셔야하는 경우
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
		
		
		
		
		
		
		
		
		
		
		//페이징
				int nowPage = 0;		//현재 페이지 번호(= page)
				int begin = 0;			//SQL 조건 > 시작값
				int end = 0;
				int pageSize = 4;		//한 페이지 당 출력할 게시물 수
				
				int totalCount = 0;		//총 게시물 수
				int totalPage = 0; 		//총 페이지 수
				
				
				//list.do > (페이지 정보 없으면 1) > list.do?page=1
				//list.do?page=3	
				
				String page = req.getParameter("page");
				
				if (page == null || page == "") nowPage = 1;
				else nowPage = Integer.parseInt(page);
				
				
				
				//nowPage > 현재 보게될 페이지 번호user
				//list.do?page=1 > where rnum between 1 and 10
				//list.do?page=2 > where rnum between 11 and 20
				//list.do?page=3 > where rnum between 21 and 30
				
				begin = ((nowPage - 1) * pageSize) + 1;
				end = begin + pageSize - 1;
				
						
				String id = dto.getId();
				
				AnimalDAO dao = new AnimalDAO();
				
				ArrayList<AniResDTO> reslist = new ArrayList<AniResDTO>();
				
				reslist = dao.reslist(id);
				
				//이제 지난 예약 내역 리스트 뽑아야함
				ArrayList<AniResDTO> beflist = new ArrayList<AniResDTO>();
				
				beflist = dao.beflist(id, begin, end);
				
				

				totalCount = dao.getTotalCount(id); //map > 검색조건을 dao에 넘김
				totalPage = (int)Math.ceil((double)totalCount / pageSize);
				
				
				
				String pagebar = "";
				int blockSize = 10;	//한번에 보여질 페이지 개수
				int n = 0;			//페이지 번호
				int loop = 0;		//루프
				
				pagebar = "";

				
				loop = 1;
				n = ((nowPage - 1) / blockSize) * blockSize + 1; //각 페이지마다 보여지는 페이지가 달라짐
				

				
				
				pagebar += "<ul class=\"pagination\">";
				
				
				
				if (n == 1) {
					pagebar += String.format(" <li class=\"page-item\">\r\n"
							+ "			      <a class=\"page-link\" href=\"#!\" aria-label=\"Previous\">\r\n"
							+ "			        <span aria-hidden=\"true\">&laquo;</span>\r\n"
							+ "			      </a>\r\n"
							+ "			    </li> "
							);
					
				} else {
					pagebar += String.format(" <li class=\"page-item\">\r\n"
							+ "			      <a class=\"page-link\" href=\"/fanimal/user/mypage/reshos.do?page=%d\" aria-label=\"Previous\">\r\n"
							+ "			        <span aria-hidden=\"true\">&laquo;</span>\r\n"
							+ "			      </a>\r\n"
							+ "			    </li> "
							, n - 1
							);
					
				}
				
				
				
				
				while (!(loop > blockSize || n > totalPage)) {			
					
					if (n == nowPage) {
						
						pagebar += String.format("  <li class=\"page-item active\"><a class=\"page-link\" href=\"#!\">%d</a></li> "																
																		, n);
					} else {
						
						pagebar += String.format("  <li class=\"page-item\"><a class=\"page-link\" href=\"/fanimal/user/mypage/reshos.do?page=%d\">%d</a></li> "
																		, n
																		, n);
					}
					
					loop++;
					n++;
				}
			
				

				
				
				if (n > totalPage) {			
					pagebar += String.format(" <li class=\"page-item\">\r\n"
							+ "			      <a class=\"page-link\" href=\"#!\" aria-label=\"Next\">\r\n"
							+ "			        <span aria-hidden=\"true\">&raquo;</span>\r\n"
							+ "			      </a>\r\n"
							+ "			    </li> "
							);
				} else {
					
					pagebar += String.format(" <li class=\"page-item\">\r\n"
							+ "			      <a class=\"page-link\" href=\"/fanimal/user/mypage/reshos.do?page=%d\" aria-label=\"Next\">\r\n"
							+ "			        <span aria-hidden=\"true\">&raquo;</span>\r\n"
							+ "			      </a>\r\n"
							+ "			    </li> "
							, n
							);
				}
				
				
				
				
				pagebar += "</ul>";
		
		
		
		
		
		
		
		
		
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		
		req.setAttribute("nowPage", nowPage);
		
		req.setAttribute("pagebar", pagebar);
		
		
		req.setAttribute("reslist", reslist);
		req.setAttribute("beflist", beflist);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/reshos.jsp");

		dispatcher.forward(req, resp);

	}

}
