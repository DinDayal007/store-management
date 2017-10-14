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
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.MainGroup;
import com.storemanagement.entities.PurchaseInvoiceDetails;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.SubGroup;
import com.storemanagement.entities.Supplier;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.GroupService;
import com.storemanagement.services.ItemService;
import com.storemanagement.utils.InvoicesCounterUtil;
@WebServlet("/purchases")
public class PurchasesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Supplier> suppliers = EntityService.getAllObjects(Supplier.class);
		List<Inventory> inventories = EntityService.getAllObjects(Inventory.class);
		List<Cache> caches = EntityService.getAllObjects(Cache.class);
		List<MainGroup> mainGroups = EntityService.getAllObjects(MainGroup.class);
		request.setAttribute("suppliers", suppliers);
		request.setAttribute("inventories", inventories);
		request.setAttribute("caches", caches);
		request.setAttribute("mainGroups", mainGroups);
		request.getRequestDispatcher("purchases/index.jsp").forward(request, response);
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
			PurchaseInvoiceHeader purchaseInvoiceHeader = new PurchaseInvoiceHeader();
			purchaseInvoiceHeader.setNumber(InvoicesCounterUtil.getPurchaseInvoiceCounter());
			purchaseInvoiceHeader.setDate(new Date());
			purchaseInvoiceHeader.setType(Integer.parseInt(request.getParameter("inv_type")));
			if(purchaseInvoiceHeader.getType() == 1) {
				purchaseInvoiceHeader.setPaid(Double.parseDouble(request.getParameter("paid")));
				purchaseInvoiceHeader.setRemain(Double.parseDouble(request.getParameter("remain")));
			}else {
				purchaseInvoiceHeader.setPaid(Double.parseDouble(request.getParameter("finalTotal")));
				purchaseInvoiceHeader.setRemain(0);
			}
			User user = new User();
			user.setId(1);
			purchaseInvoiceHeader.setUser(user);
			Supplier supplier = new Supplier();
			supplier.setId(Integer.parseInt(request.getParameter("supplier")));
			purchaseInvoiceHeader.setSupplier(supplier);
			Inventory inventory = new Inventory();
			inventory.setId(Integer.parseInt(request.getParameter("inventory")));
			purchaseInvoiceHeader.setInventory(inventory);
			int cacheId = Integer.parseInt(request.getParameter("cache"));
			Cache cache = (Cache) EntityService.getObject(Cache.class, cacheId);
			purchaseInvoiceHeader.setCache(cache);
			purchaseInvoiceHeader.setTotal(Double.parseDouble(request.getParameter("totalPrice")));
			if(Integer.parseInt(request.getParameter("discountType")) == 0)
				purchaseInvoiceHeader.setDiscount(request.getParameter("discount") + " %");
			else purchaseInvoiceHeader.setDiscount(request.getParameter("discount") + " EGP");
			if(request.getParameter("tax").equals(""))
				purchaseInvoiceHeader.setTax(0);
			else purchaseInvoiceHeader.setTax(Integer.parseInt(request.getParameter("tax")));
			purchaseInvoiceHeader.setFinalTotal(Double.parseDouble(request.getParameter("finalTotal")));
			cache.setQty(cache.getQty() - purchaseInvoiceHeader.getFinalTotal());
			EntityService.updateObject(cache);
			EntityService.addObject(purchaseInvoiceHeader);
			InvoicesCounterUtil.incrementPurchaseInvoiceCounter();
			
			String[] itemIds = request.getParameter("itemIds").split(",");
			String[] itemQty = request.getParameter("itemQty").split(",");
			String[] itemTotal = request.getParameter("itemTotal").split(",");
			for(int i = 0; i < itemIds.length; i++) {
				PurchaseInvoiceDetails purchaseInvoiceDetails = new PurchaseInvoiceDetails();
				purchaseInvoiceDetails.setPurchaseInvoiceHeader(purchaseInvoiceHeader);
				Item item = new Item();
				item.setId(Integer.parseInt(itemIds[i]));
				purchaseInvoiceDetails.setItem(item);
				purchaseInvoiceDetails.setQty(Integer.parseInt(itemQty[i]));
				purchaseInvoiceDetails.setPrice(Integer.parseInt(itemTotal[i]));
				EntityService.addObject(purchaseInvoiceDetails);
			}
			response.getWriter().print("saved!");
		}
	}
}
