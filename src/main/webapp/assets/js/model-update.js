/**
 * Created by yxy on 2015/1/6.
 */

$(function () {
    $("#btn_modelSubmit").click(function(){
        validation();
    });
});

function validation(){
    var flag_name=false;
    var temp_name=$("#temp_name").val();
    var name = $("#modelName").val();
    if (name == "") {
        $("#nameDiv").removeClass("has-error has-success");
        $("#nameDiv").addClass("has-warning");
        $("#nameDiv").find("a").removeClass("fa-times-circle-o fa-check");
        $("#nameDiv").find("a").addClass("fa-warning");
        $("#nameDiv").find("span").text("模式名称不能为空!");
        $("#modelName").focus();
        return false;
    }else{
        if(temp_name==name && temp_name!="" ){
            //alert("模式名称未更改!(修改信息)");
            flag_name=true;
        }else{
            var params={};
            params["name"]=name;
            $.ajax({
                url: deployContext +"/model/check_name",
                data: params,
                method: "POST",
                success: function (data) {
                    if(data==1){
                        $("#nameDiv").removeClass("has-warning has-error");
                        $("#nameDiv").addClass("has-success");
                        $("#nameDiv").find("a").removeClass("fa-warning fa-times-circle-o");
                        $("#nameDiv").find("a").addClass("fa-check");
                        $("#nameDiv").find("span").text("模式名称可用!");
                        flag_name=true;
                        if(flag_name){
                            $("#modelForm").submit();
                            return true;
                        }
                    }else{
                        $("#nameDiv").removeClass("has-warning has-success");
                        $("#nameDiv").addClass("has-error");
                        $("#nameDiv").find("a").removeClass("fa-warning fa-check");
                        $("#nameDiv").find("a").addClass("fa-times-circle-o");
                        $("#nameDiv").find("span").text("模式名称已存在!");
                        return false;
                    }
                }
            });
        }
    }
    if(flag_name){
        return false;
    }
}

