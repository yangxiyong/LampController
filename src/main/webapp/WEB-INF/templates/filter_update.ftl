<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Service Test Tools</title>
    <@css href="jquery-ui.min.css"/>
    <@css href="common.css"/>
    <@js src="jquery-2.1.1.min.js,jquery-ui.min.js"/>
    <@js src="common.js,filter-update.js"/>
</head>
<body>
<div id="header">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="#" id="back_home"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#" id="back_main">分类管理</a></li>
            <li class="active">修改分类类型</li>
        </ol>
    </section>
</div>

    <div class="box box-primary" style="width: 50% ">
        <div class="box-header">
            <h3 class="box-title">修改分类类型</h3>
        </div>
        <form action="update"  id="editForm" name="updateForm" role="form" method="post">
            <div class="box-body">
                <input type="hidden" id="id" name="id" value="${filterBean.id}"/><br/>
                <input type="hidden" id="temp_name" value="${filterBean.filterName}">
                <div id="nameDiv" class="form-group">
                    <label class="control-label" for="filterName">
                        <i class="fa"></i>
                        <span>分类类型名:</span>
                    </label>
                    <input type="text" id="filterName" name="filterName" class="form-control" value="${filterBean.filterName}">
                </div>
                <br/>
                <div class="form-group">
                    <label>地点:</label>
                    <input type="text" id="place" name="place" class="form-control" value="${filterBean.place}">
                </div>
                <br/>
                <div class="form-group">
                    <label>灯控用户:</label>
                    <input type="text" id="userId" name="user.userID" readonly="" class="form-control" value="${filterBean.user.userID}">
                </div>
                <br/>
                <div class="form-group">
                    <label>创建者:</label>
                    <input type="text" id="createBy" name="createBy" readonly="" class="form-control" value="${filterBean.createBy?if_exists}" >
                </div>
                <br/>
                <div class="form-group">
                    <label>创建日期:</label>
                    <input type="text" id="createDate" name="createDate" readonly="" class="form-control" value="${filterBean.createDate?date}" >
                </div>
                <br/>
                <div class="form-group">
                    <label>备注:</label>
                    <textarea id="remark" name="remark" placeholder="Enter ..." rows="3" class="form-control">${filterBean.remark?if_exists}</textarea>
                </div>
                <br/>
            </div>
            <div class="box-footer">
                <button id="btn_groupSubmit" class="btn btn-primary" type="button">保存</button>
                <button id="btn_back" class="btn btn-primary" type="button">返回</button>
            </div>
        </form>
    </div>

</body>
</html>
</@master>