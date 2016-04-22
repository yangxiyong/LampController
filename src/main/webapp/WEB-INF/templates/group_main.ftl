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
    <script type="text/javascript">
        $(function () {
            $("#query_btn").click(function () {
                $("#findForm").submit();
            });
            //添加奇偶行css样式
            $("#data_table tr:odd").addClass("odd");
            $("#data_table tr:even").addClass("event");

        });

        function goPage(pageNo,pageSize) {
            var page_no = $("#page_no").val();
            var page_count = $("#page_count").val();
            var page_size=$("#page_size").val();
           /* switch (pageNo) {
                case 0:
                    pageNo = 1;
                    break;//首页 1-1
                case 2:
                    pageNo = page_count;
                    break;//尾页
               case -1:
                    page_no = page_no - 1;
                    break;//上一页
                case 1:
                    page_no = page_no + 1;
                    break;
                default :
                    pageNo = 1;
            }*/
            if (pageNo < 1) {
                alert("已经是第一页");
            }
            else {
                /*if (page_no > page_count) {
                    alert("已经是最后一页");
                }*/
               // else {
                    var params = {};
                    params["pageNo"] = pageNo;
                    params["pageSize"] = page_size;
                    $.ajax({
                        url: "<@url value='/group/asypage'/>",
                        data: params,
                        method: "GET",
                        success: function (data) {
                            var html = "";
                            $("#resultDiv").html("page status :" + data.status + ",pageSize:" + data.pageSize + ",pageNo:" + data.pageNo + ",pageCount" + data.pageCount + ",list:" + data.pageList);
                            $("#groupTable").find("tbody").html("");
                            $("#page_no").val("" + data.pageNo);
                            $("#page_count").val("" + data.pageCount);
                            var groups = data.pageList;
                            var j = 0;
                            for (var i = 0; i < groups.length; i++) {
                                var group = groups[i];
                                ++j;
                               /* html += "<tr><td><div>" + j + "</div></td>";
                                html += "<td><div>" + group.groupName + "</div></td>";
                                html += "<td><div>" + group.createDate+"?date" + "</div></td>";
                                html += "<td><div>" + group.createBy + "</div></td>";
                                html += "<td><div>" + "线路集" + "</div></td>";
                                html += "<td><div>" + group.remark+"?if_exists" + "</div></td>";
                                html += "<td><div><a>" + "查看" + "</a></div>";
                                html += "<div><a>" + "删除" + "</a></div></td></tr>"; */

                                html+="<tr><td >"+i+"<input type='hidden' value='" +group.groupID + "'>"+"</td>";
                                html+="<td>"+group.groupName+"</td>";
                                html+="<td>"+ group.createDate +"?date" +"</td>";
                                html+="<td>"+ group.createBy + "</td>";
                                html+="<td>"+ group.remark + "?if_exists" + "</td>";
                                html+="<td><button onClick='myOpen(" + group.groupID + ")' class='btn btn-primary btn-sm btn-flat'>"+"Open"+"</button></td>";
                                html+="<td><button onClick='edit(" + group.groupID + ")' data-toggle='tooltip' class='btn btn-warning btn-sm dropdown-toggle'>"+"<i class='fa fa-bars'>"+"</i></button><button onClick='del(" + group.groupID +")'  data-toggle='tooltip' data-widget='remove' class='btn btn-danger btn-sm' data-original-title='Remove'>"+"<i class='fa fa-times'>"+"</i></button></td></tr>";
                            }
                            $("#groupTable").find("tbody").html(html);
                        }
                    });
               // }
            }
        }

        function edit(gid) {
            window.location = "edit?gid=" + gid;
        }
        function del(gid) {
            if (confirm("确认删除？")) {
                window.location = "delete?gid=" + gid;
            }
        }
        function myOpen(gid) {
                window.location = "advance?gid=" + gid;
        }
        function showByPage(pageNo, pageSize) {
            window.location = "page?pageNo=" + pageNo+"&pageSize="+pageSize;
        }
    </script>
