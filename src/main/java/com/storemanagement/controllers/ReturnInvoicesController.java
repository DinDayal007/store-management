package com.storemanagement.controllers;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.ReturnPurchaseInvoiceDetails;
import com.storemanagement.entities.ReturnPurchaseInvoiceHeader;
import com.storemanagement.entities.ReturnSalesInvoiceDetails;
import com.storemanagement.entities.ReturnSalesInvoiceHeader;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;

public class ReturnInvoicesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("1"))
			saveReturnSalesInvoice(request, response);
		else if(request.getParameter("action").equals("2"))
			saveReturnPurchaseInvoice(request, response);
	}
	
	//add new sales return invoice
	protected void saveReturnSalesInvoice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("salesInvoiceId").equals("") && !request.getParameter("total").equals("")) {
			int id = Integer.parseInt(request.getParameter("salesInvoiceId"));
			ReturnSalesInvoiceHeader returnSalesInvoiceHeader = new ReturnSalesInvoiceHeader();
			SalesInvoiceHeader salesInvoiceHeader = new SalesInvoiceHeader();
			salesInvoiceHeader.setId(id);
			returnSalesInvoiceHeader.setSalesInvoiceHeader(salesInvoiceHeader);
			returnSalesInvoiceHeader.setNumber("1");
			returnSalesInvoiceHeader.setTotal(Double.parseDouble(request.getParameter("total")));
			returnSalesInvoiceHeader.setDate(new Date());
			User user = new User();
			user.setId(1);
			returnSalesInvoiceHeader.setUser(user);
			int cacheId = Integer.parseInt(request.getParameter("cache"));
			Cache cache = (Cache) EntityService.getObject(Cache.class, cacheId);
			cache.setQty(cache.getQty() - returnSalesInvoiceHeader.getTotal());
			EntityService.updateObject(cache);
			EntityService.addObject(returnSalesInvoiceHeader);
			
			String[] itemIds = request.getParameter("itemIds").replaceAll("\"", "").split(",");
			String[] itemQty = request.getParameter("itemQty").replaceAll("\"", "").split(",");
			String[] itemTotal = request.getParameter("itemTotal").replaceAll("\"", "").split(",");
			for(int i = 0; i < itemIds.length; i++) {
				ReturnSalesInvoiceDetails returnSalesInvoiceDetails = new ReturnSalesInvoiceDetails();
				returnSalesInvoiceDetails.setReturnSalesInvoiceHeader(returnSalesInvoiceHeader);
				Item item = new Item();
				item.setId(Integer.parseInt(itemIds[i]));
				returnSalesInvoiceDetails.setItem(item);
				returnSalesInvoiceDetails.setQty(Integer.parseInt(itemQty[i]));
				returnSalesInvoiceDetails.setPrice(Double.parseDouble(itemTotal[i]));
				EntityService.addObject(returnSalesInvoiceDetails);
			}
			response.getWriter().print("saved!");response.getWriter().print("saved!");
		}
	}
	
	//add new sales return invoice
	protected void saveReturnPurchaseInvoice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("purchaseInvoiceId").equals("") && !request.getParameter("total").equals("")) {
			int id = Integer.parseInt(request.getParameter("purchaseInvoiceId"));
			ReturnPurchaseInvoiceHeader returnPurchaseInvoiceHeader = new ReturnPurchaseInvoiceHeader();
			PurchaseInvoiceHeader purchaseInvoiceHeader = new PurchaseInvoiceHeader();
			purchaseInvoiceHeader.setId(id);
			returnPurchaseInvoiceHeader.setPurchaseInvoiceHeader(purchaseInvoiceHeader);
			returnPurchaseInvoiceHeader.setNumber("1");
			returnPurchaseInvoiceHeader.setTotal(Double.parseDouble(request.getParameter("total")));
			returnPurchaseInvoiceHeader.setDate(new Date());
			User user = new User();
			user.setId(1);
			returnPurchaseInvoiceHeader.setUser(user);
			int cacheId = Integer.parseInt(request.getParameter("cache"));
			Cache cache = (Cache) EntityService.getObject(Cache.class, cacheId);
			cache.setQty(cache.getQty() + returnPurchaseInvoiceHeader.getTotal());
			EntityService.updateObject(cache);
			EntityService.addObject(returnPurchaseInvoiceHeader);
			
			String[] itemIds = request.getParameter("itemIds").replaceAll("\"", "").split(",");
			String[] itemQty = request.getParameter("itemQty").replaceAll("\"", "").split(",");
			String[] itemTotal = request.getParameter("itemTotal").replaceAll("\"", "").split(",");
			for(int i = 0; i < itemIds.length; i++) {
				ReturnPurchaseInvoiceDetails returnPurchaseInvoiceDetails = new ReturnPurchaseInvoiceDetails();
				returnPurchaseInvoiceDetails.setReturnPurchaseInvoiceHeader(returnPurchaseInvoiceHeader);
				Item item = new Item();
				item.setId(Integer.parseInt(itemIds[i]));
				returnPurchaseInvoiceDetails.setItem(item);
				returnPurchaseInvoiceDetails.setQty(Integer.parseInt(itemQty[i]));
				returnPurchaseInvoiceDetails.setPrice(Double.parseDouble(itemTotal[i]));
				EntityService.addObject(returnPurchaseInvoiceDetails);
			}
			response.getWriter().print("saved!");
		}
	}

}
