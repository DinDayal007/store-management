package com.storemanagement.services;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.PurchaseInvoiceDetails;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.ReturnPurchaseInvoiceDetails;
import com.storemanagement.entities.ReturnPurchaseInvoiceHeader;
import com.storemanagement.entities.ReturnSalesInvoiceDetails;
import com.storemanagement.entities.ReturnSalesInvoiceHeader;
import com.storemanagement.entities.SalesInvoiceDetails;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.entities.Supplier;
import com.storemanagement.entities.Unit;
public class InvoiceService extends EntityService {
	public static List<PurchaseInvoiceHeader> getPurchaseInvoicesFromSupplier( Supplier supplier) {
		List<PurchaseInvoiceHeader> purchaseInvoiceHeaders = null;
		try {
			openSession();
			purchaseInvoiceHeaders = getSession()
					.createQuery("from PurchaseInvoiceHeader where supplier.id = :supplierId")
					.setParameter("supplierId", supplier.getId()).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return purchaseInvoiceHeaders;
	}
	
	public static List<SalesInvoiceHeader> getSalesInvoicesFromClient(Client client){
		List<SalesInvoiceHeader> salesInvoiceHeaders = null;
		try {
			openSession();
			salesInvoiceHeaders = getSession()
					.createQuery("from SalesInvoiceHeader where client.id = :clientId")
					.setParameter("clientId", client.getId()).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return salesInvoiceHeaders;
	}
	
	public static int getInvoicesFromItem(Item item) {
		List<PurchaseInvoiceHeader> purchaseInvoiceHeaders = null;
		List<SalesInvoiceHeader> salesInvoiceHeaders = null;
		int sum = 0;
		try {
			openSession();
			purchaseInvoiceHeaders = getSession().createQuery(
					"from PurchaseInvoiceDetails where item.id = :itemId")
					.setParameter("itemId", item.getId()).list();
			if (purchaseInvoiceHeaders.size() == 0) {
				salesInvoiceHeaders = getSession().createQuery(
						"from SalesInvoiceDetails where item.id = :itemId")
						.setParameter("itemId", item.getId()).list();
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
	
	public static boolean hasReturnSalesInvoice(SalesInvoiceHeader salesInvoiceHeader) {
		List<ReturnSalesInvoiceHeader> headers = null;
		try {
			openSession();
			headers = getSession()
					.createQuery("from ReturnSalesInvoiceHeader where salesInvoiceHeader.id = :salesInvoiceHeaderId")
					.setParameter("salesInvoiceHeaderId", salesInvoiceHeader.getId()).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return headers.size() > 0;
	}
	
	public static boolean hasReturnPurchaseInvoice(PurchaseInvoiceHeader purchaseInvoiceHeader) {
		List<ReturnPurchaseInvoiceHeader> headers = null;
		try {
			openSession();
			headers = getSession()
					.createQuery("from ReturnPurchaseInvoiceHeader where purchaseInvoiceHeader.id = :purchaseInvoiceHeaderId")
					.setParameter("purchaseInvoiceHeaderId", purchaseInvoiceHeader.getId()).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return headers.size() > 0;
	}
	//return sales invoice max number
	public static List<Long> getInvoicesNumbers(){
		Long salesInvoicenumber = null,
		purchaseInvoicenumber = null,
		returnSalesInvoicenumber = null,
		returnPurchaseInvoicenumber = null;
		List<Long> invoiceNumbers = new ArrayList<>();
		try {
			openSession();
			salesInvoicenumber = (Long) getSession().createCriteria(SalesInvoiceHeader.class).setProjection(Projections.max("number")).uniqueResult();
			purchaseInvoicenumber = (Long) getSession().createCriteria(PurchaseInvoiceHeader.class).setProjection(Projections.max("number")).uniqueResult();
			returnSalesInvoicenumber = (Long) getSession().createCriteria(ReturnSalesInvoiceHeader.class).setProjection(Projections.max("number")).uniqueResult();
			returnPurchaseInvoicenumber = (Long) getSession().createCriteria(ReturnPurchaseInvoiceHeader.class).setProjection(Projections.max("number")).uniqueResult();
			invoiceNumbers.add(salesInvoicenumber == null ? 1 : salesInvoicenumber + 1);
			invoiceNumbers.add(purchaseInvoicenumber == null ? 1 : purchaseInvoicenumber + 1);
			invoiceNumbers.add(returnSalesInvoicenumber == null ? 1 : returnSalesInvoicenumber + 1);
			invoiceNumbers.add(returnPurchaseInvoicenumber == null ? 1 : returnPurchaseInvoicenumber + 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return invoiceNumbers;
	}
	//check if invoices details have this unit
	public static boolean hasDetailsFromUnit(Unit unit){
		List<SalesInvoiceDetails> salesInvoiceDetails = null;
		List<PurchaseInvoiceDetails> purchaseInvoiceDetails = null;
		List<ReturnSalesInvoiceDetails> returnSalesInvoiceDetails = null;
		List<ReturnPurchaseInvoiceDetails> returnPurchaseInvoiceDetails = null;
		try {
			openSession();
			salesInvoiceDetails = getSession().createQuery(
					"from SalesInvoiceDetails where unit.id = :unitId")
					.setParameter("unitId", unit.getId()).list();
			if (salesInvoiceDetails.size() > 0) return true;
			else {
				purchaseInvoiceDetails = getSession().createQuery(
						"from PurchaseInvoiceDetails where unit.id = :unitId")
						.setParameter("unitId", unit.getId()).list();
			}
			if(purchaseInvoiceDetails.size() > 0) return true;
			else {
				returnSalesInvoiceDetails = getSession().createQuery(
						"from ReturnSalesInvoiceDetails where unit.id = :unitId")
						.setParameter("unitId", unit.getId()).list();
			}
			if(returnSalesInvoiceDetails.size() > 0) return true;
			else {
				returnPurchaseInvoiceDetails = getSession().createQuery(
						"from ReturnPurchaseInvoiceDetails where unit.id = :unitId")
						.setParameter("unitId", unit.getId()).list();
			}
			if(returnPurchaseInvoiceDetails.size() > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return false;
	}
	
	//search sales invoices
	public static List<SalesInvoiceHeader> searchInvoices(Integer userId, Integer paymentType, Integer cacheId, Integer inventoryId, Integer clientId, Date from, Date to){
		List<SalesInvoiceHeader> salesInvoiceHeaders = null;
		try {
			openSession();
			Criteria criteria = getSession().createCriteria(SalesInvoiceHeader.class).addOrder(Order.desc("id"));
			if(userId != null)
				criteria.add(Restrictions.eq("user.id", userId));
			if(paymentType != null)
				criteria.add(Restrictions.eq("type", paymentType));
			if(cacheId != null)
				criteria.add(Restrictions.eq("cache.id", cacheId));
			if(inventoryId != null)
				criteria.add(Restrictions.eq("inventory.id", inventoryId));
			if(clientId != null)
				criteria.add(Restrictions.eq("client.id", clientId));
			if(from != null)
				criteria.add(Restrictions.ge("date", from));
			if(to != null)
				criteria.add(Restrictions.le("date", to));
			salesInvoiceHeaders = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return salesInvoiceHeaders;
	}
	
	//search purchase invoices
	public static List<PurchaseInvoiceHeader> searchPurchaseInvoices(Integer userId, Integer paymentType, Integer cacheId, Integer inventoryId, Integer supplierId, Date from, Date to){
		List<PurchaseInvoiceHeader> purchaseInvoiceHeaders = null;
		try {
			openSession();
			Criteria criteria = getSession().createCriteria(PurchaseInvoiceHeader.class).addOrder(Order.desc("id"));
			if(userId != null)
				criteria.add(Restrictions.eq("user.id", userId));
			if(paymentType != null)
				criteria.add(Restrictions.eq("type", paymentType));
			if(cacheId != null)
				criteria.add(Restrictions.eq("cache.id", cacheId));
			if(inventoryId != null)
				criteria.add(Restrictions.eq("inventory.id", inventoryId));
			if(supplierId != null)
				criteria.add(Restrictions.eq("supplier.id", supplierId));
			if(from != null)
				criteria.add(Restrictions.ge("date", from));
			if(to != null)
				criteria.add(Restrictions.le("date", to));
			purchaseInvoiceHeaders = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return purchaseInvoiceHeaders;
	}

}
