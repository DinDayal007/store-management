package com.storemanagement.controllers;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Unit;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
@WebServlet("/units")
public class UnitsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Unit> units = EntityService.getAllObjects(Unit.class);
		request.setAttribute("units", units);
		request.getRequestDispatcher("units/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("add"))
			add(request, response);
		else if(request.getParameter("action").equals("edit"))
			edit(request, response);
		else if(request.getParameter("action").equals("delete"))
			delete(request, response);
		response.sendRedirect("units");
	}
	//add new unit
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("unit_name").equals("")) {
			User createdBy = (User) request.getSession().getAttribute("user");
			String name = request.getParameter("unit_name");
			String description = request.getParameter("unit_description");
			Unit unit = new Unit();
			unit.setName(name);
			unit.setDescription(description);
			unit.setQty(Integer.parseInt(request.getParameter("unit_qty")));
			unit.setCreatedDate(new Date());
			unit.setLastUpdatedDate(new Date());
			unit.setCreatedBy(createdBy);
			EntityService.addObject(unit);
		}
	}
	//edit existing unit
	protected void edit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("unit_name").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("unit_name");
			String description = request.getParameter("unit_description");
			Unit unit = new Unit();
			unit.setId(id);
			unit.setName(name);
			unit.setDescription(description);
			unit.setQty(Integer.parseInt(request.getParameter("unit_qty")));
			unit.setLastUpdatedDate(new Date());
			EntityService.updateObject(unit);
		}
	}
	//delete existing unit
	protected void delete(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("id").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Unit unit = new Unit();
			unit.setId(id);
			EntityService.removeObject(unit);
		}
	}
}
