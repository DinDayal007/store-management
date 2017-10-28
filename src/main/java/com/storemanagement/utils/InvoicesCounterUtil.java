package com.storemanagement.utils;
import java.util.HashMap;
import java.util.List;
import com.storemanagement.services.InvoiceService;
public class InvoicesCounterUtil {
	/**
	 * 1 => sales invoice counter
	 * 2 => purchase invoice counter
	 * 3 => return sales invoice counter
	 * 4 => return purchase invoice counter
	 */
	private static HashMap<Integer, Long> invoicesCounter  = new HashMap<>();
	public static void initializeValues() {
		List<Long> invoiceNumbers = InvoiceService.getInvoicesNumbers();
		invoicesCounter.put(1, invoiceNumbers.get(0));
		invoicesCounter.put(2, invoiceNumbers.get(1));
		invoicesCounter.put(3, invoiceNumbers.get(2));
		invoicesCounter.put(4, invoiceNumbers.get(3));
	}
	public static HashMap<Integer, Long> getInvoicesCounter(){
		return invoicesCounter;
	}
	public static long getSalesInvoiceCounter() {
		return invoicesCounter.get(1);
	}
	public static long getPurchaseInvoiceCounter() {
		return invoicesCounter.get(2);
	}
	public static long getReturnSalesInvoiceCounter() {
		return invoicesCounter.get(3);
	}
	public static long getReturnSPurchaseInvoiceCounter() {
		return invoicesCounter.get(4);
	}
	//increment sales invoices counter
	public static void incrementSalesInvoiceCounter() {
		invoicesCounter.put(1, invoicesCounter.get(1) + 1);
	}
	//increment sales invoices counter
	public static void incrementPurchaseInvoiceCounter() {
		invoicesCounter.put(2, invoicesCounter.get(2) + 1);
	}
	//increment sales invoices counter
	public static void incrementReturnSalesInvoiceCounter() {
		invoicesCounter.put(3, invoicesCounter.get(3) + 1);
	}
	//increment sales invoices counter
	public static void incrementReturnPurechaseInvoiceCounter() {
		invoicesCounter.put(4, invoicesCounter.get(4) + 1);
	}
}
