package com.storemanagement.controllers;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovement;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.ItemBalance;
import com.storemanagement.entities.MainGroup;
import com.storemanagement.entities.SalesInvoiceDetails;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.entities.SubGroup;
import com.storemanagement.entities.Unit;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.GroupService;
import com.storemanagement.services.InvoiceService;
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
		List<Unit> units = EntityService.getObjectsWithOrder(Unit.class, "id", "asc");
		request.setAttribute("clients", clients);
		request.setAttribute("inventories", inventories);
		request.setAttribute("caches", caches);
		request.setAttribute("mainGroups", mainGroups);
		request.setAttribute("units", units);
		request.setAttribute("title", "فاتورة بيع جديدة");
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
		else if(request.getParameter("action").equals("4"))
			getItemFromCode(request, response);
		else if(request.getParameter("action").equals("5"))
			searchInvoices(request, response);
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
		User user = (User) request.getSession().getAttribute("user");
		if(!request.getParameter("itemId").equals("")) {
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			//Item item = (Item) ItemService.getObject(Item.class, itemId);
			//int itemQty = ItemService.getItemQty(itemId, user.getInventory().getId());
			ItemBalance itemBalance = ItemService.getItemBalance(itemId, user.getInventory().getId());
			if(itemBalance != null){
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("itemId", itemId);
				jsonObject.put("itemCode", itemBalance.getItemCode());
				jsonObject.put("itemName", itemBalance.getItemName());
				jsonObject.put("itemPrice", itemBalance.getItemSalePrice());
				jsonObject.put("itemQty", itemBalance.getItemQty());
				jsonObject.put("itemMin", itemBalance.getItemMin());
				response.getWriter().print(jsonObject.toString());
			}
		}
	}
	
	//get item from code
	protected void getItemFromCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("itemCode").equals("")){
			ItemBalance itemBalance = ItemService.getItemFromCode(request.getParameter("itemCode"));
			if(itemBalance != null){
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("itemId", itemBalance.getItemId());
				jsonObject.put("itemCode", request.getParameter("itemCode"));
				jsonObject.put("itemName", itemBalance.getItemName());
				jsonObject.put("itemPrice", itemBalance.getItemSalePrice());
				jsonObject.put("itemQty", itemBalance.getItemQty());
				jsonObject.put("itemMin", itemBalance.getItemMin());
				response.getWriter().print(jsonObject.toString());
			}
		}
	}
	
	//get items from subgroups
	protected void searchInvoices(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("userId").equals("") || !request.getParameter("from").equals("")
				|| !request.getParameter("to").equals("") || !request.getParameter("paymentType").equals("")
				|| !request.getParameter("cacheId").equals("") || !request.getParameter("inventoryId").equals("")
				|| !request.getParameter("clientId").equals("")){
			Integer userId = null, paymentType = null, cacheId = null, inventoryId = null, clientId = null;
			if(!request.getParameter("userId").equals(""))
				userId = Integer.parseInt(request.getParameter("userId"));
			if(!request.getParameter("paymentType").equals(""))
				paymentType = Integer.parseInt(request.getParameter("paymentType"));
			if(!request.getParameter("cacheId").equals(""))
				cacheId = Integer.parseInt(request.getParameter("cacheId"));
			if(!request.getParameter("inventoryId").equals(""))
				inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
			if(!request.getParameter("clientId").equals(""))
				clientId = Integer.parseInt(request.getParameter("clientId"));
			Date from = null, to = null;
			try{
				if(!request.getParameter("from").equals("")){
					String f = request.getParameter("from") + " 00:00:00";
					from = (Date) new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(f);
				}
				if(!request.getParameter("to").equals("")){
					String t = request.getParameter("to") + " 23:59:59";
					to = (Date) new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(t);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			List<SalesInvoiceHeader> salesInvoiceHeaders = InvoiceService.searchInvoices(userId, paymentType, cacheId, inventoryId, clientId, from, to);
			StringBuilder out = new StringBuilder();
			out.append("<table class=\"table table-striped table-bordered table-hover\" id=\"dataTables-example\"><thead>" +
					"		 <tr><th>رقم الفاتورة</th>" + 
					"		 <th>تاريخ الفاتورة</th>" + 
					"		 <th>المستخدم</th>" + 
					"		 <th>إجمالى الفاتورة</th>" + 
					"        <th>طريقة الدفع</th>" + 
					"        <th>المخزن</th>" + 
					"        <th>الخزنة</th>" + 
					"        <th>مشاهدة</th></tr>" + 
					"        </thead><tbody>");
			if(salesInvoiceHeaders.size() == 0)
				out.append("<tr><td colspan=\"8\"><p class=\"lead text-center text-danger\">عفوا لا يوجد فواتير بيع</p></td></tr>");
			else{
				for(SalesInvoiceHeader salesInvoiceHeader : salesInvoiceHeaders){
					out.append("<tr><td>" + salesInvoiceHeader.getNumber() + "</td>");
					out.append("<td>" + salesInvoiceHeader.getDate() + "</td>");
					out.append("<td>" + salesInvoiceHeader.getUser().getName() + "</td>");
					out.append("<td>" + salesInvoiceHeader.getFinalTotal() + "</td>");
					out.append("<td>" + (salesInvoiceHeader.getType() == 0 ? "فورى" : "اجل") + "</td>");
					out.append("<td>" + salesInvoiceHeader.getInventory().getName() + "</td>");
					out.append("<td>" + salesInvoiceHeader.getCache().getName() + "</td>");
					out.append("<td><a href=\"/store-management-system/sales/invoice.jsp?id=" + salesInvoiceHeader.getId() + "\"><button class=\"btn btn-default\"><i class=\"fa fa-eye\"></i></button></a></td></tr>");
				}
			}
			out.append("</tbody></table>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
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
			User user = (User) request.getSession().getAttribute("user");
			salesInvoiceHeader.setUser(user);
			Client client = new Client();
			client.setId(Integer.parseInt(request.getParameter("client")));
			if(client.getId() == 0)
				salesInvoiceHeader.setClient(null);
			else salesInvoiceHeader.setClient(client);
			//Inventory inventory = new Inventory();
			//inventory.setId(Integer.parseInt(request.getParameter("inventory")));
			Inventory inventory = user.getInventory();
			salesInvoiceHeader.setInventory(inventory);
			//int cacheId = Integer.parseInt(request.getParameter("cache"));
			int cacheId = user.getCache().getId();
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
			//update cache quantity
			if(salesInvoiceHeader.getType() == 0)
				cache.setQty(cache.getQty() + salesInvoiceHeader.getPaid());
			else cache.setQty(cache.getQty() - salesInvoiceHeader.getPaid());
			EntityService.updateObject(cache);
			EntityService.addObject(salesInvoiceHeader);
			//add new cache movement from the sales invoice
			CacheMovement cacheMovement = new CacheMovement();
			if(salesInvoiceHeader.getType() == 0)
				cacheMovement.setAmount(salesInvoiceHeader.getFinalTotal());
			else cacheMovement.setAmount(salesInvoiceHeader.getFinalTotal() * -1);
			cacheMovement.setCache(cache);
			cacheMovement.setClient(client.getId() == 0 ? null : client);
			cacheMovement.setDate(salesInvoiceHeader.getDate());
			String invType = salesInvoiceHeader.getType() == 0 ? "فورى" : "آجل";
			cacheMovement.setDescription("فاتورة بيع - " + invType);
			cacheMovement.setInventory(inventory);
			cacheMovement.setRefNumber(salesInvoiceHeader.getNumber());
			cacheMovement.setSupplier(null);
			cacheMovement.setType(4);
			cacheMovement.setUser(user);
			EntityService.addObject(cacheMovement);
			InvoicesCounterUtil.incrementSalesInvoiceCounter();
			
			String[] itemIds = request.getParameter("itemIds").split(",");
			String[] itemQty = request.getParameter("itemQty").split(",");
			String[] itemPrice = request.getParameter("itemPrice").split(",");
			String[] itemTotal = request.getParameter("itemTotal").split(",");
			String[] unitIds = request.getParameter("unitId").split(",");
			for(int i = 0; i < itemIds.length; i++) {
				SalesInvoiceDetails salesInvoiceDetails = new SalesInvoiceDetails();
				salesInvoiceDetails.setSalesInvoiceHeader(salesInvoiceHeader);
				Item item = new Item();
				item.setId(Integer.parseInt(itemIds[i]));
				System.out.println(item.getId());
				Unit unit = new Unit();
				unit.setId(Integer.parseInt(unitIds[i]));
				salesInvoiceDetails.setItem(item);
				salesInvoiceDetails.setUnit(unit);
				salesInvoiceDetails.setQty(Integer.parseInt(itemQty[i]));
				salesInvoiceDetails.setPrice(Double.parseDouble(itemPrice[i]));
				salesInvoiceDetails.setTotal(Double.parseDouble(itemTotal[i]));
				EntityService.addObject(salesInvoiceDetails);
			}
			response.getWriter().print("saved!");
		}
	}
}