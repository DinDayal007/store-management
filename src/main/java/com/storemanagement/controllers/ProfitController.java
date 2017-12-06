package com.storemanagement.controllers;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Branch;
import com.storemanagement.entities.ItemBalance;
import com.storemanagement.entities.Privilege;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.ItemService;

public class ProfitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
		if(!privileges.get(16).isView()) response.sendRedirect("error");
		else{
			List<Branch> branchs = EntityService.getAllObjects(Branch.class);
			request.setAttribute("branchs", branchs);
			request.setAttribute("title", "هامش الربح");
			request.getRequestDispatcher("profit/index.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getItemsFromInventory(request, response);
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
					out.append("<option value=\"" + itemBalance.getItemId() + "\">" + itemBalance.getItemName() + "</option>");
				}
			}else 
				out.append("<option value=\"\" disabled>لا توجد أصناف فى هذا المخزن</option>");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(out.toString());
		}
	}
}
