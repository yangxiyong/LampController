<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>添加APK</title>
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
            <li class="active">添加APK</li>
        </ol>
    </section>
</div>

<div class="box box-primary" style="width: 40% ">
    <div class="box-header">
        <h3 class="box-title">添加APK</h3>
    </div>
    <form action="add" id="apkForm" name="apkAddForm" role="form" method="post" enctype="multipart/form-data">
        <div class="box-body">
        <input type="hidden" id="apkID" name="apkID" value="0"/><br/>
        <div id="apkNameDiv" class="form-group">
                <label class="control-label" for="apkName">
                    <i class="fa"></i>
                    <span>APK名称:</span>
                </label>
                <input type="text" id="apkName" name="apkName" class="form-control" placeholder="apk name is ...">
        </div>
        <br/>
        <div id="versionDiv" class="form-group">
                <label class="control-label" for="versionID">
                    <i class="fa"></i>
                    <span>版本号:</span>
                </label>
                <input type="text" id="versionID" name="versionID" class="form-control" placeholder="1.0.0" >
        </div>
        <br/>
        <div class="form-group">
                <label for="exampleInputFile">上传地址:</label>
                <input type="file" id="address" name="address">
                <p id="addr_promt" class="help-block">请上传</p>
        </div>
        <br/>
        <div class="form-group">
                <label>what is News</label>
                <textarea class="form-control"name="whatNews" placeholder="Enter ..." rows="3"></textarea>
        </div>
        <br/>
        <div class="form-group">
            <label>创建者:</label>
            <input type="text" id="createBy" name="createBy" readonly="" class="form-control" value=" ${userName?if_exists}" >
        </div>
        <br/>
        </div>
        <div class="box-footer">
            <button id="btn_apksSubmit" class="btn btn-primary" type="button">保存</button>
            <button id="btn_back" class="btn btn-primary" type="button">返回</button>
        </div>
    </form>
</div>
</body>
</html>
</@master>