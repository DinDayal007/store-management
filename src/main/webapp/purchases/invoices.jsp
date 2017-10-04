<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.PurchaseInvoiceHeader"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<PurchaseInvoiceHeader> purchaseInvoiceHeaders = EntityService.getAllObjects(PurchaseInvoiceHeader.class);
%>
<jsp:include page="../header.jsp" />

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">متابعة فواتير الشراء</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">
			<a href="/store-management/purchases"><button class="btn btn-lg btn-primary">إضافة فاتورة شراء جديدة</button></a>
		</div>
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
							<th>طريقة الدفع</th>
							<th>المخزن</th>
							<th>الخزنة</th>
							<th>مشاهدة</th>
						</tr>
					</thead>
					<tbody>
					<% for(PurchaseInvoiceHeader purchaseInvoiceHeader : purchaseInvoiceHeaders){ %>
						<tr>
							<td><%= purchaseInvoiceHeader.getNumber() %></td>
							<td><%= purchaseInvoiceHeader.getDate() %></td>
							<td><%= purchaseInvoiceHeader.getUser().getName() %></td>
							<td><%= purchaseInvoiceHeader.getFinalTotal() %></td>
							<td><%= purchaseInvoiceHeader.getType() == 0 ? "فورى" : "اجل" %></td>
							<td><%= purchaseInvoiceHeader.getInventory().getName() %></td>
							<td><%= purchaseInvoiceHeader.getCache().getName() %></td>
							<td><a href="/store-management/purchases/invoice.jsp?id=<%= purchaseInvoiceHeader.getId() %>"><button class="btn btn-default"><i class="fa fa-eye"></i></button></a></td>
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

<jsp:include page="../footer.jsp" />