package com.storemanagement.services;

import java.util.List;
import com.storemanagement.entities.Branch;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.entities.User;

public class InventoryService extends EntityService {
	public static int getInvoicesFromInventory(Inventory inventory) {
		List<PurchaseInvoiceHeader> purchaseInvoiceHeaders = null;
		List<SalesInvoiceHeader> salesInvoiceHeaders = null;
		int sum = 0;
		try {
			openSession();
			purchaseInvoiceHeaders = getSession().createQuery(
					"from PurchaseInvoiceHeader where inventory.id = :inventoryId")
					.setParameter("inventoryId", inventory.getId()).list();
			if (purchaseInvoiceHeaders.size() == 0) {
				salesInvoiceHeaders = getSession().createQuery(
						"from SalesInvoiceHeader where inventory.id = :inventoryId")
						.setParameter("inventoryId", inventory.getId()).list();
				sum = salesInvoiceHeaders.size();
			} else
				sum = purchaseInvoiceHeaders.size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return sum;
	}
	
	public static List<Inventory> getInventoriesFromBranch(Branch branch){
		List<Inventory> inventories = null;
		try {
			openSession();
			inventories = getSession()
					.createQuery(
							"from Inventory where branch.id = :branchId")
					.setParameter("branchId", branch.getId()).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return inventories;
	}
	//get users from a branch
	public static boolean hasInventoriesFromBranch(Branch branch) {
		List<Inventory> inventories = null;
		try {
			openSession();
			inventories = getSession()
					.createQuery(
							"from Inventory where branch.id = :branchId")
					.setParameter("branchId", branch.getId()).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return inventories.size() > 0;
	}
}
