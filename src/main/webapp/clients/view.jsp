<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int id = Integer.parseInt(request.getParameter("id"));
Client client = (Client) EntityService.getObject(Client.class, id);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | معلومات إضافية للعميل</title>

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
                        <h3 class="panel-title" style="float: right;">معلومات العميل</h3>
                        <a href="/store-management-system/clients" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body" style="overflow: hidden; text-align: center;">
                        <table class="table table-">
	                        <thead>
	                        	<tr>
	                        		<td class="key"><label>الكود</label></td>
	                        		<td class="value"><label><%= client.getCode() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>الاسم</label></td>
	                        		<td class="value"><label><%= client.getName() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>البريد الالكترونى</label></td>
	                        		<td class="value"><label><%= client.getEmail() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>رقم الهاتف</label></td>
	                        		<td class="value"><label><%= client.getPhone() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>رقم الموبايل 1</label></td>
	                        		<td class="value"><label><%= client.getMobile1() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>رقم الموبايل 2</label></td>
	                        		<td class="value"><label><%= client.getMobile2() %></label></td>
	                        	</tr>
	                        	<tr>
	                        		<td class="key"><label>الوصف</label></td>
	                        		<td class="value"><label><%= client.getDescription() %></label></td>
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