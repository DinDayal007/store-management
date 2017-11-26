<%@page import="com.storemanagement.entities.Privilege"%>
<%@page import="java.util.List"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.Supplier"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
if(!privileges.get(10).isView()) response.sendRedirect("/store-management-system/error");
int id = Integer.parseInt(request.getParameter("id"));
Supplier supplier = (Supplier) EntityService.getObject(Supplier.class, id);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | معلومات إضافية للمورد</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="../css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="../css/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
	<style>
		.key{width: 30%}
		.value{width: 60%}
		.login-panel{margin-top: 20%}
	</style>
</head>
<body>

    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading" style="overflow: hidden;">
                        <h3 class="panel-title" style="float: right;">معلومات المورد</h3>
                        <a href="/store-management-system/suppliers" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body" style="overflow: hidden; text-align: center;">
                        <table class="table table-">
	                        <thead>
	                        	<tr>
	                        		<td class="key"><label>الكود</label></td>
	                        		<td class="value"><label><%= supplier.getCode() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>الاسم</label></td>
	                        		<td class="value"><label><%= supplier.getName() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>البريد الالكترونى</label></td>
	                        		<td class="value"><label><%= supplier.getEmail() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>رقم الهاتف</label></td>
	                        		<td class="value"><label><%= supplier.getPhone() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>رقم الموبايل 1</label></td>
	                        		<td class="value"><label><%= supplier.getMobile1() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>رقم الموبايل 2</label></td>
	                        		<td class="value"><label><%= supplier.getMobile2() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>الوصف</label></td>
	                        		<td class="value"><label><%= supplier.getDescription() %></label></td>
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

</body>

</html>