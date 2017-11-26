<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.Branch"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">الفروع</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">      

                <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="branches/add.jsp"><button class="btn btn-lg btn-primary">إضافة فرع جديد</button></a>
                        </div>
                        <!-- /.panel-heading -->
                        <%
                        List<Branch> branches = (List<Branch>) request.getAttribute("branches");
                        int i = 0;
                        %>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>اسم الفرع</th>
                                            <th>عنوان الفرع</th>
                                            <th>وصف الفرع</th>
                                            <th>تعديل</th>
                                            <th>حذف</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<% for(Branch branch : branches){
											i++;	
										%>
										<tr>
                                            <td><%= i %></td>
                                            <td><%= branch.getName() %></td>
                                            <td><%= branch.getAddress() %></td>
                                            <td><%= branch.getDescription() %></td>
                                            <td><a href="branches/edit.jsp?id=<%= branch.getId() %>"><button class="btn btn-success"><i class="fa fa-edit"></i></button></a></td>
                                            <td><a href="branches/delete.jsp?id=<%= branch.getId() %>"><button class="btn btn-danger"><i class="fa fa-close"></i></button></a></td>
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