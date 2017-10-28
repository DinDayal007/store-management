<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.Supplier"%>
<%@page import="com.storemanagement.entities.Client"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Client> clients = EntityService.getAllObjects(Client.class);
List<Supplier> suppliers = EntityService.getAllObjects(Supplier.class);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | إضافة حركة خزنة</title>

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
                        <h3 class="panel-title" style="float: right;">إضافة حركة خزنة</h3>
                    	<a href="/store-management/cache-movements" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="/store-management/cache-movements">
                            <fieldset>
                                <div class="form-group">
                                	<label for="movement_type">نوع الحركة</label>
                                    <select class="form-control" name="movement_type" id="movement_type" required>
                                    	<option value="">اختر نوع الحركة</option>
                                    	<option value="0">سحب</option>
                                    	<option value="1">إيداع</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                	<label for="movement_refNum">رقم الحركة</label>
                                    <input class="form-control" placeholder="رقم الحركة" name="movement_refNum" type="number" id="movement_refNum" required>
                                </div>
                                <div class="form-group">
                                	<label for="movement_client">العميل</label>
                                    <select class="form-control" name="movement_client" id="movement_client">
                                    	<option value="">اختر العميل</option>
                                    	<% for(Client client : clients){ %>
                                    	<option value="<%= client.getId() %>"><%= client.getName() %></option>
                                    	<% } %>
                                    </select>
                                </div>
                                <div class="form-group">
                                	<label for="movement_supplier">المورد</label>
                                    <select class="form-control" name="movement_supplier" id="movement_supplier">
                                    	<option value="">اختر المورد</option>
                                    	<% for(Supplier supplier : suppliers){ %>
                                    	<option value="<%= supplier.getId() %>"><%= supplier.getName() %></option>
                                    	<% } %>
                                    </select>
                                </div>
                                <div class="form-group">
                                	<label for="movement_amount">المبلغ</label>
                                    <input class="form-control" placeholder="المبلغ" name="movement_amount" type="number" id="movement_amount" required>
                                </div>
                                <div class="form-group">
                                	<label for="movement_description">الوصف</label>
                                    <textarea class="form-control" placeholder="الوصف" name="movement_description" id="movement_description" required></textarea>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="hidden" name="action" value="add" />
                                <input type="submit" class="btn btn-lg btn-primary btn-block" value="حفظ" />
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