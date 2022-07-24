package com.project.care.main.community;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.care.dto.CommuDTO;
import com.project.care.dto.UserDTO;


@WebServlet("/community/freeaddok.do")
public class FreeAddOk extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		UserDTO dto1 = (UserDTO) session.getAttribute("auth");
		
		req.setCharacterEncoding("UTF-8");
		
		MultipartRequest multi = null;

		//String path = req.getSession().getServletContext().getRealPath("/images");
		String path = req.getRealPath("/files");
		int size = 1024 * 1024 * 100;
		
		try {
			
			multi = new MultipartRequest(
											req,
											path,
											size,
											"UTF-8",
											new DefaultFileRenamePolicy()
										);
			
		} catch (Exception e) {
			System.out.println("AddOk.doPost");
			e.printStackTrace();
		}
		
		String title = multi.getParameter("title");
		String field = multi.getParameter("field");
		//System.out.println(title + field);
		String division = multi.getParameter("division");
		
		
		//파일 업로드 처리
		String attachFile = multi.getFilesystemName("attach");
		String orgattachFile = multi.getOriginalFileName("attach");
		
		//db
		CommuDTO dto = new CommuDTO();
		
	
		
		dto.setTypeseq(division);
		
		dto.setTitle(title);
		dto.setField(field);
		dto.setId((String)dto1.getId());
	
		
		dto.setAttachFile(attachFile);
		dto.setOrgattachFile(orgattachFile);

		
		CommuDAO dao = new CommuDAO();
		
		
		int result = 0;
		
		if (dto1.getId() != null) {
			result = dao.add(dto);
		}
		
		
		
		
		
		req.setAttribute("result", result);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/freeaddok.jsp");
		dispatcher.forward(req, resp);

		
	}

}
