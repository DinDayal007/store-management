<%@page import="com.storemanagement.entities.Privilege"%>
<%@page import="java.util.List"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.Branch"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
if(!privileges.get(17).isUpdate()) response.sendRedirect("/store-management-system-system/error");
int id = Integer.parseInt(request.getParameter("id"));
Branch branch = (Branch) EntityService.getObject(Branch.class, id);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | تعديل فرع</title>

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
                        <h3 class="panel-title" style="float: right;">تعديل الفرع</h3>
                    	<a href="/store-management-system/branches" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="/store-management-system/branches">
                            <fieldset>
                                <div class="form-group">
                                	<label for="branch_name">اسم الفرع</label>
                                    <input class="form-control" placeholder="اسم الفرع" value="<%= branch.getName() %>" name="branch_name" type="text" id="branch_name" autofocus required>
                                </div>
                                <div class="form-group">
                                	<label for="branch_address">عنوان الفرع</label>
                                    <input class="form-control" placeholder="عنوان الفرع" value="<%= branch.getAddress() %>" name="branch_address" type="text" id="branch_address" required>
                                </div>
                                <div class="form-group">
                                	<label for="branch_description">وصف الفرع</label>
                                    <textarea class="form-control" placeholder="وصف الفرع" name="branch_description" id="branch_description" cols="" rows=""><%= branch.getDescription() %></textarea>
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