package com.storemanagement.controllers;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.MainGroup;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
@WebServlet("/groups")
public class GroupsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<MainGroup> mainGroups = EntityService.getAllObjects(MainGroup.class);
		request.setAttribute("mainGroups", mainGroups);
		request.getRequestDispatcher("groups/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("add"))
			add(request, response);
		else if(request.getParameter("action").equals("edit"))
			edit(request, response);
		else if(request.getParameter("action").equals("delete"))
			delete(request, response);
		response.sendRedirect("groups");
	}
	// add new group
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("group_name").equals("")) {
			String name = request.getParameter("group_name");
			User createdBy = (User) request.getSession().getAttribute("user");
			MainGroup mainGroup = new MainGroup();
			mainGroup.setName(name);
			mainGroup.setCreatedDate(new Date());
			mainGroup.setLastUpdatedDate(new Date());
			mainGroup.setCreatedBy(createdBy);
			EntityService.addObject(mainGroup);
		}
	}
	//edit existing group
	protected void edit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("group_name").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("group_name");
			MainGroup mainGroup = new MainGroup();
			mainGroup.setId(id);
			mainGroup.setName(name);
			mainGroup.setLastUpdatedDate(new Date());
			EntityService.updateObject(mainGroup);
		}
	}
	//delete existing group
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("id").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			MainGroup mainGroup = new MainGroup();
			mainGroup.setId(id);
			EntityService.removeObject(mainGroup);
		}
	}
}
