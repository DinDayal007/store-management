package com.storemanagement.services;
import java.util.List;
import com.storemanagement.entities.Item;
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
}
