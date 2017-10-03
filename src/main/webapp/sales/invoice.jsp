<%@page import="java.util.List"%>
<%@page import="com.storemanagement.entities.SalesInvoiceDetails"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.SalesInvoiceHeader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int id = Integer.parseInt(request.getParameter("id"));
SalesInvoiceHeader salesInvoiceHeader = (SalesInvoiceHeader) EntityService.getObject(SalesInvoiceHeader.class, id);
List<SalesInvoiceDetails> invoiceDetails = (List<SalesInvoiceDetails>) salesInvoiceHeader.getSalesInvoiceDetails();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | متابعة فاتورة البيع</title>

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
                        <h3 class="panel-title" style="float: right;">فاتورة البيع</h3>
                        <a href="/store-management/sales/invoices.jsp" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body" style="overflow: hidden; text-align: center;">
                    	<div class="row">
                    		<div class="col-md-6">
                    			<label class="key">رقم الفاتورة</label>
                    			<label class="value"><%= salesInvoiceHeader.getNumber() %></label>
                    			
                    			<label class="key">تاريخ الفاتورة</label>
                    			<label class="value"><%= salesInvoiceHeader.getDate() %></label>
                    			
                    			<label class="key">طريقة الدفع</label>
                    			<label class="value"><%= salesInvoiceHeader.getType() == 0 ? "فورى" : "آجل" %></label>
	                        	
	                        	<label class="key">المخزن</label>
                    			<label class="value"><%= salesInvoiceHeader.getInventory().getName() %></label>
                    			
	                        	<label class="key">الخزنة</label>
	                        	<label class="value"><%= salesInvoiceHeader.getCache().getName() %></label>
	                        	
	                        	<label class="key">العميل</label>
                    			<label class="value"><%= salesInvoiceHeader.getClient() == null ? "عميل نقدي" : salesInvoiceHeader.getClient().getName() %></label>
                    			
                    			<label class="key">مستخدم النظام</label>
	                        	<label class="value"><%= salesInvoiceHeader.getUser().getName() %></label>
                    		</div>
                    		<div class="col-md-6">
                    			<label class="key">إجمالى الفاتورة</label>
	                        	<label class="value"><%= salesInvoiceHeader.getTotal() + " EGP" %></label>
                    			                    			             			
                    			<label class="key">قيمة الخصم</label>
                    			<label class="value"><%= salesInvoiceHeader.getDiscount() %></label>
                    			
                    			<label class="key">قيمة الضريبة</label>
                    			<label class="value"><%= salesInvoiceHeader.getTax() + " %" %></label>
                    			
	                        	<label class="key">الاجمالى النهائى</label>
	                        	<label class="value"><%= salesInvoiceHeader.getFinalTotal() + " EGP" %></label>
	                        	
	                        	<label class="key">المدفوع</label>
                    			<label class="value"><%= salesInvoiceHeader.getPaid() + " EGP" %></label>
                    			                    			
                    			<label class="key">الباقى</label>
                    			<label class="value"><%= salesInvoiceHeader.getRemain() + " EGP" %></label>
                    		</div>
                    	</div>
                    	<h3>تفاصيل الفاتورة</h3>
                        <div class="table-responsive" style="margin-top: 20px">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>كود الصنف</th>
										<th>اسم الصنف</th>
										<th>السعر</th>
										<th>الكمية</th>
										<th>الإجمالى</th>
									</tr>
								</thead>
								<tbody>
									<% for(SalesInvoiceDetails detail : invoiceDetails){ %>
									<tr>
										<td><%= detail.getItem().getCode() %></td>
										<td><%= detail.getItem().getName() %></td>
										<td><%= detail.getItem().getPrice() %></td>
										<td><%= detail.getQty() %></td>
										<td><%= detail.getPrice() %></td>
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