package com.project.care.main.user.mypage.diary;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.project.care.dto.WDiaryDTO;

@WebServlet("/cdiary/listwdiaryajax.do")
public class ListWDiaryAjax extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		
		String wseq = req.getParameter("wseq");
		
		
		WDiaryDAO dao = new WDiaryDAO();

		WDiaryDTO cdto = dao.viewCDairy(wseq); // cdto를 받아옴
		
		
		cdto.setDatetime(cdto.getDatetime().substring(0,11));
	
		
		// JSON 반환
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");

		String jsonStr = "{\"name\" : \"" +   cdto.getName() +  " \","
					    + "\"title\" : \"" +   cdto.getTitle() +  " \", "
					    + "\"wseq\" : \"" +   cdto.getWseq() +  " \", "
					    + "\"place\" : \"" +   cdto.getPlace() +  " \", "
					    + "\"pic\" : \"" +   cdto.getPic() +  " \", "
					    + "\"datetime\" : \"" +   cdto.getDatetime() +  " \", "
					    + "\"content\" : \"" +   cdto.getContent() +  " \"} ";
		
		
		
		JSONParser parser = new JSONParser();
		Object obj = null;
		
		try {
			obj = parser.parse(jsonStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject jsonObj = (JSONObject) obj;

		System.out.println("JsonSTR :" + jsonStr);
		System.out.println("jsonObj :" + jsonObj);
		
		resp.getWriter().print(jsonObj);

	}
		
	
}