package com.storemanagement.controllers;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovement;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.ReturnPurchaseInvoiceDetails;
import com.storemanagement.entities.ReturnPurchaseInvoiceHeader;
import com.storemanagement.entities.ReturnSalesInvoiceDetails;
import com.storemanagement.entities.ReturnSalesInvoiceHeader;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.entities.Supplier;
import com.storemanagement.entities.Unit;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.utils.InvoicesCounterUtil;

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
			int invType = Integer.parseInt(request.getParameter("invType"));
			ReturnSalesInvoiceHeader returnSalesInvoiceHeader = new ReturnSalesInvoiceHeader();
			SalesInvoiceHeader salesInvoiceHeader = new SalesInvoiceHeader();
			salesInvoiceHeader.setId(id);
			returnSalesInvoiceHeader.setSalesInvoiceHeader(salesInvoiceHeader);
			returnSalesInvoiceHeader.setNumber(InvoicesCounterUtil.getReturnSalesInvoiceCounter());
			returnSalesInvoiceHeader.setTotal(Double.parseDouble(request.getParameter("total")));
			returnSalesInvoiceHeader.setDate(new Date());
			User user = (User) request.getSession().getAttribute("user");
			returnSalesInvoiceHeader.setUser(user);
			int cacheId = Integer.parseInt(request.getParameter("cache"));
			Cache cache = (Cache) EntityService.getObject(Cache.class, cacheId);
			if(invType == 0)
				cache.setQty(cache.getQty() - returnSalesInvoiceHeader.getTotal());
			else cache.setQty(cache.getQty() + returnSalesInvoiceHeader.getTotal());
			EntityService.updateObject(cache);
			EntityService.addObject(returnSalesInvoiceHeader);
			//add new cache movement from the return sales invoice
			CacheMovement cacheMovement = new CacheMovement();
			cacheMovement.setCache(cache);
			int clientId = Integer.parseInt(request.getParameter("clientId"));
			if(clientId == 0) cacheMovement.setClient(null);
			else{
				Client client = new Client();
				client.setId(clientId);
				cacheMovement.setClient(client);
			}
			String type = invType == 0 ? "فورى" : "آجل";
			cacheMovement.setDate(returnSalesInvoiceHeader.getDate());
			cacheMovement.setDescription("فاتورة مرتجع بيع - " + type);
			if(invType == 0)
				cacheMovement.setAmount(returnSalesInvoiceHeader.getTotal() * -1);
			else cacheMovement.setAmount(returnSalesInvoiceHeader.getTotal());
			cacheMovement.setInventory(user.getInventory());
			cacheMovement.setRefNumber(returnSalesInvoiceHeader.getNumber());
			cacheMovement.setSupplier(null);
			cacheMovement.setType(3);
			cacheMovement.setUser(user);
			EntityService.addObject(cacheMovement);
			InvoicesCounterUtil.incrementReturnSalesInvoiceCounter();
			
			String[] itemIds = request.getParameter("itemIds").replaceAll("\"", "").split(",");
			String[] itemQty = request.getParameter("itemQty").replaceAll("\"", "").split(",");
			String[] itemPrice = request.getParameter("itemPrice").replaceAll("\"", "").split(",");
			String[] itemTotal = request.getParameter("itemTotal").replaceAll("\"", "").split(",");
			String[] unitIds = request.getParameter("unitIds").replaceAll("\"", "").split(",");
			for(int i = 0; i < itemIds.length; i++) {
				ReturnSalesInvoiceDetails returnSalesInvoiceDetails = new ReturnSalesInvoiceDetails();
				returnSalesInvoiceDetails.setReturnSalesInvoiceHeader(returnSalesInvoiceHeader);
				Item item = new Item();
				item.setId(Integer.parseInt(itemIds[i]));
				returnSalesInvoiceDetails.setItem(item);
				returnSalesInvoiceDetails.setQty(Integer.parseInt(itemQty[i]));
				returnSalesInvoiceDetails.setPrice(Double.parseDouble(itemPrice[i]));
				returnSalesInvoiceDetails.setTotal(Double.parseDouble(itemTotal[i]));
				Unit unit = new Unit();
				unit.setId(Integer.parseInt(unitIds[i]));
				returnSalesInvoiceDetails.setUnit(unit);
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
			returnPurchaseInvoiceHeader.setNumber(InvoicesCounterUtil.getReturnSPurchaseInvoiceCounter());
			returnPurchaseInvoiceHeader.setTotal(Double.parseDouble(request.getParameter("total")));
			returnPurchaseInvoiceHeader.setDate(new Date());
			User user = (User) request.getSession().getAttribute("user");
			returnPurchaseInvoiceHeader.setUser(user);
			int cacheId = Integer.parseInt(request.getParameter("cache"));
			Cache cache = (Cache) EntityService.getObject(Cache.class, cacheId);
			cache.setQty(cache.getQty() + returnPurchaseInvoiceHeader.getTotal());
			EntityService.updateObject(cache);
			EntityService.addObject(returnPurchaseInvoiceHeader);
			//add new cache movement from the return sales invoice
			CacheMovement cacheMovement = new CacheMovement();
			cacheMovement.setAmount(returnPurchaseInvoiceHeader.getTotal());
			cacheMovement.setCache(cache);
			cacheMovement.setClient(null);
			cacheMovement.setDate(returnPurchaseInvoiceHeader.getDate());
			cacheMovement.setDescription("فاتورة مرتجع شراء");
			cacheMovement.setInventory(user.getInventory());
			cacheMovement.setRefNumber(returnPurchaseInvoiceHeader.getNumber());
			int supplierId = Integer.parseInt(request.getParameter("supplierId"));
			Supplier supplier = new Supplier();
			supplier.setId(supplierId);
			cacheMovement.setSupplier(supplier);
			cacheMovement.setType(5);
			cacheMovement.setUser(user);
			EntityService.addObject(cacheMovement);
			InvoicesCounterUtil.incrementReturnPurechaseInvoiceCounter();
			
			String[] itemIds = request.getParameter("itemIds").replaceAll("\"", "").split(",");
			String[] itemQty = request.getParameter("itemQty").replaceAll("\"", "").split(",");
			String[] itemPrice = request.getParameter("itemPrice").replaceAll("\"", "").split(",");
			String[] itemTotal = request.getParameter("itemTotal").replaceAll("\"", "").split(",");
			String[] unitIds = request.getParameter("unitIds").replaceAll("\"", "").split(",");
			for(int i = 0; i < itemIds.length; i++) {
				ReturnPurchaseInvoiceDetails returnPurchaseInvoiceDetails = new ReturnPurchaseInvoiceDetails();
				returnPurchaseInvoiceDetails.setReturnPurchaseInvoiceHeader(returnPurchaseInvoiceHeader);
				Item item = new Item();
				item.setId(Integer.parseInt(itemIds[i]));
				returnPurchaseInvoiceDetails.setItem(item);
				returnPurchaseInvoiceDetails.setQty(Integer.parseInt(itemQty[i]));
				returnPurchaseInvoiceDetails.setPrice(Double.parseDouble(itemPrice[i]));
				returnPurchaseInvoiceDetails.setTotal(Double.parseDouble(itemTotal[i]));
				Unit unit = new Unit();
				unit.setId(Integer.parseInt(unitIds[i]));
				returnPurchaseInvoiceDetails.setUnit(unit);
				EntityService.addObject(returnPurchaseInvoiceDetails);
			}
			response.getWriter().print("saved!");
		}
	}

}
