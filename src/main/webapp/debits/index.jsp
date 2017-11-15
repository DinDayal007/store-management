<%@page import="com.storemanagement.entities.Client"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Client> clients = (List<Client>) request.getAttribute("clients");
%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">كشف مديونيات العملاء</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">      

                <div class="panel panel-default">
                        <div class="panel-heading">
                           	<label>إختر مدخلات البحث</label>
                        </div>
                        <!-- /.panel-heading -->
 
                        <div class="panel-body">
                        	<div class="row">
                        		<form action="/store-management-system/reports" method="get" role="form" target="_blank">
	                        		<input type="hidden" name="r" value="cd" />
	                        		<div class="col-md-6">
	                        			<label for="client">إختر العميل</label>
	                        			<select class="form-control" name="client" id="client" required>
	                        				<option value="">إختر العميل</option>
	                        				<% for(Client client : clients){ %>
	                        				<option value="<%= client.getId() %>"><%= client.getName() %></option>
	                        				<% } %>
	                        			</select>
	                        			<label for="from">من تاريخ</label>
	                        			<input type="date" class="form-control" name="from" id="from" />
	                        			<label for="to">إلى تاريخ</label>
	                        			<input type="date" class="form-control" name="to" id="to" />
	                        		</div>
                        	</div><br />
                            <input type="submit" class="btn btn-lg btn-primary" value="طباعة التقرير">
	                       </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
            </div>
        </div>
        <!-- /#page-wrapper -->

<jsp:include page="../footer.html" />