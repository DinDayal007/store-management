package com.storemanagement.services;
import java.util.List;
import org.hibernate.criterion.Projections;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.ReturnPurchaseInvoiceHeader;
import com.storemanagement.entities.ReturnSalesInvoiceHeader;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.entities.Supplier;
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
	public static long getSalesInvoicesNumber(){
		long number = 0;
		try {
			openSession();
			number = (Long) getSession().createCriteria(SalesInvoiceHeader.class).setProjection(Projections.max("number")).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return number + 1;
	}
	//return purchase invoice max number
	public static long getPurchaseInvoicesNumber(){
		long number = 0;
		try {
			openSession();
			number = (Long) getSession().createCriteria(PurchaseInvoiceHeader.class).setProjection(Projections.max("number")).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return number + 1;
	}
	//return sales return invoice max number
	public static long getReturnSalesInvoicesNumber(){
		long number = 0;
		try {
			openSession();
			number = (Long) getSession().createCriteria(ReturnSalesInvoiceHeader.class).setProjection(Projections.max("number")).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return number + 1;
	}
	//return purchase return invoice max number
	public static long getReturnPurchaseInvoicesNumber(){
		long number = 0;
		try {
			openSession();
			number = (Long) getSession().createCriteria(ReturnPurchaseInvoiceHeader.class).setProjection(Projections.max("number")).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return number + 1;
	}
}
