package com.storemanagement.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Privilege;
import com.storemanagement.services.EntityService;
@WebServlet("/debits")
public class ClientsDebitsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
		if(!privileges.get(15).isView()) response.sendRedirect("error");
		else{
			List<Client> clients = EntityService.getAllObjects(Client.class);
			request.setAttribute("clients", clients);
			request.setAttribute("title", "مديونيات العملاء");
			request.getRequestDispatcher("debits/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
