<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
                           	<label>إختر تاريخ الفواتير من والى لحساب هامش الربح فى هذه الفترة</label>
                        </div>
                        <!-- /.panel-heading -->
 
                        <div class="panel-body">
                        	<div class="row">
                        		<form action="/store-management-system/reports" method="get" role="form" target="_blank">
	                        		<input type="hidden" name="r" value="profit" />
	                        		<div class="col-md-6">
	                        			<label for="from">من تاريخ</label>
	                        			<input type="date" class="form-control" name="from" id="from" required />
	                        			<label for="to">إلى تاريخ</label>
	                        			<input type="date" class="form-control" name="to" id="to" required />
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