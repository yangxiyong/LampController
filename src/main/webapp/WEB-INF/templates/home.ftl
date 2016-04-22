<@master template="layout/master">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>AdminLTE | Widgets</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- bootstrap 3.0.2 -->
    <link href="bootstrap.min.css" />
    <!-- font Awesome -->
    <link href="font-awesome.min.css" />
    <!-- Ionicons -->
    <link href="ionicons.min.css"/>
    <!-- Theme style -->
    <link href="AdminLTE.css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="html5shiv.js"></script>
    <script src="respond.min.js"></script>
    <![endif]-->
</head>
<body class="skin-blue">


<div class="wrapper row-offcanvas row-offcanvas-left">


    <!-- Right side column. Contains the navbar and content of the page -->
    <aside class="right-side">
        <!-- Main content -->
        <section class="content">
            <!-- Small boxes (Stat box) -->
            <div class="row"  >
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-green">
                        <div class="inner">
                            <h3>
                                用户
                            </h3>
                            <p>
                                User
                            </p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-person"></i>
                        </div>
                        <a href="${contextPath}/user/page" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div><!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-aqua">
                        <div class="inner">
                            <h3>
                                用户组
                            </h3>
                            <p>
                                User Group
                            </p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-ios7-people"></i>
                        </div>
                        <a href="${contextPath}/group/page" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div><!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-yellow">
                        <div class="inner">
                            <h3>
                                控制器
                            </h3>
                            <p>
                                Controller
                            </p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-ios7-film"></i>
                        </div>
                        <a href="${contextPath}/control/page" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div><!-- ./col -->

            </div><!-- /.row -->

            <!-- Small boxes (Stat box) -->
            <div class="row">
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-red">
                        <div class="inner">
                            <h3>
                                线路
                            </h3>
                            <p>
                                Route
                            </p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-drag"></i>
                        </div>
                        <a href="${contextPath}/route/page" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div><!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-blue">
                        <div class="inner">
                            <h3>
                                模式
                            </h3>
                            <p>
                                Model
                            </p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-link"></i>
                        </div>
                        <a href="${contextPath}/model/page" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div><!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-purple">
                        <div class="inner">
                            <h3>
                                APK版本
                            </h3>
                            <p>
                                APK Version
                            </p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-social-android "></i>
                        </div>
                        <a href="apk/page" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div><!-- ./col -->
            </div><!-- /.row -->

            <!-- Small boxes (Stat box) -->
            <div class="row">
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-purple">
                        <div class="inner">
                            <h3>
                                分类
                            </h3>
                            <p>
                                Filter
                            </p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-drag"></i>
                        </div>
                        <a href="filter/page" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div><!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->

                </div><!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->

                </div><!-- ./col -->
            </div><!-- /.row -->

        </section><!-- /.content -->
    </aside><!-- /.right-side -->
</div><!-- ./wrapper -->


<!-- jQuery 2.0.2 -->
<script src="jquery.min.js"></script>
<!-- Bootstrap -->
<script src="bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="app.js"></script>

</body>
</html>
</@master>