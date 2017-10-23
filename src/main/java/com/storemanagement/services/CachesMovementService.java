package com.storemanagement.services;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovementSum;
import com.storemanagement.entities.Inventory;

public class CachesMovementService extends EntityService {
	public static List<CacheMovementSum> getCacheMovements(Inventory inventory, Cache cache, Date from, Date to){
		List<CacheMovementSum> cacheMovementSums = null;
		try {
			openSession();
			Criteria criteria = getSession().createCriteria(CacheMovementSum.class).addOrder(Order.asc("date"));
			if(inventory != null)
				criteria.add(Restrictions.eq("inventoryId", inventory.getId()));
			if(cache != null)
				criteria.add(Restrictions.eq("cacheId", cache.getId()));
			if(from != null)
				criteria.add(Restrictions.ge("date", from));
			if(to != null)
				criteria.add(Restrictions.le("date", to));
			cacheMovementSums = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return cacheMovementSums;
	}
}
