<%@page import="com.storemanagement.entities.MainGroup"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<MainGroup> mainGroups = (List<MainGroup>) request.getAttribute("mainGroups");
%>
<jsp:include page="../header.jsp" />

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">جرد الأصناف</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="row">

	<div class="panel panel-default">
		<div class="panel-heading">
			<a href="reports?r=ib" target="_blank"><button class="btn btn-lg btn-primary">كميات الأصناف</button></a>
			<a href="reports?r=ib&data=min" target="_blank"><button class="btn btn-lg btn-primary">متابعة النواقص</button></a>
			<a href="reports?r=ib&data=max" target="_blank"><button class="btn btn-lg btn-primary">متابعة الزيادات</button></a>
		</div>
		<div class="panel-body">
			
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					<div class="form-group">
						<label for="mainGroup">اختر المجموعة الرئيسية</label>
						<select class="form-control" name="mainGroup" id="mainGroup">
							<option value="">اختر المجموعة الرئيسية</option>
							<% for(MainGroup mainGroup : mainGroups){ %>
							<option value="<%= mainGroup.getId() %>"><%= mainGroup.getName() %></option>
							<% } %>
						</select>
					</div>
					<div class="form-group">
						<label for="subGroup">اختر المجموعة الفرعية</label>
						<select class="form-control" name="subGroup" id="subGroup"></select>
					</div>
					<form action="reports" method="get" target="_blank">
						<input type="hidden" name="r" value="im" />
						<div class="form-group">
							<label for="item">اختر الصنف</label>
							<select class="form-control" name="item" id="item" required></select>
						</div>
						<div class="form-group">
							<input type="submit" class="btn btn-lg btn-primary" style="float: left;" value="متابعة حركة الصنف" />
						</div>
					</form>
				</div>
			</div>
			
		</div>
		<!-- /.panel-body -->
	</div>

</div>


<jsp:include page="../footer.html" />

<script>
//send ajax request to get subGroups from mainGroupId
$('#mainGroup').change(function(){
	var mainGroup_id = $(this).val();
	$.ajax({
		url : "/store-management-system/purchases",
		method : "POST",
		data : {mainGroup_id : mainGroup_id, action : "1"},
		dataType : "text",
		success : function(data){
			$('#subGroup').html(data);
		}
	});
});
//send ajax request to get items from subGroupId
$('#subGroup').change(function(){
	var subGroup_id = $(this).val();
	if(subGroup_id != ''){
		$.ajax({
			url : "/store-management-system/purchases",
			method : "POST",
			data : {subGroup_id : subGroup_id, action : "2"},
			dataType : "text",
			success : function(data){
				$('#item').html(data);
			}
		});
	}
});
</script>