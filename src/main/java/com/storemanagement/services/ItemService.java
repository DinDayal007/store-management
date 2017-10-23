package com.storemanagement.services;
import java.util.List;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.ItemBalance;
import com.storemanagement.entities.SubGroup;
public class ItemService extends EntityService {
	public static List<Item> getItemsFromSubGroup(SubGroup subGroup) {
		List<Item> items = null;
		try {
			openSession();
			items = getSession()
					.createQuery("from Item where subGroup.id = :subGroupId")
					.setParameter("subGroupId", subGroup.getId()).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return items;
	}
	//get item balance from id
	public static List<ItemBalance> getItemBalance(int itemId, int inventoryId){
		List<ItemBalance> itemBalances = null;
		try {
			openSession();
			itemBalances = (List<ItemBalance>) getSession().createSQLQuery("SELECT @rownum\\:=@rownum+1 AS id, A.* FROM(SELECT @rownum\\:=0 r, INVENTORY_ID, INVENTORY_NAME, ITEM_ID, MAINGROUP, sub_name, item_name, HOME, CODE, PRICE, MIN_LIMIT, MAX_LIMIT, SUM(ITEM_QTY) TOTAL_QTY FROM item_movements GROUP BY INVENTORY_ID , INVENTORY_NAME , ITEM_ID , MAINGROUP, sub_name , item_name ,HOME, CODE, PRICE, MIN_LIMIT , MAX_LIMIT )A WHERE ITEM_ID = :itemId AND INVENTORY_ID = :inventoryId").setParameter("itemId", itemId).setParameter("inventoryId", inventoryId).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return itemBalances;
	}
}
