<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>添加用户</title>
    <@css href="common.css"/>
    <@js src="jquery-2.1.1.min.js,jquery-ui.min.js"/>
    <@js src="common.js"/>
</head>
<body>
<div id="header">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="#" id="back_home"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#" id="back_main">用户管理</a></li>
            <li class="active">添加用户</li>
        </ol>
    </section>
</div>

<div class="box box-primary" style="width: 40% ">
    <div class="box-header">
        <h3 class="box-title">添加用户</h3>
        <input type="hidden" id="temp_name" class="form-control" value="">
    </div>
    <form action="add" id="userForm" name="userAddForm" role="form" method="post">
        <div class="box-body">
        <input type="hidden" id="userID" name="userID" value="0"/><br/>
        <div id="nameDiv" class="form-group">
                <label class="control-label" for="userName">
                    <i class="fa"></i>
                    <span>用户名:</span>
                </label>
                <input type="text" id="userName" name="userName" class="form-control" placeholder="user name is ...">
        </div>
        <br/>
        <div id="passwordDiv" class="form-group">
                <label class="control-label" for="passwords">
                    <i class="fa"></i>
                    <span>密码:</span>
                </label>
                <input type="password" id="passwords" name="passwords" class="form-control" placeholder="123456" >
        </div>
        <br/>
        <div class="form-group">
            <label>组别:</label>
            <select class="form-control" id="groupId" name="group.groupID">
                <#list groupList as group>
                    <option value="${group.groupID}">${group.groupName}</option>
                </#list>
            </select>
        </div>
        <br/>
        <div class="form-group">
            <label>创建者:</label>
            <input type="text" id="createBy" name="createBy" readonly="" class="form-control" value=" ${userName?if_exists}" >
        </div>
        <br/>
    </div>
        <div class="box-footer">
            <button id="btn_userSubmit" class="btn btn-primary" type="button">保存</button>
            <button id="btn_back" class="btn btn-primary" type="button">返回</button>
        </div>
    </form>
</div>

</body>
</html>
</@master>