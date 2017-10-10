<%@page import="com.storemanagement.entities.CacheMovement"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.Branch"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">حركة الخزنة</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">      

                <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="cache-movements/add.html"><button class="btn btn-lg btn-primary">إضافة حركة خزنة جديدة</button></a>
                        </div>
                        <!-- /.panel-heading -->
                        <%
                        List<CacheMovement> cacheMovements = (List<CacheMovement>) request.getAttribute("cacheMovements");
                        int i = 0;
                        %>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>المستخدم</th>
                                            <th>الفرع</th>
                                            <th>الخزنة</th>
                                            <th>التاريخ</th>
                                            <th>النوع</th>
                                            <th>المبلغ</th>
                                            <th>الوصف</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<% for(CacheMovement cacheMovement : cacheMovements){
											i++;	
										%>
										<tr>
                                            <td><%= i %></td>
                                            <th><%= cacheMovement.getUser().getName() %></th>
                                            <th><%= cacheMovement.getBranch().getName() %></th>
                                            <th><%= cacheMovement.getCache().getName() %></th>
                                            <th><%= cacheMovement.getDate() %></th>
                                            <th><%= cacheMovement.getType() %></th>
                                            <th><%= cacheMovement.getAmount() %> + EGP</th>
                                            <th><%= cacheMovement.getDescription() %></th>
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