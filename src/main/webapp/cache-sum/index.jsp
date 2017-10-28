<%@page import="com.storemanagement.entities.Supplier"%>
<%@page import="com.storemanagement.entities.Client"%>
<%@page import="com.storemanagement.entities.Cache"%>
<%@page import="com.storemanagement.entities.Branch"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Branch> branchs = (List<Branch>) request.getAttribute("branchs");
List<Cache> caches = (List<Cache>) request.getAttribute("caches");
List<Client> clients = (List<Client>) request.getAttribute("clients");
List<Supplier> suppliers = (List<Supplier>) request.getAttribute("suppliers");
%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">كشف حساب حركة الخزنة</h1>
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
                        		<form action="/store-management/reports" method="get" role="form" target="_blank">
	                        		<input type="hidden" name="r" value="cm" />
	                        		<div class="col-md-6">
	                        			<label for="branch">إختر الفرع</label>
	                        			<select class="form-control" name="branch" id="branch">
	                        				<option value="">إختر الفرع</option>
	                        				<% for(Branch branch : branchs){ %>
	                        				<option value="<%= branch.getId() %>"><%= branch.getName() %></option>
	                        				<% } %>
	                        			</select>
	                        			<label for="cache">إختر الخزنة</label>
	                        			<select class="form-control" name="cache" id="cache">
	                        				<option value="">إختر الخزنة</option>
	                        				<% for(Cache cache : caches){ %>
	                        				<option value="<%= cache.getId() %>"><%= cache.getName() %></option>
	                        				<% } %>
	                        			</select>
	                        			<label for="client">إختر العميل</label>
	                        			<select class="form-control" name="client" id="client">
	                        				<option value="">إختر العميل</option>
	                        				<% for(Client client : clients){ %>
	                        				<option value="<%= client.getId() %>"><%= client.getName() %></option>
	                        				<% } %>
	                        			</select>
	                        			<label for="from">من تاريخ</label>
	                        			<input type="date" class="form-control" name="from" id="from" />
	                        		</div>
	                        		<div class="col-md-6">
	                        			<label for="inventory">إختر المخزن بناء على الفرع</label>
	                        			<select class="form-control" name="inventory" id="inventory">
	                        				<option value="">إختر المخزن</option>
	                        			</select>
	                        			<label for="type">اختر نوع الحركة</label>
	                        			<select class="form-control" name="type" id="type">
	                        				<option value="">اختر نوع الحركة</option>
	                        				<option value="0">سحب</option>
	                        				<option value="1">إيداع</option>
	                        				<option value="4">فاتورة بيع</option>
	                        				<option value="2">فاتورة شراء</option>
	                        				<option value="3">فاتورة مرتجع بيع</option>
	                        				<option value="5">فاتورة مرتجع شراء</option>
	                        			</select>
	                        			<label for="supplier">إختر المورد</label>
	                        			<select class="form-control" name="supplier" id="supplier">
	                        				<option value="">إختر المورد</option>
	                        				<% for(Supplier supplier : suppliers){ %>
	                        				<option value="<%= supplier.getId() %>"><%= supplier.getName() %></option>
	                        				<% } %>
	                        			</select>
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

<script>
$(document).ready(function(){
	//get inventories from branch
	$('#branch').change(function(){
		var branch = $(this).val();
		if(branch != ''){
			$.ajax({
				url : "/store-management/cache-sum",
				method : "POST",
				data : {branch : branch, action : "1"},
				dataType : "text",
				success : function(data){
					$('#inventory').html(data);
				}
			});
		}
	});
})
</script>