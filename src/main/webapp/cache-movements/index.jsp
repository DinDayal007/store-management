<%@page import="com.storemanagement.entities.Client"%>
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
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                        	<th>#</th>
                                            <th>المستخدم</th>
                                            <th>المخزن</th>
                                            <th>الخزنة</th>
                                            <th>العميل</th>
                                            <th>المورد</th>
                                            <th>التاريخ</th>
                                            <th>النوع</th>
                                            <th>الوصف</th>
                                            <th>المبلغ</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<% for(CacheMovement cacheMovement : cacheMovements){
											int type = cacheMovement.getType();
											Client client = cacheMovement.getClient();
											i++;
										%>
										<tr>
											<td><%= i %></td>
                                            <th><%= cacheMovement.getUser().getName() %></th>
                                            <th><%= cacheMovement.getInventory().getName() %></th>
                                            <th><%= cacheMovement.getCache().getName() %></th>
                                            <th>
                                            <%
                                            if(type == 0 || type == 2 || type == 3){
                                            	if(type == 0 || type == 2) out.print("");
                                            	else {
                                            		if(client == null) out.print("عميل نقدى");
                                            		else out.print(client.getName());
                                            	}
                                            }else{
                                            	if(type == 1 || type == 5) out.print("");
                                            	else {
                                            		if(client == null) out.print("عميل نقدى");
                                            		else out.print(client.getName());
                                            	}
                                            }
                                            %>
                                            </th>
                                            <th><%= cacheMovement.getSupplier() == null ? "" : cacheMovement.getSupplier().getName() %></th>
                                            <th><%= cacheMovement.getDate() %></th>
                                            <th><%= cacheMovement.getType() == 0 ? "سحب" : "ايداع" %></th>
                                            <th><%= cacheMovement.getDescription() %></th>
                                            <th><%= cacheMovement.getAmount() %> EGP</th>
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