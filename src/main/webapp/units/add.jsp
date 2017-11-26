<%@page import="com.storemanagement.entities.Privilege"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
if(!privileges.get(9).isInsert()) response.sendRedirect("/store-management-system/error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | إضافة وحدة جديدة</title>

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
                        <h3 class="panel-title" style="float: right;">إضافة وحدة جديدة</h3>
                    	<a href="/store-management-system/units" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="/store-management-system/units">
                            <fieldset>
                                <div class="form-group">
                                	<label for="unit_name">اسم الوحدة</label>
                                    <input class="form-control" placeholder="اسم الوحدة" name="unit_name" type="text" id="unit_name" autofocus required>
                                </div>
                                <div class="form-group">
                                	<label for="unit_description">وصف الوحدة</label>
                                    <input class="form-control" placeholder="وصف الوحدة" name="unit_description" type="text" id="unit_description">
                                </div>
                                <div class="form-group">
                                	<label for="unit_qty">كمية الوحدة</label>
                                    <input class="form-control" placeholder="كمية الوحدة" name="unit_qty" type="number" min="1" id="unit_qty" required>
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