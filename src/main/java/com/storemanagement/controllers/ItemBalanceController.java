package com.storemanagement.controllers;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.MainGroup;
import com.storemanagement.entities.Privilege;
import com.storemanagement.services.EntityService;

public class ItemBalanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
		if(!privileges.get(6).isView()) response.sendRedirect("error");
		else{
			List<MainGroup> mainGroups = EntityService.getAllObjects(MainGroup.class);
			request.setAttribute("mainGroups", mainGroups);
			request.setAttribute("title", "تقارير الأصناف");
			request.getRequestDispatcher("item-balance/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
