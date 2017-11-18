package com.storemanagement.controllers;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovement;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Supplier;
import com.storemanagement.services.CachesMovementService;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.InvoiceService;
import com.storemanagement.utils.ReportsUtil;
@WebServlet("/reports")
public class ReportsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportsUtil reportsUtil = new ReportsUtil();
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("r").equals("s")){
			Integer userId = null, paymentType = null, cacheId = null, inventoryId = null, clientId = null;
			if(!request.getParameter("user").equals(""))
				userId = Integer.parseInt(request.getParameter("user"));
			if(!request.getParameter("type").equals(""))
				paymentType = Integer.parseInt(request.getParameter("type"));
			if(!request.getParameter("cache").equals(""))
				cacheId = Integer.parseInt(request.getParameter("cache"));
			if(!request.getParameter("inventory").equals(""))
				inventoryId = Integer.parseInt(request.getParameter("inventory"));
			if(!request.getParameter("client").equals(""))
				clientId = Integer.parseInt(request.getParameter("client"));
			Date from = null, to = null;
			try{
				if(!request.getParameter("from").equals(""))
					from = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("from"));
				if(!request.getParameter("to").equals(""))
					to = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("to"));
			}catch (Exception e) {
				e.printStackTrace();
			}
			reportsUtil.showSalesInvoicesReport(request, response, InvoiceService.searchInvoices(userId, paymentType, cacheId, inventoryId, clientId, from, to));
		}else if(request.getParameter("r").equals("p")){
			Integer userId = null, paymentType = null, cacheId = null, inventoryId = null, supplierId = null;
			if(!request.getParameter("user").equals(""))
				userId = Integer.parseInt(request.getParameter("user"));
			if(!request.getParameter("type").equals(""))
				paymentType = Integer.parseInt(request.getParameter("type"));
			if(!request.getParameter("cache").equals(""))
				cacheId = Integer.parseInt(request.getParameter("cache"));
			if(!request.getParameter("inventory").equals(""))
				inventoryId = Integer.parseInt(request.getParameter("inventory"));
			if(!request.getParameter("supplier").equals(""))
				supplierId = Integer.parseInt(request.getParameter("supplier"));
			Date from = null, to = null;
			try{
				if(!request.getParameter("from").equals(""))
					from = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("from"));
				if(!request.getParameter("to").equals(""))
					to = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("to"));
			}catch (Exception e) {
				e.printStackTrace();
			}
			reportsUtil.showPurchaseInvoicesReport(request, response, InvoiceService.searchPurchaseInvoices(userId, paymentType, cacheId, inventoryId, supplierId, from, to));
		}else if(request.getParameter("r").equals("c"))
			reportsUtil.showCachsQtyReport(request, response, EntityService.getAllObjects(Cache.class));
		else if(request.getParameter("r").equals("cm")){
			Inventory inventory = new Inventory();
			if(request.getParameter("inventory").equals(""))
				inventory = null;
			else inventory.setId(Integer.parseInt(request.getParameter("inventory")));
			Cache cache = new Cache();
			if(request.getParameter("cache").equals(""))
				cache = null;
			else cache.setId(Integer.parseInt(request.getParameter("cache")));
			Client client = new Client();
			if(request.getParameter("client").equals(""))
				client = null;
			else client.setId(Integer.parseInt(request.getParameter("client")));
			Supplier supplier = new Supplier();
			if(request.getParameter("supplier").equals(""))
				supplier = null;
			else supplier.setId(Integer.parseInt(request.getParameter("supplier")));
			try{
				Date from = null, to = null;
				Integer type = null;
				if(!request.getParameter("type").equals(""))
					type = Integer.parseInt(request.getParameter("type"));
				if(!request.getParameter("from").equals(""))
					from = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("from"));
				if(!request.getParameter("to").equals(""))
					to = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("to"));
			reportsUtil.showCachsMovementReport(request, response, CachesMovementService.getCacheMovements(inventory, cache, client, supplier, from, to, type), client == null ? 0 : client.getId(), supplier == null ? 0 : supplier.getId());
			} catch (ParseException e) {
			e.printStackTrace();
			}
		}else if(request.getParameter("r").equals("si")){
			//sales invoice report
			int invId = Integer.parseInt(request.getParameter("id"));
			reportsUtil.showSalesInvoiceReport(request, response, invId);
		}else if(request.getParameter("r").equals("pi")){
			//purchase invoice report
			int invId = Integer.parseInt(request.getParameter("id"));
			reportsUtil.showPurchaseInvoiceReport(request, response, invId);
		}else if(request.getParameter("r").equals("cd")){
			Client client = new Client();
			if(request.getParameter("client").equals(""))
				client = null;
			else client.setId(Integer.parseInt(request.getParameter("client")));
			try{
				Date from = null, to = null;
				if(!request.getParameter("from").equals(""))
					from = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("from"));
				if(!request.getParameter("to").equals(""))
					to = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("to"));
				reportsUtil.showClientsDebitReport(request, response, CachesMovementService.getClientsDebits(client, from, to), client == null ? 0 : client.getId());
			}catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(request.getParameter("r").equals("cache")){
			int cacheMovementId = Integer.parseInt(request.getParameter("id"));
			reportsUtil.showSingleCacheMovementReport(request, response, (CacheMovement)EntityService.getObject(CacheMovement.class, cacheMovementId));
		}else if(request.getParameter("r").equals("ib")){
			if(null == request.getParameter("data"))
				reportsUtil.showItemQuantitiesReport(request, response, null);
			else if(request.getParameter("data").equals("min"))
				reportsUtil.showItemQuantitiesReport(request, response, "min");
			else if(request.getParameter("data").equals("max"))
				reportsUtil.showItemQuantitiesReport(request, response, "max");
		}else if(request.getParameter("r").equals("im")){
			int itemId = Integer.parseInt(request.getParameter("item"));
			reportsUtil.showItemMovementReport(request, response, itemId);
		}else if(request.getParameter("r").equals("profit")){
			try{
				Date from = null, to = null;
				if(!request.getParameter("from").equals(""))
					from = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("from"));
				if(!request.getParameter("to").equals(""))
					to = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("to"));
				reportsUtil.showProfitReport(request, response, from, to);
			}catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
