package com.storemanagement.services;
import java.util.List;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.SalesInvoiceHeader;
public class CacheService extends EntityService {
	public static int getInvoicesFromCache(Cache cache) {
		List<PurchaseInvoiceHeader> purchaseInvoiceHeaders = null;
		List<SalesInvoiceHeader> salesInvoiceHeaders = null;
		int sum = 0;
		try {
			openSession();
			purchaseInvoiceHeaders = getSession().createQuery(
					"from PurchaseInvoiceHeader where cache.id = :cacheId")
					.setParameter("cacheId", cache.getId()).list();
			if (purchaseInvoiceHeaders.size() == 0) {
				salesInvoiceHeaders = getSession().createQuery(
						"from SalesInvoiceHeader where cache.id = :cacheId")
						.setParameter("cacheId", cache.getId()).list();
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
}
