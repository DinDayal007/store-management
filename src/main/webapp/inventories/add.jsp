<%@page import="com.storemanagement.entities.Privilege"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.Branch"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
if(!privileges.get(18).isInsert()) response.sendRedirect("/store-management-system/error");
List<Branch> branchs = (List<Branch>) EntityService.getAllObjects(Branch.class);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | إضافة مخزن جديد</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="../css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="../css/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">  
	
</head>
<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading" style="overflow: hidden;">
                        <h3 class="panel-title" style="float: right;">إضافة مخزن جديد</h3>
                    	<a href="/store-management-system/inventories" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="/store-management-system/inventories">
                            <fieldset>
                            	<div class="form-group">
                                	<label for="inventory_branch">الفرع التابع له المخزن</label>
                                   	<select class="form-control" name="inventory_branch" id="inventory_branch" required>
                                   		<option value="">اختر الفرع التابع له المخزن</option>
                                   		<% for(Branch branch :branchs){ %>
                                   			<option value="<%= branch.getId() %>"><%= branch.getName() %></option>
                                   		<% } %>
                                   	</select>
                                </div>
                                <div class="form-group">
                                	<label for="inventory_name">اسم المخزن</label>
                                    <input class="form-control" placeholder="اسم المخزن" name="inventory_name" type="text" id="inventory_name" autofocus required>
                                </div>
                                <div class="form-group">
                                	<label for="inventory_description">وصف المخزن</label>
                                    <input class="form-control" placeholder="وصف المخزن" name="inventory_description" type="text" id="inventory_description" autofocus>
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