package com.storemanagement.controllers;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Branch;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Supplier;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.InventoryService;

public class CacheSumController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Branch> branchs = EntityService.getAllObjects(Branch.class);
		List<Cache> caches = EntityService.getAllObjects(Cache.class);
		List<Client> clients = EntityService.getAllObjects(Client.class);
		List<Supplier> suppliers = EntityService.getAllObjects(Supplier.class);
		request.setAttribute("branchs", branchs);
		request.setAttribute("caches", caches);
		request.setAttribute("clients", clients);
		request.setAttribute("suppliers", suppliers);
		request.getRequestDispatcher("cache-sum/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("1"))
			getInventoriesFromBranch(request, response);
	}
	//get inventories from branch
	protected void getInventoriesFromBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("branch").equals("")) {
			int id = Integer.parseInt(request.getParameter("branch"));
			Branch branch = new Branch();
			branch.setId(id);
			List<Inventory> inventories = InventoryService.getInventoriesFromBranch(branch);
			StringBuilder out = new StringBuilder("");
			out.append("<option value=\"\">اختر المخزن</option>");
			if(inventories.size() > 0) {
				for(Inventory inventory : inventories) {
					out.append("<option value=\"" + inventory.getId() + "\">" + inventory.getName() + "</option>");
				}
			}else
				out.append("<option value=\"\">لا يوجد مخازن لهذا الفرع</option>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
		}
	}
}
