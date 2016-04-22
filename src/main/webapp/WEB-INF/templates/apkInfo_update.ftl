<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>修改APK</title>
    <@css href="common.css"/>
    <@js src="jquery-2.1.1.min.js,jquery-ui.min.js"/>
    <@js src="common.js"/>
</head>
<body>
<div id="header">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="#" id="back_home"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#" id="back_main">APK管理</a></li>
            <li class="active">修改APK信息</li>
        </ol>
    </section>
</div>

<div class="box box-primary" style="width: 50% ">
    <div class="box-header">
        <h3 class="box-title">修改APK信息</h3>
    </div>
    <form action="update" id="apkForm" name="apkUpdateForm" role="form" method="post">
        <div class="box-body">
        <input type="hidden" id="apkID" name="apkID" value="${bean.apkID}"/><br/>
        <div id="apkNameDiv" class="form-group">
                <label class="control-label" for="apkName">
                    <i class="fa"></i>
                    <span>APK名称:</span>
                </label>
                <input type="text" id="apkName" name="apkName" class="form-control" value="${bean.apkName}">
        </div>
        <br/>
        <div id="versionDiv" class="form-group">
                <label class="control-label" for="versionID">
                    <i class="fa"></i>
                    <span>版本号:</span>
                </label>
                <input type="text" id="versionID" name="versionID" class="form-control" value="${bean.versionID}" >
        </div>
        <br/>
        <div class="form-group">
            <label class="control-label" for="versionID">
                <i class="fa"></i>
                <span>服务器地址:</span>
            </label>
                <input type="hidden" id="address" name="address" value="${bean.address}">
        </div>
        <br/>
        <div class="form-group">
                <label>what is News</label>
                <textarea class="form-control"name="whatNews" rows="3">${bean.whatNews}</textarea>
        </div>
        <br/>
        <div class="form-group">
            <label>创建者:</label>
            <input type="text" id="createBy" name="createBy" readonly="" class="form-control" value="${bean.createBy}" >
        </div>
        <br/>
        </div>
        <div class="form-group">
            <label>创建日期:</label>
            <input type="text" id="createDate" name="createDate" readonly="" class="form-control" value="${bean.createDate?date}" >
        </div>
        <br/>
        <div class="box-footer">
            <button id="btn_apksSubmit" class="btn btn-primary" type="button">保存</button>
            <button id="btn_back" class="btn btn-primary" type="button">返回</button>
        </div>
    </form>
</div>
</body>
</html>
</@master>