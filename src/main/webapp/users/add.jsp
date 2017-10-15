<%@page import="com.storemanagement.entities.User"%>
<%@page import="com.storemanagement.entities.Branch"%>
<%@page import="com.storemanagement.entities.Cache"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.Role"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
User user = (User) session.getAttribute("user");
if(user.getRole().getId() != 1) response.sendRedirect("/store-management/users");
List<Role> roles = (List<Role>) EntityService.getAllObjects(Role.class);
List<Cache> caches = (List<Cache>) EntityService.getAllObjects(Cache.class);
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

    <title>برنامج إدارة المبيعات | إضافة مستخدم جديد</title>

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
                        <h3 class="panel-title" style="float: right;">إضافة مستخدم جديد</h3>
                    	<a href="/store-management/users" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="/store-management/users">
                            <fieldset>
                            	<div class="form-group">
                                	<label for="user_role">دور المستخدم</label>
                                   	<select class="form-control" name="user_role" id="user_role" autofocus required>
                                   		<option value="">اختر دور المستخدم</option>
                                   		<% for(Role role : roles){ %>
                                   			<option value="<%= role.getId() %>"><%= role.getName() %></option>
                                   		<% } %>
                                   	</select>
                                </div>
                                <div class="form-group">
                                	<label for="user_cache">خزنة المستخدم</label>
                                   	<select class="form-control" name="user_cache" id="user_cache" required>
                                   		<option value="">اختر خزنة المستخدم</option>
                                   		<% for(Cache cache : caches){ %>
                                   			<option value="<%= cache.getId() %>"><%= cache.getName() %></option>
                                   		<% } %>
                                   	</select>
                                </div>
                                <div class="form-group">
                                	<label for="user_branch">فرع المستخدم</label>
                                   	<select class="form-control" name="user_branch" id="user_branch" required>
                                   		<option value="">اختر فرع المستخدم</option>
                                   		<% for(Branch branch : branchs){ %>
                                   			<option value="<%= branch.getId() %>"><%= branch.getName() %></option>
                                   		<% } %>
                                   	</select>
                                </div>
                                <div class="form-group">
                                	<label for="user_inventory">مخزن المستخدم</label>
                                   	<select class="form-control" name="user_inventory" id="user_inventory" required>
                                   		<option value="">اختر مخزن المستخدم</option>
                                   	</select>
                                </div>
                                <div class="form-group">
                                	<label for="user_name">اسم المستخدم</label>
                                    <input class="form-control" placeholder="اسم المستخدم" name="user_name" type="text" id="user_name" required>
                                </div>
                                <div class="form-group">
                                	<label for="user_password">كلمة المرور</label>
                                    <input class="form-control" placeholder="كلمة المرور" name="user_password" type="password" id="user_password">
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
	
	<script>
		$(document).ready(function(){
			$('#user_branch').change(function(){
				var branchId = $(this).val();
				$.ajax({
					url : "/store-management/users",
					method : "POST",
					data : {branchId : branchId, action : "1"},
					dataType : "text",
					success : function(data){
						$('#user_inventory').html(data);
					}
				});
			});
		})
	</script>
</body>

</html>