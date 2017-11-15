package com.storemanagement.controllers;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Supplier;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
@WebServlet("/suppliers")
public class SuppliersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Supplier> suppliers  = EntityService.getAllObjects(Supplier.class);
		request.setAttribute("suppliers", suppliers);
		request.setAttribute("title", "الموردين");
		request.getRequestDispatcher("suppliers/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("add"))
			add(request, response);
		else if(request.getParameter("action").equals("edit"))
			edit(request, response);
		else if(request.getParameter("action").equals("delete"))
			delete(request, response);
		response.sendRedirect("suppliers");
	}
	//add new supplier
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("supplier_name").equals("")) {
			User createdBy = (User) request.getSession().getAttribute("user");
			Supplier supplier = new Supplier();
			supplier.setName(request.getParameter("supplier_name"));
			supplier.setCode(request.getParameter("supplier_code"));
			supplier.setDescription(request.getParameter("supplier_description"));
			supplier.setEmail(request.getParameter("supplier_email"));
			supplier.setPhone(request.getParameter("supplier_phone"));
			supplier.setMobile1(request.getParameter("supplier_mobile1"));
			supplier.setMobile2(request.getParameter("supplier_mobile2"));
			supplier.setCreatedDate(new Date());
			supplier.setLastUpdatedDate(new Date());
			supplier.setCreatedBy(createdBy);
			EntityService.addObject(supplier);
		}
	}
	//edit existing supplier
	protected void edit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("supplier_name").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Supplier supplier = new Supplier();
			supplier.setId(id);
			supplier.setName(request.getParameter("supplier_name"));
			supplier.setCode(request.getParameter("supplier_code"));
			supplier.setDescription(request.getParameter("supplier_description"));
			supplier.setEmail(request.getParameter("supplier_email"));
			supplier.setPhone(request.getParameter("supplier_phone"));
			supplier.setMobile1(request.getParameter("supplier_mobile1"));
			supplier.setMobile2(request.getParameter("supplier_mobile2"));
			supplier.setLastUpdatedDate(new Date());
			EntityService.updateObject(supplier);
		}
	}
	//remove existing supplier
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("id").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Supplier supplier = new Supplier();
			supplier.setId(id);
			EntityService.removeObject(supplier);
		}
	}
}
