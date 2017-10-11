<%@page import="com.storemanagement.entities.Cache"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">الخزائن</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">      

                <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="caches/add.html"><button class="btn btn-lg btn-primary">إضافة خزنة جديدة</button></a>
                        </div>
                        <!-- /.panel-heading -->
                        <%
                        List<Cache> caches = (List<Cache>) request.getAttribute("caches");
                        int i = 0;
                        %>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>اسم الخزنة</th>
                                            <th>المبلغ المتوفر</th>
                                            <th>تعديل</th>
                                            <th>حذف</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                        for(Cache cache : caches){
                                        	i++;
                                        %>
                                        <tr>
                                            <td><%= i %></td>
                                            <td><%= cache.getName() %></td>
                                            <td><%= cache.getQty() %> EGP</td>
                                            <td><a href="caches/edit.jsp?id=<%= cache.getId() %>"><button class="btn btn-success"><i class="fa fa-edit"></i></button></a></td>
                                            <td><a href="caches/delete.jsp?id=<%= cache.getId() %>"><button class="btn btn-danger"><i class="fa fa-close"></i></button></a></td>
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