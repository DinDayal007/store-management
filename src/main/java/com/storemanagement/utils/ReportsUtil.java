package com.storemanagement.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovement;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Facility;
import com.storemanagement.entities.Profit;
import com.storemanagement.entities.PurchaseInvoiceHeader;
import com.storemanagement.entities.SalesInvoiceHeader;
import com.storemanagement.entities.Supplier;
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
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put sales invoices data
		for(SalesInvoiceHeader header : salesInvoiceHeaders){
			Map<String, Object> map = new HashMap<>();
			map.put("number", header.getNumber());
			map.put("date", header.getDate());
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
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Sales invoices report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Sales invoices report.pdf");
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
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put purchase invoices data
		for(PurchaseInvoiceHeader header : purchaseInvoiceHeaders){
			Map<String, Object> map = new HashMap<>();
			map.put("number", header.getNumber());
			map.put("date", header.getDate());
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
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Purchase invoices report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Purchase invoices report.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	//item movement report
	public void showItemMovementReport(HttpServletRequest request, HttpServletResponse response, int itemId) throws IOException{
		Map<String, Object> parameters = new HashMap<>();
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put the item id
		parameters.put("itemId", itemId);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/ItemMovement.jrxml").getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, JDBCUtil.getCon());
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Item movements report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Item movements report.pdf");
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
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put caches data
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
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Cache balance report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Cache balance report.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	//caches movement report
	public void showCachsMovementReport(HttpServletRequest request, HttpServletResponse response, List<CacheMovement> cacheMovements, int clientId, int supplierId) throws IOException{
		List<Map<String, ?>> ds = new ArrayList<>();
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put cache movements to the report
		for(CacheMovement cacheMovement : cacheMovements){
			Map<String, Object> map = new HashMap<>();
			int type = cacheMovement.getType();
			Client client = cacheMovement.getClient();
			if(type == 0 || type == 2 || type == 3){
				map.put("type", "سحب");
            	if(type == 0 || type == 2) map.put("client", "");
            	else {
            		if(client == null) map.put("client", "عميل نقدى");
            		else map.put("client", client.getName());
            	}
            }else if(type == 10) {
            	map.put("type", "مصروفات");
            }
			else{
            	map.put("type", "إيداع");
            	if(type == 1 || type == 5) map.put("client", "");
            	else {
            		if(client == null) map.put("client", "عميل نقدى");
            		else map.put("client", client.getName());
            	}
            }
			map.put("total", cacheMovement.getAmount());
			String title = "";
			if(clientId > 0) title = "كشف حساب العميل : " + client.getName();
			if(supplierId > 0) title = "كشف حساب المورد : " + cacheMovement.getSupplier().getName();
			if(clientId == 0 && supplierId == 0) title = "كشف حركات الخزنة";
			map.put("title", title);
			map.put("user", cacheMovement.getUser().getName());
			map.put("inventory", cacheMovement.getInventory().getName());
			map.put("cache", cacheMovement.getCache().getName());
			map.put("number", cacheMovement.getRefNumber());
			map.put("supplier", cacheMovement.getSupplier() == null ? "" : cacheMovement.getSupplier().getName());
			map.put("date", cacheMovement.getDate());
			map.put("description", cacheMovement.getDescription());
			ds.add(map);
		}
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/CacheMovementSum.jrxml").getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Cache movements report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Cache movements report.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	//clients debit report
	public void showClientsDebitReport(HttpServletRequest request, HttpServletResponse response, List<CacheMovement> cacheMovements, int clientId) throws IOException{
		List<Map<String, ?>> ds = new ArrayList<>();
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put client debits to the report
		for(CacheMovement cacheMovement : cacheMovements){
			Map<String, Object> map = new HashMap<>();
			Client client = cacheMovement.getClient();
			String title = "";
			if(clientId > 0) title = "كشف مديونيات العميل : " + client.getName();
			if(clientId == 0) title = "كشف مديونيات العملاء";
			map.put("title", title);
			map.put("user", cacheMovement.getUser().getName());
			map.put("inventory", cacheMovement.getInventory().getName());
			map.put("cache", cacheMovement.getCache().getName());
			map.put("number", cacheMovement.getRefNumber());
			map.put("client", client == null ? "" : client.getName());
			map.put("total", cacheMovement.getAmount());
			map.put("date", cacheMovement.getDate());
			map.put("type", cacheMovement.getType() == 0 ? "سحب" : "إيداع");
			map.put("description", cacheMovement.getDescription());
			ds.add(map);
		}
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/ClientsDebits.jrxml").getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Client debits report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Client debits report.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	//clients debit report
	public void showSuppliersDebitReport(HttpServletRequest request, HttpServletResponse response, List<CacheMovement> cacheMovements, int supplierId) throws IOException{
		List<Map<String, ?>> ds = new ArrayList<>();
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put client debits to the report
		for(CacheMovement cacheMovement : cacheMovements){
			Map<String, Object> map = new HashMap<>();
			Supplier supplier = cacheMovement.getSupplier();
			String title = "";
			if(supplierId > 0) title = "كشف مديونيات المورد : " + supplier.getName();
			if(supplierId == 0) title = "كشف مديونيات الموردين";
			map.put("title", title);
			map.put("user", cacheMovement.getUser().getName());
			map.put("inventory", cacheMovement.getInventory().getName());
			map.put("cache", cacheMovement.getCache().getName());
			map.put("number", cacheMovement.getRefNumber());
			map.put("client", supplier.getName());
			map.put("total", cacheMovement.getType() == 5 ? (cacheMovement.getAmount() * -1) : cacheMovement.getAmount());
			map.put("date", cacheMovement.getDate());
			map.put("type", cacheMovement.getType() == 0 ? "سحب" : "إيداع");
			map.put("description", cacheMovement.getDescription());
			ds.add(map);
		}
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/SuppliersDebits.jrxml").getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Supplier debits report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Client debits report.pdf");
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
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put cache movement data of report
		Map<String, Object> map = new HashMap<>();
		Client client = cacheMovement.getClient();
		if(client == null) map.put("client", "");
		else map.put("client", client.getName());
		map.put("id", cacheMovement.getId());
		map.put("user", cacheMovement.getUser().getName());
		map.put("inventory", cacheMovement.getInventory().getName());
		map.put("cache", cacheMovement.getCache().getName());
		map.put("number", cacheMovement.getRefNumber());
		map.put("supplier", cacheMovement.getSupplier() == null ? "" : cacheMovement.getSupplier().getName());
		map.put("date", cacheMovement.getDate());
		if(cacheMovement.getType() == 0)
			map.put("type", "إيصال سحب نقدية");
		else if(cacheMovement.getType() == 10)
			map.put("type", "إيصال مصروفات");
		else map.put("type", "إيصال إيداع نقدية");
		map.put("description", cacheMovement.getDescription());
		map.put("total", cacheMovement.getAmount());
		ds.add(map);
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/CacheMovement.jrxml").getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Cache movement report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Cache movement report.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	//sales invoice report
	public void showSalesInvoiceReport(HttpServletRequest request, HttpServletResponse response, long invoiceId) throws IOException{
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put the invoice id
		parameters.put("id", Integer.parseInt(String.valueOf(invoiceId)));
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/SalesInvoice2.jrxml").getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, JDBCUtil.getCon());
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Sales invoice report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Sales invoice report.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//purchase invoice report
	public void showPurchaseInvoiceReport(HttpServletRequest request, HttpServletResponse response, long invoiceId) throws IOException{
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put the invoice id
		parameters.put("id", Integer.parseInt(String.valueOf(invoiceId)));
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/PurchaseInvoice.jrxml").getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, JDBCUtil.getCon());
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Purchase invoice report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Purchase invoice report.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//show transfer items report
	public void showTransferItemsReport(HttpServletRequest request, HttpServletResponse response, int transferId) throws IOException{
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put the transfer id
		parameters.put("id", transferId);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/TransferItems.jrxml").getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, JDBCUtil.getCon());
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Transfer items report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Transfer items report.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//show item quantities report
	public void showItemQuantitiesReport(HttpServletRequest request, HttpServletResponse response, String data) throws IOException{
		String fileName = "";
		if(null == data) fileName = "reports/ItemQuantities.jrxml";
		else if(data.equals("min")) fileName = "reports/ItemMin.jrxml";
		else if(data.equals("max")) fileName = "reports/ItemMax.jrxml";
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, JDBCUtil.getCon());
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Item balance report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Item balance report.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//show profit margin report
	public void showProfitReport(HttpServletRequest request, HttpServletResponse response, List<Profit> profits) throws IOException{
		List<Map<String, ?>> ds = new ArrayList<>();
		Facility facility = (Facility) request.getSession().getAttribute("facility");
		//put facility information
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("facilityName", facility.getName());
		parameters.put("facilityLocation", facility.getGovernorate() + " - " + facility.getCity());
		parameters.put("facilityAddress", facility.getAddress());
		parameters.put("facilityPhone", facility.getPhone());
		parameters.put("facilityMobiles", facility.getMobile1() + " / " + facility.getMobile2());
		parameters.put("facilityInfo", facility.getMoreInfo());
		//put profits data
		for(Profit profit : profits){
			Map<String, Object> map = new HashMap<>();
			map.put("invNumber", profit.getSalesInvoiceHeader().getNumber());
			map.put("invDate", profit.getSalesInvoiceHeader().getDate());
			map.put("itemCode", profit.getItem().getCode());
			map.put("itemName", profit.getItem().getName());
			map.put("itemPurchasePrice", profit.getItem().getPurchasePrice());
			map.put("price", profit.getPrice());
			map.put("qty", profit.getQty());
			map.put("profit", profit.getProfit());
			ds.add(map);
		}
		JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("reports/ProfitMargin.jrxml").getFile());
		InputStream inputStream = new FileInputStream(file);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("contentType", "application/pdf");
			response.addHeader("Content-disposition", "filename=Profit margin report.pdf");
			//response.addHeader("Content-disposition", "attachement; filename=Profit margin report.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
