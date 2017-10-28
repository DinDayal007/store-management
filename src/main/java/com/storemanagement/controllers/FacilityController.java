package com.storemanagement.controllers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Facility;
import com.storemanagement.services.EntityService;
@WebServlet("/facility")
public class FacilityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Facility facility = (Facility) EntityService.getObject(Facility.class, 1);
		request.setAttribute("facility", facility);
		request.getRequestDispatcher("facility/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		save(request, response);
		response.sendRedirect("facility");
	}
	
	//save facility data
	protected void save(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("facilityName").equals("") && !request.getParameter("facilityMobile1").equals("")){
			Facility facility = new Facility();
			facility.setId(1);
			facility.setName(request.getParameter("facilityName"));
			facility.setGovernorate(request.getParameter("facilityGovernorate"));
			facility.setCity(request.getParameter("facilityCity"));
			facility.setAddress(request.getParameter("facilityAddress"));
			facility.setPhone(request.getParameter("facilityPhone"));
			facility.setMobile1(request.getParameter("facilityMobile1"));
			facility.setMobile2(request.getParameter("facilityMobile2"));
			facility.setMoreInfo(request.getParameter("facilityMoreInfo"));
			EntityService.updateObject(facility);
		}
	}
}
