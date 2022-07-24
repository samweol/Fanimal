package com.project.care.main.user.mypage.diary;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.care.dto.CDiaryDTO;

//이름 바꿈 -> addcdiary(가상매핑주소 모두 소문자로 )
@WebServlet("/cdiary/post.do")
public class PostCDiary extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int result=0;

		try {

			req.setCharacterEncoding("UTF-8");

			MultipartRequest multi = null;
			String filename = "";

			String path = req.getSession().getServletContext().getRealPath("/files");
			
			System.out.println(path);
			int size = 1024 * 1024 * 100;

			multi = new MultipartRequest(req, path, size, "UTF-8", new DefaultFileRenamePolicy());
			filename = multi.getFilesystemName("attach");

			// 업로드한 파일이 있을 경우에만 
			if(!filename.equals(null)) {
				req.setAttribute("filename", filename);
			}
			

			// TODO: 추후 세션에서 얻어오기
			String id = "hong1234";
			
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String diaryType = multi.getParameter("diaryType");
	
			
			// TODO: 파라미터로 얻어오기(동물번호,진료확인서) - 나중에 받아올 데이터 -여기까지는 데이터 도착
			String aSeq =multi.getParameter("aseq");  //fix값 - 동물번호
			String pSeq = multi.getParameter("pseq"); //동적 - 진료확인서번호 

	
			String datetune = multi.getParameter("datetune");
		

			String diaryPath = "";
		
			if (diaryType.equals("care")) {//케어일기
				diaryPath = "/WEB-INF/views/user/mypage/cdiary";
				
				CDiaryDAO dao = new CDiaryDAO();
				CDiaryDTO cdiary = new CDiaryDTO();
				
				cdiary.setPicture(filename);
				cdiary.setTitle(title);
				cdiary.setContent(content);
				cdiary.setDatetune(datetune);
				cdiary.setId(id);
				cdiary.setPSeq(pSeq);
				cdiary.setASeq(aSeq);

			
					result = dao.insertCDiary(cdiary);//************	
				
				
				req.setAttribute("cdiary", cdiary);
				req.setAttribute("result", result);
				

			}else {//산책일기
				// TODO: path 수정 필수
				diaryPath = "/WEB-INF/views/user/mypage/cdiary";
				System.out.println(diaryType);
			}
			
			
			System.out.println(result);
  			RequestDispatcher dispatcher = req.getRequestDispatcher(diaryPath + "/post.jsp?");
			dispatcher.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
