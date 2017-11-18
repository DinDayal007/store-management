package com.storemanagement.controllers;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.MainGroup;
import com.storemanagement.entities.SubGroup;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.GroupService;
import com.storemanagement.services.ItemService;
@WebServlet("/items")
public class ItemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<MainGroup> mainGroups = EntityService.getAllObjects(MainGroup.class);
		List<Item> items = EntityService.getAllObjects(Item.class);
		request.setAttribute("mainGroups", mainGroups);
		request.setAttribute("items", items);
		request.setAttribute("title", "الأصناف");
		request.getRequestDispatcher("items/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("1"))
			getSubGroups(request, response);
		else if(request.getParameter("action").equals("2"))
			getItemsFromSubGroup(request, response);
		else {
			if(request.getParameter("action").equals("add"))
				add(request, response);
			else if(request.getParameter("action").equals("edit"))
				edit(request, response);
			else if(request.getParameter("action").equals("delete"))
				delete(request, response);
			response.sendRedirect("items");
		}
	}
	//add new item
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("item_name").equals("") && !request.getParameter("item_code").equals("")) {
			SubGroup subGroup = new SubGroup();
			subGroup.setId(Integer.parseInt(request.getParameter("subGroups")));
			Item item = new Item();
			item.setSubGroup(subGroup);
			item.setCode(request.getParameter("item_code"));
			item.setName(request.getParameter("item_name"));
			item.setPrice(Double.parseDouble(request.getParameter("item_price")));
			item.setHome(request.getParameter("item_home"));
			item.setMinLimit(Integer.parseInt(request.getParameter("item_minLimit")));
			item.setMaxLimit(Integer.parseInt(request.getParameter("item_maxLimit")));
			item.setDescription(request.getParameter("description"));
			try {
				if(!request.getParameter("item_productionDate").equals(""))
					item.setProductionDate((Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("item_productionDate")));
				else item.setProductionDate(null);
				if(!request.getParameter("item_expirationDate").equals(""))
					item.setExpirationDate((Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("item_expirationDate")));
				else item.setExpirationDate(null);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			User createdBy = (User) request.getSession().getAttribute("user");
			item.setCreatedDate(new Date());
			item.setLastUpdatedDate(new Date());
			item.setCreatedBy(createdBy);
			item.setLastUpdatedBy(createdBy);
			EntityService.addObject(item);
		}
	}
	//edit existing item
	protected void edit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("item_name").equals("") && !request.getParameter("item_code").equals("")) {
			User lastUpdatedBy = (User) request.getSession().getAttribute("user");
			int id = Integer.parseInt(request.getParameter("id"));
			SubGroup subGroup = new SubGroup();
			subGroup.setId(Integer.parseInt(request.getParameter("subGroups")));
			Item item = new Item();
			item.setId(id);
			item.setSubGroup(subGroup);
			item.setCode(request.getParameter("item_code"));
			item.setName(request.getParameter("item_name"));
			item.setPrice(Double.parseDouble(request.getParameter("item_price")));
			item.setHome(request.getParameter("item_home"));
			item.setMinLimit(Integer.parseInt(request.getParameter("item_minLimit")));
			item.setMaxLimit(Integer.parseInt(request.getParameter("item_maxLimit")));
			item.setDescription(request.getParameter("description"));
			try {
				if(!request.getParameter("item_productionDate").equals(""))
					item.setProductionDate((Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("item_productionDate")));
				else item.setProductionDate(null);
				if(!request.getParameter("item_expirationDate").equals(""))
					item.setExpirationDate((Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("item_expirationDate")));
				else item.setExpirationDate(null);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			item.setLastUpdatedDate(new Date());
			item.setLastUpdatedBy(lastUpdatedBy);
			EntityService.updateObject(item);
		}
	}
	//delete existing item
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("id").equals("")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Item item = new Item();
			item.setId(id);
			EntityService.removeObject(item);
		}
	}
	//get subGroups from mainGroup
	protected void getSubGroups(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("mainGroup_id").equals("")) {
			int id = Integer.parseInt(request.getParameter("mainGroup_id"));
			MainGroup mainGroup = new MainGroup();
			mainGroup.setId(id);
			List<SubGroup> subGroups = GroupService.getSubGroupsFromMainGroup(mainGroup);
			StringBuilder out = new StringBuilder("");
			out.append("<option value=\"\">اختر المجموعة الفرعية</option>");
			if(subGroups.size() > 0) {
				for(SubGroup subGroup : subGroups) {
					out.append("<option value=\"" + subGroup.getId() + "\">" + subGroup.getName() + "</option>");
				}
			}else 
				out.append("<option value=\"\" disabled>لا توجد مجموعات فرعية لهذه المجموعة الرئيسية</option>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
		}
	}
	//get items from subGroup
	protected void getItemsFromSubGroup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("subGroupId").equals("")) {
			int id = Integer.parseInt(request.getParameter("subGroupId"));
			SubGroup subGroup = new SubGroup();
			subGroup.setId(id);
			List<Item> items = ItemService.getItemsFromSubGroup(subGroup);
			StringBuilder out = new StringBuilder("");
			out.append("<table class=\"table table-striped table-bordered table-hover\" id=\"dataTables-example\"><thead>" +
					"		 <tr><th>المجموعة الرئيسية</th>" + 
					"		 <th>المجموعة الفرعية</th>" + 
					"		 <th>المجموعة الكود</th>" + 
					"		 <th>الأسم</th>" + 
					"        <th>مشاهدة</th>" + 
					"        <th>تعديل</th>" + 
					"        <th>حذف</th></tr>" + 
					"        </thead><tbody>");
			if(items.size() == 0)
				out.append("<tr><td colspan=\"7\"><p class=\"lead text-center text-danger\">عفوا لا يوجد أصناف تندرج تحت هذه المجموعة الفرعية</p></td></tr>");
			else {
				for(Item item : items) {
					out.append("<tr><td>" + item.getSubGroup().getMainGroup().getName() + "</td>");
					out.append("<td>" + item.getSubGroup().getName() + "</td>");
					out.append("<td>" + item.getCode() + "</td>");
					out.append("<td>" + item.getName() + "</td>");
					out.append("<td><a href=\"items/view.jsp?id=" + item.getId() + "\"><button class=\"btn btn-default\"><i class=\"fa fa-eye\"></i></button></a></td>");
					out.append("<td><a href=\"items/edit.jsp?id=" + item.getId() + "\"><button class=\"btn btn-success\"><i class=\"fa fa-edit\"></i></button></a></td>");
					out.append("<td><a href=\"items/delete.jsp?id=" + item.getId() + "\"><button class=\"btn btn-danger\"><i class=\"fa fa-close\"></i></button></a></td></tr>");
				}
			}
			out.append("</tbody></table>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
		}
	}
}
