package com.storemanagement.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.SalesInvoiceHeader;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportsUtil {
	//sales invoices report
	public void showSalesInvoicesReport(HttpServletRequest request, HttpServletResponse response, List<SalesInvoiceHeader> salesInvoiceHeaders) throws IOException{
		List<Map<String, ?>> ds = new ArrayList<>();
		for(SalesInvoiceHeader header : salesInvoiceHeaders){
			Map<String, Object> map = new HashMap<>();
			map.put("number", header.getNumber());
			map.put("date", header.getDate().toString());
			map.put("discount", header.getDiscount());
			map.put("tax", header.getTax() + " %");
			map.put("total", header.getTotal() + " EGP");
			Client client = header.getClient();
			if(null == client) map.put("client", "عميل نقدى");
			else map.put("client", client.getName());
			map.put("inventory", header.getInventory().getName());
			map.put("cache", header.getCache().getName());
			map.put("user", header.getUser().getName());
			ds.add(map);
		}
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/SalesInvoices.jrxml").getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	//purchase invoices report
	public void showPurchaseInvoicesReport(HttpServletRequest request, HttpServletResponse response, List<PurchaseInvoiceHeader> purchaseInvoiceHeaders) throws IOException{
		List<Map<String, ?>> ds = new ArrayList<>();
		for(PurchaseInvoiceHeader header : purchaseInvoiceHeaders){
			Map<String, Object> map = new HashMap<>();
			map.put("number", header.getNumber());
			map.put("date", header.getDate().toString());
			map.put("total", header.getTotal() + " EGP");
			map.put("supplier", header.getSupplier().getName());
			map.put("inventory", header.getInventory().getName());
			map.put("cache", header.getCache().getName());
			map.put("user", header.getUser().getName());
			ds.add(map);
		}
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/PurchaseInvoices.jrxml").getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
