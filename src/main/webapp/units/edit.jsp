<%@page import="com.storemanagement.services.InvoiceService"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.Unit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int id = Integer.parseInt(request.getParameter("id"));
Unit unit = (Unit) EntityService.getObject(Unit.class, id);
boolean hasInvoices = InvoiceService.hasDetailsFromUnit(unit);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | تعديل وحدة</title>

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
                        <h3 class="panel-title" style="float: right;">تعديل الوحدة</h3>
                    	<a href="/store-management/units" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="/store-management/units">
                            <fieldset>
                                <div class="form-group">
                                	<label for="unit_name">اسم الوحدة</label>
                                    <input class="form-control" placeholder="اسم الوحدة" name="unit_name" value="<%= unit.getName() %>" type="text" id="unit_name" autofocus required>
                                </div>
                                <div class="form-group">
                                	<label for="unit_description">وصف الوحدة</label>
                                    <input class="form-control" placeholder="وصف الوحدة" name="unit_description" value="<%= unit.getDescription() %>" type="text" id="unit_description">
                                </div>
                                <% if(!hasInvoices){ %>
                                <div class="form-group">
                                	<label for="unit_qty">كمية الوحدة</label>
                                    <input class="form-control" placeholder="كمية الوحدة" name="unit_qty" value="<%= unit.getQty() %>" type="number" min="1" id="unit_qty">
                                </div>
                                <% } else { %>
                                <input type="hidden" name="unit_qty" value="<%= unit.getQty() %>" />
                                <% } %>
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