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
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.entities.Supplier;
import com.storemanagement.services.CachesMovementService;
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
		else if(request.getParameter("r").equals("c"))
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
