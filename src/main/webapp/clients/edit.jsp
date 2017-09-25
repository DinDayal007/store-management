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

    <title>برنامج إدارة المبيعات | تعديل عميل </title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="../css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="../css/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
	<style>
		textarea {resize: vertical;}
	</style>
</head>
<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading" style="overflow: hidden;">
                        <h3 class="panel-title" style="float: right;">تعديل العميل</h3>
                        <a href="/store-management/clients" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="/store-management/clients">
                            <fieldset>
                                <div class="form-group">
                                	<label for="client_name">اسم العميل</label>
                                    <input class="form-control" placeholder="اسم العميل" value="<%= client.getName() %>" name="client_name" type="text" id="client_name" autofocus required>
                                </div>
                                <div class="form-group">
                                	<label for="client_code">كود العميل</label>
                                    <input class="form-control" placeholder="كود العميل" value="<%= client.getCode() %>" name="client_code" type="text" id="client_code">
                                </div>
                                <div class="form-group">
                                	<label for="client_email">البريد الالكترونى</label>
                                    <input class="form-control" placeholder="البريد الالكترونى" value="<%= client.getEmail() %>" name="client_email" type="email" id="client_email">
                                </div>
                                <div class="form-group">
                                	<label for="client_phone">رقم الهاتف</label>
                                    <input class="form-control" placeholder="رقم الهاتف" value="<%= client.getPhone() %>" name="client_phone" type="text" id="client_phone">
                                </div>
                                <div class="form-group">
                                	<label for="client_mobile1">رقم الموبايل 1</label>
                                    <input class="form-control" placeholder="رقم الموبايل 1" value="<%= client.getMobile1() %>" name="client_mobile1" type="text" id="client_mobile1">
                                </div>
                                <div class="form-group">
                                	<label for="client_mobile2">رقم الموبايل 2</label>
                                    <input class="form-control" placeholder="رقم الموبايل 2" value="<%= client.getMobile2() %>" name="client_mobile2" type="text" id="client_mobile2">
                                </div>
                                <div class="form-group">
                                	<label for="client_description">وصف العميل</label>
                                    <textarea class="form-control" placeholder="وصف العميل" name="client_description" id="client_description"><%= client.getDescription() %></textarea>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="hidden" name="id" value="<%= id %>" />
                                <input type="hidden" name="action" value="edit" />
                                <input type="submit" class="btn btn-lg btn-primary btn-block" value="تعديل" />
                            </fieldset>
                        </form>
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