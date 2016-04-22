<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="en">
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
            <li><a  id="back_home" href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a id="back_main" href="#"> 模式管理</a></li>
            <li class="active">添加模式</li>
        </ol>
    </section>
</div>

<div class="box box-primary">
    <div class="box-header">
        <h3 class="box-title">添加模式</h3>
    </div>
    <form action="add" id="modelForm" name="modelAddForm" role="form" method="post">
        <div class="box-body">
        <input type="hidden" id="modelID" name="modelID" value="0"/><br/>
        <input type="hidden" id="temp_name" value="">
        <div id="nameDiv" class="form-group">
                <label class="control-label" for="modelName">
                    <i class="fa"></i>
                    <span>模式名:</span>
                </label>
                <input type="text" id="modelName" name="modelName" class="form-control">
        </div>
        <br/>
        <div class="form-group">
            <label>创建者:</label>
            <input type="text" id="createBy" name="createBy" readonly="" class="form-control" value=" ${userName?if_exists}" >
        </div>
        <br/>

        <div class="form-group">
            <label>备注:</label>
            <textarea id="remark" name="remark" placeholder="Enter ..." rows="3" class="form-control"></textarea>
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