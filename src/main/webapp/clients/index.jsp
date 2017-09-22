<%@page import="com.storemanagement.entities.Client"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">العملاء</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="#"><button class="btn btn-lg btn-primary">إضافة عميل جديد</button></a>
                        </div>
                        <!-- /.panel-heading -->
                        <%
                        List<Client> clients = (List<Client>) request.getAttribute("clients");
                        %>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>الكود</th>
                                            <th>الاسم</th>
                                            <th>الوصف</th>
                                            <th>مشاهدة</th>
                                            <th>تعديل</th>
                                            <th>حذف</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for(Client client : clients){ %>
                                        <tr class="odd gradeX">
                                            <td><%= client.getCode() %></td>
                                            <td><%= client.getName() %></td>
                                            <td><%= client.getDescription() %></td>
                                            <td><a href="clients/view.jsp?id=<%= client.getId() %>"><button class="btn btn-default"><i class="fa fa-eye"></i></button></a></td>
                                            <td><a href="clients/edit.jsp?id=<%= client.getId() %>"><button class="btn btn-success"><i class="fa fa-edit"></i></button></a></td>
                                            <td><a href="clients/delete.jsp?id=<%= client.getId() %>"><button class="btn btn-danger"><i class="fa fa-close"></i></button></a></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->

        </div>
        <!-- /#page-wrapper -->
<jsp:include page="../footer.jsp" />