<%@page import="com.storemanagement.entities.Page"%>
<%@page import="com.storemanagement.entities.Role"%>
<%@page import="java.util.List"%>
<%@page import="com.storemanagement.entities.Facility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
<style>
textarea{resize: none;}
.btn span.glyphicon{opacity: 0;}
.btn.active span.glyphicon{opacity: 1;}
</style>
<%
Facility facility = (Facility) request.getAttribute("facility");
List<Role> roles = (List<Role>) request.getAttribute("roles");
List<Page> pages = (List<Page>) request.getAttribute("pages");
int i = 1;
%>

<div class="row">
	<div class="col-lg-12">
		<ul class="nav nav-tabs" style="margin-top: 50px">
			<li class="active"><a data-toggle="tab" href="#facility"><i class="fa fa-home fa-fw"></i> معلومات المنشأة</a></li>
			<li><a data-toggle="tab" href="#roles"><i class="fa fa-lock fa-fw"></i> الأدوار</a></li>
		</ul>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="tab-content" style="padding: 0 15px">
		<!-- facility section -->
		<div id="facility" class="tab-pane fade in active">
			<div class="panel panel-default">
	         <div class="panel-heading">
	         	<button id="addInfoBtn" class="btn btn-primary">تعديل معلومات المنشأة</button>
	         </div>
	         <!-- /.panel-heading -->
	         
	         <div class="panel-body">
	             <div class="row">
	             	<div class="col-md-8 col-md-offset-2">
	             		<form action="/store-management-system/facility" method="post" role="form">
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
		             			<input type="submit" class="btn btn-primary" value="حفظ" />
		             			<a href=""><button type="button" class="btn btn-default">إلغاء</button></a>
		             		</div>
	             		</form>
	             	</div>
	             </div>
	         </div>
	         <!-- /.panel-body -->
	     	</div>
		</div>
		
		<!-- roles section -->
		<div id="roles" class="tab-pane fade">
			<div class="panel panel-default">
	         <div class="panel-heading">
	         	<button id="addRoleBtn" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addRoleModal">إضافة دور جديد</button>
	         </div>
	         <!-- /.panel-heading -->
	         
	         <div class="panel-body">
	             <div class="table-responsive">
	             	<table class="table table-striped table-bordered table-hover">
                         <thead>
                             <tr>
                                 <th colspan="1">#</th>
                                 <th colspan="3">اسم الدور</th>
                                 <th colspan="1">مشاهدة الصلاحيات</th>
                                 <th colspan="1">حذف</th>
                             </tr>
                         </thead>
                         <tbody>
                         	<% for(Role role : roles){ %>
                         	<tr>
                         		<td colspan="1"><%= i %></td>
                         		<td colspan="3"><%= role.getName() %></td>
                         		<td colspan="1"><button type="button" id="<%= role.getId() %>" class="btn btn-default viewRole" data-toggle="modal" data-target="#viewRoleModal"><i class="fa fa-eye"></i></button></td>
                         		<td colspan="1"><a href="settings/delete-role.jsp?id=<%= role.getId() %>"><button class="btn btn-danger"><i class="fa fa-close"></i></button></a></td>
                         	</tr>
                         	<% i++;} %>
                         </tbody>
                     </table>
	             </div>
	         </div>
	         <!-- /.panel-body -->
	     	</div>
		</div>
	</div>
</div>

<div id="addRoleModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">إضافة دور جديد وصلاحياته</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="roleName">اسم الدور</label>
					<input type="text" class="form-control" name="roleName" id="roleName" required/>
				</div>
				<label>تحديد الصلاحيات</label>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover" >
                        <thead>
                            <tr class="text-center">
                                <th>اسم الصفحة</th>
                                <th>مشاهدة</th>
                                <th>إضافة</th>
                                <th>تعديل</th>
                                <th>حذف</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(Page pg : pages){ %>
                            <tr class="well well-sm">
                                <td><%= pg.getName() %></td>
<!--                                 <td data-toggle="buttons"> -->
<!--                                 	<label class="btn btn-default active"> -->
<!--                                 		<input type="checkbox" class="view" data-id="" autocomplete="off" /> -->
<!--                                 		<span class="glyphicon glyphicon-check"></span> -->
<!--                                 	</label> -->
<!--                                 </td> -->
								<td><input type="checkbox" class="view" data-id="<%= pg.getId() %>" checked="checked" /></td>
								<td><input type="checkbox" class="add" /></td>
								<td><input type="checkbox" class="edit" /></td>
								<td><input type="checkbox" class="delete" /></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" id="saveRole">حفظ</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">الغاء</button>
			</div>
		</div>
	</div>
</div>

<!-- view role privileges modal -->
<div id="viewRoleModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">مشاهدة صلاحيات الدور</h4>
			</div>
			<div class="modal-body" id="role_privileges">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">رجوع</button>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../footer.html" />
<script>
	$(document).ready(function(){
		//initialize arrays
		var pages = [];
		var viewArr = [];
		var addArr = [];
		var editArr = [];
		var deleteArr = [];
		//send ajax request to settings controller to save the role and privileges
		$('#saveRole').click(function(){
			var roleName = $('#roleName').val();
			if($.trim(roleName).length == 0) alert('يجب إدخال اسم الدور');
			else{
				initializeData();
				$.ajax({
					url : "/store-management-system/settings",
					method : "POST",
					data : {
						roleName : roleName, pages : pages, viewArr : viewArr,
						addArr : addArr, editArr : editArr, deleteArr : deleteArr,
						action : "1"
					},
					success : function(data){
						alert(data);
						window.location.reload();
					}
				});
			}
		});
		
		//generate arrays
		function initializeData(){
			//fill in the view array
			$('.view').each(function(){
				var value = (this.checked ? 1 : 0);
				var pageId = $(this).data("id");
				viewArr.push(value);
				pages.push(pageId);
			});
			//fill in the add array
			$('.add').each(function(){
				var value = (this.checked ? 1 : 0);
				addArr.push(value);
			});
			//fill in the edit array
			$('.edit').each(function(){
				var value = (this.checked ? 1 : 0);
				editArr.push(value);
			});
			//fill in the delete array
			$('.delete').each(function(){
				var value = (this.checked ? 1 : 0);
				deleteArr.push(value);
			});
			pages = '"' + pages + '"';
			viewArr = '"' + viewArr + '"';
			addArr = '"' + addArr + '"';
			editArr = '"' + editArr + '"';
			deleteArr = '"' + deleteArr + '"';
		}
		
		$('#addInfoBtn').click(function(){
			$('input').removeAttr('readonly');
			$('textarea').removeAttr('readonly');
			$('#saveFacility').removeClass('hidden');
			$(this).addClass('hidden');
		});
		
		//view role privileges on modal
		$(document).on('click', '.viewRole', function(){
			var roleId = $(this).attr('id');
			$.ajax({
				url : "/store-management-system/settings",
				method : "POST",
				data : {
					action : "2", roleId : roleId
				},
				success : function(data){
					$('#role_privileges').html(data);
					$('#viewRoleModal').show();
				}
			});
		});
	})
</script>