/**
 * Created by yxy on 2015/1/4.
 */

$(function () {
    $("#btn_controlSubmit").click(function(){
        var controlFlag=controlValidation();
        if(controlFlag){
            $("#controlForm").submit();
        }
    });
});

function isIP(ip) {
    var reSpaceCheck = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
    if (reSpaceCheck.test(ip)) {
        ip.match(reSpaceCheck);
        if (RegExp.$1 <= 255 && RegExp.$1 >= 0
            && RegExp.$2 <= 255 && RegExp.$2 >= 0
            && RegExp.$3 <= 255 && RegExp.$3 >= 0
            && RegExp.$4 <= 255 && RegExp.$4 >= 0) {
            return true;
        }
    }
    return false;
}
function  isUnsignedInteger(a)
{
   // var reg ="^([1-9]//d*|0|20)$/";
    // var reg=/^(([^0][\d]?)|0|100)$/;
    var reg=/^[1-9]\d*|0$/; //非负整数 即正整数+0
    if(reg.test(a)){
        if(a>=0 && a<=65535){
            return true;
        }
    }
    return false;
}
function controlValidation(){
    var flag_1=flag_2=flag_3=flag_4=false;
    var temp_name=$("#temp_name").val();
    //check control port null begin
    var port_val=$("#controlPort").val();
    if(port_val==""){
        $("#portDiv").removeClass("has-error has-success");
        $("#portDiv").addClass("has-warning");
        $("#portDiv").find("a").removeClass("fa-times-circle-o fa-check");
        $("#portDiv").find("a").addClass("fa-warning");
        $("#portDiv").find("span").text("控制器端口号不能为空!");
        $("#controlPort").focus();
    }else{ <!--端口号只有整数，范围是从0 到65535（2^16-1） -->
        var port=isUnsignedInteger(port_val);
        if(port){
               // if(port_val>=0 && port_val<=65535){
                    $("#portDiv").removeClass("has-warning has-error");
                    $("#portDiv").addClass("has-success");
                    $("#portDiv").find("a").removeClass("fa-warning fa-times-circle-o");
                    $("#portDiv").find("a").addClass("fa-check");
                    $("#portDiv").find("span").text("控制器端口号可用!");
                    flag_3=true;
               // }
        }else{
            $("#portDiv").removeClass("has-warning has-success");
            $("#portDiv").addClass("has-error");
            $("#portDiv").find("a").removeClass("fa-warning fa-check");
            $("#portDiv").find("a").addClass("fa-times-circle-o");
            $("#portDiv").find("span").text("控制器端口号输入不合法!");
            $("#controlPort").focus();
        }
    }
    //check control IP null begin
    var ip_val=$("#controlIP").val();
    flag_4=isIP(ip_val);
    if(!flag_4){
        $("#ipDiv").removeClass("has-error has-success");
        $("#ipDiv").addClass("has-warning");
        $("#ipDiv").find("a").removeClass("fa-times-circle-o fa-check");
        $("#ipDiv").find("a").addClass("fa-warning");
        if(ip_val==""){
            $("#ipDiv").find("span").text("控制器ip不能为空!");
        }else{
            $("#ipDiv").find("span").text("控制器ip输入不合法!");
            ip_val="";
        }
        $("#controlIP").focus();
    }

    //check control Name null begin
    var name = $("#controlName").val();
    if (name == "") {
        $("#nameDiv").removeClass("has-error has-success");
        $("#nameDiv").addClass("has-warning");
        $("#nameDiv").find("a").removeClass("fa-times-circle-o fa-check");
        $("#nameDiv").find("a").addClass("fa-warning");
        $("#nameDiv").find("span").text("控制器名称不能为空!");
        $("#controlName").focus();
    }
    if(temp_name==name && temp_name!="" ){
        //alert("控制器名称未更改!(修改信息)");
        flag_1=true;
        name="";
    }

    //check control name and ip begin
    if( name != "" || ip_val!=""){
        var params = {};
        params["name"] = $("#controlName").val();
        params["ip"] = ip_val;
        $.ajax({
            url: deployContext +"/control/check_name",
            data: params,
            method: "GET",
            success: function (data) {
                var array=data.split(";");
                for(var i=0;i<array.length-1;i++){
                    switch (array[i]){
                        case "name0":
                            $("#nameDiv").removeClass("has-warning has-success");
                            $("#nameDiv").addClass("has-error");
                            $("#nameDiv").find("a").removeClass("fa-warning fa-check");
                            $("#nameDiv").find("a").addClass("fa-times-circle-o");
                            $("#nameDiv").find("span").text("控制器名称已存在!");
                            $("#controlName").focus();
                            break;
                        case "name1":
                            $("#nameDiv").removeClass("has-warning has-error");
                            $("#nameDiv").addClass("has-success");
                            $("#nameDiv").find("a").removeClass("fa-warning fa-times-circle-o");
                            $("#nameDiv").find("a").addClass("fa-check");
                            $("#nameDiv").find("span").text("控制器名称可用!");
                            flag_1=true;
                            break;
                        case "ip1":
                                $("#ipDiv").removeClass("has-warning has-error");
                                $("#ipDiv").addClass("has-success");
                                $("#ipDiv").find("a").removeClass("fa-warning fa-times-circle-o");
                                $("#ipDiv").find("a").addClass("fa-check");
                                $("#ipDiv").find("span").text("控制器ip可用!");
                                flag_2=true;
                                break;
                        case "ip0":
                            $("#ipDiv").removeClass("has-warning has-success");
                            $("#ipDiv").addClass("has-error");
                            $("#ipDiv").find("a").removeClass("fa-warning fa-check");
                            $("#ipDiv").find("a").addClass("fa-times-circle-o");
                            if(!flag_4){
                                $("#ipDiv").find("span").text("控制器ip输入不合法!");
                            }else{
                                $("#ipDiv").find("span").text("控制器ip已存在!");
                            }
                            $("#controlIP").focus();
                            break;
                    }
                }
                if(flag_1 && flag_2 && flag_3){
                    $("#controlForm").submit();
                    return true;
                }
            }
        });
    }
    //check control name and ip end

    if(!flag_1 || !flag_2 || !flag_3){
        return false;
    }

}

