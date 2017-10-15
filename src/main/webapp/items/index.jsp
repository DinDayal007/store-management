<%@page import="com.storemanagement.entities.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">الأصناف</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="items/add.jsp"><button class="btn btn-lg btn-primary">إضافة صنف جديد</button></a>
                        </div>
                        <!-- /.panel-heading -->
                        <%
                        List<Item> items = (List<Item>) request.getAttribute("items");
                        %>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                        	<th>المجموعة الرئيسية</th>
                                        	<th>المجموعة الفرعية</th>
                                            <th>الكود</th>
                                            <th>الإسم</th>
                                            <th>مشاهدة</th>
                                            <th>تعديل</th>
                                            <th>حذف</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<% for(Item item : items){ %>
										<tr class="odd gradeX">
											<td><%= item.getSubGroup().getMainGroup().getName() %></td>
											<td><%= item.getSubGroup().getName() %></td>
                                            <td><%= item.getCode() %></td>
                                            <td><%= item.getName() %></td>
                                            <td><a href="items/view.jsp?id=<%= item.getId() %>"><button class="btn btn-default"><i class="fa fa-eye"></i></button></a></td>
                                            <td><a href="items/edit.jsp?id=<%= item.getId() %>"><button class="btn btn-success"><i class="fa fa-edit"></i></button></a></td>
                                            <td><a href="items/delete.jsp?id=<%= item.getId() %>"><button class="btn btn-danger"><i class="fa fa-close"></i></button></a></td>
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
        
<jsp:include page="../footer.html" />