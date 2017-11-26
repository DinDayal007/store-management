package com.storemanagement.controllers;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovement;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Privilege;
import com.storemanagement.entities.Supplier;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
@WebServlet("/cache-movements")
public class CacheMovementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
		if(!privileges.get(13).isView()) response.sendRedirect("error");
		else{
			List<CacheMovement> cacheMovements = EntityService.getAllObjects(CacheMovement.class);
			request.setAttribute("cacheMovements", cacheMovements);
			request.setAttribute("title", "حركات الخزنة");
			request.getRequestDispatcher("cache-movements/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("add"))
			add(request, response);
		//response.sendRedirect("cache-movements");
	}
	
	//add new cache-movement
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("movement_type").equals("") && !request.getParameter("movement_amount").equals("")) {
			CacheMovement cacheMovement = new CacheMovement();
			int type = Integer.parseInt(request.getParameter("movement_type"));
			cacheMovement.setType(type);
			double amount = Double.parseDouble(request.getParameter("movement_amount"));
			cacheMovement.setAmount(type == 0 ? amount * -1 : amount);
			cacheMovement.setDescription(request.getParameter("movement_description"));
			cacheMovement.setDate(new Date());
			cacheMovement.setRefNumber(Long.parseLong(request.getParameter("movement_refNum")));
			Client client = new Client();
			if(request.getParameter("movement_client").equals(""))
				cacheMovement.setClient(null);
			else{
				client.setId(Integer.parseInt(request.getParameter("movement_client")));
				cacheMovement.setClient(client);
			}
			Supplier supplier = new Supplier();
			if(request.getParameter("movement_supplier").equals(""))
				cacheMovement.setSupplier(null);
			else{
				supplier.setId(Integer.parseInt(request.getParameter("movement_supplier")));
				cacheMovement.setSupplier(supplier);
			}
			User user = (User) request.getSession().getAttribute("user");
			cacheMovement.setUser(user);
			int cacheId = user.getCache().getId();
			Cache cache = (Cache) EntityService.getObject(Cache.class, cacheId);			
			cacheMovement.setCache(cache);
			Inventory inventory = user.getInventory();
			cacheMovement.setInventory(inventory);
			cache.setQty(cache.getQty() + cacheMovement.getAmount());
			EntityService.updateObject(cache);
			int cacheMovementId = EntityService.addObject(cacheMovement);
			response.getWriter().print(cacheMovementId);
		}
	}
}
