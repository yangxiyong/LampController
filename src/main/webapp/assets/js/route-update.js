/**
 * Created by yxy on 2015/1/6.
 */

$(function () {
    var rm_flag=false;
    $("#btn_routeSubmit").click(function(){
        validation();
    });
    $("#controlId").change(function() {
        var temp_route_no=$("#temp_routeNo").val();
        var temp_control_id=$("#temp_controlId").val();
        var control_id=$("#controlId").val();
        var params = {};
        if(!rm_flag && temp_control_id=="" ){
            $("#controlId option:first").remove();
            rm_flag=true;
        }
        params["controlId"] = control_id;
        $.ajax({
            url: deployContext +"/route/available_routeNo",
            data: params,
            method: "POST",
            success: function (data) {
                $("#routeNo").find("option").remove();
                if(data==null){
                    $("#routeNo").append("<option value='0'>"+"==请重新选择控制器=="+"</option>");
                }else{
                    for (var i = 0; i < data.length; i++) {
                        $("#routeNo").append("<option value='"+data[i]+"'>"+"第"+data[i]+"号线"+"</option>");
                    }
                }
                if(temp_control_id==control_id  && temp_control_id!="" && temp_route_no!="" ){
                    $("#routeNo").append("<option value='"+temp_route_no+"'>"+"第"+temp_route_no+"号线(原)"+"</option>");
                    flag_name=true;
                }
            }
        });
    });


});

function validation(){
    var flag_name=false;
    var flag_rno=flag_cid=true;
    var temp_name=$("#temp_name").val();
    var name = $("#routeName").val();
    var cId=$("#controlId").val();
    var rNo=$("#routeNo").val();
    if(rNo==""){
        $("#routeDiv").removeClass("has-error has-success");
        $("#routeDiv").addClass("has-warning");
        $("#routeDiv").find("a").removeClass("fa-times-circle-o fa-check");
        $("#routeDiv").find("a").addClass("fa-warning");
        $("#routeDiv").find("label").text("线路编号不能为空!");
        flag_rno=false;
    }else{
        $("#routeDiv").removeClass("has-error has-warning");
        $("#routeDiv").addClass("has-success");
        $("#routeDiv").find("a").removeClass("fa-times-circle-o fa-warning");
        $("#routeDiv").find("a").addClass("fa-check");
        $("#routeDiv").find("label").text("线路编号选择正确!");
    }
    if(cId==""){
        $("#controlDiv").removeClass("has-error has-success");
        $("#controlDiv").addClass("has-warning");
        $("#controlDiv").find("a").removeClass("fa-times-circle-o fa-check");
        $("#controlDiv").find("a").addClass("fa-warning");
        $("#controlDiv").find("label").text("控制器不能为空!");
        flag_cid=false;
    }else{
        $("#controlDiv").removeClass("has-error has-warning");
        $("#controlDiv").addClass("has-success");
        $("#controlDiv").find("a").removeClass("fa-times-circle-o fa-warning");
        $("#controlDiv").find("a").addClass("fa-check");
        $("#controlDiv").find("label").text("控制器选择正确!");
    }
    if (name == "") {
        $("#nameDiv").removeClass("has-error has-success");
        $("#nameDiv").addClass("has-warning");
        $("#nameDiv").find("a").removeClass("fa-times-circle-o fa-check");
        $("#nameDiv").find("a").addClass("fa-warning");
        $("#nameDiv").find("span").text("线路名称不能为空!");
        $("#routeName").focus();
    }else{
        if(temp_name==name && temp_name!="" ){
            //alert("线路名称未更改!(修改信息)");
            flag_name=true;
        }else{
            var params={};
            params["name"]=name;
            $.ajax({
                url: deployContext +"/route/check_name",
                data: params,
                method: "POST",
                success: function (data) {
                    if(data==1){
                        $("#nameDiv").removeClass("has-warning has-error");
                        $("#nameDiv").addClass("has-success");
                        $("#nameDiv").find("a").removeClass("fa-warning fa-times-circle-o");
                        $("#nameDiv").find("a").addClass("fa-check");
                        $("#nameDiv").find("span").text("线路名称可用!");
                        flag_name=true;
                        if(flag_name  && flag_rno && flag_cid){
                            $("#routeForm").submit();
                            return true;
                        }
                    }else{
                        $("#nameDiv").removeClass("has-warning has-success");
                        $("#nameDiv").addClass("has-error");
                        $("#nameDiv").find("a").removeClass("fa-warning fa-check");
                        $("#nameDiv").find("a").addClass("fa-times-circle-o");
                        $("#nameDiv").find("span").text("线路名称已存在!");
                        return false;
                    }
                }
            });
        }
    }
    if(flag_name  && flag_rno && flag_cid){
        $("#routeForm").submit();
        return true;
    }
    return false;
}

