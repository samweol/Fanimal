package com.project.care.main.company.reservation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.care.dto.productDTO;

@WebServlet("/ajax/product.do")
public class ProductAjax extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String iseq = req.getParameter("iseq");
		
		System.out.println("iseq=" + iseq);
		
		prescriptionDAO dao = new prescriptionDAO();
		
		ArrayList<productDTO> plist = new ArrayList<productDTO>();
		
		plist = dao.protype(iseq);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		
		int i = 0;
		
		writer.print("[");
		
		for (productDTO item : plist) {
			writer.print("{");
			writer.printf("\"pseq\": \"%s\",", item.getPseq());
			writer.printf("\"pname\": \"%s\""   , item.getPname());
			writer.print("}");
			
			if (i < plist.size() - 1) {
				writer.print(",");
			}
			
			i++;
		}
		
		writer.print("]");
		
		
		writer.close();
	}
}