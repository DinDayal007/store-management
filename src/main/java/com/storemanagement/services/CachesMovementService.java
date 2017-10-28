package com.storemanagement.services;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovement;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Supplier;

public class CachesMovementService extends EntityService {
	public static List<CacheMovement> getCacheMovements(Inventory inventory, Cache cache, Client client, Supplier supplier, Date from, Date to, Integer type){
		List<CacheMovement> cacheMovements = null;
		try {
			openSession();
			Criteria criteria = getSession().createCriteria(CacheMovement.class).addOrder(Order.desc("id"));
			if(inventory != null)
				criteria.add(Restrictions.eq("inventory.id", inventory.getId()));
			if(cache != null)
				criteria.add(Restrictions.eq("cache.id", cache.getId()));
			if(client != null)
				criteria.add(Restrictions.eq("client.id", client.getId()));
			if(supplier != null)
				criteria.add(Restrictions.eq("supplier.id", supplier.getId()));
			if(type != null)
				criteria.add(Restrictions.eq("type", type));
			if(from != null)
				criteria.add(Restrictions.ge("date", from));
			if(to != null)
				criteria.add(Restrictions.le("date", to));
			cacheMovements = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return cacheMovements;
	}
	//get clients debt
	public static List<CacheMovement> getClientsDebits(Client client, Date from, Date to){
		List<CacheMovement> cacheMovements = null;
		try {
			openSession();
			Criteria criteria = getSession().createCriteria(CacheMovement.class).addOrder(Order.desc("id"));
			criteria.add(Restrictions.or(Restrictions.in("type", 0, 1), Restrictions.eq("description", "فاتورة بيع - آجل"), Restrictions.eq("description", "فاتورة مرتجع بيع - آجل")));
			if(client != null)
				criteria.add(Restrictions.eq("client.id", client.getId()));
			if(from != null)
				criteria.add(Restrictions.ge("date", from));
			if(to != null)
				criteria.add(Restrictions.le("date", to));
			cacheMovements = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return cacheMovements;
	}
}
