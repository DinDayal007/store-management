package com.storemanagement.controllers;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Privilege;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
@WebServlet("/clients")
public class ClientsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
		if(!privileges.get(11).isView()) response.sendRedirect("error");
		else{
			List<Client> clients = EntityService.getAllObjects(Client.class);
			request.setAttribute("clients", clients);
			request.setAttribute("title", "العملاء");
			request.getRequestDispatcher("clients/index.jsp").forward(request, response);
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
		response.sendRedirect("clients");
	}
	//add new client
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("client_name").equals("")) {
			User createdBy = (User) request.getSession().getAttribute("user");
			Client client = new Client();
			client.setName(request.getParameter("client_name"));
			client.setCode(request.getParameter("client_code"));
			client.setDescription(request.getParameter("client_description"));
			client.setEmail(request.getParameter("client_email"));
			client.setPhone(request.getParameter("client_phone"));
			client.setMobile1(request.getParameter("client_mobile1"));
			client.setMobile2(request.getParameter("client_mobile2"));
			client.setCreatedDate(new Date());
			client.setLastUpdatedDate(new Date());
			client.setCreatedBy(createdBy);
			client.setLastUpdatedBy(createdBy);
			EntityService.addObject(client);
		}
	}
	//edit existing client
	protected void edit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("client_name").equals("")) {
			User lastUpdatedBy = (User) request.getSession().getAttribute("user");
			int id = Integer.parseInt(request.getParameter("id"));
			Client client = new Client();
			client.setId(id);
			client.setName(request.getParameter("client_name"));
			client.setCode(request.getParameter("client_code"));
			client.setDescription(request.getParameter("client_description"));
			client.setEmail(request.getParameter("client_email"));
			client.setPhone(request.getParameter("client_phone"));
			client.setMobile1(request.getParameter("client_mobile1"));
			client.setMobile2(request.getParameter("client_mobile2"));
			client.setLastUpdatedDate(new Date());
			client.setLastUpdatedBy(lastUpdatedBy);
			EntityService.updateObject(client);
		}
	}
	//remove existing client
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("id").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Client client = new Client();
			client.setId(id);
			EntityService.removeObject(client);
		}
	}
}
