package com.storemanagement.controllers;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.MainGroup;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.entities.SubGroup;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.GroupService;
import com.storemanagement.services.ItemService;
@WebServlet("/sales")
public class SalesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Client> clients = EntityService.getAllObjects(Client.class);
		List<Inventory> inventories = EntityService.getAllObjects(Inventory.class);
		List<Cache> caches = EntityService.getAllObjects(Cache.class);
		List<MainGroup> mainGroups = EntityService.getAllObjects(MainGroup.class);
		request.setAttribute("clients", clients);
		request.setAttribute("inventories", inventories);
		request.setAttribute("caches", caches);
		request.setAttribute("mainGroups", mainGroups);
		request.getRequestDispatcher("sales/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("1"))
			getSubGroups(request, response);
		else if(request.getParameter("action").equals("2"))
			getItems(request, response);
		else if(request.getParameter("action").equals("3"))
			getItemById(request, response);
		else if(request.getParameter("action").equals("save"))
			saveInvoice(request, response);
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
			if(subGroups.size() > 0) {
				out.append("<option value=\"0\">اختر مجموعة فرعية</option>");
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
	//get items from subgroups
	protected void getItems(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("subGroup_id").equals("")) {
			int id = Integer.parseInt(request.getParameter("subGroup_id"));
			SubGroup subGroup = new SubGroup();
			subGroup.setId(id);
			List<Item> items = ItemService.getItemsFromSubGroup(subGroup);
			StringBuilder out = new StringBuilder("");
			if(items.size() > 0) {
				for(Item item : items) {
					out.append("<option value=\"" + item.getId() + "\">" + item.getName() + "</option>");
				}
			}else 
				out.append("<option value=\"\" disabled>لا توجد أصناف لهذه المجموعة الفرعية</option>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
		}
	}
	//get item from id
	protected void getItemById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("itemId").equals("")) {
			int id = Integer.parseInt(request.getParameter("itemId"));
			Item item = (Item) ItemService.getObject(Item.class, id);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("itemId", id);
			jsonObject.put("itemCode", item.getCode());
			jsonObject.put("itemName", item.getName());
			jsonObject.put("itemPrice", item.getPrice());
			response.getWriter().print(jsonObject.toString());
		}
	}
	
	//save the sales invoice
	protected void saveInvoice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("inv_num").equals("")) {
			SalesInvoiceHeader salesInvoiceHeader = new SalesInvoiceHeader();
			salesInvoiceHeader.setNumber(Integer.parseInt(request.getParameter("inv_num")));
			salesInvoiceHeader.setDate(new Date());

			User user = new User();
			user.setId(1);
			salesInvoiceHeader.setUser(user);
			Client client = new Client();
			client.setId(Integer.parseInt(request.getParameter("client")));
			salesInvoiceHeader.setClient(client);
			Inventory inventory = new Inventory();
			inventory.setId(Integer.parseInt(request.getParameter("inventory")));
			salesInvoiceHeader.setInventory(inventory);
			Cache cache = new Cache();
			cache.setId(Integer.parseInt(request.getParameter("cache")));
			salesInvoiceHeader.setCache(cache);
			salesInvoiceHeader.setTotal(Double.parseDouble(request.getParameter("totalPrice")));
			salesInvoiceHeader.setDiscount(request.getParameter("discount"));
			salesInvoiceHeader.setTax(Integer.parseInt(request.getParameter("tax")));
			salesInvoiceHeader.setPaid(Double.parseDouble(request.getParameter("paid")));
			salesInvoiceHeader.setRemain(Double.parseDouble(request.getParameter("remain")));
			salesInvoiceHeader.setFinalTotal(Double.parseDouble(request.getParameter("finalTotal")));
			int id = EntityService.addObject(salesInvoiceHeader);
			System.out.println(id);
		}
	}
}
