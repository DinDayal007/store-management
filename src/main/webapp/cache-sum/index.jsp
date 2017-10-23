<%@page import="com.storemanagement.entities.Cache"%>
<%@page import="com.storemanagement.entities.Branch"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Branch> branchs = (List<Branch>) request.getAttribute("branchs");
List<Cache> caches = (List<Cache>) request.getAttribute("caches");
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
	                        		<div class="col-md-4">
	                        			<label for="branch">إختر الفرع</label>
	                        			<select class="form-control" name="branch" id="branch">
	                        				<option value="">إختر الفرع</option>
	                        				<% for(Branch branch : branchs){ %>
	                        				<option value="<%= branch.getId() %>"><%= branch.getName() %></option>
	                        				<% } %>
	                        			</select>
	                        			<label for="inventory">إختر المخزن بناء على الفرع</label>
	                        			<select class="form-control" name="inventory" id="inventory">
	                        				<option value="">إختر المخزن</option>
	                        			</select>
	                        		</div>
	                        		<div class="col-md-4">
	                        			<label for="from">من تاريخ</label>
	                        			<input type="date" class="form-control" name="from" id="from" />
	                        			<label for="to">إلى تاريخ</label>
	                        			<input type="date" class="form-control" name="to" id="to" />
	                        		</div>
	                        		<div class="col-md-4">
	                        			<label for="cache">إختر الخزنة</label>
	                        			<select class="form-control" name="cache" id="cache">
	                        				<option value="">إختر الخزنة</option>
	                        				<% for(Cache cache : caches){ %>
	                        				<option value="<%= cache.getId() %>"><%= cache.getName() %></option>
	                        				<% } %>
	                        			</select>
	                        		</div>
                        	</div><br />
                            <input type="submit" class="btn btn-lg btn-primary" style="float: left;" value="طباعة التقرير">
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