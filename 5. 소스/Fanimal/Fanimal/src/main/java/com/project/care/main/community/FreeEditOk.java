package com.project.care.main.community;

import java.io.File;
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


@WebServlet("/community/freeeditok.do")
public class FreeEditOk extends HttpServlet {

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		UserDTO dto1 = (UserDTO) session.getAttribute("auth");
		
		req.setCharacterEncoding("UTF-8");
		
		String path = req.getRealPath("/files");
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
			System.out.println("FreeEditOk.doPost");
			e.printStackTrace();
		}
		
		
		String title = multi.getParameter("title");
		String field = multi.getParameter("field");
		String seq = multi.getParameter("seq");
		//System.out.println(seq);
		String isSearch = multi.getParameter("isSearch");
		String column = multi.getParameter("column");
		String word = multi.getParameter("word");
		
		CommuDTO dto = new CommuDTO();
		
		dto.setTitle(title);
		dto.setField(field);
		dto.setCommuseq(seq);
		
		CommuDAO dao = new CommuDAO();

		String attachFile = multi.getFilesystemName("attachFile");

		CommuDTO tempdto = dao.get(seq);
		
		if (tempdto.getAttachFile() != null && attachFile != null) {

			File file = new File(path + "\\" + tempdto.getAttachFile());
			file.delete();
			
			dto.setAttachFile(attachFile);
		} else if (attachFile == null && multi.getParameter("delfile").equals("y")) {

			File file = new File(path + "\\" + tempdto.getAttachFile());
			file.delete();
			dto.setAttachFile(attachFile);
			
		} else if (attachFile == null) {

			dto.setAttachFile(tempdto.getAttachFile());
		} else if (tempdto.getAttachFile() == null && attachFile != null) {

			dto.setAttachFile(attachFile);
		}
		
		int temp = 0;
		
		if (dto1.getId() == null) {
			temp = 1; 
		} else if (dto1.getId() != null) { 
			
			if (dto1.getId().equals(dao.get(seq).getId())) {
				temp = 2;
			} else {
				
				if (dto1.getId().toString().equals("admin")) {
					temp = 3;
				} else {
					temp = 4; 
				}
				
			}
			
		}
				
		
		int result = 0;
		
		if (temp == 2 || temp == 3) {
			result = dao.edit(dto);
		}

		req.setAttribute("result", result);
		req.setAttribute("seq", seq);
		
		
		req.setAttribute("isSearch", isSearch);
		req.setAttribute("column", column);
		req.setAttribute("word", word);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/freeeditok.jsp");
		dispatcher.forward(req, resp);
		}
	
}
