<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>添加控制器</title>
    <@css href="common.css"/>
    <@js src="jquery-2.1.1.min.js,jquery-ui.min.js"/>
    <@js src="common.js,control-update.js"/>
</head>
<body>
<div id="header">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="#" id="back_home"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#" id="back_main">控制器管理</a></li>
            <li class="active">添加控制器</li>
        </ol>
    </section>
</div>

<div class="box box-primary" style="width: 50% ">
    <div class="box-header">
        <h3 class="box-title">添加控制器</h3>
    </div>
    <form action="add" id="controlForm" name="controlForm" role="form" method="post">
        <div class="box-body">
        <input type="hidden" id="controlID" name="controlID" value="0"/>
        <input type="hidden" id="temp_name" value="">
        <br/>
        <div id="nameDiv" class="form-group">
                <label class="control-label" for="controlName">
                    <i class="fa"></i>
                    <span>控制器名:</span>
                </label>
            <input type="text" id="controlName" name="controlName" class="form-control">
        </div>
        <br/>
        <div id="ipDiv" class="form-group">
                <label for="controlIP">
                    <i class="fa"></i>
                    <span>控制器IP:</span>
                </label>
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-laptop"></i>
                    </div>
                    <input id="controlIP" name="controlIP" class="form-control focus.inputmask" type="text" data-mask="" data-inputmask="'alias': 'ip'">
                </div>
        </div>
        <br/>
        <div id="portDiv" class="form-group">
                <label class="control-label" for="controlPort">
                    <i class="fa"></i><!--端口号只有整数，范围是从0 到65535（2^16-1） -->
                    <span>控制器端口:</span>
                </label>
            <input type="text" id="controlPort" name="controlPort" class="form-control" placeholder="8080">
        </div>
        <br/>
        <div class="form-group">
            <label>创建者:</label>
            <input type="text" id="createBy" name="createBy" readonly="" class="form-control" value=" ${userName?if_exists}" >
        </div>
        <br/>
    </div>
        <div class="box-footer">
            <button id="btn_controlSubmit" class="btn btn-primary" type="button">保存</button>
            <button id="btn_back" class="btn btn-primary" type="button">返回</button>
        </div>
    </form>
</div>
</body>
</html>
</@master>