<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>Service Test Tools</title>
    <@css href="jquery-ui.min.css"/>
    <@css href="common.css"/>
    <@js src="jquery-2.1.1.min.js,jquery-ui.min.js"/>
    <@js src="common.js,model-update.js"/>
</head>
<body>
<div id="header">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="#" id="back_home"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a id="back_main" href="#"> 模式管理</a></li>
            <li class="active">修改模式</li>
        </ol>
    </section>
</div>

    <div class="box box-primary">
        <div class="box-header">
            <h3 class="box-title">修改模式</h3>
        </div>
        <form action="update"  id="modelForm" name="grouEditForm" role="form" method="post">
            <div class="box-body">
                <input type="hidden" id="modelID" name="modelID" value="${bean.modelID}"/><br/>
                <input type="hidden" id="temp_name" value="${bean.modelName}">
                <div id="nameDiv" class="form-group">
                    <label class="control-label" for="modelName">
                        <i class="fa"></i>
                        <span>模式名:</span>
                    </label>
                    <input type="text" id="modelName" name="modelName" class="form-control" value="${bean.modelName}">
                </div>
                <br/>
                <div class="form-group">
                    <label>创建者:</label>
                    <input type="text" id="createBy" name="createBy" readonly="" placeholder="登录用户本身..." class="form-control" value="${bean.createBy}" >
                </div>
                <br/>
                <div class="form-group">
                    <label>创建日期:</label>
                    <input type="text" id="createDate" name="createDate" readonly="" class="form-control" value="${bean.createDate?date}" >
                </div>
                <br/>
                <div class="form-group">
                    <label>备注:</label>
                    <textarea id="remark" name="remark" placeholder="Enter ..." rows="3" class="form-control">${bean.remark?if_exists}</textarea>
                </div>
                <br/>
            </div>
            <div class="box-footer">
                <button id="btn_modelSubmit" class="btn btn-primary" type="button">保存</button>
                <button id="btn_back" class="btn btn-primary" type="button">返回</button>
            </div>
        </form>
    </div>

</body>
</html>
</@master>