package com.storemanagement.controllers;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovement;
import com.storemanagement.entities.Facility;
import com.storemanagement.entities.Page;
import com.storemanagement.entities.Privilege;
import com.storemanagement.entities.Role;
import com.storemanagement.entities.TransferDetails;
import com.storemanagement.entities.TransferHeader;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.RoleService;
import com.storemanagement.services.TransferService;
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
			List<TransferHeader> transferHeaders = EntityService.getObjectsWithEqRestriction(TransferHeader.class, "status", false);
			request.setAttribute("facility", facility);
			request.setAttribute("roles", roles);
			request.setAttribute("pages", pages);
			request.setAttribute("title", "لوحة التحكم");
			request.setAttribute("transferHeaders", transferHeaders);
			request.getRequestDispatcher("settings/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("1"))
			saveRole(request, response);
		else if(request.getParameter("action").equals("2"))
			getRole(request, response);
		else if(request.getParameter("action").equals("3"))
			getTransferDetails(request, response);
		else if(request.getParameter("action").equals("4"))
			confirmTransfer(request, response);
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
	
	//get role from its id
	protected void getTransferDetails(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("transferId").equals("")){
			int transferId = Integer.parseInt(request.getParameter("transferId"));
			List<TransferDetails> transferDetails = EntityService.getObjectsWithEqRestriction(TransferDetails.class, "transferHeader.id", transferId);
			StringBuilder out = new StringBuilder("");
			out.append("<div class=\"table-responsive\">"
	                   + "<table class=\"table table-striped table-bordered table-hover\" >"
	                       +" <thead><tr class=\"text-center\">"
	                           + "<th>الكود</th>"
	                           + "<th>الصنف</th>"
	                           + "<th>الكمية</th>"
	                           + "<th>السعر</th>"
	                           + "<th>الإجمالى</th></tr></thead><tbody>");
			for(TransferDetails detail : transferDetails){
				out.append("<tr><td>" + detail.getItem().getCode() + "</td>");
				out.append("<td>" + detail.getItem().getName() + "</td>");
				out.append("<td>" + detail.getQty() + "</td>");
				out.append("<td>" + detail.getPrice() + " EGP</td>");
				out.append("<td>" + detail.getTotal() + " EGP</td></tr>");
			}
			out.append("</tbody></table></div>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
		}
	}
	
	//confirm transfer
	protected void confirmTransfer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("transferId").equals("")){
			int transferId = Integer.parseInt(request.getParameter("transferId"));
			User user = (User) request.getSession().getAttribute("user");
			TransferHeader header = (TransferHeader) TransferService.getObject(TransferHeader.class, transferId);
			
			int updatedId = TransferService.confirmTransfer(transferId, user);
			
			Cache cacheFrom = (Cache) EntityService.getObject(Cache.class, header.getCacheFrom().getId());
			cacheFrom.setQty(cacheFrom.getQty() - header.getTotalPrice());
			EntityService.updateObject(cacheFrom);
			
			Cache cacheTo = (Cache) EntityService.getObject(Cache.class, header.getCacheTo().getId());
			cacheTo.setQty(cacheTo.getQty() + header.getTotalPrice());
			EntityService.updateObject(cacheTo);
			
			CacheMovement cacheMovementFrom = new CacheMovement();
			cacheMovementFrom.setUser(header.getCreatedBy());
			cacheMovementFrom.setInventory(header.getInventoryFrom());
			cacheMovementFrom.setCache(cacheFrom);
			cacheMovementFrom.setClient(null);
			cacheMovementFrom.setSupplier(null);
			cacheMovementFrom.setDate(header.getCreatedDate());
			cacheMovementFrom.setType(0);
			cacheMovementFrom.setDescription("سحب أصناف");
			cacheMovementFrom.setAmount(header.getTotalPrice() * -1);
			cacheMovementFrom.setRefNumber(0);
			EntityService.addObject(cacheMovementFrom);
			
			CacheMovement cacheMovementTo = new CacheMovement();
			cacheMovementTo.setUser(header.getCreatedBy());
			cacheMovementTo.setInventory(header.getInventoryTo());
			cacheMovementTo.setCache(cacheTo);
			cacheMovementTo.setClient(null);
			cacheMovementTo.setSupplier(null);
			cacheMovementTo.setDate(header.getCreatedDate());
			cacheMovementTo.setType(1);
			cacheMovementTo.setDescription("إيداع أصناف");
			cacheMovementTo.setAmount(header.getTotalPrice());
			cacheMovementTo.setRefNumber(0);
			EntityService.addObject(cacheMovementTo);
			
			response.getWriter().print(updatedId);
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
