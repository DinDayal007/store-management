<%@page import="com.storemanagement.entities.Supplier"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">الموردين</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="suppliers/add.html"><button class="btn btn-lg btn-primary">إضافة مورد جديد</button></a>
                        </div>
                        <!-- /.panel-heading -->
                        <%
                        List<Supplier> suppliers = (List<Supplier>) request.getAttribute("suppliers");
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
                                    	<% for(Supplier supplier : suppliers){ %>
                                        <tr class="odd gradeX">
                                            <td><%= supplier.getCode() %></td>
                                            <td><%= supplier.getName() %></td>
                                            <td><%= supplier.getDescription() %></td>
                                            <td><a href="suppliers/view.jsp?id=<%= supplier.getId() %>"><button class="btn btn-default"><i class="fa fa-eye"></i></button></a></td>
                                            <td><a href="suppliers/edit.jsp?id=<%= supplier.getId() %>"><button class="btn btn-success"><i class="fa fa-edit"></i></button></a></td>
                                            <td><a href="suppliers/delete.jsp?id=<%= supplier.getId() %>"><button class="btn btn-danger"><i class="fa fa-close"></i></button></a></td>
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