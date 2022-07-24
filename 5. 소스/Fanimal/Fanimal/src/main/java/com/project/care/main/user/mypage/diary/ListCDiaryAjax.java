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

import com.project.care.dto.CDiaryDTO;


@WebServlet("/cdiary/listcdiaryajax.do")
public class ListCDiaryAjax extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String cdseq = req.getParameter("cdseq");

		CDiaryDAO dao = new CDiaryDAO();

		CDiaryDTO cdto = dao.viewCDairy(cdseq); // cdto를 받아옴
		
		//cdto로 받아온 데이터 가공하기
		//케어일기 작성 날짜 자르기
		cdto.setDatetune(cdto.getDatetune().substring(0,11));
		
		//null처리하기
		if(cdto.getVaccination()==null) {
			cdto.setVaccination("해당없음");
		}
		
		if(cdto.getSurgery()==null) {
			cdto.setSurgery("해당없음");
		}
		
			
		
		// JSON 반환
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");

		String jsonStr = "{\"animalName\" : \"" +   cdto.getAnimalName() +  " \","
					    + "\"explain\" : \"" +   cdto.getExplain() +  " \", "
					    + "\"picture\" : \"" +   cdto.getPicture() +  " \", "
					   	+ "\"pname\" : \"" +   cdto.getPname() +  " \", "
					   	+ "\"pdate\" : \"" +   cdto.getPDate() +  " \", "
					   	+ "\"usage\" : \"" +   cdto.getUsage() +  " \", "
					   	+ "\"component\" : \"" +   cdto.getComponent() +  " \", "
					   	+ "\"surgery\" : \"" +   cdto.getSurgery() +  " \", "
					   	+ "\"purpose\" : \"" +   cdto.getPurpose() +  " \", "
					   	+ "\"content\" : \"" +   cdto.getContent() +  " \", "
					   	+ "\"datetune\" : \"" +   cdto.getDatetune() +  " \", "
					   	+ "\"cdseq\" : \"" +   cdto.getCdSeq() +  " \", "
					   	+ "\"vaccination\" : \"" +   cdto.getVaccination() +  " \"} ";
		
		System.out.println(cdto.getExplain());
		
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(jsonStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject jsonObj = (JSONObject) obj;

		System.out.println("JsonSTR ㅎㅎ:" + jsonStr);
		System.out.println("jsonObj ㅎㅎ:" + jsonObj);
		
		resp.getWriter().print(jsonObj);

	}
}