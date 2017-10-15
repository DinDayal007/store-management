package com.storemanagement.controllers;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.storemanagement.entities.Branch;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Role;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.InventoryService;
@WebServlet("/users")
public class UsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user.getRole().getId() == 1) {
			List<User> users = EntityService.getAllObjects(User.class);
			request.setAttribute("users", users);
			request.getRequestDispatcher("users/index.jsp").forward(request, response);
		}else request.getRequestDispatcher("users/authentication-error.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("1"))
			getInventoriesFromBranch(request, response);
		else {
			if(request.getParameter("action").equals("add"))
				add(request, response);
			response.sendRedirect("users");
		}
	}
	//get inventories from branch
	protected void getInventoriesFromBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("branchId").equals("")) {
			int id = Integer.parseInt(request.getParameter("branchId"));
			Branch branch = new Branch();
			branch.setId(id);
			List<Inventory> inventories = InventoryService.getInventoriesFromBranch(branch);
			StringBuilder out = new StringBuilder("");
			out.append("<option value=\"\">اختر مخزن المستخدم</option>");
			if(inventories.size() > 0) {
				for(Inventory inventory : inventories) {
					out.append("<option value=\"" + inventory.getId() + "\">" + inventory.getName() + "</option>");
				}
			}else
				out.append("<option value=\"\" disabled>لا يوجد مخازن لهذا الفرع</option>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
		}
	}
	//add new user
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("user_role").equals("") && !request.getParameter("user_name").equals("") && !request.getParameter("user_password").equals("")) {
			Role role = new Role();
			role.setId(Integer.parseInt(request.getParameter("user_role")));
			Cache cache = new Cache();
			cache.setId(Integer.parseInt(request.getParameter("user_cache")));
			Inventory inventory = new Inventory();
			inventory.setId(Integer.parseInt(request.getParameter("user_inventory")));
			User user = new User();
			user.setName(request.getParameter("user_name"));
			user.setPassword(request.getParameter("user_password"));
			user.setRole(role);
			user.setCache(cache);
			user.setInventory(inventory);
			EntityService.addObject(user);
		}
	}
}
