<%@page import="com.storemanagement.entities.Supplier"%>
<%@page import="com.storemanagement.entities.Client"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Client> clients = (List<Client>) request.getAttribute("clients");
List<Supplier> suppliers = (List<Supplier>) request.getAttribute("suppliers");
%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">كشف مديونيات العملاء والموردين</h1>
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
		                        		<div class="form-group">
		                        			<label for="debitType">اختر نوع المديونيات</label>
		                        			<select class="form-control" name="debitType" id="debitType">
		                        				<option value="0">مديونيات العملاء</option>
		                        				<option value="1">مديونيات الموردين</option>
		                        			</select>
		                        		</div>
	                        			<div class="form-group" id="clientsList">
	                        				<label for="client">إختر العميل</label>
		                        			<select class="form-control" name="client" id="client" required>
		                        				<option value="">إختر العميل</option>
		                        				<% for(Client client : clients){ %>
		                        				<option value="<%= client.getId() %>"><%= client.getName() %></option>
		                        				<% } %>
		                        			</select>
	                        			</div>
	                        			<div class="form-group hidden" id="suppliersList">
	                        				<label for="supplier">إختر المورد</label>
		                        			<select class="form-control" name="supplier" id="supplier">
		                        				<option value="">إختر المورد</option>
		                        				<% for(Supplier supplier : suppliers){ %>
		                        				<option value="<%= supplier.getId() %>"><%= supplier.getName() %></option>
		                        				<% } %>
		                        			</select>
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="from">من تاريخ</label>
	                        				<input type="date" class="form-control" name="from" id="from" />
	                        			</div>
	                        			<div class="form-group">
		                        			<label for="to">إلى تاريخ</label>
		                        			<input type="date" class="form-control" name="to" id="to" />
	                        			</div>
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

<script>
	$(document).ready(function(){
		$('#debitType').change(function(){
			var value = $(this).val();
			if(value == 0){
				$('#suppliersList').addClass('hidden');
				$('#clientsList').removeClass('hidden');
				$('#supplier').removeAttr('required');
				$('#client').attr('required', 'required');
			}else if(value == 1){
				$('#clientsList').addClass('hidden');
				$('#suppliersList').removeClass('hidden');
				$('#client').removeAttr('required');
				$('#supplier').attr('required', 'required');
			}
		});
	});
</script>