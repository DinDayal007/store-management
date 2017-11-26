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
import com.storemanagement.entities.Privilege;
import com.storemanagement.entities.SubGroup;
import com.storemanagement.entities.User;
import com.storemanagement.services.GroupService;
@WebServlet("/subgroups")
public class SubGroupsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
		if(!privileges.get(8).isView()) response.sendRedirect("error");
		else{
			List<SubGroup> subGroups = GroupService.getAllObjects(SubGroup.class);
			request.setAttribute("subGroups", subGroups);
			List<MainGroup> mainGroups = GroupService.getAllObjects(MainGroup.class);
			request.setAttribute("mainGroups", mainGroups);
			request.setAttribute("title", "مجموعات الأصناف الفرعية");
			request.getRequestDispatcher("subgroups/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("1"))
			getSubGroups(request, response);
		else {
			if(request.getParameter("action").equals("add"))
				add(request, response);
			else if(request.getParameter("action").equals("edit"))
				edit(request, response);
			else if(request.getParameter("action").equals("delete"))
				delete(request, response);
			response.sendRedirect("subgroups");
		}
	}
	// add new subGroup
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("subGroup_name").equals("") && !request.getParameter("mainGroups").equals("")) {
			String name = request.getParameter("subGroup_name");
			int mainGroupId = Integer.parseInt(request.getParameter("mainGroups"));
			MainGroup mainGroup = new MainGroup();
			mainGroup.setId(mainGroupId);
			SubGroup subGroup = new SubGroup();
			subGroup.setName(name);
			subGroup.setMainGroup(mainGroup);
			User createdBy = (User) request.getSession().getAttribute("user");
			subGroup.setCreatedDate(new Date());
			subGroup.setLastUpdatedDate(new Date());
			subGroup.setCreatedBy(createdBy);
			subGroup.setLastUpdatedBy(createdBy);
			GroupService.addObject(subGroup);
		}
	}
	//edit existing subGroup
	protected void edit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("subGroup_name").equals("") && !request.getParameter("mainGroups").equals("")) {
			User lastUpdatedBy = (User) request.getSession().getAttribute("user");
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("subGroup_name");
			int mainGroupId = Integer.parseInt(request.getParameter("mainGroups"));
			MainGroup mainGroup = new MainGroup();
			mainGroup.setId(mainGroupId);
			SubGroup subGroup = new SubGroup();
			subGroup.setId(id);
			subGroup.setName(name);
			subGroup.setMainGroup(mainGroup);
			subGroup.setLastUpdatedDate(new Date());
			subGroup.setLastUpdatedBy(lastUpdatedBy);
			GroupService.updateObject(subGroup);
		}
	}
	//delete existing subGroup
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("id").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			SubGroup subGroup = new SubGroup();
			subGroup.setId(id);
			GroupService.removeObject(subGroup);
		}
	}
	//get subGroups from mainGroup
	protected void getSubGroups(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("mainGroup_id").equals("")) {
			int id = Integer.parseInt(request.getParameter("mainGroup_id"));
			MainGroup mainGroup = new MainGroup();
			mainGroup.setId(id);
			List<SubGroup> subGroups = id > 0 ? GroupService.getSubGroupsFromMainGroup(mainGroup) : GroupService.getAllObjects(SubGroup.class);
			StringBuilder out = new StringBuilder("");
			out.append("<table class=\"table table-striped table-bordered table-hover\">" + 
					"  		<thead>" +
					"		 <tr><th>#</th><th>اسم المجموعة الرئيسية</th>" + 
					"        <th>تعديل</th>" + 
					"        <th>حذف</th></tr>" + 
					"        </thead><tbody>");
			if(subGroups.size() == 0) 
				out.append("<tr><td colspan=\"4\"><p class=\"lead text-center text-danger\">عفوا لا يوجد مجموعات فرعية لهذه المجموعة الرئيسية</p></td></tr>");
			else {
				int i = 0;
				for(SubGroup subGroup : subGroups) {
					i++;
					out.append("<tr><td>" + i + "</td>");
					out.append("<td>" + subGroup.getName() + "</td>");
					out.append("<td><a href=\"subgroups/edit.jsp?id=" + subGroup.getId() + "\"><button class=\"btn btn-success\"><i class=\"fa fa-edit\"></i></button></a></td>");
					out.append("<td><a href=\"subgroups/delete.jsp?id=" + subGroup.getId() + "\"><button class=\"btn btn-danger\"><i class=\"fa fa-close\"></i></button></a></td></tr>");
				}
			}
			out.append("</tbody></table>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
		}
	}
}
