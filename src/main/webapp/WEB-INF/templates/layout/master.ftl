<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>宜加1+Best</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<@css href="bootstrap.min.css,font-awesome.min.css,ionicons.min.css,morris.css,jquery-jvectormap-1.2.2.css,fullcalendar.css,daterangepicker-bs3.css,bootstrap3-wysihtml5.min.css,core.css"/>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <@js src="html5shiv.js,respond.min.js"/>
    <![endif]-->
<@js src="jquery-2.1.1.min.js,jquery-ui-1.10.3.min.js,bootstrap.min.js,app.js,dashboard.js,common.js"/>
</head>
<body class="skin-blue">
<!-- header logo: style can be found in header.less -->
<header class="header">
    <input type="hidden" id="deployContext" value="${contextPath}"/>
    <a href="index.html" class="logo">
        <!-- Add the class icon to your logo image or logo icon to add the margining -->
        宜加灯控
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>

        <div class="navbar-right">
            <ul class="nav navbar-nav">
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="glyphicon glyphicon-user"></i>
                        <span> <#if myName2??>
                        ${myName2}
                        <#else>
                        ${myName!"user name not exit"}
                        </#if> <i class="caret"></i></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header bg-light-blue">
                            <img src="<@url value='/assets/images/avatar.png' />" class="img-circle" alt="User Image"/>

                            <p>
                            <#if myName2??>
                            ${myName2}
                            <#else>
                            ${myName!"user name not exit"}
                            </#if>
                                <small>Member since Nov. 2012</small>
                            </p>
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-right">
                                <a href="${contextPath}/logout" class="btn btn-default btn-flat">Sign out</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div class="wrapper row-offcanvas row-offcanvas-left">
    <aside class="left-side sidebar-offcanvas">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="<@url value='/assets/images/avatar.png' />" class="img-circle" alt="User Image"/>
                </div>
                <div class="pull-left info">
                    <p>Hello,  <#if myName2??>
                    ${myName2}
                    <#else>
                    ${myName!"user name not exit"}
                    </#if>
                    </p>
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="treeview">
                    <a id="umytree" style="cursor: pointer">
                        <i class="fa fa-user"></i>
                        <span>用户管理</span>
                        <i class="fa fa-angle-left pull-right"></i>
                        <small class="badge pull-right bg-green">3</small>
                    </a>
                    <ul id="umytree-menu" class="treeview-menu">
                        <li><a href="${contextPath}/user/page"><i class="fa fa-angle-double-right"></i> 用户列表</a></li>
                        <li><a href="${contextPath}/user/edit?uid=0"><i class="fa fa-angle-double-right"></i> 添加用户</a>
                        </li>
                        <li><a href="${contextPath}/user/turn_update_pwd?id=${userId?if_exists}"><i
                                class="fa fa-angle-double-right"></i> 修改密码</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a id="gmytree" style="cursor: pointer">
                        <i class="fa fa-group"></i> <span>用户组管理</span>
                        <i class="fa fa-angle-left pull-right"></i>
                        <small class="badge pull-right bg-green">3</small>
                    </a>
                    <ul id="gmytree-menu" class="treeview-menu">
                        <li><a href="${contextPath}/group/page"><i class="fa fa-angle-double-right"></i> 用户组列表</a></li>
                        <li><a href="${contextPath}/group/edit?gid=0"><i class="fa fa-angle-double-right"></i>
                            添加用户组</a></li>
                        <li><a href="${contextPath}/group/advance?gid=<#if myGroupId2??>
                        ${myGroupId2}
                        <#else>
                        ${myGroupId?if_exists}
                        </#if>"><i
                                class="fa fa-angle-double-right"></i> 用户组线路</a></li>
                    </ul>
                </li>
                <li>
                    <a href="${contextPath}/control/page">
                        <i class="fa fa-film"></i>
                        <span>控制器管理</span>
                    </a>
                </li>
                <li>
                    <a href="${contextPath}/route/page">
                        <i class="fa fa-bars"></i>
                        <span>线路管理</span>
                    </a>
                </li>
                <li class="treeview">
                    <a id="mmytree" style="cursor: pointer">
                        <i class="fa fa-chain"></i>
                        <span>模式管理</span>
                        <i class="fa fa-angle-left pull-right"></i>
                        <small class="badge pull-right bg-green">2</small>
                    </a>
                    <ul id="mmytree-menu" class="treeview-menu">
                        <li><a href="${contextPath}/model/page"><i class="fa fa-angle-double-right"></i> 模式列表</a></li>
                        <li><a href="${contextPath}/model/edit?id=0"><i class="fa fa-angle-double-right"></i>添加模式</a>
                        </li>
                    </ul>
                </li>
                <li class="treeview">
                    <a id="gmytree" style="cursor: pointer">
                        <i class="fa fa-bars"></i> <span>分类(过滤)管理</span>
                        <i class="fa fa-angle-left pull-right"></i>
                        <small class="badge pull-right bg-green">1</small>
                    </a>
                    <ul id="gmytree-menu" class="treeview-menu">
                        <li><a href="${contextPath}/filter/page"><i class="fa fa-angle-double-right"></i> 分类列表</a></li>
                       <#-- <li><a href="${contextPath}/filter/advanceModel?id=1"><i class="fa fa-angle-double-right"></i>
                           分类-模式</a></li>
                        <li><a href="/lampcontroller/filter/advanceControl?id=1"><i class="fa fa-angle-double-right"></i>
                            分类-线路</a></li>-->
                    </ul>
                </li>
                <li>
                    <a href="${contextPath}/apk/page">
                        <i class="fa fa-android"></i> <span>APK版本管理</span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
    <aside class="right-side">
        <!-- Content Header (Page header) -->
    <@body/>
    </aside>
    <!-- /.right-side -->
</div>
</body>
</html>