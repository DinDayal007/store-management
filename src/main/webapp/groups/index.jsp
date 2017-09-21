<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">مجموعات الأصناف</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
            
                <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-lg-6">
                                    <a href="unit.html"><button class="btn btn-lg btn-primary">إضافة مجموعة رئيسية</button></a>
                                </div>
                                <div class="col-lg-6">
                                    <label>اختر المجموعة الرئيسية</label>
                                    <select class="form-control">
                                    <option value="">المجموعة الرئيسية الاولى</option>
                                    <option value="">المجموعة الرئيسية الاولى</option>
                                    <option value="">المجموعة الرئيسية الاولى</option>
                                </select>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>اسم المجموعة الفرعية</th>
                                            <th>تعديل</th>
                                            <th>حذف</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Mark</td>
                                            <td><button class="btn btn-success"><i class="fa fa-edit"></i></button></td>
                                            <td><button class="btn btn-danger"><i class="fa fa-close"></i></button></td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Mark</td>
                                            <td><button class="btn btn-success"><i class="fa fa-edit"></i></button></td>
                                            <td><button class="btn btn-danger"><i class="fa fa-close"></i></button></td>
                                        </tr>
                                        
                                    </tbody>
                                </table>
                                <form class="form-inline">
                                    <input type="text" class="form-control" name="" />
                                    <button class="btn btn-primary">إضافة مجموعة فرعية</button>
                                </form>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>

            </div>
        </div>
        <!-- /#page-wrapper -->
        
<jsp:include page="../footer.jsp" />