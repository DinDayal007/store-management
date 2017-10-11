package com.storemanagement.controllers;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Cache;
import com.storemanagement.services.EntityService;
@WebServlet("/caches")
public class CachesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Cache> caches = EntityService.getAllObjects(Cache.class);
		request.setAttribute("caches", caches);
		request.getRequestDispatcher("caches/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("add"))
			add(request, response);
		else if(request.getParameter("action").equals("edit"))
			edit(request, response);
		else if(request.getParameter("action").equals("delete"))
			delete(request, response);
		response.sendRedirect("caches");
	}
	//add new cache
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("cache_name").equals("") && !request.getParameter("cache_qty").equals("")) {
			Cache cache = new Cache();
			cache.setName(request.getParameter("cache_name"));
			cache.setQty(Double.parseDouble(request.getParameter("cache_qty")));
			EntityService.addObject(cache);
		}
	}
	//edit existing cache
	protected void edit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("cache_name").equals("") && !request.getParameter("cache_qty").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Cache cache = new Cache();
			cache.setId(id);
			cache.setName(request.getParameter("cache_name"));
			cache.setQty(Double.parseDouble(request.getParameter("cache_qty")));
			EntityService.updateObject(cache);
		}
	}
	//remove existing cache
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("id").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Cache cache = new Cache();
			cache.setId(id);
			EntityService.removeObject(cache);
		}
	}
}
