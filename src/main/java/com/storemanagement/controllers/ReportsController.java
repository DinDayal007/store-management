package com.storemanagement.controllers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovementSum;
import com.storemanagement.entities.ItemMovement;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.services.EntityService;
import com.storemanagement.utils.ReportsUtil;
@WebServlet("/reports")
public class ReportsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportsUtil reportsUtil = new ReportsUtil();
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("r").equals("s"))
			reportsUtil.showSalesInvoicesReport(request, response, EntityService.getAllObjects(SalesInvoiceHeader.class));
		else if(request.getParameter("r").equals("p"))
			reportsUtil.showPurchaseInvoicesReport(request, response, EntityService.getAllObjects(PurchaseInvoiceHeader.class));
		else if(request.getParameter("r").equals("it"))
			reportsUtil.showItemMovementsReport(request, response, EntityService.getAllObjects(ItemMovement.class));
		else if(request.getParameter("r").equals("c"))
			reportsUtil.showCachsQtyReport(request, response, EntityService.getAllObjects(Cache.class));
		else if(request.getParameter("r").equals("cm"))
			reportsUtil.showCachsMovementReport(request, response, EntityService.getAllObjects(CacheMovementSum.class));
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
