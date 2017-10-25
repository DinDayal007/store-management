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
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovement;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.ItemMovement;
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
			map.put("total", header.getFinalTotal());
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
			map.put("total", header.getTotal());
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
	//item movements report
	public void showItemMovementsReport(HttpServletRequest request, HttpServletResponse response, List<ItemMovement> itemMovements) throws IOException{
		List<Map<String, ?>> ds = new ArrayList<>();
		for(ItemMovement itemMovement : itemMovements){
			Map<String, Object> map = new HashMap<>();
			map.put("invNumber", itemMovement.getInvNumber());
			map.put("date", itemMovement.getDate());
			map.put("inventoryName", itemMovement.getInventoryName());
			map.put("clientName", itemMovement.getClientName());
			map.put("cacheName", itemMovement.getCacheName());
			map.put("itemName", itemMovement.getItemName());
			map.put("subGroup", itemMovement.getSubGroupName());
			map.put("mainGroup", itemMovement.getMainGroupName());
			map.put("itemQty", itemMovement.getItemQty());
			map.put("itemMinLimit", itemMovement.getItemMinLimit());
			map.put("itemMaxLimit", itemMovement.getItemMaxLimit());
			map.put("itemHome", itemMovement.getItemHome());
			map.put("finalTotal", itemMovement.getInvFinalTotal());
			map.put("invType", itemMovement.getInvType());
			ds.add(map);
		}
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/ItemMovements.jrxml").getFile());
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
	//caches quantity report
	public void showCachsQtyReport(HttpServletRequest request, HttpServletResponse response, List<Cache> caches) throws IOException{
		List<Map<String, ?>> ds = new ArrayList<>();
		for(Cache cache : caches){
			Map<String, Object> map = new HashMap<>();
			map.put("name", cache.getName());
			map.put("qty", cache.getQty());
			ds.add(map);
		}
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/CachesQty.jrxml").getFile());
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
	//caches movement report
	public void showCachsMovementReport(HttpServletRequest request, HttpServletResponse response, List<CacheMovement> cacheMovements) throws IOException{
		List<Map<String, ?>> ds = new ArrayList<>();
		for(CacheMovement cacheMovement : cacheMovements){
			Map<String, Object> map = new HashMap<>();
			int type = cacheMovement.getType();
			Client client = cacheMovement.getClient();
			if(type == 0 || type == 2 || type == 3){
				map.put("total", cacheMovement.getAmount() * -1);
            	if(type == 0 || type == 2) map.put("client", "");
            	else {
            		if(client == null) map.put("client", "عميل نقدى");
            		else map.put("client", client.getName());
            	}
            }else{
            	map.put("total", cacheMovement.getAmount());
            	if(type == 1 || type == 5) map.put("client", "");
            	else {
            		if(client == null) map.put("client", "عميل نقدى");
            		else map.put("client", client.getName());
            	}
            }
			map.put("user", cacheMovement.getUser().getName());
			map.put("inventory", cacheMovement.getInventory().getName());
			map.put("cache", cacheMovement.getCache().getName());
			map.put("number", cacheMovement.getRefNumber());
			//map.put("client", client == null ? "" : client.getName());
			map.put("supplier", cacheMovement.getSupplier() == null ? "" : cacheMovement.getSupplier().getName());
			map.put("date", cacheMovement.getDate().toString());
			map.put("type", cacheMovement.getType() == 0 ? "سحب" : "إيداع");
			map.put("description", cacheMovement.getDescription());
			ds.add(map);
		}
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/CacheMovementSum.jrxml").getFile());
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
	//single cache movement report
	public void showSingleCacheMovementReport(HttpServletRequest request, HttpServletResponse response, CacheMovement cacheMovement) throws IOException{
		List<Map<String, ?>> ds = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("id", cacheMovement.getId());
		map.put("user", cacheMovement.getUser().getName());
		map.put("inventory", cacheMovement.getInventory().getName());
		map.put("cache", cacheMovement.getCache().getName());
		map.put("number", cacheMovement.getRefNumber());
		map.put("client", cacheMovement.getClient() == null ? "" : cacheMovement.getClient().getName());
		map.put("supplier", cacheMovement.getSupplier() == null ? "" : cacheMovement.getSupplier().getName());
		map.put("date", cacheMovement.getDate());
		map.put("type", cacheMovement.getType() == 0 ? "سحب نقدية" : "إيداع نقدية");
		map.put("description", cacheMovement.getDescription());
		map.put("total", cacheMovement.getType() == 0 ? (cacheMovement.getAmount() * -1) : cacheMovement.getAmount());
		ds.add(map);
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/CacheMovement.jrxml").getFile());
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
	//sales invoice report
	public void showSalesInvoiceReport(HttpServletRequest request, HttpServletResponse response, SalesInvoiceHeader salesInvoiceHeader) throws IOException, JRException{
		
		List<SalesInvoiceHeader> headers = new ArrayList<>();
		headers.add(salesInvoiceHeader);
//		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(headers);
//		ClassLoader classLoader = getClass().getClassLoader();
//		File file = new File(classLoader.getResource("reports/SalesInvoice.jrxml").getFile());
//		InputStream inputStream = new FileInputStream(file);
//		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), beanCollectionDataSource);
//		response.setCharacterEncoding("UTF-8");
//		response.addHeader("Content-disposition", "attachment; filename=salesInvoiceReport.pdf");
//		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//		response.getOutputStream().flush();
//		response.getOutputStream().close();
		
		List<Map<String, ?>> ds = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("number", salesInvoiceHeader.getNumber());
		map.put("date", salesInvoiceHeader.getDate().toString());
		map.put("type", salesInvoiceHeader.getType() == 0 ? "فورى" : "آجل");
		map.put("inventory", salesInvoiceHeader.getInventory().getName());
		map.put("user", salesInvoiceHeader.getUser().getName());
		Client client = salesInvoiceHeader.getClient();
		if(null == client) map.put("client", "عميل نقدى");
		else map.put("client", salesInvoiceHeader.getClient().getName());
		map.put("total", salesInvoiceHeader.getTotal());
		map.put("discount", salesInvoiceHeader.getDiscount());
		map.put("tax", salesInvoiceHeader.getTax() + " %");
		map.put("finalTotal", salesInvoiceHeader.getFinalTotal());
		map.put("salesInvoiceDetails", salesInvoiceHeader.getSalesInvoiceDetails());
		ds.add(map);
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/SalesInvoice.jrxml").getFile());
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
