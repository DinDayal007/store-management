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
import com.storemanagement.entities.SalesInvoiceDetails;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.entities.SubGroup;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.GroupService;
import com.storemanagement.services.ItemService;
import com.storemanagement.utils.InvoicesCounterUtil;
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
		if(!request.getParameter("inv_num").equals("") && !request.getParameter("finalTotal").equals("")) {
			SalesInvoiceHeader salesInvoiceHeader = new SalesInvoiceHeader();
			salesInvoiceHeader.setNumber(InvoicesCounterUtil.getSalesInvoiceCounter());
			salesInvoiceHeader.setDate(new Date());
			salesInvoiceHeader.setType(Integer.parseInt(request.getParameter("inv_type")));
			if(salesInvoiceHeader.getType() == 1) {
				salesInvoiceHeader.setPaid(Double.parseDouble(request.getParameter("paid")));
				salesInvoiceHeader.setRemain(Double.parseDouble(request.getParameter("remain")));
			}else {
				salesInvoiceHeader.setPaid(Double.parseDouble(request.getParameter("finalTotal")));
				salesInvoiceHeader.setRemain(0);
			}
			User user = new User();
			user.setId(1);
			salesInvoiceHeader.setUser(user);
			Client client = new Client();
			client.setId(Integer.parseInt(request.getParameter("client")));
			if(client.getId() == 0)
				salesInvoiceHeader.setClient(null);
			else salesInvoiceHeader.setClient(client);
			Inventory inventory = new Inventory();
			inventory.setId(Integer.parseInt(request.getParameter("inventory")));
			salesInvoiceHeader.setInventory(inventory);
			int cacheId = Integer.parseInt(request.getParameter("cache"));
			Cache cache = (Cache) EntityService.getObject(Cache.class, cacheId);
			salesInvoiceHeader.setCache(cache);
			salesInvoiceHeader.setTotal(Double.parseDouble(request.getParameter("totalPrice")));
			if(Integer.parseInt(request.getParameter("discountType")) == 0)
				salesInvoiceHeader.setDiscount(request.getParameter("discount") + " %");
			else salesInvoiceHeader.setDiscount(request.getParameter("discount") + " EGP");
			if(request.getParameter("tax").equals(""))
				salesInvoiceHeader.setTax(0);
			else salesInvoiceHeader.setTax(Integer.parseInt(request.getParameter("tax")));
			salesInvoiceHeader.setFinalTotal(Double.parseDouble(request.getParameter("finalTotal")));
			cache.setQty(cache.getQty() + salesInvoiceHeader.getPaid());
			EntityService.updateObject(cache);
			EntityService.addObject(salesInvoiceHeader);
			InvoicesCounterUtil.incrementSalesInvoiceCounter();
			
			String[] itemIds = request.getParameter("itemIds").split(",");
			String[] itemQty = request.getParameter("itemQty").split(",");
			String[] itemTotal = request.getParameter("itemTotal").split(",");
			for(int i = 0; i < itemIds.length; i++) {
				SalesInvoiceDetails salesInvoiceDetails = new SalesInvoiceDetails();
				salesInvoiceDetails.setSalesInvoiceHeader(salesInvoiceHeader);
				Item item = new Item();
				item.setId(Integer.parseInt(itemIds[i]));
				salesInvoiceDetails.setItem(item);
				salesInvoiceDetails.setQty(Integer.parseInt(itemQty[i]));
				salesInvoiceDetails.setPrice(Integer.parseInt(itemTotal[i]));
				EntityService.addObject(salesInvoiceDetails);
			}
			response.getWriter().print("saved!");
		}
	}
}