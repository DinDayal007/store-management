<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.ReturnPurchaseInvoiceHeader"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<ReturnPurchaseInvoiceHeader> returnPurchaseInvoiceHeaders = EntityService.getAllObjects(ReturnPurchaseInvoiceHeader.class);
request.setAttribute("title", "متابعة فواتير مرتجع الشراء");
%>
<jsp:include page="../header.jsp" />

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">متابعة فواتير مرتجع الشراء</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="panel panel-default">
		
		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover" id="dataTables-example">
					<thead>
						<tr>
							<th>رقم الفاتورة</th>
							<th>تاريخ الفاتورة</th>
							<th>المستخدم</th>
							<th>إجمالى الفاتورة</th>
							<th>فاتورة الشراء</th>
							<th>مشاهدة</th>
						</tr>
					</thead>
					<tbody>
					<% for(ReturnPurchaseInvoiceHeader returnPurchaseInvoiceHeader : returnPurchaseInvoiceHeaders){ %>
						<tr>
							<td><%= returnPurchaseInvoiceHeader.getNumber() %></td>
							<td><%= returnPurchaseInvoiceHeader.getDate() %></td>
							<td><%= returnPurchaseInvoiceHeader.getUser().getName() %></td>
							<td><%= returnPurchaseInvoiceHeader.getTotal() %> EGP</td>
							<td><a href="/store-management-system/purchases/invoice.jsp?id=<%= returnPurchaseInvoiceHeader.getPurchaseInvoiceHeader().getId() %>"><button class="btn btn-success"><i class="fa fa-eye"></i></button></a></td>
							<td><a href="/store-management-system/purchases/return-invoice.jsp?id=<%= returnPurchaseInvoiceHeader.getId() %>"><button class="btn btn-default"><i class="fa fa-eye"></i></button></a></td>
						</tr>
						<% } %>
					</tbody>
				</table>
			</div>
			<!-- /.table-responsive -->
		</div>
		<!-- /.panel-body -->
	</div>

</div>
</div>
<!-- /#page-wrapper -->

<jsp:include page="../footer.html" />