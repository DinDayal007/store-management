<%@page import="com.storemanagement.entities.ReturnPurchaseInvoiceDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.ReturnPurchaseInvoiceHeader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int id = Integer.parseInt(request.getParameter("id"));
ReturnPurchaseInvoiceHeader returnPurchaseInvoiceHeader = (ReturnPurchaseInvoiceHeader) EntityService.getObject(ReturnPurchaseInvoiceHeader.class, id);
List<ReturnPurchaseInvoiceDetails> invoiceDetails = (List<ReturnPurchaseInvoiceDetails>) returnPurchaseInvoiceHeader.getReturnPurchaseInvoiceDetails();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | متابعة فاتورة مرتجع الشراء</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="../css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="../css/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
	<style>
		.key{width: 40%; margin-bottom: 10px;}
		.value{width: 40%; margin-bottom: 10px;}
		.login-panel{margin-top: 5%}
	</style>
</head>
<body>

    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading" style="overflow: hidden;">
                        <h3 class="panel-title" style="float: right;">فاتورة مرتجع الشراء</h3>
                        <a href="/store-management/purchases/return-invoices.jsp" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body" style="overflow: hidden; text-align: center;">
                    	<div class="row">
                    		<div class="col-md-6">
                    			<label class="key">رقم الفاتورة</label>
                    			<label class="value"><%= returnPurchaseInvoiceHeader.getNumber() %></label>
                    			                    				                        	                    				                        	                    			
                    			<label class="key">مستخدم النظام</label>
	                        	<label class="value"><%= returnPurchaseInvoiceHeader.getUser().getName() %></label>
                    		</div>
                    		<div class="col-md-6">
                    			<label class="key">تاريخ الفاتورة</label>
                    			<label class="value"><%= returnPurchaseInvoiceHeader.getDate() %></label>
                    			
                    			<label class="key">إجمالى الفاتورة</label>
	                        	<label class="value" id="invTotal"><%= returnPurchaseInvoiceHeader.getTotal() + " EGP" %></label>
                    		</div>
                    	</div>
                    	<h3>تفاصيل الفاتورة</h3>
                        <div class="table-responsive" style="margin-top: 20px">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr class="header">
										<th>كود الصنف</th>
										<th>اسم الصنف</th>
										<th>الوحدة</th>
										<th>السعر</th>
										<th>الكمية</th>
										<th>الإجمالى</th>
									</tr>
								</thead>
								<tbody>
									<% for(ReturnPurchaseInvoiceDetails detail : invoiceDetails){ %>
									<tr class="detail">
										<td><%= detail.getItem().getCode() %></td>
										<td><%= detail.getItem().getName() %></td>
										<td><%= detail.getUnit().getName() %></td>
										<td><%= detail.getPrice() %></td>
										<td><%= detail.getQty() %></td>
										<td><%= detail.getTotal() %></td>
									</tr>
									<% } %>
								</tbody>
							</table>
						</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery Version 1.11.0 -->
    <script src="../js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>