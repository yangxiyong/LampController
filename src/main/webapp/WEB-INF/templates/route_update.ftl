<@master template="layout/master">
<!DOCTYPE html>
<html>
<head lang="zh">
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
            <li class="active">修改线路</li>
        </ol>
    </section>
</div>

    <div class="box box-primary" style="width: 50% ">
        <div class="box-header">
            <h3 class="box-title">修改线路</h3>
        </div>
        <form action="update" id="routeForm" name="routeEditForm" role="form" method="post">
            <div class="box-body">
                <input type="hidden" id="routeID" name="routeID" value="${bean.routeID}"/><br/>
                <input type="hidden" id="temp_name" value="${bean.routeName}">
                <input type="hidden" id="temp_routeNo" value="${bean.routeNo}">
                <input type="hidden" id="temp_controlId" value="${bean.control.controlID}">
                <div id="nameDiv" class="form-group">
                    <label class="control-label" for="routeName">
                        <i class="fa"></i>
                        <span>线路名:</span>
                    </label>
                    <input type="text" id="routeName" name="routeName" class="form-control" value="${bean.routeName}">
                </div>
                <br/>
                <div class="form-group">
                    <label>所属控制器:</label>
                    <select class="form-control" id="controlId" name="control.controlID">
                        <#if selectList??>
                            <option value="${bean.control.controlID}" selected > ${bean.control.controlName} </option>
                        <#list selectList as control>
                            <#if control.controlID!=bean.control.controlID>
                                <option value="${control.controlID}">${control.controlName}</option>
                            </#if>
                        </#list>
                        <#else>
                            <option value="${bean.control.controlID}" selected > ${bean.control.controlName} </option>
                        </#if>
                    </select>
                </div>
                <br/>
                <div class="form-group">
                    <label>线路编号:</label>
                    <select class="form-control" id="routeNo" name="routeNo">
                        <option value="${bean.routeNo}" selected > ${bean.routeNo}号线 </option>
                       <#-- <#list 1..8 as i>
                            <#if i=bean.routeNo>
                                <option value="${bean.routeNo}" selected > ${bean.routeNo}号线 </option>
                            <#else>
                                <option value="${i}">${i}号线</option>
                            </#if>
                        </#list> -->
                    </select>
                </div>
                <br/>
                <div class="form-group">
                    <label>创建者:</label>
                    <input type="text" id="createBy" name="createBy" readonly="" class="form-control" value="${bean.createBy}" >
                </div>
                <br/>
                <div class="form-group">
                    <label>创建日期:</label>
                    <input type="text" id="createDate" name="createDate" readonly="" class="form-control" value="${bean.createDate?date}" >
                </div>
                <br/>
            </div>
            <div class="box-footer">
                <button id="btn_routeSubmit" class="btn btn-primary" type="button">保存</button>
                <button id="btn_back" class="btn btn-primary" type="button" onclick="goMain()">返回</button>
            </div>
        </form>
    </div>

</body>
</html>
</@master>