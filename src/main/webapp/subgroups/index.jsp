<%@page import="com.storemanagement.entities.SubGroup"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">مجموعات الأصناف الفرعية</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
            
                <div class="panel panel-default">
                        <div class="panel-heading">
                        	<a href="#"><button class="btn btn-lg btn-primary">إضافة مجموعة فرعية</button></a>
                        </div>
                        <!-- /.panel-heading -->
                        <%
                        List<SubGroup> subGroups = (List<SubGroup>) request.getAttribute("subGroups");
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
                                        <% for(SubGroup subGroup : subGroups){
                                        	i++; 
                                        %>
                                        <tr>
                                            <td><%= i %></td>
                                            <td><%= subGroup.getName() %></td>
                                            <td><a href="subgroups/edit.jsp?id=<%= subGroup.getId() %>"><button class="btn btn-success"><i class="fa fa-edit"></i></button></a></td>
                                            <td><a href="subgroups/delete.jsp?id=<%= subGroup.getId() %>"><button class="btn btn-danger"><i class="fa fa-close"></i></button></a></td>
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