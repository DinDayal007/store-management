package com.storemanagement.controllers;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Branch;
import com.storemanagement.entities.Inventory;
import com.storemanagement.services.EntityService;
@WebServlet("/inventories")
public class InventoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Inventory> inventories = EntityService.getAllObjects(Inventory.class);
		request.setAttribute("inventories", inventories);
		request.getRequestDispatcher("inventories/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("add"))
			add(request, response);
		else if(request.getParameter("action").equals("edit"))
			edit(request, response);
		else if(request.getParameter("action").equals("delete"))
			delete(request, response);
		response.sendRedirect("inventories");
	}
	//add new inventory
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("inventory_name").equals("")) {
			String name = request.getParameter("inventory_name");
			String description = request.getParameter("inventory_description");
			Branch branch = new Branch();
			branch.setId(Integer.parseInt(request.getParameter("inventory_branch")));
			Inventory inventory = new Inventory();
			inventory.setName(name);
			inventory.setDescription(description);
			inventory.setBranch(branch);
			EntityService.addObject(inventory);
		}
	}
	//edit existing inventory
	protected void edit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("inventory_name").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("inventory_name");
			String description = request.getParameter("inventory_description");
			Branch branch = new Branch();
			branch.setId(Integer.parseInt(request.getParameter("inventory_branch")));
			Inventory inventory = new Inventory();
			inventory.setId(id);
			inventory.setName(name);
			inventory.setDescription(description);
			inventory.setBranch(branch);
			EntityService.updateObject(inventory);
		}
	}
	//delete existing inventory
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("id").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Inventory inventory = new Inventory();
			inventory.setId(id);
			EntityService.removeObject(inventory);
		}
	}
}
