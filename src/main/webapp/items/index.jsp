<%@page import="com.storemanagement.entities.MainGroup"%>
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
                        <div class="panel-heading" style="overflow: hidden">
                            <a href="items/add.jsp" style="float: right"><button class="btn btn-lg btn-primary">إضافة صنف جديد</button></a>
                        	<div style="float: left;">
                        		<div class="row">
                        			<div class="col-md-6">
                        				<label for="mainGroups">اختر المجموعة الرئيسية</label>
		                        		<% List<MainGroup> mainGroups = (List<MainGroup>) request.getAttribute("mainGroups"); %>
		                        		<select class="form-control" name="mainGroups" id="mainGroups">
		                        			<option value="">اختر المجموعة الرئيسية</option>
		                        			<% for(MainGroup mainGroup : mainGroups){ %>
		                        			<option value="<%= mainGroup.getId() %>"><%= mainGroup.getName() %></option>
		                        			<% } %>
		                        		</select>
                        			</div>
                        			<div class="col-md-6">
                        				<label for="subGroups">اختر المجموعة الفرعية</label>
		                        		<select class="form-control" name="subGroups" id="subGroups">
		                        			<option value="">اختر المجموعة الفرعية</option>
		                        		</select>
                        			</div>
                        		</div>
                        	</div>
                        </div>
                        <!-- /.panel-heading -->
                        <%
                        List<Item> items = (List<Item>) request.getAttribute("items");
                        %>
                        <div class="panel-body">
                            <div class="table-responsive" id="itemsData">
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

<script>
	$(document).ready(function(){
		//get subGroups from mainGroups
		$('#mainGroups').change(function(){
			var mainGroup_id = $(this).val();
			if(mainGroup_id != ''){
				$.ajax({
					url : "/store-management-system/items",
					method : "POST",
					data : {mainGroup_id : mainGroup_id, action : "1"},
					dataType : "text",
					success : function(data){
						$('#subGroups').html(data);
					}
				});
			}
		});
		//get items from subGroups
		$('#subGroups').change(function(){
			var subGroupId = $(this).val();
			if(subGroupId != ''){
				$.ajax({
					url : "/store-management-system/items",
					method : "POST",
					data : {subGroupId : subGroupId, action : "2"},
					dataType : "text",
					success : function(data){
						$('#itemsData').html(data);
						//$('#dataTables-example').dataTable();
					}
				});
			}
		})
	})
</script>