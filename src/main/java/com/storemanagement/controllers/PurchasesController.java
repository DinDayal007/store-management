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
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.MainGroup;
import com.storemanagement.entities.Privilege;
import com.storemanagement.entities.PurchaseInvoiceDetails;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.SubGroup;
import com.storemanagement.entities.Supplier;
import com.storemanagement.entities.Unit;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.GroupService;
import com.storemanagement.services.InvoiceService;
import com.storemanagement.services.ItemService;
import com.storemanagement.utils.InvoicesCounterUtil;
@WebServlet("/purchases")
public class PurchasesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
		//2 is the privilege of purchase page
		if(privileges.get(2).isView()){
			List<Supplier> suppliers = EntityService.getAllObjects(Supplier.class);
			List<Inventory> inventories = EntityService.getAllObjects(Inventory.class);
			List<Cache> caches = EntityService.getAllObjects(Cache.class);
			List<MainGroup> mainGroups = EntityService.getAllObjects(MainGroup.class);
			List<Unit> units = EntityService.getObjectsWithOrder(Unit.class, "id", "asc");
			request.setAttribute("suppliers", suppliers);
			request.setAttribute("inventories", inventories);
			request.setAttribute("caches", caches);
			request.setAttribute("mainGroups", mainGroups);
			request.setAttribute("units", units);
			request.setAttribute("title", "فاتورة شراء جديدة");
			request.getRequestDispatcher("purchases/index.jsp").forward(request, response);
		}else response.sendRedirect("error");
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
			Item item = (Item) ItemService.getObject(Item.class, itemId);
			int itemQty = ItemService.getItemQty(itemId, user.getInventory().getId());
