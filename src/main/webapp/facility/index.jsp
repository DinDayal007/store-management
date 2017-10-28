<%@page import="com.storemanagement.entities.Facility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
<style>textarea{resize: none;}</style>
<%
Facility facility = (Facility) request.getAttribute("facility");
%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">معلومات المنشأة</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="row">
            
    <div class="panel panel-default">
         <div class="panel-heading">
         	<button id="addInfoBtn" class="btn btn-lg btn-primary">تعديل معلومات المنشأة</button>
         </div>
         <!-- /.panel-heading -->
         
         <div class="panel-body">
             <div class="row">
             	<div class="col-md-8 col-md-offset-2">
             		<form action="" method="post" role="form">
	             		<div class="form-group">
	             			<label for="facilityName">إسم المنشأة</label>
	             			<input type="text" class="form-control" readonly name="facilityName" value="<%= facility.getName() %>" placeholder="إسم المنشأة" id="facilityName" required />
	             		</div>
	             		<div class="form-group">
	             			<label for="facilityGovernorate">المحافظة</label>
	             			<input type="text" class="form-control" readonly name="facilityGovernorate" value="<%= facility.getGovernorate() %>" placeholder="المحافظة" id="facilityGovernorate" required />
	             		</div>
	             		<div class="form-group">
	             			<label for="facilityCity">المدينة</label>
	             			<input type="text" class="form-control" readonly name="facilityCity" value="<%= facility.getCity() %>" placeholder="المدينة" id="facilityCity" required />
	             		</div>
	             		<div class="form-group">
	             			<label for="facilityAddress">العنوان</label>
	             			<input type="text" class="form-control" readonly name="facilityAddress" value="<%= facility.getAddress() %>" placeholder="العنوان" id="facilityAddress" required />
	             		</div>
	             		<div class="form-group">
	             			<label for="facilityPhone">التليفون الأرضى</label>
	             			<input type="number" min="1" class="form-control" readonly name="facilityPhone" value="<%= facility.getPhone() %>" placeholder="التليفون الأرضى" id="facilityPhone" required />
	             		</div>
	             		<div class="form-group">
	             			<label for="facilityMobile1">رقم الموبايل الأول</label>
	             			<input type="number" min="1" class="form-control" readonly name="facilityMobile1" value="<%= facility.getMobile1() %>" placeholder="رقم الموبايل الأول" id="facilityMobile1" required />
	             		</div>
	             		<div class="form-group">
	             			<label for="facilityMobile2">رقم الموبايل الثانى</label>
	             			<input type="number" min="1" class="form-control" readonly name="facilityMobile2" value="<%= facility.getMobile2() %>" placeholder="رقم الموبايل الثانى" id="facilityMobile2" />
	             		</div>
	             		<div class="form-group">
	             			<label for="facilityMoreInfo">معلومات أخرى</label>
	             			<textarea class="form-control" name="facilityMoreInfo" readonly id="facilityMoreInfo" cols="" rows=""><%= facility.getMoreInfo() %></textarea>
	             		</div>
	             		<div class="form-group hidden" id="saveFacility">
	             			<input type="submit" class="btn btn-primary btn-lg" value="حفظ" />
	             			<a href=""><button type="button" class="btn btn-default btn-lg">إلغاء</button></a>
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
	$(document).ready(function(){
		$('#addInfoBtn').click(function(){
			$('input').removeAttr('readonly');
			$('textarea').removeAttr('readonly');
			$('#saveFacility').removeClass('hidden');
			$(this).addClass('hidden');
		});
	})
</script>