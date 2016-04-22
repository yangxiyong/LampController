<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Service Test Tools</title>
    <@css href="jquery-ui.min.css"/>
    <@css href="common.css"/>
    <@js src="jquery-2.1.1.min.js,jquery-ui.min.js"/>
    <@js src="common.js,route-update.js"/>
</head>
<body>
<div id="header">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="#" id="back_home"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#" id="back_main">线路管理</a></li>
            <li class="active">添加线路</li>
        </ol>
    </section>
</div>

<div class="box box-primary" style="width: 50% ">
    <div class="box-header">
        <h3 class="box-title">添加线路</h3>
    </div>
    <form action="add"  id="routeForm" name="routeAddForm" role="form" method="post">
        <div class="box-body">
        <input type="hidden" id="routeID" name="routeID" value="0"/><br/>
        <input type="hidden" id="temp_name" value="">
        <input type="hidden" id="temp_routeNo" value="">
        <input type="hidden" id="temp_controlId" value="">
            <div id="nameDiv" class="form-group">
                <label class="control-label" for="routeName">
                    <i class="fa"></i>
                    <span>线路名:</span>
                </label>
                <input type="text" id="routeName" name="routeName" class="form-control" placeholder="route name is ....">
        </div>
        <br/>
        <div id="controlDiv" class="form-group">
             <label>所属控制器:</label>
            <select class="form-control" id="controlId" name="control.controlID">
                <option value="">==请选择控制器==</option>
                <#if selectList??>
                <#list selectList as control >
                    <option value="${control.controlID}">${control.controlName}</option>
                </#list>
                 <#else>
                     <option value="">==请添加可用控制器==</option>
                </#if>
            </select>
        </div>
        <br/>
        <div id="routeDiv" class="form-group">
            <label>线路编号:</label>
            <select class="form-control" id="routeNo" name="routeNo">
                <option value="">==请选择线路编号==</option>
              <#--  <#list 1..8 as i>
                    <#list selectList as route >
                    <#if i!=route.routeNo>
                        <option value="${i}">${i}号线</option>
                    </#if>
                    </#list>
                </#list> -->
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
            <button id="btn_routeSubmit" class="btn btn-primary" type="button">保存</button>
            <button id="btn_back" class="btn btn-primary" type="button">返回</button>
        </div>
    </form>
</div>

</body>
</html>
</@master>