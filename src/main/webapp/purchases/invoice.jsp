<%@page import="com.storemanagement.services.InvoiceService"%>
<%@page import="com.storemanagement.entities.PurchaseInvoiceDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.storemanagement.entities.PurchaseInvoiceHeader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int id = Integer.parseInt(request.getParameter("id"));
PurchaseInvoiceHeader purchaseInvoiceHeader = (PurchaseInvoiceHeader) InvoiceService.getObject(PurchaseInvoiceHeader.class, id);
List<PurchaseInvoiceDetails> invoiceDetails = (List<PurchaseInvoiceDetails>) purchaseInvoiceHeader.getPurchaseInvoiceDetails();
boolean hasReturnInvoice = InvoiceService.hasReturnPurchaseInvoice(purchaseInvoiceHeader);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | متابعة فاتورة الشراء</title>

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
                        <h3 class="panel-title" style="float: right;">فاتورة الشراء</h3>
                        <a href="/store-management/purchases/invoices.jsp" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body" style="overflow: hidden; text-align: center;">
                    	<div class="row">
                    		<div class="col-md-6">
                    			<label class="key">رقم الفاتورة</label>
                    			<label class="value"><%= purchaseInvoiceHeader.getNumber() %></label>
                    			
                    			<label class="key">طريقة الدفع</label>
                    			<label class="value"><%= purchaseInvoiceHeader.getType() == 0 ? "فورى" : "آجل" %></label>
	                        	
	                        	<label class="key">الخزنة</label>
	                        	<label class="value"><%= purchaseInvoiceHeader.getCache().getName() %></label>
	                        	<input type="hidden" id="cache" value="<%= purchaseInvoiceHeader.getCache().getId() %>" />
	                        	
                    			<label class="key">مستخدم النظام</label>
	                        	<label class="value"><%= purchaseInvoiceHeader.getUser().getName() %></label>
                    		</div>
                    		<div class="col-md-6">
                    			<label class="key">تاريخ الفاتورة</label>
                    			<label class="value"><%= purchaseInvoiceHeader.getDate() %></label>
                    			
                    			<label class="key">المخزن</label>
                    			<label class="value"><%= purchaseInvoiceHeader.getInventory().getName() %></label>
                    			
                    			<label class="key">المورد</label>
                    			<label class="value"><%= purchaseInvoiceHeader.getSupplier().getName() %></label>
                    			
                    			<label class="key">إجمالى الفاتورة</label>
	                        	<label class="value"  id="invTotal"><%= purchaseInvoiceHeader.getTotal() + " EGP" %></label>
                    		</div>
                    	</div>
                    	<h3>تفاصيل الفاتورة</h3>
                        <div class="table-responsive" style="margin-top: 20px">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr class="header">
										<th>كود الصنف</th>
										<th>اسم الصنف</th>
										<th>السعر</th>
										<th>الكمية</th>
										<th>الإجمالى</th>
									</tr>
								</thead>
								<tbody>
									<% for(PurchaseInvoiceDetails detail : invoiceDetails){ %>
									<tr class="detail">
										<input type="hidden" class="itemId" name="itemId[]" value = "<%= detail.getItem().getId() %>"/>
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
						<% if(!hasReturnInvoice){ %>
						<div class="row">
							<div class="col-md-2 col-md-offset-10">
								<button class="btn btn-primary" id="convertToReturn">تحويل لفاتورة مرتجع</button>
							</div>
							<div class="col-md-6 hidden" id="totalReturn">
								<label class="key">إجمالى الأصناف المرتجعة</label>
	                        	<label class="value" id="TotalReturnValue">0 EGP</label>
<!-- 	                        	<label class="key">إجمالى فاتورة المرتجع النهائى</label> -->
<!-- 	                        	<label class="value" id="finalTotalReturnValue">0 EGP</label> -->
							</div>
							<div class="col-md-3 col-md-offset-3 hidden" id="saveInv">
								<button class="btn btn-primary" id="saveReturnInvoice">حفظ فاتورة مرتجع جديدة</button>
								<button class="btn btn-default" id="cancelReturnInvoice">الغاء</button>
							</div>
						</div>
						<% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery Version 1.11.0 -->
    <script src="../js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>
    <script>
    $(document).ready(function(){
    	//add the decrease qty button
    	$('#convertToReturn').click(function(){
    		$('.header').append('<th>تقليل</th>');
    		$('.detail').append('<td><input type="checkbox" class="form-check-input decreaseQty"/></td>');
    		$(this).addClass('hidden');
    		$('#saveInv').removeClass('hidden');
    		$('#totalReturn').removeClass('hidden');
    		assignItemIdToCheckboxVal();
    	});
    	//cancel saving the return invoice
    	$('#cancelReturnInvoice').click(function(){
    		location.reload();
    	});
    	
    	var items = $('input[type="hidden"][name="itemId[]"]').map(function(){
    		  return this.getAttribute("value"); }).get();
    	function assignItemIdToCheckboxVal(){
    		var i = 0;
    		$('input[type=checkbox]').each(function(){
    				$(this).val(items[i]);
    				i++;
    		});
    	}
    	
    	//allow user to decrease qty which min = 1 and max = qty
    	$(document).on('change', '.decreaseQty', function(){
    		var qty = $(this).closest('td').prev().prev().text();
    		if(this.checked){
    			$(this).closest('td').prev().prev().text('').append('<input type="number" class="form-control quantity" min="1" max="' + qty + '" value="' + qty + '" />');
    		} else{
    			var delQty = parseFloat($(this).closest('td').prev().text()) / parseFloat($(this).closest('td').prev().prev().prev().text());
    			$(this).closest('td').prev().prev().text(delQty);
    		}
    		sumReturnTotal();
    	});
    	
    	//modify price each time a change is happened to quantity
    	$(document).on('change keyup', '.quantity', function(){
    		var qty = $(this).val();
    		var price = $(this).closest('td').prev().text();
    		$(this).closest('td').next().text(qty * price);
    		sumReturnTotal();
    	});
    	
    	//sum returnTotal
    	function sumReturnTotal(){
    		var sum = 0;
    		//loop through checked chekboxs
        	$('input:checked').each(function(){
				sum += parseFloat($(this).closest('td').prev().text());
        	});
    		$('#TotalReturnValue').text(sum + " EGP");
//     		if(sum == parseInt($('#invTotal').text()))
//     			$('#finalTotalReturnValue').text($('#invPaid').text());
//     		else {
//     			$('#finalTotalReturnValue').text(sum - parseInt($('#invRemain').text()) + " EGP");
//     		}
    	}
    	
    	//save return invoice
    	$('#saveReturnInvoice').click(function(){
    		var i = 0;
    		var id = <%= id %>;
    		var cache = $('#cache').val();
    		var total = parseFloat($('#TotalReturnValue').text());
    		var itemIds = [];
    		var itemQty = [];
    		var itemTotal = [];
        	$('input:checked').each(function(){
        		itemIds[i] = $(this).val();
        		itemQty[i] = $(this).closest('td').prev().prev().find('input').val();
        		itemTotal[i] = $(this).closest('td').prev().text();
        		i++;
        	});
        	if(i > 0){
        		itemIds = '"' + itemIds + '"';
        		itemQty = '"' + itemQty + '"';
        		itemTotal = '"' + itemTotal + '"';
        		$.ajax({
        			url : "/store-management/return-invoices",
        			method : "POST",
        			data : {
        				purchaseInvoiceId : id, total : total, cache : cache,
        				itemIds : itemIds, itemQty : itemQty,
						itemTotal : itemTotal, action : "2"
        			},
        			dataType : "text",
        			success : function(data){
        				if(data){
        					alert("تم حفظ الفاتورة بنجاح");
        					location.reload();
        				}
        			}
        		});
        	}else alert('يجب اختيار صنف او اكثر لاضافة فاتورة المرتجع');
    	});
    })
	</script>

</body>

</html>