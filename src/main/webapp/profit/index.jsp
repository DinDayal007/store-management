<%@page import="com.storemanagement.entities.Branch"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Branch> branchs = (List<Branch>) request.getAttribute("branchs");
%>

<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">تقرير هامش الربح</h1>
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
	                        		<input type="hidden" name="r" value="profit" />
	                        		<div class="col-md-6">
	                        			<div class="form-group">
	                        				<label for="from">من تاريخ</label>
	                        				<input type="date" class="form-control" name="from" id="from" />
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="branch">إختر الفرع</label>
		                        			<select class="form-control" name="branch" id="branch">
		                        				<option value="">إختر الفرع</option>
		                        				<% for(Branch branch : branchs){ %>
		                        				<option value="<%= branch.getId() %>"><%= branch.getName() %></option>
		                        				<% } %>
		                        			</select>
	                        			</div>
	                        		</div>
	                        		
	                        		<div class="col-md-6">
	                        			<div class="form-group">
	                        				<label for="to">إلى تاريخ</label>
	                        				<input type="date" class="form-control" name="to" id="to" />
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="inventory">إختر المخزن بناء على الفرع</label>
		                        			<select class="form-control" name="inventory" id="inventory">
		                        				<option value="">إختر المخزن</option>
		                        			</select>
	                        			</div>
	                        		</div>
	                        		
	                        		<div class="col-md-6">
	                        			<div class="form-group">
	                        				<label for="item">اختر الصنف</label>
	                        				<select name="item" id="item" class="form-control">
	                        					<option value="">اختر الصنف</option>
	                        				</select>
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
	//get inventories from branch
	$('#branch').change(function(){
		var branch = $(this).val();
		if(branch != ''){
			$.ajax({
				url : "/store-management-system/cache-sum",
				method : "POST",
				data : {branch : branch, action : "1"},
				dataType : "text",
				success : function(data){
					$('#inventory').html(data);
				}
			});
		}
	});
	
	//get items from inventory
	$('#inventory').change(function(){
		var inventoryId = $(this).val();
		if(inventoryId != ''){
			$.ajax({
				url : "/store-management-system/profit",
				method : "POST",
				data : {inventoryId : inventoryId},
				dataType : "text",
				success : function(data){
					$('#item').html(data);
				}
			});
		}
	});
})
</script>