//			ItemBalance itemBalance = ItemService.getItemBalance(itemId, user.getInventory().getId());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("itemId", itemId);
			jsonObject.put("itemCode", item.getCode());
			jsonObject.put("itemName", item.getName());
			jsonObject.put("itemPrice", item.getPurchasePrice());
			jsonObject.put("itemQty", itemQty);
			jsonObject.put("itemMax", item.getMaxLimit());
			response.getWriter().print(jsonObject.toString());
		}
	}
	
	//get item from code
	protected void getItemFromCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(!request.getParameter("itemCode").equals("")){
			Item item = ItemService.getItemFromBarcode(request.getParameter("itemCode"));
			//ItemBalance itemBalance = ItemService.getItemFromCode(request.getParameter("itemCode"));
			if(item != null){
				int itemQty = ItemService.getItemQty(item.getId(), user.getInventory().getId());
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("itemId", item.getId());
				jsonObject.put("itemCode", request.getParameter("itemCode"));
				jsonObject.put("itemName", item.getName());
				jsonObject.put("itemPrice", item.getPurchasePrice());
				jsonObject.put("itemQty", itemQty);
				jsonObject.put("itemMax", item.getMaxLimit());
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
					|| !request.getParameter("supplierId").equals("")){
				Integer userId = null, paymentType = null, cacheId = null, inventoryId = null, supplierId = null;
				if(!request.getParameter("userId").equals(""))
					userId = Integer.parseInt(request.getParameter("userId"));
				if(!request.getParameter("paymentType").equals(""))
					paymentType = Integer.parseInt(request.getParameter("paymentType"));
				if(!request.getParameter("cacheId").equals(""))
					cacheId = Integer.parseInt(request.getParameter("cacheId"));
				if(!request.getParameter("inventoryId").equals(""))
					inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
				if(!request.getParameter("supplierId").equals(""))
					supplierId = Integer.parseInt(request.getParameter("supplierId"));
				Date from = null, to = null;
				try{
					if(!request.getParameter("from").equals(""))
						from = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("from"));
					if(!request.getParameter("to").equals(""))
						to = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("to"));
				}catch (Exception e) {
					e.printStackTrace();
				}
				List<PurchaseInvoiceHeader> purchaseInvoiceHeaders = InvoiceService.searchPurchaseInvoices(userId, paymentType, cacheId, inventoryId, supplierId, from, to);
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
				if(purchaseInvoiceHeaders.size() == 0)
					out.append("<tr><td colspan=\"8\"><p class=\"lead text-center text-danger\">عفوا لا يوجد فواتير بيع</p></td></tr>");
				else{
					for(PurchaseInvoiceHeader purchaseInvoiceHeader : purchaseInvoiceHeaders){
						out.append("<tr><td>" + purchaseInvoiceHeader.getNumber() + "</td>");
						out.append("<td>" + purchaseInvoiceHeader.getDate() + "</td>");
						out.append("<td>" + purchaseInvoiceHeader.getUser().getName() + "</td>");
						out.append("<td>" + purchaseInvoiceHeader.getFinalTotal() + "</td>");
						out.append("<td>" + (purchaseInvoiceHeader.getType() == 0 ? "فورى" : "اجل") + "</td>");
						out.append("<td>" + purchaseInvoiceHeader.getInventory().getName() + "</td>");
						out.append("<td>" + purchaseInvoiceHeader.getCache().getName() + "</td>");
						out.append("<td><a href=\"/store-management-system/purchases/invoice.jsp?id=" + purchaseInvoiceHeader.getId() + "\"><button class=\"btn btn-default\"><i class=\"fa fa-eye\"></i></button></a></td></tr>");
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
		if(!request.getParameter("inv_num").equals("") && !request.getParameter("inv_type").equals("")) {
			PurchaseInvoiceHeader purchaseInvoiceHeader = new PurchaseInvoiceHeader();
			purchaseInvoiceHeader.setNumber(Long.parseLong(request.getParameter("inv_num")));
			purchaseInvoiceHeader.setDate(new Date());
			purchaseInvoiceHeader.setType(Integer.parseInt(request.getParameter("inv_type")));
//			if(purchaseInvoiceHeader.getType() == 1) {
//				purchaseInvoiceHeader.setPaid(Double.parseDouble(request.getParameter("paid")));
//				purchaseInvoiceHeader.setRemain(Double.parseDouble(request.getParameter("remain")));
//			}else {
//				purchaseInvoiceHeader.setPaid(Double.parseDouble(request.getParameter("finalTotal")));
//				purchaseInvoiceHeader.setRemain(0);
//			}
			User user = (User) request.getSession().getAttribute("user");
			purchaseInvoiceHeader.setUser(user);
			Supplier supplier = new Supplier();
			supplier.setId(Integer.parseInt(request.getParameter("supplier")));
			purchaseInvoiceHeader.setSupplier(supplier);
			Inventory inventory = user.getInventory();
			purchaseInvoiceHeader.setInventory(inventory);
			int cacheId = user.getCache().getId();
			Cache cache = (Cache) EntityService.getObject(Cache.class, cacheId);
			purchaseInvoiceHeader.setCache(cache);
			purchaseInvoiceHeader.setTotal(Double.parseDouble(request.getParameter("totalPrice")));
			purchaseInvoiceHeader.setFinalTotal(purchaseInvoiceHeader.getTotal());
//			if(Integer.parseInt(request.getParameter("discountType")) == 0)
//				purchaseInvoiceHeader.setDiscount(request.getParameter("discount") + " %");
//			else purchaseInvoiceHeader.setDiscount(request.getParameter("discount") + " EGP");
//			if(request.getParameter("tax").equals(""))
//				purchaseInvoiceHeader.setTax(0);
//			else purchaseInvoiceHeader.setTax(Integer.parseInt(request.getParameter("tax")));
//			purchaseInvoiceHeader.setFinalTotal(Double.parseDouble(request.getParameter("finalTotal")));
			if(purchaseInvoiceHeader.getType() == 0)
				cache.setQty(cache.getQty() - purchaseInvoiceHeader.getTotal());
			else cache.setQty(cache.getQty() + purchaseInvoiceHeader.getTotal());
			EntityService.updateObject(cache);
			EntityService.addObject(purchaseInvoiceHeader);
			//add new cache movement from the purchase invoice
			CacheMovement cacheMovement = new CacheMovement();
			if(purchaseInvoiceHeader.getType() == 0)
				cacheMovement.setAmount(purchaseInvoiceHeader.getTotal() * -1);
			else cacheMovement.setAmount(purchaseInvoiceHeader.getTotal());
			cacheMovement.setCache(cache);
			cacheMovement.setClient(null);
			cacheMovement.setDate(purchaseInvoiceHeader.getDate());
			String invType = purchaseInvoiceHeader.getType() == 0 ? "فورى" : "آجل";
			cacheMovement.setDescription("فاتورة شراء - " + invType);
			cacheMovement.setInventory(inventory);
			cacheMovement.setRefNumber(purchaseInvoiceHeader.getNumber());
			cacheMovement.setSupplier(supplier);
			cacheMovement.setType(2);
			cacheMovement.setUser(user);
			EntityService.addObject(cacheMovement);
			InvoicesCounterUtil.incrementPurchaseInvoiceCounter();
			
			String[] itemIds = request.getParameter("itemIds").split(",");
			String[] itemQty = request.getParameter("itemQty").split(",");
			String[] itemPrice = request.getParameter("itemPrice").split(",");
			String[] itemTotal = request.getParameter("itemTotal").split(",");
			String[] unitIds = request.getParameter("unitId").split(",");
			for(int i = 0; i < itemIds.length; i++) {
				PurchaseInvoiceDetails purchaseInvoiceDetails = new PurchaseInvoiceDetails();
				purchaseInvoiceDetails.setPurchaseInvoiceHeader(purchaseInvoiceHeader);
				Item item = new Item();
				item.setId(Integer.parseInt(itemIds[i]));
				Unit unit = new Unit();
				unit.setId(Integer.parseInt(unitIds[i]));
				purchaseInvoiceDetails.setItem(item);
				purchaseInvoiceDetails.setUnit(unit);
				purchaseInvoiceDetails.setQty(Integer.parseInt(itemQty[i]));
				purchaseInvoiceDetails.setPrice(Double.parseDouble(itemPrice[i]));
				purchaseInvoiceDetails.setTotal(Double.parseDouble(itemTotal[i]));
				EntityService.addObject(purchaseInvoiceDetails);
			}
			response.getWriter().print("saved!");
		}
	}
}
