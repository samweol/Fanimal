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
import com.project.care.dto.WDiaryDTO;

//이름 바꿈 -> addcdiary(가상매핑주소 모두 소문자로 )
@WebServlet("/wdiary/post.do")
public class PostWDiary extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int result = 0;

		try {

			System.out.println("querystring :" + req.getQueryString());
			 String url = ((HttpServletRequest)req).getRequestURL().toString();
				System.out.println("url :" + url);			 
			
			req.setCharacterEncoding("UTF-8");

			MultipartRequest multi = null;
			String pic = "";

			String path = req.getSession().getServletContext().getRealPath("/files");

			int size = 1024 * 1024 * 100;

			
			
			multi = new MultipartRequest(req, path, size, "UTF-8", new DefaultFileRenamePolicy());
			pic = multi.getFilesystemName("pic");

			// 업로드한 파일이 있을 경우에만
			if (!pic.equals(null)) {
				req.setAttribute("pic", pic);
			}

			String id = "hong1234"; // TODO: 추후 세션에서 얻어오기
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String diaryType = multi.getParameter("diaryType");
			String place = multi.getParameter("place");
			String datetime = multi.getParameter("datetime");
			String value = multi.getParameter("value");

			String aseq = multi.getParameter("aseq");

			
//			System.out.println("aseq postwd : " + aseq);

			WDiaryDAO dao = new WDiaryDAO();
			WDiaryDTO wdiary = new WDiaryDTO();

			wdiary.setDatetime(datetime);
			wdiary.setPlace(place);
			wdiary.setPic(pic);
			wdiary.setTitle(title);
			wdiary.setContent(content);
			wdiary.setId(id);
			wdiary.setAseq(aseq);

			try {
				result = dao.insertWDiary(wdiary, aseq);// ************
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			req.setAttribute("wdiary", wdiary);
			req.setAttribute("result", result);
			req.setAttribute("value", value);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/wdiary/post.jsp");
			dispatcher.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