</head>
<body>
<div id="header">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="#" id="back_home"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">用户组管理</li>
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
                    <input type="text" id="query_input" name="gName" class="form-control" placeholder="请输入用户组名称来查询">
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
        <h3 class="box-title">用户组数据表</h3>
    </div><!-- /.box-header -->
    <div class="box-body table-responsive">
        <div role="grid" class="dataTables_wrapper form-inline" id="example1_wrapper">
            <table id="groupTable" class="table table-bordered table-striped dataTable" aria-describedby="example1_info">
            <thead>
            <tr role="row"><th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 174px;" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending">序号</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 256px;" aria-label="Browser: activate to sort column ascending">用户组名称</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 229px;" aria-label="Platform(s): activate to sort column ascending">创建时间</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 146px;" aria-label="Engine version: activate to sort column ascending">创建者</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 146px;" aria-label="Engine version: activate to sort column ascending">备注</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 146px;" aria-label="Engine version: activate to sort column ascending">线路</th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" style="width: 103px;" aria-label="CSS grade: activate to sort column ascending">操作</th></tr>
            </thead>
            <tfoot>
            <tr><th rowspan="1" colspan="1">序号</th>
                <th rowspan="1" colspan="1">用户组名称</th>
                <th rowspan="1" colspan="1">创建时间</th>
                <th rowspan="1" colspan="1">创建者</th>
                <th rowspan="1" colspan="1">备注</th>
                <th rowspan="1" colspan="1">线路</th>
                <th rowspan="1" colspan="1">操作</th></tr>
            </tfoot>
            <tbody role="alert" aria-live="polite" aria-relevant="all">
            <#list groupPage.list as group>
            <tr id="${group_index+1}+newTr">
                <td class=" ">${group_index+1}<input type="hidden" value="${group.groupID}"></td>
                <td class=" ">${group.groupName}</td>
                <td class=" ">${group.createDate?string("yyy-MM-dd")}</td>
                <td class=" ">${group.createBy}</td>
                <td class=" ">${group.remark?if_exists}</td>
                <td class=" "><button onClick="myOpen(${group.groupID})" class="btn btn-primary btn-sm btn-flat">Open</button></td>
                <td class=" ">
                    <button title="" onClick="edit(${group.groupID})" data-toggle="tooltip" class="btn btn-warning btn-sm dropdown-toggle"><i class="fa fa-bars"></i></button>
                    <button title="" onClick="del(${group.groupID})"  data-toggle="tooltip" data-widget="remove" class="btn btn-danger btn-sm" data-original-title="Remove"><i class="fa fa-times"></i></button>
                </td>
            </tr>
            </#list>
          </tbody>
          </table>
            <div class="row">
                <div class="col-xs-6"><div class="dataTables_info" id="example1_info">Showing <span>${(groupPage.pageNo-1)*groupPage.pageSize+1}</span> to <span>${(groupPage.pageNo)*groupPage.pageSize}</span> of <span>${groupPage.totalCount}</span> entries</div></div>
                <div class="col-xs-6"><div class="dataTables_paginate paging_bootstrap"><ul class="pagination"><li class="prev"><a onclick="goPage(${groupPage.pageNo-1},${groupPage.pageSize});">← Previous</a></li><li class="active"><a href="#">1</a></li><li><a href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">5</a></li><li class="next"><a onclick="goPage(${groupPage.pageNo+1},${groupPage.pageSize})">Next → </a></li></ul></div></div>
            </div>
        </div>
    </div><!-- /.box-body -->
</div>
<!--   data table end -->
<input type="hidden" id="page_no" value="${groupPage.pageNo}">
<input type="hidden" id="page_count" value="${groupPage.pageCount}">
<input type="hidden" id="page_size" value="${groupPage.pageSize}">
<div id="resultDiv">

</div>
</body>
</html>
</@master>