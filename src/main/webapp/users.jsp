<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">المستخدمين</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">       

                <div class="panel panel-default">
                        <div class="panel-heading">
                            <a href="unit.html"><button class="btn btn-lg btn-primary">إضافة مستخدم جديد</button></a>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>اسم المستخدم</th>
                                            <th>كلمة السر</th>
                                            <th>دور المستخدم</th>
                                            <th>تعديل</th>
                                            <th>حظر</th>
                                            <th>حذف نهائى</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Mark</td>
                                            <td>Otto</td>
                                            <td>Otto</td>
                                            <td><button class="btn btn-success"><i class="fa fa-edit"></i></button></td>
                                            <td><button class="btn btn-default"><i class="fa fa-ban"></i></button></td>
                                            <td><button class="btn btn-danger"><i class="fa fa-close"></i></button></td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Mark</td>
                                            <td>Otto</td>
                                            <td>Otto</td>
                                            <td><button class="btn btn-success"><i class="fa fa-edit"></i></button></td>
                                            <td><button class="btn btn-default"><i class="fa fa-ban"></i></button></td>
                                            <td><button class="btn btn-danger"><i class="fa fa-close"></i></button></td>
                                        </tr>
                                        
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>

            </div>

        </div>
        <!-- /#page-wrapper -->

<jsp:include page="footer.jsp" />