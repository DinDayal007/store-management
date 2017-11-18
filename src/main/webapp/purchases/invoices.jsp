<%@page import="com.storemanagement.entities.Supplier"%>
<%@page import="com.storemanagement.entities.Inventory"%>
<%@page import="com.storemanagement.entities.Cache"%>
<%@page import="com.storemanagement.services.UserService"%>
<%@page import="com.storemanagement.entities.User"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.PurchaseInvoiceHeader"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<PurchaseInvoiceHeader> purchaseInvoiceHeaders = EntityService.getAllObjects(PurchaseInvoiceHeader.class);
List<User> users = UserService.getUsers();
List<Cache> caches = EntityService.getAllObjects(Cache.class);
List<Inventory> inventories = EntityService.getAllObjects(Inventory.class);
List<Supplier> suppliers = EntityService.getAllObjects(Supplier.class);
request.setAttribute("title", "متابعة فواتير الشراء");
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
			<div class="row">
				<form action="/store-management-system/reports" method="get" target="_blank">
					<div class="col-md-4">
						<div class="form-group">
							<input type="hidden" name="r" value="p" />
							<label for="user">اختر المستخدم</label>
							<select name="user" id="user" class="form-control">
								<option value="">اختر المستخدم</option>
								<% for(User user : users){ %>
								<option value="<%= user.getId() %>"><%= user.getName() %></option>
								<% } %>
							</select>
						</div>
						<div class="form-group">
							<label for="type">طريقة الدفع</label>
		                    <select name="type" id="type" class="form-control">
								<option value="">اختر طريقة الدفع</option>
								<option value="0">فورى</option>
								<option value="1">آجل</option>
							</select>
						</div>
						<div class="form-group">
							<label for="client">المورد</label>
		                    <select name="supplier" id="supplier" class="form-control">
								<option value="">اختر المورد</option>
								<% for(Supplier supplier : suppliers){ %>
								<option value="<%= supplier.getId() %>"><%= supplier.getName() %></option>
								<% } %>
							</select>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="form-group">
							<label for="from">من تاريخ</label>
		                    <input type="date" class="form-control" name="from" id="from" />
						</div>
						<div class="form-group">
							<label for="cache">الخزنة</label>
		                    <select name="cache" id="cache" class="form-control">
								<option value="">اختر الخزنة</option>
								<% for(Cache cache : caches){ %>
								<option value="<%= cache.getId() %>"><%= cache.getName() %></option>
								<% } %>
							</select>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="form-group">
							<label for="to">إلى تاريخ</label>
	                        <input type="date" class="form-control" name="to" id="to" />
						</div>
						<div class="form-group">
							<label for="inventory">المخزن</label>
		                    <select name="inventory" id="inventory" class="form-control">
								<option value="">اختر المخزن</option>
								<% for(Inventory inventory : inventories){ %>
								<option value="<%= inventory.getId() %>"><%= inventory.getName() %></option>
								<% } %>
							</select>
						</div>
						<div class="form-group text-left" style="margin-top: 40px">
							<button type="button" class="btn btn-default" id="searchInvoicesBtn">بحث</button>
							<input type="submit" class="btn btn-primary" value="طباعة فواتير المشتريات" />
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="table-responsive" id="invoicesData">
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
							<td><%= purchaseInvoiceHeader.getTotal() %></td>
							<td><%= purchaseInvoiceHeader.getType() == 0 ? "فورى" : "اجل" %></td>
							<td><%= purchaseInvoiceHeader.getInventory().getName() %></td>
							<td><%= purchaseInvoiceHeader.getCache().getName() %></td>
							<td><a href="/store-management-system/purchases/invoice.jsp?id=<%= purchaseInvoiceHeader.getId() %>"><button class="btn btn-default"><i class="fa fa-eye"></i></button></a></td>
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


<script>
	$(document).ready(function(){
		$('#searchInvoicesBtn').click(function(){
			var userId = $('#user').val();
			var from = $('#from').val();
			var to = $('#to').val();
			var paymentType = $('#type').val();
			var cacheId = $('#cache').val();
			var inventoryId = $('#inventory').val();
			var supplierId = $('#supplier').val();
			$.ajax({
				url : "/store-management-system/purchases",
				method : "POST",
				data : {
					userId : userId, from : from, to : to, paymentType : paymentType, 
					cacheId : cacheId, inventoryId : inventoryId, supplierId : supplierId,
					action : "5"
				},
				success : function(data){
					if(data){
						$('#invoicesData').html(data);
					} else alert('من فضلك اختر مدخلات البحث');
				}
			});
		});
	})
</script>