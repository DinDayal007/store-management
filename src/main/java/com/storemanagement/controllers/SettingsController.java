package com.storemanagement.controllers;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Facility;
import com.storemanagement.entities.Page;
import com.storemanagement.entities.Privilege;
import com.storemanagement.entities.Role;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.RoleService;
@WebServlet("/settings")
public class SettingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
		if(!privileges.get(20).isView()) response.sendRedirect("error");
		else{
			Facility facility = (Facility) EntityService.getObject(Facility.class, 1);
			List<Role> roles = EntityService.getAllObjects(Role.class);
			List<Page> pages = EntityService.getAllObjects(Page.class);
			request.setAttribute("facility", facility);
			request.setAttribute("roles", roles);
			request.setAttribute("pages", pages);
			request.setAttribute("title", "لوحة التحكم");
			request.getRequestDispatcher("settings/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("1"))
			saveRole(request, response);
		else if(request.getParameter("action").equals("2"))
			getRole(request, response);
		else if(request.getParameter("action").equals("delete")){
			delete(request, response);
			response.sendRedirect("settings");
		}
	}
	//save the new added role
	protected void saveRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("roleName").equals("")){
			Role role = new Role();
			role.setName(request.getParameter("roleName"));
			int roleId = RoleService.addObject(role);
			role.setId(roleId);
			String[] pages = request.getParameter("pages").replaceAll("\"", "").split(",");
			String[] viewArr = request.getParameter("viewArr").replaceAll("\"", "").split(",");
			String[] addArr = request.getParameter("addArr").replaceAll("\"", "").split(",");
			String[] editArr = request.getParameter("editArr").replaceAll("\"", "").split(",");
			String[] deleteArr = request.getParameter("deleteArr").replaceAll("\"", "").split(",");
			for(int i = 0; i < pages.length; i++){
				Page page = new Page();
				page.setId(Integer.parseInt(pages[i]));
				Privilege privilege = new Privilege();
				privilege.setRole(role);
				privilege.setPage(page);
				privilege.setView(viewArr[i].equals("1") ? true : false);
				privilege.setInsert(addArr[i].equals("1") ? true : false);
				privilege.setUpdate(editArr[i].equals("1") ? true : false);
				privilege.setDelete(deleteArr[i].equals("1") ? true : false);
				RoleService.addObject(privilege);
			}
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("تم الحفظ بنجاح");
		}
	}
	//get role from its id
	protected void getRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("roleId").equals("")){
			int roleId = Integer.parseInt(request.getParameter("roleId"));
			Role role = (Role) RoleService.getObject(Role.class, roleId);
			StringBuilder out = new StringBuilder("");
			out.append("<label>" + role.getName() + "</label>"
                + "<div class=\"table-responsive\">"
                   + "<table class=\"table table-striped table-bordered table-hover\" >"
                       +" <thead><tr class=\"text-center\">"
                           + "<th>اسم الصفحة</th>"
                           + "<th>مشاهدة</th>"
                           + "<th>إضافة</th>"
                           + "<th>تعديل</th>"
                           + "<th>حذف</th></tr></thead><tbody>");
			List<Privilege> privileges = RoleService.getPrivileges(role);
			for(Privilege privilege : privileges){
				out.append("<tr><td>" + privilege.getPage().getName() + "</td>");
				out.append("<td>" + (privilege.isView() ? "<i class=\"fa fa-check-square text-success\"></i></td>" : "</td>"));
				out.append("<td>" + (privilege.isInsert() ? "<i class=\"fa fa-check-square text-success\"></i></td>" : "</td>"));
				out.append("<td>" + (privilege.isUpdate() ? "<i class=\"fa fa-check-square text-success\"></i></td>" : "</td>"));
				out.append("<td>" + (privilege.isDelete() ? "<i class=\"fa fa-check-square text-success\"></i></td>" : "</td>"));
				out.append("</tr>");
			}
			out.append("</tbody></table></div>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
		}
	}
	//delete a role from its id
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("id").equals("")){
			Role role = new Role();
			role.setId(Integer.parseInt(request.getParameter("id")));
			RoleService.deletePrivileges(role);
			RoleService.removeObject(role);
		}
	}
}
