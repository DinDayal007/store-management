<%@page import="com.storemanagement.entities.MainGroup"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">مجموعات الأصناف الرئيسية</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
            
                <div class="panel panel-default">
                        <div class="panel-heading">
                        	<a href="groups/add.html"><button class="btn btn-lg btn-primary">إضافة مجموعة رئيسية</button></a>
                        </div>
                        <!-- /.panel-heading -->
                        <%
                        List<MainGroup> mainGroups = (List<MainGroup>) request.getAttribute("mainGroups");
                        int i = 0;
                        %>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>اسم المجموعة الرئيسية</th>
                                            <th>تعديل</th>
                                            <th>حذف</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for(MainGroup mainGroup : mainGroups){
                                        	i++; 
                                        %>
                                        <tr>
                                            <td><%= i %></td>
                                            <td><%= mainGroup.getName() %></td>
                                            <td><a href="groups/edit.jsp?id=<%= mainGroup.getId() %>"><button class="btn btn-success"><i class="fa fa-edit"></i></button></a></td>
                                            <td><a href="groups?id=<%= mainGroup.getId() %>"><button class="btn btn-danger"><i class="fa fa-close"></i></button></a></td>
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
        
<jsp:include page="../footer.jsp" />