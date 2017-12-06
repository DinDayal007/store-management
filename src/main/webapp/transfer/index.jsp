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
			<!-- /.table-responsive -->
		</div>
		
		<div class="panel-footer">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group form-inline">
						<label for="totalPrice">إجمالى المبلغ الذى سيتم تحويله</label>
						<input type="number" style="width: 40%;" class="form-control" value="0" name="totalPrice" id="totalPrice" readonly />
					</div>
				</div>
				<div class="col-md-3 col-md-offset-3 text-left">
					<div class="form-group form-inline">
						<input type="hidden" name="action" value="save" />
						<input type="submit" id="saveTransferBtn" class="btn btn-primary" value="حفظ التحويل" />
						<a href="" id="exitBtn"><button class="btn btn-default" type="button">خروج</button></a>
					</div>
				</div>
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
			}else if(inventoryFrom == inventoryTo)
				alert('يجب اختيار مخزنين مختلفين للتحويل بينهم');
			else{
				var itemId = $('#item option:selected').data('id');
				var itemName = $('#item option:selected').text();
				var itemCode = $('#item').find(':selected').data('code');
				var itemQty = $('#item option:selected').data('qty');
				var itemPrice = $('#item option:selected').data('price');
				addRows(itemId, itemName, itemCode, itemQty, itemPrice);
				disableSearchFields();
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
			sumTotal();
		}
		
		function disableSearchFields(){
			$('#branchFrom').attr('disabled', 'disabled');
			$('#branchTo').attr('disabled', 'disabled');
			
			$('#inventoryFrom').attr('disabled', 'disabled');
			$('#inventoryTo').attr('disabled', 'disabled');
			
			$('#cacheFrom').attr('disabled', 'disabled');
			$('#cacheTo').attr('disabled', 'disabled');
		}
		
		function enableSearchFields(){
			$('#branchFrom').removeAttr('disabled');
			$('#branchTo').removeAttr('disabled');
			
			$('#inventoryFrom').removeAttr('disabled');
			$('#inventoryTo').removeAttr('disabled');
			
			$('#cacheFrom').removeAttr('disabled');
			$('#cacheTo').removeAttr('disabled');
		}
		
		//remove item from invoice
		$(document).on('click', '.btn-remove', function(){
			var buttonId = $(this).attr('id');
			$('#row' + buttonId).remove();
			sumTotal();
			var tot = $('#totalPrice').val();
			if(tot == 0) enableSearchFields();
		});
		
		//sum total for the totalPrice and finalTotal
		function sumTotal(){
			var sum = 0;
			$('.itemTotal').each(function(){
				sum += parseFloat($(this).val());
			});
			$('#totalPrice').val(sum);
		}
		
		//modify total price each time a change is happened to quantity
		$(document).on('change keyup', '.itemQty', function(){
			var price = $(this).closest('td').next().find('input').val();
			var inventory = $(this).data('inventory');
			var qty = $(this).val();
			if(qty <= 0) {
				alert("كمية الصنف يجب أن تكون أكبر من صفر");
				$(this).val(1);
				$(this).closest('td').next().next().find('input').val(price);
			} else if(inventory - qty < 0) {
				alert("كمية الصنف فى المخزن غير كافية");
				$(this).val(1);
				$(this).closest('td').next().next().find('input').val(price);
			} else{
				$(this).closest('td').next().next().find('input').val(qty * price);
			}
			sumTotal();
		});
		
		//modify total price each time a change is happened to price
		$(document).on('change keyup', '.itemPrice', function(){
			var price = $(this).val();
			var qty = $(this).closest('td').prev().find('input').val();
			$(this).closest('td').next().find('input').val(qty * price);
			sumTotal();
		});
		
		//submit the values
		$('#saveTransferBtn').click(function(){
			//send transferDetails data
			var itemIds = $('input.itemId[type=hidden]').map(function() {
			       return $(this).val(); }).get().join();
			var itemQty = $('input.itemQty[type=number]').map(function() {
			       return $(this).val(); }).get().join();
			var itemPrice = $('input.itemPrice[type=number]').map(function() {
			       return $(this).val(); }).get().join();
			var itemTotal = $('input.itemTotal[type=number]').map(function() {
			       return $(this).val(); }).get().join();
			
			if(itemIds.length < 1) alert('يجب إضافة على الأقل صنف واحد لتحويله');
			else{
				enableSearchFields();
				//send transferHeader data
				var inventoryFrom = $('#inventoryFrom').val();
				var inventoryTo = $('#inventoryTo').val();
				var cacheFrom = $('#cacheFrom').val();
				var cacheTo = $('#cacheTo').val();
				var totalPrice = $('#totalPrice').val();
				
				$.ajax({
					url : "/store-management-system/transfer",
					method : "POST",
					data : {
						inventoryFrom : inventoryFrom, inventoryTo : inventoryTo,
						cacheFrom : cacheFrom, cacheTo : cacheTo, totalPrice : totalPrice,
						itemIds : itemIds, itemQty : itemQty,
						itemPrice : itemPrice, itemTotal : itemTotal, action : "save"
					},
					dataType : "text",
					beforeSend : function(){
						$('#saveTransferBtn').val('جار الطباعة');
						$('#saveTransferBtn').attr('disabled', 'disabled');
						$('#exitBtn').addClass('hidden');
					},
					success : function(data){
						if(data){
							console.log("done: " + data);
							window.location.replace('/store-management-system/reports?r=ti&id=' + data);
						}
					}
				});
			}
		});
	})
</script>