<%@page import="com.storemanagement.entities.Cache"%>
<%@page import="com.storemanagement.entities.Inventory"%>
<%@page import="com.storemanagement.entities.Client"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
<%
List<Client> clients = (List<Client>) request.getAttribute("clients");
List<Inventory> inventories = (List<Inventory>) request.getAttribute("inventories");
List<Cache> caches = (List<Cache>) request.getAttribute("caches");
%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">فاتورة بيع جديدة</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">

	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="row">
				<div class="col-md-4">
					<div class="form-group form-inline">
						<label for="inv_num">رقم الفاتورة</label> 
						<input style="width: 40%" class="form-control" type="number" id="inv_num" name="inv_num" value="1" disabled />
					</div>
					<div class="form-group form-inline">
						<label for="inv_type">نوع الفاتورة</label> 
						<select class="form-control" id="inv_type" name="inv_type">
							<option value="0">فوري</option>
							<option value="1">آجل</option>
						</select>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group form-inline">
						<label for="inv_date">التاريخ</label> 
						<input style="width: 40%" class="form-control" type="text" id="inv_date" name="inv_date" value="10/11/2017" disabled />
					</div>
					<div class="form-group form-inline">
						<label for="store">المخزن</label> 
						<select class="form-control" id="store" name="store">
							<% for(Inventory inventory : inventories){ %>
							<option value="<%= inventory.getId() %>"><%= inventory.getName() %></option>
							<% } %>
						</select>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group form-inline">
						<label for="client">العميل</label> 
						<select class="form-control" id="client" name="client">
							<option value="0">عميل نقدي</option>
							<% for(Client client : clients){ %>
							<option value="<%= client.getId() %>"><%= client.getName() %></option>
							<% } %>
						</select>
					</div>
					<div class="form-group form-inline">
						<label for="cache">الخزنة</label> 
						<select class="form-control" id="cache" name="cache">
							<% for(Cache cache : caches){ %>
							<option value="<%= cache.getId() %>"><%= cache.getName() %></option>
							<% } %>
						</select>
					</div>
				</div>
			</div>
		</div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>كود الصنف</th>
							<th>اسم الصنف</th>
							<th>السعر</th>
							<th>الكمية</th>
							<th>الإجمالى</th>
							<th>حذف</th>
							<th><button class="btn btn-success"><i class="fa fa-plus"></i></button></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="form-control" type="text" autofocus /></td>
							<td><input class="form-control" type="text" /></td>
							<td><input class="form-control" type="number" min="1" disabled /></td>
							<td><input class="form-control" type="number" min="1" /></td>
							<td><input class="form-control" type="number" min="1" disabled /></td>
							<td><button class="btn btn-danger"><i class="fa fa-close"></i></button></td>
						</tr>
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