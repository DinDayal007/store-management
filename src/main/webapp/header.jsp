<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>برنامج إدارة المبيعات</title>

    <!-- Bootstrap Core CSS -->
    <link href="http://localhost:8080/store-management/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="http://localhost:8080/store-management/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="http://localhost:8080/store-management/css/plugins/timeline.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="http://localhost:8080/store-management/css/plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="http://localhost:8080/store-management/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="http://localhost:8080/store-management/css/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/store-management">برنامج إدارة المبيعات</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="settings"><i class="fa fa-gear fa-fw"></i> لوحة التحكم</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="/store-management/logout"><i class="fa fa-sign-out fa-fw"></i> تسجيل الخروج</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <br /><br />
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="#"><i class="fa fa-shopping-cart fa-fw"></i> المبيعات<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="http://localhost:8080/store-management/sales">فاتورة بيع جديدة</a>
                                </li>
                                <li>
                                    <a href="http://localhost:8080/store-management/sales/invoices.jsp">متابعة فواتبر البيع</a>
                                </li>
                                <li>
                                    <a href="http://localhost:8080/store-management/sales/return-invoices.jsp">متابعة فواتبر المرتجع</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-credit-card fa-fw"></i> المشتريات<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="http://localhost:8080/store-management/purchases">فاتورة شراء جديدة</a>
                                </li>
                                <li>
                                    <a href="http://localhost:8080/store-management/purchases/invoices.jsp">متابعة فواتبر الشراء</a>
                                </li>
                                <li>
                                    <a href="http://localhost:8080/store-management/purchases/return-invoices.jsp">متابعة فواتبر المرتجع</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="http://localhost:8080/store-management/items"><i class="fa fa-list fa-fw"></i> الأصناف</a>
                        </li>
                        <li>
                            <a href="http://localhost:8080/store-management/groups"><i class="fa fa-asterisk fa-fw"></i> مجموعات الأصناف الرئيسية</a>
                        </li>
						
						<li>
                            <a href="http://localhost:8080/store-management/subgroups"><i class="fa fa-asterisk fa-fw"></i> مجموعات الأصناف الفرعية</a>
                        </li>
						
                        <li>
                            <a href="http://localhost:8080/store-management/units"><i class="fa fa-cubes fa-fw"></i> وحدات الأصناف</a>
                        </li>
                        <li>
                            <a href="http://localhost:8080/store-management/suppliers"><i class="fa fa-truck fa-fw"></i> الموردين</a>
                        </li>
                        <li>
                            <a href="http://localhost:8080/store-management/clients"><i class="fa fa-male fa-fw"></i> العملاء</a>
                        </li>
                        <li>
                            <a href="http://localhost:8080/store-management/caches"><i class="fa fa-money fa-fw"></i> الخزائن</a>
                        </li>
                        <li>
                            <a href="http://localhost:8080/store-management/inventories"><i class="fa fa-database fa-fw"></i> المخازن</a>
                        </li>
                        <li>
                            <a href="http://localhost:8080/store-management/branches"><i class="fa fa-th-large fa-fw"></i> الفروع</a>
                        </li>
                        <li>
                            <a href="http://localhost:8080/store-management/cache-movements"><i class="fa fa-money fa-fw"></i> حركة الخزنة</a>
                        </li>
                        <li>
                            <a href="http://localhost:8080/store-management/users"><i class="fa fa-users fa-fw"></i> المستخدمين</a>
                        </li>

                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
    