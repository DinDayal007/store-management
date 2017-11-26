<%@page import="com.storemanagement.entities.Privilege"%>
<%@page import="com.storemanagement.entities.Role"%>
<%@page import="com.storemanagement.entities.Branch"%>
<%@page import="com.storemanagement.entities.Cache"%>
<%@page import="java.util.List"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
List<Privilege> privileges = (List<Privilege>) request.getSession().getAttribute("privileges");
if(!privileges.get(19).isUpdate()) response.sendRedirect("/store-management-system/error");
int id = Integer.parseInt(request.getParameter("id"));
User user = (User) EntityService.getObject(User.class, id);
List<Cache> caches = EntityService.getAllObjects(Cache.class);
List<Branch> branchs = EntityService.getAllObjects(Branch.class);
List<Role> roles = EntityService.getAllObjects(Role.class);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات | تعديل مستخدم</title>

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
                        <h3 class="panel-title" style="float: right;">تعديل مستخدم</h3>
                    	<a href="/store-management-system/users" style="float: left;"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="/store-management-system/users">
                            <fieldset>
                            	<div class="form-group">
                                	<label for="user_role">دور المستخدم</label>
                                   	<select class="form-control" name="user_role" id="user_role" autofocus required>
                                   		<option value="">اختر دور المستخدم</option>
                                   		<% for(Role role : roles){ %>
                                   			<option value="<%= role.getId() %>" <%= role.getId() == user.getRole().getId() ? "selected" : "" %>><%= role.getName() %></option>
                                   		<% } %>
                                   	</select>
                                </div>
                                <div class="form-group">
                                	<label for="user_cache">خزنة المستخدم</label>
                                   	<select class="form-control" name="user_cache" id="user_cache" required>
                                   		<option value="">اختر خزنة المستخدم</option>
                                   		<% for(Cache cache : caches){ %>
                                   			<option value="<%= cache.getId() %>" <%= cache.getId() == user.getCache().getId() ? "selected" : "" %>><%= cache.getName() %></option>
                                   		<% } %>
                                   	</select>
                                </div>
                                <div class="form-group">
                                	<label for="user_branch">فرع المستخدم</label>
                                   	<select class="form-control" name="user_branch" id="user_branch" required>
                                   		<option value="">اختر فرع المستخدم</option>
                                   		<% for(Branch branch : branchs){ %>
                                   			<option value="<%= branch.getId() %>" <%= branch.getId() == user.getInventory().getBranch().getId() ? "selected" : "" %>><%= branch.getName() %></option>
                                   		<% } %>
                                   	</select>
                                </div>
                                <div class="form-group">
                                	<label for="user_inventory">مخزن المستخدم</label>
                                   	<select class="form-control" name="user_inventory" id="user_inventory" required>
                                   		<option value="<%= user.getInventory().getId() %>"><%= user.getInventory().getName() %></option>
                                   	</select>
                                </div>
                                <div class="form-group">
                                	<label for="user_name">اسم المستخدم</label>
                                    <input class="form-control" placeholder="اسم المستخدم" name="user_name" type="text" id="user_name" value="<%= user.getName() %>" required>
                                </div>
                                <div class="form-group">
                                	<label for="user_password">كلمة المرور</label>
                                    <input class="form-control" placeholder="كلمة المرور" name="user_password" type="password" id="user_password" value="<%= user.getPassword() %>" required>
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

	<script>
		$(document).ready(function(){
			$('#user_branch').change(function(){
				var branchId = $(this).val();
				$.ajax({
					url : "/store-management-system/users",
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