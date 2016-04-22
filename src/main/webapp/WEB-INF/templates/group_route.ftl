<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>Service Test Tools</title>
    <@css href="common.css"/>
    <@js src="jquery-2.1.1.min.js,jquery-ui.min.js"/>
    <@js src="common.js"/>
    <script type="text/javascript">
        $(function () {
            $("#saveRoutes").click(function () {
                var sum_routeId = "";
                $("input:checkbox:checked[name='ck_routId']").each(function () {
                    sum_routeId += $(this).val() + ',';
                });
                $("#temp_routeId_val").val(sum_routeId);
                $("#groupRouteForm").submit();
            });
        });
    </script>
</head>
<body>
<div id="header">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a id="back_home" href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#" id="back_main">用户组管理</a></li>
            <li class="active">用户组线路管理</li>
        </ol>
    </section>
</div>
<section class="content-header">
    <h3>
        用户组线路
    </h3>
    <ol class="breadcrumb">
        <a class="btn btn-app">
            <i id="saveRoutes" class="fa fa-save"></i>
            Save
        </a>
    </ol>
</section>
<form role="form" id="groupRouteForm" action="save" method="post">
    <input id="temp_routeId_val" name="temp_routeId_val" type="hidden" value="">
    <input id="param_groupId" name="param_groupId" type="hidden" value="${bean.groupID}">
    <input id="param_userName" name="param_userName" type="hidden" value=" ${userName?if_exists}">
    <section id="routeSection" class="content">
        <div class="row">
            <div class="col-md-6">
                <div class="box box-primary for-double-table">
                    <table id="checkedRoute" class="table table-bordered table-striped dataTable"
                           aria-describedby="example1_info">
                        <caption><h3 class="box-title">已控线路</h3></caption>
                        <thead>
                        <tr role="row">
                            <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example1"
                                rowspan="1" colspan="1" style="width: 174px;" aria-sort="ascending"
                                aria-label="Rendering engine: activate to sort column descending">序号
                            </th>
                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1"
                                colspan="1" style="width: 256px;"
                                aria-label="Browser: activate to sort column ascending">线路名称
                            </th>
                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1"
                                colspan="1" style="width: 256px;"
                                aria-label="Browser: activate to sort column ascending">所属控制器
                            </th>
                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1"
                                colspan="1" style="width: 256px;"
                                aria-label="Browser: activate to sort column ascending">线路编号
                            </th>
                        </thead>
                        <tfoot>
                        <tr>
                            <th rowspan="1" colspan="1">序号</th>
                            <th rowspan="1" colspan="1">线路名称</th>
                            <th rowspan="1" colspan="1">所属控制器</th>
                            <th rowspan="1" colspan="1">线路编号</th>
                        </tfoot>
                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                            <#list bean.routes as route>
                            <tr>
                                <td>${route_index+1}<input name="ck_routId" type="checkbox" checked
                                                           value="${route.routeID}">
                                    <input type="hidden" value="${route.routeID}"></td>
                                <td>${route.routeName}/${route.routeID}</td>
                                <td>${route.control.controlName}</td>
                                <td>${route.routeNo}</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-6">
                <div class="box box-primary for-double-table">
                    <table id="checkRoute" class="table table-bordered table-striped dataTable"
                           aria-describedby="example1_info">
                        <caption><h3 class="box-title">未控线路</h3></caption>
                        <thead>
                        <tr role="row">
                            <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example1"
                                rowspan="1" colspan="1" style="width: 174px;" aria-sort="ascending"
                                aria-label="Rendering engine: activate to sort column descending">序号
                            </th>
                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1"
                                colspan="1" style="width: 256px;"
                                aria-label="Browser: activate to sort column ascending">线路名称
                            </th>
                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1"
                                colspan="1" style="width: 256px;"
                                aria-label="Browser: activate to sort column ascending">所属控制器
                            </th>
                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1"
                                colspan="1" style="width: 256px;"
                                aria-label="Browser: activate to sort column ascending">线路编号
                            </th>
                        </thead>
                        <tfoot>
                        <tr>
                            <th rowspan="1" colspan="1">序号</th>
                            <th rowspan="1" colspan="1">线路名称</th>
                            <th rowspan="1" colspan="1">所属控制器</th>
                            <th rowspan="1" colspan="1">线路编号</th>
                        </tfoot>
                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                            <#list otherRoutes as unCKRoute>
                            <tr>
                                <td>${unCKRoute_index+1}<input name="ck_routId" type="checkbox"
                                                               value="${unCKRoute.routeID}">
                                    <input type="hidden" value="${unCKRoute.routeID}"></td>
                                <td>${unCKRoute.routeName}/${unCKRoute.routeID}</td>
                                <td>${unCKRoute.control.controlName}</td>
                                <td>${unCKRoute.routeNo}</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</form>
</body>
</html>
</@master>