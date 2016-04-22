<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>Service Test Tools</title>
    <@css href="jquery-ui.min.css"/>
    <@css href="common.css"/>
    <@js src="jquery-2.1.1.min.js,jquery-ui.min.js"/>
    <@js src="common.js"/>
    <style>
    </style>
    <script type="text/javascript">
        $(function () {
            $("#query_btn").click(function () {
                $("#findForm").submit();
            });
            //添加奇偶行css样式
            $("#data_table tr:odd").addClass("odd");
            $("#data_table tr:even").addClass("event");

        });

        function edit(id) {
            window.location = "edit?id=" + id;
        }
        function del(id) {
            if (confirm("确认删除？")) {
                window.location = "delete?id=" + id;
            }
        }
    </script>
</head>
<body>
<div id="header">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="#" id="back_home"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">线路管理</li>
        </ol>
    </section>
</div>
<div id="actionBar">
    <table align="left">
        <tr>
            <td width="7%">
                <a class="btn btn-app" onClick="edit(0)">
                    <i class="fa fa-edit"></i> Add
                </a>
            </td>
            <td width="7%">
                <form action="find_name" id="findForm" method="post" >
                <div class="input-group input-group-sm">
                    <input type="text" id="query_input" name="name" class="form-control" placeholder="请输入线路名称来查询">
                                        <span class="input-group-btn">
                                            <button type="button" id="query_btn" class="btn btn-info btn-flat">Go!</button>
                                        </span>
                </div>
                </form>
            </td>
        </tr>
    </table>
</div>
<br/><br/><br/>
<!-- data table start -->
<div class="box">
    <div class="box-header">
        <h3 class="box-title">线路数据表</h3>
    </div><!-- /.box-header -->
    <div class="box-body table-responsive">
        <div role="grid" class="dataTables_wrapper form-inline" id="example1_wrapper">
            <table id="example1" class="table table-bordered table-striped dataTable" aria-describedby="example1_info">
            <thead>
            <tr role="row"><th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 174px;" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending">序号</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 256px;" aria-label="Browser: activate to sort column ascending">线路名称</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 256px;" aria-label="Browser: activate to sort column ascending">所属控制器</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 256px;" aria-label="Browser: activate to sort column ascending">线路编号</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 229px;" aria-label="Platform(s): activate to sort column ascending">创建时间</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 146px;" aria-label="Engine version: activate to sort column ascending">创建者</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 103px;" aria-label="CSS grade: activate to sort column ascending">操作</th></tr>
            </thead>
            <tfoot>
            <tr><th rowspan="1" colspan="1">序号</th>
                <th rowspan="1" colspan="1">线路名称</th>
                <th rowspan="1" colspan="1">所属控制器</th>
                <th rowspan="1" colspan="1">线路编号</th>
                <th rowspan="1" colspan="1">创建时间</th>
                <th rowspan="1" colspan="1">创建者</th>
                <th rowspan="1" colspan="1">操作</th></tr>
            </tfoot>
            <tbody role="alert" aria-live="polite" aria-relevant="all">
            <#list page.list as route>
            <tr id="${route_index+1}+newTr">
                <td class="sorting_1">${route_index+1}<input type="hidden" value="${route.routeID}"></td>
                <td>${route.routeName}</td>
                <td>${route.control.controlName}</td>
                <td>${route.routeNo}</td>
                <td>${route.createDate?string("yyy-MM-dd")}</td>
                <td>${route.createBy}</td>
                <td>
                    <button onClick="edit(${route.routeID})" data-toggle="tooltip" class="btn btn-warning btn-sm dropdown-toggle"><i class="fa fa-bars"></i></button>
                    <button onClick="del(${route.routeID})"  data-toggle="tooltip" data-widget="remove" class="btn btn-danger btn-sm" data-original-title="Remove"><i class="fa fa-times"></i></button>
                </td>
            </tr>
            </#list>
          </tbody>
            </table>
            <div class="row">
                <div class="col-xs-6"><div class="dataTables_info" id="example1_info">Showing <span>${(page.pageNo-1)*page.pageSize+1}</span> to <span>${(page.pageNo)*page.pageSize}</span> of <span>${page.totalCount}</span> entries</div></div>
                <div class="col-xs-6"><div class="dataTables_paginate paging_bootstrap"><ul class="pagination"><li class="prev disabled"><a href="#">← Previous</a></li><li class="active"><a href="#">1</a></li><li><a href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">5</a></li><li class="next"><a href="#">Next → </a></li></ul></div></div>
            </div>
        </div>
    </div><!-- /.box-body -->
</div>
<!--   data table end -->
</body>
</html>
</@master>