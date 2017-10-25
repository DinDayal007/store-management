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
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.ItemMovement;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.services.CachesMovementService;
import com.storemanagement.services.EntityService;
import com.storemanagement.utils.ReportsUtil;
import net.sf.jasperreports.engine.JRException;
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
		else if(request.getParameter("r").equals("cm")){
			Inventory inventory = new Inventory();
			if(request.getParameter("inventory").equals(""))
				inventory = null;
			else inventory.setId(Integer.parseInt(request.getParameter("inventory")));
			Cache cache = new Cache();
			if(request.getParameter("cache").equals(""))
				cache = null;
			else cache.setId(Integer.parseInt(request.getParameter("cache")));
			try{
				Date from = null, to = null;
				Integer type = null;
				if(!request.getParameter("type").equals(""))
					type = Integer.parseInt(request.getParameter("type"));
				if(!request.getParameter("from").equals(""))
					from = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("from"));
				if(!request.getParameter("to").equals(""))
					to = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("to"));
			reportsUtil.showCachsMovementReport(request, response, CachesMovementService.getCacheMovements(inventory, cache, from, to, type));
			} catch (ParseException e) {
			e.printStackTrace();
			}
		}else if(request.getParameter("r").equals("si")){
			//sales invoice report
			SalesInvoiceHeader header = (SalesInvoiceHeader) EntityService.getObject(SalesInvoiceHeader.class, 5);
			try {
				reportsUtil.showSalesInvoiceReport(request, response, header);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}else if(request.getParameter("r").equals("pi")){
			//purchase invoice report
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
