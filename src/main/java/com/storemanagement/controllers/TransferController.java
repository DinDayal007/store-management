package com.storemanagement.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Branch;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.ItemBalance;
import com.storemanagement.entities.Unit;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.InventoryService;
import com.storemanagement.services.ItemService;

public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Branch>branchs = EntityService.getAllObjects(Branch.class);
		List<Unit> units = EntityService.getObjectsWithOrder(Unit.class, "id", "asc");
		List<Cache> caches = EntityService.getAllObjects(Cache.class); 
		request.setAttribute("branchs", branchs);
		request.setAttribute("units", units);
		request.setAttribute("caches", caches);
		request.setAttribute("title", "التحويلات بين المخازن");
		request.getRequestDispatcher("transfer/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("1"))
			getInventoriesFromBranch(request, response);
		else if(request.getParameter("action").equals("2"))
			getItemsFromInventory(request, response);
		else if(request.getParameter("action").equals("save"))
			saveTransfer(request, response);
	}
	
	//get inventories from branch
	protected void getInventoriesFromBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("branch").equals("")) {
			int id = Integer.parseInt(request.getParameter("branch"));
			Branch branch = new Branch();
			branch.setId(id);
			List<Inventory> inventories = InventoryService.getInventoriesFromBranch(branch);
			StringBuilder out = new StringBuilder("");
			out.append("<option value=\"\">اختر المخزن</option>");
			if(inventories.size() > 0) {
				for(Inventory inventory : inventories) {
					out.append("<option value=\"" + inventory.getId() + "\">" + inventory.getName() + "</option>");
				}
			}else
				out.append("<option value=\"\" disabled>لا يوجد مخازن لهذا الفرع</option>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
		}
	}
	
	//get items from inventory
	protected void getItemsFromInventory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("inventoryId").equals("")){
			int inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
			List<ItemBalance> itemBalances = ItemService.getItemsFromInventory(inventoryId);
			StringBuilder out = new StringBuilder("");
			out.append("<option value=\"\">اختر الصنف</option>");
			if(itemBalances.size() > 0) {
				for(ItemBalance itemBalance : itemBalances) {
					out.append("<option data-id=\"" + itemBalance.getItemId() + "\" data-qty=\"" + itemBalance.getItemQty() + "\" data-code=\"" + itemBalance.getItemCode() + "\" data-price=\"" + itemBalance.getItemPurchasePrice() + "\" value=\"" + itemBalance.getItemId() + "\">" + itemBalance.getItemName() + "</option>");
				}
			}else 
				out.append("<option value=\"\" disabled>لا توجد أصناف فى هذا المخزن</option>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
		}
	}
	
	//get items from inventory
	protected void saveTransfer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameter("inventoryFrom").equals("")){
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("saved!");
		}
	}

}
