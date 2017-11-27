package com.storemanagement.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;

public class PrintBarCodeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameter("barCodeNumber").equals("")) {
			int count = Integer.parseInt(request.getParameter("barCodeNumber"));
			String barCode = request.getParameter("barCode");
			String name = request.getParameter("name");
			Document document = new Document(new Rectangle(PageSize.A4));
			try {
				PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
				document.open();
				document.addTitle(name);
				Barcode128 barcode128 = new Barcode128();
				barcode128.setGenerateChecksum(true);
				for(int i = 0; i < count; i++){
					barcode128.setCode(barCode);
					document.add(barcode128.createImageWithBarcode(writer.getDirectContent(), null, null));
					document.add(new Paragraph(""));
				}
				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "inline; filename=" + name + count + ".pdf");
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
}
