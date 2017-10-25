package com.storemanagement.services;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovement;
import com.storemanagement.entities.Inventory;

public class CachesMovementService extends EntityService {
	public static List<CacheMovement> getCacheMovements(Inventory inventory, Cache cache, Date from, Date to, Integer type){
		List<CacheMovement> cacheMovements = null;
		try {
			openSession();
			Criteria criteria = getSession().createCriteria(CacheMovement.class).addOrder(Order.desc("id"));
			if(inventory != null)
				criteria.add(Restrictions.eq("inventoryId", inventory.getId()));
			if(cache != null)
				criteria.add(Restrictions.eq("cacheId", cache.getId()));
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
}
