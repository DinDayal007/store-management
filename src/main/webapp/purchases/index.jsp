<%@page import="com.storemanagement.entities.Supplier"%>
<%@page import="com.storemanagement.services.EntityService"%>
<%@page import="com.storemanagement.entities.Item"%>
<%@page import="com.storemanagement.entities.MainGroup"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.storemanagement.entities.Cache"%>
<%@page import="com.storemanagement.entities.Inventory"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
<%
List<Supplier> suppliers = (List<Supplier>) request.getAttribute("suppliers");
List<Inventory> inventories = (List<Inventory>) request.getAttribute("inventories");
List<Cache> caches = (List<Cache>) request.getAttribute("caches");
List<MainGroup> mainGroups = (List<MainGroup>) request.getAttribute("mainGroups");
%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">فاتورة شراء جديدة</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<jsp:include page="../footer.jsp" />