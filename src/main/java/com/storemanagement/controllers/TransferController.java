package com.storemanagement.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Branch;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovement;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.ItemBalance;
import com.storemanagement.entities.Privilege;
import com.storemanagement.entities.TransferDetails;
import com.storemanagement.entities.TransferHeader;
import com.storemanagement.entities.Unit;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.InventoryService;
import com.storemanagement.services.ItemService;

public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
		if(!privileges.get(22).isView()) response.sendRedirect("error");
		else{
			List<Branch>branchs = EntityService.getAllObjects(Branch.class);
			List<Unit> units = EntityService.getObjectsWithOrder(Unit.class, "id", "asc");
			List<Cache> caches = EntityService.getAllObjects(Cache.class); 
			request.setAttribute("branchs", branchs);
			request.setAttribute("units", units);
			request.setAttribute("caches", caches);
			request.setAttribute("title", "التحويلات بين المخازن");
			request.getRequestDispatcher("transfer/index.jsp").forward(request, response);
		}
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
		if(!request.getParameter("inventoryFrom").equals("") && !request.getParameter("totalPrice").equals("")){
			TransferHeader header = new TransferHeader();
			
			User createdBy = (User) request.getSession().getAttribute("user");
			header.setCreatedBy(createdBy);
			header.setCreatedDate(new Date());
			header.setStatus(false);
			
			Inventory inventoryFrom = new Inventory();
			inventoryFrom.setId(Integer.parseInt(request.getParameter("inventoryFrom")));
			header.setInventoryFrom(inventoryFrom);
			
			Inventory inventoryTo = new Inventory();
			inventoryTo.setId(Integer.parseInt(request.getParameter("inventoryTo")));
			header.setInventoryTo(inventoryTo);
			
			Cache cacheFrom = new Cache();
			cacheFrom.setId(Integer.parseInt(request.getParameter("cacheFrom")));
			header.setCacheFrom(cacheFrom);
			
			Cache cacheTo = new Cache();
			cacheTo.setId(Integer.parseInt(request.getParameter("cacheTo")));
			header.setCacheTo(cacheTo);
			
			header.setTotalPrice(Double.parseDouble(request.getParameter("totalPrice")));

			int transferId = EntityService.addObject(header);
			
			cacheFrom = (Cache) EntityService.getObject(Cache.class, cacheFrom.getId());
			cacheFrom.setQty(cacheFrom.getQty() - header.getTotalPrice());
			EntityService.updateObject(cacheFrom);
			
			cacheTo = (Cache) EntityService.getObject(Cache.class, cacheTo.getId());
			cacheTo.setQty(cacheTo.getQty() + header.getTotalPrice());
			EntityService.updateObject(cacheTo);
			
			CacheMovement cacheMovementFrom = new CacheMovement();
			cacheMovementFrom.setUser(createdBy);
			cacheMovementFrom.setInventory(inventoryFrom);
			cacheMovementFrom.setCache(cacheFrom);
			cacheMovementFrom.setClient(null);
			cacheMovementFrom.setSupplier(null);
			cacheMovementFrom.setDate(new Date());
			cacheMovementFrom.setType(0);
			cacheMovementFrom.setDescription("سحب أصناف");
			cacheMovementFrom.setAmount(header.getTotalPrice() * -1);
			cacheMovementFrom.setRefNumber(0);
			EntityService.addObject(cacheMovementFrom);
			
			CacheMovement cacheMovementTo = new CacheMovement();
			cacheMovementTo.setUser(createdBy);
			cacheMovementTo.setInventory(inventoryTo);
			cacheMovementTo.setCache(cacheTo);
			cacheMovementTo.setClient(null);
			cacheMovementTo.setSupplier(null);
			cacheMovementTo.setDate(new Date());
			cacheMovementTo.setType(1);
			cacheMovementTo.setDescription("إيداع أصناف");
			cacheMovementTo.setAmount(header.getTotalPrice());
			cacheMovementTo.setRefNumber(0);
			EntityService.addObject(cacheMovementTo);
			
			String[] itemIds = request.getParameter("itemIds").split(",");
			String[] itemQty = request.getParameter("itemQty").split(",");
			String[] itemPrice = request.getParameter("itemPrice").split(",");
			String[] itemTotal = request.getParameter("itemTotal").split(",");
			
			for(int i = 0; i < itemIds.length; i++){
				TransferDetails details = new TransferDetails();
				details.setTransferHeader(header);
				Item item = new Item();
				item.setId(Integer.parseInt(itemIds[i]));
				details.setItem(item);
				details.setQty(Integer.parseInt(itemQty[i]));
				details.setPrice(Double.parseDouble(itemPrice[i]));
				details.setTotal(Double.parseDouble(itemTotal[i]));
				EntityService.addObject(details);
			}
			
			response.getWriter().print(transferId);
		}
	}

}
