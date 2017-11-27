<%@page import="com.storemanagement.entities.Privilege"%>
<%@page import="java.util.List"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
if(!privileges.get(5).isView()) response.sendRedirect("/store-management-system/error");
int id = Integer.parseInt(request.getParameter("id"));
Item item = (Item) EntityService.getObject(Item.class, id);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | معلومات إضافية للصنف</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="../css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="../css/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
	<style>
		.key{width: 30%}
		.value{width: 60%}
		.login-panel{margin-top: 5%}
	</style>
</head>
<body>

    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading" style="overflow: hidden;">
                        <h3 class="panel-title" style="float: right;">معلومات الصنف</h3>
                        <a href="/store-management-system/items" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body" style="overflow: hidden; text-align: center;">
                        <table class="table table-">
	                        <thead>
	                        	<tr>
	                        		<td class="key"><label>المجموعة الرئيسية</label></td>
	                        		<td class="value"><label><%= item.getSubGroup().getMainGroup().getName() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>المجموعة الفرعية</label></td>
	                        		<td class="value"><label><%= item.getSubGroup().getName() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>الكود</label></td>
	                        		<td class="value" id="code"><label><%= item.getCode() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>الاسم</label></td>
	                        		<td class="value" id="name"><label><%= item.getName() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>سعر الشراء</label></td>
	                        		<td class="value"><label><%= item.getPurchasePrice() %> EGP</label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>سعر البيع</label></td>
	                        		<td class="value"><label><%= item.getSalePrice() %> EGP</label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>بلد المنشأ</label></td>
	                        		<td class="value"><label><%= item.getHome() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>أقل كمية</label></td>
	                        		<td class="value"><label><%= item.getMinLimit() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>أعلى كمية</label></td>
	                        		<td class="value"><label><%= item.getMaxLimit() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>تاريخ الانتاج</label></td>
	                        		<td class="value"><label><%= item.getProductionDate() != null ? item.getProductionDate() : "" %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>تاريخ إنتهاء الصلاحية</label></td>
	                        		<td class="value"><label><%= item.getExpirationDate() != null ? item.getExpirationDate() : "" %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>وصف الصنف</label></td>
	                        		<td class="value"><label><%= item.getDescription() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><input type="number" class="form-control" value="1" placeholder="عدد مرات طباعة الباركود" min="1" id="barCodeNumber" name="barCodeNumber" /></td>
	                        		<td class="value"><button class="btn btn-primary btn-block" id="printBarCode">طباعة الباركود</button></td>
	                        	</tr>
	                        </thead>
                        </table>
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
    		$('#printBarCode').click(function(){
    			var barCodeNumber = $('#barCodeNumber').val();
    			if(barCodeNumber == '' || barCodeNumber <= 0)
    				alert('عدد مرات طباعة الباركود يجب أن تكون أكبر من الصفر')
    			else{
    				var barCode = $('#code').text();
    				var name = $('#name').text();
    				$.ajax({
    					url : "/store-management-system/items",
    					method : "POST",
    					data : {barCodeNumber : barCodeNumber, barCode : barCode, name : name, action : "barcode"},
    					beforeSend : function(){
    						$('#printBarCode').text('جار الطباعة');
    					},
    					success : function(data){
    						window.location.replace('/store-management-system/print-barcode?barCodeNumber=' + barCodeNumber + '&barCode=' + barCode + '&name=' + name);
    					}
    				});
    			}
    		});
    	})
    </script>

</body>

</html>