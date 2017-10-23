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
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
@WebServlet("/cache-movements")
public class CacheMovementController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<CacheMovement> cacheMovements = EntityService.getAllObjects(CacheMovement.class);
		request.setAttribute("cacheMovements", cacheMovements);
		request.getRequestDispatcher("cache-movements/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("add"))
			add(request, response);
		response.sendRedirect("cache-movements");
	}
	
	//add new cache-movement
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("movement_type").equals("") && !request.getParameter("movement_amount").equals("")) {
			CacheMovement cacheMovement = new CacheMovement();
			cacheMovement.setType(Integer.parseInt(request.getParameter("movement_type")));
			cacheMovement.setAmount(Integer.parseInt(request.getParameter("movement_amount")));
			cacheMovement.setDescription(request.getParameter("movement_description"));
			cacheMovement.setDate(new Date());
			User user = (User) request.getSession().getAttribute("user");
			cacheMovement.setUser(user);
			int cacheId = user.getCache().getId();
			Cache cache = (Cache) EntityService.getObject(Cache.class, cacheId);			
			cacheMovement.setCache(cache);
			Inventory inventory = user.getInventory();
			cacheMovement.setInventory(inventory);
			if(cacheMovement.getType() == 0)
				cache.setQty(cache.getQty() - cacheMovement.getAmount());
			else if(cacheMovement.getType() == 1) cache.setQty(cache.getQty() + cacheMovement.getAmount());
			EntityService.updateObject(cache);
			EntityService.addObject(cacheMovement);
		}
	}
}
