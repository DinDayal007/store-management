package com.storemanagement.controllers;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Branch;
import com.storemanagement.entities.Privilege;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
@WebServlet("/branches")
public class BranchesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
		if(!privileges.get(17).isView()) response.sendRedirect("error");
		else{
			List<Branch> branches = EntityService.getAllObjects(Branch.class);
			request.setAttribute("branches", branches);
			request.setAttribute("title", "الفروع");
			request.getRequestDispatcher("branches/index.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("add"))
			add(request, response);
		else if(request.getParameter("action").equals("edit"))
			edit(request, response);
		else if(request.getParameter("action").equals("delete"))
			delete(request, response);
		response.sendRedirect("branches");
	}
	//add new branch
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("branch_name").equals("")) {
			User createdBy = (User) request.getSession().getAttribute("user");
			Branch branch = new Branch();
			branch.setName(request.getParameter("branch_name"));
			branch.setAddress(request.getParameter("branch_address"));
			branch.setDescription(request.getParameter("branch_description"));
			branch.setCreatedDate(new Date());
			branch.setLastUpdatedDate(new Date());
			branch.setCreatedBy(createdBy);
			branch.setLastUpdatedBy(createdBy);
			EntityService.addObject(branch);
		}
	}
	//edit existing branch
	protected void edit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("branch_name").equals("")) {
			User lastUpdatedBy = (User) request.getSession().getAttribute("user");
			int id = Integer.parseInt(request.getParameter("id"));
			Branch branch = new Branch();
			branch.setId(id);
			branch.setName(request.getParameter("branch_name"));
			branch.setAddress(request.getParameter("branch_address"));
			branch.setDescription(request.getParameter("branch_description"));
			branch.setLastUpdatedDate(new Date());
			branch.setLastUpdatedBy(lastUpdatedBy);
			EntityService.updateObject(branch);
		}
	}
	//remove existing cache
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("id").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Branch branch = new Branch();
			branch.setId(id);
			EntityService.removeObject(branch);
		}
	}
}
