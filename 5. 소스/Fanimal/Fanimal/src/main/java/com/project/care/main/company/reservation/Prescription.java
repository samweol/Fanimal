package com.project.care.main.company.reservation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.care.dto.CompanyDTO;
import com.project.care.dto.DosageDTO;
import com.project.care.dto.PidDTO;
import com.project.care.dto.componentDTO;
import com.project.care.dto.prescriptionDTO;
import com.project.care.dto.proIngDTO;
import com.project.care.dto.reservationDTO;

@WebServlet("/company/prescription.do")
public class Prescription extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");

		// 회원정보 불러 오기
		reservationDAO dao = new reservationDAO();

		reservationDTO dto = dao.get(seq);

		// 성분명 불러오기 & 제품군
		prescriptionDAO cdao = new prescriptionDAO();

		componentDTO cdto = new componentDTO();
		prescriptionDTO pdto = new prescriptionDTO();

		prescriptionDAO pdao = new prescriptionDAO();

		ArrayList<componentDTO> clist = new ArrayList<componentDTO>();
		// ArrayList<productDTO> plist = new ArrayList<productDTO>();

		clist = pdao.comtype();
		// plist = pdao.protype();

		req.setAttribute("seq", seq);
		req.setAttribute("dto", dto);
		req.setAttribute("clist", clist);
		// req.setAttribute("plist", plist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/company/prescription.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 인코딩
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		// 기업의 세션 쓰셔야하는 경우
		CompanyDTO cdto = (CompanyDTO) session.getAttribute("auth");
		String id = cdto.getId();

		// 1.5
		// 수의사번호
		// String pcseq = req.getParameter("pcseq");
		// 예약날짜
		// String prseq = req.getParameter("prseq");
		String rhseq = req.getParameter("seq");
		
		System.out.println(rhseq);

		prescriptionDAO dao = new prescriptionDAO();

		// 수의사 번호 받기
		String doc = dao.get(id);

		// 예약 날짜 받기
		// String prdto = dao.get1(prseq);

		// req.setAttribute("pcseq", pcseq);
		// req.setAttribute("prseq", prseq);

		// 2. 데이터 보내기
		String avail = req.getParameter("avail");
		String iseq = req.getParameter("type");
		String pseq = req.getParameter("kind");
		String usage = req.getParameter("usage");
		String amount = req.getParameter("amount");
		String pdate = req.getParameter("pdate");
		String explain = req.getParameter("explain");

		// 2. 열기
		prescriptionDAO pdao = new prescriptionDAO();

		// 3. 포장하기
		prescriptionDTO pdto = new prescriptionDTO();

		// 처방전
		pdto.setAvail(avail);
		System.out.println(avail);
		pdto.setExplain(explain);
		
		// tblProIng
		pdto.setIseq(iseq);
		pdto.setPseq(pseq);
		
		// tblDosage
		pdto.setAmount(amount);
		pdto.setUsage(usage);
		pdto.setPdate(pdate);

		

		// 추가한 번호 알아내기
		
		// 처벙전
		
		int eresult = 0;

		eresult = pdao.prescription(pdto, rhseq, doc);
		
		
		
		
//		int pseq = 0;
//
//		pseq = pdao.findpseq();

		// tblDosage
//		DosageDTO ddto = new DosageDTO();
//
//		ddto.setUsage(usage);
//		ddto.setAmount(amount);
//		ddto.setPdate(pdate);

		int dresult = 0;

		dresult = pdao.addDosage(pdto);

		// tblProIng
//		proIngDTO proingdto = new proIngDTO();
//
//		proingdto.setIseq(component);
//		proingdto.setPseq(pname);

//		int proingresult = 0;
//
//		proingresult = pdao.addProIng(pdto);

		// tblProIng 선택
		
		System.out.println(iseq);
		System.out.println(pseq);
		String proingseq = dao.findpiseq(iseq, pseq);
		
		
		//??처방전 최대
		
		// 뭐 최
		String ptseq = dao.findpseq();
		String dseq = dao.finddseq();
		
		// tblPID
//		PidDTO piddto = new PidDTO();
//
//		piddto.setPseq(Integer.toString(pseq));
//
		int pidresult = 0;

		pidresult = pdao.addPID(proingseq, dseq, ptseq);
		
		// 4. 처방전 작성
		
		resp.sendRedirect("/fanimal/company/reservationlist.do");
		
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/company/prescription.jsp");
//		dispatcher.forward(req, resp);
	}
}