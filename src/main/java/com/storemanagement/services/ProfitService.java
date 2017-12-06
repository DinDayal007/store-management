package com.storemanagement.services;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.Profit;

public class ProfitService extends EntityService {
	public static List<Profit> getProfitMargin(Inventory inventory, Item item, Date from, Date to){
		List<Profit> profits = null;
		try{
			openSession();
			Criteria criteria = getSession().createCriteria(Profit.class).addOrder(Order.desc("id"));
			if(inventory != null)
				criteria.add(Restrictions.eq("salesInvoiceHeader.inventory.id", inventory.getId()));
			if(item != null)
				criteria.add(Restrictions.eq("item.id", item.getId()));
			if(from != null)
				criteria.add(Restrictions.ge("salesInvoiceHeader.date", from));
			if(to != null)
				criteria.add(Restrictions.le("salesInvoiceHeader.date", to));
			profits = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return profits;
	}
}
