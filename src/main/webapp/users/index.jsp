<%@page import="com.storemanagement.entities.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">المستخدمين</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">       

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a href="users/add.jsp"><button class="btn btn-lg btn-primary">إضافة مستخدم جديد</button></a>
                    </div>
                     <!-- /.panel-heading -->
                     <%
                     List<User> users = (List<User>) request.getAttribute("users");
                     int i = 0;
                     %>
                     <div class="panel-body">
                         <div class="table-responsive">
                             <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                 <thead>
                                     <tr>
                                         <th>#</th>
                                         <th>اسم المستخدم</th>
                                         <th>كلمة السر</th>
                                         <th>دور المستخدم</th>
                                         <th>الفرع</th>
                                         <th>المخزن</th>
                                         <th>الخزنة</th>
                                          <th>تعديل</th>
                                          <th>حظر / فك الحظر</th>
                                     </tr>
                                 </thead>
                                 <tbody>
                                     <% for(User user : users){
                                     	i++;
                                     %>
                                     <tr>
                                         <td><%= i %></td>
                                         <td><%= user.getName() %></td>
                                         <td><%= user.getPassword() %></td>
                                         <td><%= user.getRole().getName() %></td>
                                         <td><%= user.getInventory() == null ? "" : user.getInventory().getBranch().getName() %></td>
                                         <td><%= user.getInventory() == null ? "" : user.getInventory().getName() %></td>
                                         <td><%= user.getCache() == null ? "" : user.getCache().getName() %></td>
                                          <td><a href="users/edit.jsp?id=<%= user.getId() %>"><button class="btn btn-success"><i class="fa fa-edit"></i></button></a></td>
                                          <% if(user.getStatus() == 1){ %>
                                          <td><button id="<%= user.getId() %>" data-status="<%= user.getStatus() %>" class="btn btn-danger banUnban" data-toggle="tooltip" title="حظر المستخدم"><i class="fa fa-ban"></i></button></td>
                                          <% }else{ %>
                                          <td><button id="<%= user.getId() %>" data-status="<%= user.getStatus() %>" class="btn btn-default banUnban" data-toggle="tooltip" title="فك حظر المستخدم"><i class="fa fa-unlock"></i></button></td>
                                          <% } %>
                                     </tr>
                                     <% } %>
                                 </tbody>
                             </table>
                         </div>
                         <!-- /.table-responsive -->
                     </div>
                     <!-- /.panel-body -->
                 </div>
            </div>
        </div>
        <!-- /#page-wrapper -->

<jsp:include page="../footer.html" />
<script>
	$(document).ready(function(){
		$(document).on('click', '.banUnban', function(){
			var id = $(this).attr('id');
			var status = $(this).data('status');
			var result = (status == 0) ? "تم فك حظر المستخدم بنجاح" : "تم حظر المستخدم بنجاح";
			$.ajax({
				url : "/store-management-system/users",
				method : "POST",
				data : {id : id, status : status, action : "0"},
				success : function(data){
					alert(result);
					window.location.reload();
				}
			});
		});
	})
</script>