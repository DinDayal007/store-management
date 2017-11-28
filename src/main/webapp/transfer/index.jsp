<%@page import="com.storemanagement.entities.Cache"%>
<%@page import="com.storemanagement.entities.Unit"%>
<%@page import="com.storemanagement.entities.Branch"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Branch> branchs = (List<Branch>) request.getAttribute("branchs");
List<Unit> units = (List<Unit>) request.getAttribute("units");
List<Cache> caches = (List<Cache>) request.getAttribute("caches");
%>
<jsp:include page="../header.jsp" />

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">التحويلات بين المخازن</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<!-- /.row -->
<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="row">
			
				<div class="col-md-4">
					<div class="form-group">
						<label for="branchFrom">اختر الفرع</label>
						<select name="branchFrom" id="branchFrom" class="form-control">
							<option value="">اختر الفرع</option>
							<% for(Branch branch : branchs){ %>
							<option value="<%= branch.getId() %>"><%= branch.getName() %></option>
							<% } %>
						</select>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="inventoryFrom">اختر المخزن الذى سيتم التحويل منه</label>
						<select name="inventoryFrom" id="inventoryFrom" class="form-control">
							<option value="">اختر المخزن</option>
							
						</select>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="cacheFrom">اختر الخزنة المراد تحويل المال منها</label>
						<select name="cacheFrom" id="cacheFrom" class="form-control">
							<% for(Cache cache : caches){ %>
							<option value="<%= cache.getId() %>"><%= cache.getName() %></option>
							<% } %>
						</select>
					</div>
				</div>
								
			</div>
			
			<div class="row">
			
				<div class="col-md-4">
					<div class="form-group">
						<label for="branchTo">اختر الفرع</label>
						<select name="branchTo" id="branchTo" class="form-control">
							<option value="">اختر الفرع</option>
							<% for(Branch branch : branchs){ %>
							<option value="<%= branch.getId() %>"><%= branch.getName() %></option>
							<% } %>
						</select>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="inventoryTo">اختر المخزن المراد التحويل إليه</label>
						<select name="inventoryTo" id="inventoryTo" class="form-control">
							<option value="">اختر المخزن</option>
							
						</select>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="cacheTo">اختر الخزنة المراد تحويل المال اليها</label>
						<select name="cacheTo" id="cacheTo" class="form-control">
							<% for(Cache cache : caches){ %>
							<option value="<%= cache.getId() %>"><%= cache.getName() %></option>
							<% } %>
						</select>
					</div>
				</div>
								
			</div>
			
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label for="item">اختر الصنف المراد تحويله</label>
						<div style="overflow: hidden;">
							<select name="item" id="item" class="form-control" style="float: right; width: 85%">
								<option value="">اختر الصنف</option>							
							</select>
							<button class="btn btn-success" id="add_item" type="button" style="float: left"><i class="fa fa-plus" aria-hidden="true"></i></button>
						</div>
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
							<th>عدد القطع بالمخزن</th>
							<th>الكمية</th>
							<th>السعر</th>
							<th>الإجمالى</th>
							<th>حذف</th>
						</tr>
					</thead>
					<tbody id="item-rows">
					</tbody>
				</table>	
			</div>
		</div>
		
	</div>
</div>	

<jsp:include page="../footer.html" />
<script>
	$(document).ready(function(){
		//get inventories from branchFrom
		$('#branchFrom').change(function(){
			var branchFrom = $(this).val();
			if(branchFrom != ''){
				$.ajax({
					url : "/store-management-system/transfer",
					method : "POST",
					data : {branch : branchFrom, action : "1"},
					dataType : "text",
					success : function(data){
						$('#inventoryFrom').html(data);
					}
				});
			}
		});
		
		//get inventories from branchTo
		$('#branchTo').change(function(){
			var branchTo = $(this).val();
			if(branchTo != ''){
				$.ajax({
					url : "/store-management-system/transfer",
					method : "POST",
					data : {branch : branchTo, action : "1"},
					dataType : "text",
					success : function(data){
						$('#inventoryTo').html(data);
					}
				});
			}
		});
		
		//get items from inventory
		$('#inventoryFrom').change(function(){
			var inventoryId = $(this).val();
			if(inventoryId != ''){
				$.ajax({
					url : "/store-management-system/transfer",
					method : "POST",
					data : {inventoryId : inventoryId, action : "2"},
					dataType : "text",
					success : function(data){
						$('#item').html(data);
					}
				});
			}
		});
		
		//add new row to details
		var finalTotal = null;
		var i = 0;
		$('#add_item').click(function(){
			var inventoryFrom = $('#inventoryFrom').val();
			var inventoryTo = $('#inventoryTo').val();
			var item = $('#item').val();
			if(inventoryFrom == '' || inventoryTo == '' || item == ''){
				alert('يجب ملأ جميع مدخلات البحث');
			}else{
				var itemId = $('#item option:selected').data('id');
				var itemName = $('#item option:selected').text();
				var itemCode = $('#item').find(':selected').data('code');
				var itemQty = $('#item option:selected').data('qty');
				var itemPrice = $('#item option:selected').data('price');
				addRows(itemId, itemName, itemCode, itemQty, itemPrice);
			}
		});
		
		//add rows function
		function addRows(itemId, itemName, itemCode, itemQty, itemPrice){
			i++;
			$('#item-rows').append('<tr id="row' + i + '">' + 
			'<input type="hidden" class="itemId" name="itemId[]" value = "' + itemId + '"/>' +
			'<td><input class="form-control" value="' + itemCode + '" type="text" name="item_code[]" disabled /></td>' +
			'<td><input class="form-control" value="' + itemName + '" type="text" name="item_name[]" disabled /></td>' + 
			'<td><input class="form-control" value="' + itemQty + '" type="number" disabled /></td>' + 
			'<td><input class="form-control itemQty" data-inventory="' + itemQty + '" value="1" type="number" name="itemQty[]" min="1" autofocus /></td>' +
			'<td><input class="form-control itemPrice" value="' + itemPrice + '" type="number" name="itemPrice[]" min="1" /></td>' +
			'<td><input class="form-control itemTotal" value="' + itemPrice +'" type="number" name="itemTotal[]" min="1" disabled /></td>' +
			'<td><button class="btn btn-danger btn-remove" name="remove" id="' + i + '"><i class="fa fa-close"></i></button></td>' +
			'</tr>');
			//sumTotal();
		}
		
		//remove item from invoice
		$(document).on('click', '.btn-remove', function(){
			var buttonId = $(this).attr('id');
			$('#row' + buttonId).remove();
			sumTotal();
		});
	})
</script>