<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>修改密码</title>
    <@css href="common.css"/>
    <@js src="jquery-2.1.1.min.js,jquery-ui.min.js"/>
    <@js src="common.js,update-password.js"/>
</head>
<body>
<div id="header">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="#" id="back_home"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#" id="back_main">用户管理</a></li>
            <li class="active">修改密码</li>
        </ol>
    </section>
</div>

    <div class="box box-primary" style="width: 50% ">
        <div class="box-header">
            <h3 class="box-title">修改密码</h3>
        </div>
        <form action="updatePwd" id="userForm" name="userUpdatePwdForm" role="form" method="post">
            <div class="box-body">
                <input type="hidden" id="userID" name="userID" value="${userBean.userID}"/><br/>
                <div id="nameDiv" class="form-group">
                    <label class="control-label" for="userName">
                        <i class="fa"></i>
                        <span>用户名:</span>
                    </label>
                    <input type="text" id="userName" name="userName" readonly class="form-control" value="${userBean.userName}">
                </div>
                <br/>
                <div id="oldPasswordDiv" class="form-group">
                    <label class="control-label" for="oldPassword">
                        <i class="fa"></i>
                        <span>旧密码:</span>
                    </label>
                    <input type="hidden" id="tmp_pwd" name="tmp_pwd" value="${userBean.passwords}"/><br/>
                    <input type="password" id="old_passwords" name="oldPassword" class="form-control">
                </div>
                <br/>
                <div id="newPasswordDiv" class="form-group">
                    <label class="control-label" for="newPassword">
                        <i class="fa"></i>
                        <span>新密码:</span>
                    </label>
                    <input type="password" id="new_passwords" name="newPassword" class="form-control">
                </div>
                <br/>
                <div id="confirmPasswordDiv" class="form-group">
                    <label class="control-label" for="confirm_passwords">
                        <i class="fa"></i>
                        <span>确认新密码:</span>
                    </label>
                    <input type="password" id="confirm_passwords" name="confirm_passwords" class="form-control">
                </div>
                <br/>
            </div>
            <div class="box-footer">
                <button id="btn_updatePwdSubmit" class="btn btn-primary" type="button">保存</button>
                <button id="btn_back" class="btn btn-primary" type="button">返回</button>
            </div>
        </form>
    </div>
</body>
</html>
</@master>