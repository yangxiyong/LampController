/**
 * Created by yxy on 2014/12/29.
 */

var deployContext=$("#deployContext").val();
function goMain(){
    window.location = "page";
}

$(function () {

    $("a[id$='mytree']").click(function(){
        $(this).parent().find("ul").toggle(500);
        if($(this).parent().find("ul").is(":hidden")){
            $(this).find(".fa.pull-right").removeClass("fa-angle-left").addClass("fa-angle-down");
        }else{
            $(this).find(".fa.pull-right").removeClass("fa-angle-down").addClass("fa-angle-left");

        }

    });

    $("#back_home").click(function(){
        //window.location = "/lampcontroller/home";
        window.location = deployContext+"/home";
    });
    $("#back_main").click(function(){
        window.location = "page";
    });
    $("#btn_back").click(function(){
        goMain();
    });
    $("#btn_back").addClass("btn_right");
    $(".box-primary").addClass("div_width");

    $("#btn_userSubmit").click(function(){
        var flag=userValidation();
        if(!flag){
            //alert("输入不合法!");
        }
    });

    $("#btn_apksSubmit").click(function(){
        var apkFlag=apkInfoValidation();
        if(!apkFlag){
            //alert("输入不合法!");
        }
    });

});
//check user form
function userValidation() {
    //check password begin
    var ptrue=false;
    var pwd=$("#passwords").val();
    if(pwd==""){
        $("#passwordDiv").removeClass("has-success");
        $("#passwordDiv").addClass("has-warning");
        $("#passwordDiv").find("a").removeClass("fa-check");
        $("#passwordDiv").find("a").addClass("fa-warning");
        $("#passwordDiv").find("span").text("密码不能为空!");
        $("#passwords").focus();
    }else{
        $("#passwordDiv").removeClass("has-warning");
        $("#passwordDiv").addClass("has-success");
        $("#passwordDiv").find("a").removeClass("fa-warning");
        $("#passwordDiv").find("a").addClass("fa-check");
        $("#passwordDiv").find("span").text("密码输入正确!");
        ptrue=true;
    }
    //check password end
    //check name begin
    var ctrue=false;
    var uname=$("#userName").val();
    var temp_name=$("#temp_name").val();
    if(uname==""){
        $("#nameDiv").removeClass("has-error has-success");
        $("#nameDiv").addClass("has-warning");
        $("#nameDiv").find("a").removeClass("fa-times-circle-o fa-check");
        $("#nameDiv").find("a").addClass("fa-warning");
        $("#nameDiv").find("span").text("用户名不能为空!");
        $("#userName").focus();
        return false;
    }
    if(temp_name==uname ){
        //alert("您的用户名未更改!(修改信息)");
        $("#userForm").submit();
        return true;
    }
    else{
        var params = {};
        params["uName"] = $("#userName").val();
        $.ajax({
            //url: "/lampcontroller/user/check_name",
            url: deployContext+"/user/check_name",
            data: params,
            method: "GET",
            success: function (data) {
                if(data==1){
                    $("#nameDiv").removeClass("has-warning has-error");
                    $("#nameDiv").addClass("has-success");
                    $("#nameDiv").find("a").removeClass("fa-warning fa-times-circle-o");
                    $("#nameDiv").find("a").addClass("fa-check");
                    $("#nameDiv").find("span").text("用户名可用!");
                    ctrue=true;
                }else{
                    $("#nameDiv").removeClass("has-warning has-success");
                    $("#nameDiv").addClass("has-error");
                    $("#nameDiv").find("a").removeClass("fa-warning fa-check");
                    $("#nameDiv").find("a").addClass("fa-times-circle-o");
                    $("#nameDiv").find("span").text("用户名已存在!");
                    ctrue=false;
                }
                if(ctrue && ptrue){
                    $("#userForm").submit();
                    return true;
                }
            }

        });
    }
    return false;
}

//check apk info from
function apkInfoValidation(){
    var ctrue=true;
    var ptrue=false;
    var name=$("#apkName").val();
    var version=$("#versionID").val();
    var addr=$("#address").val();
    if(name=="" || version=="" || addr==""){
        if(name==""){
            $("#apkNameDiv").removeClass("has-success");
            $("#apkNameDiv").addClass("has-warning");
            $("#apkNameDiv").find("a").removeClass("fa-check");
            $("#apkNameDiv").find("a").addClass("fa-warning");
            $("#apkNameDiv").find("span").text("apk名称不能为空!");
            $("#apkName").focus();
        }else{
            $("#apkNameDiv").removeClass("has-warning");
            $("#apkNameDiv").addClass("has-success");
            $("#apkNameDiv").find("a").removeClass("fa-warning");
            $("#apkNameDiv").find("a").addClass("fa-check");
            $("#apkNameDiv").find("span").text("apk名称输入正确!");
            ptrue=true;
        }
        if(version==""){
            $("#versionDiv").removeClass("has-success");
            $("#versionDiv").addClass("has-warning");
            $("#versionDiv").find("a").removeClass("fa-check");
            $("#versionDiv").find("a").addClass("fa-warning");
            $("#versionDiv").find("span").text("版本号不能为空!");
            $("#versionID").focus();
        }else{
            $("#versionDiv").removeClass("has-warning");
            $("#versionDiv").addClass("has-success");
            $("#versionDiv").find("a").removeClass("fa-warning");
            $("#versionDiv").find("a").addClass("fa-check");
            $("#versionDiv").find("span").text("版本号输入正确!");
            ptrue=true;
        }
        if(addr!=""){
            ptrue=true;
        }
        if(!ptrue){
            return false;
        }

    }else{
        var params = {};
        params["apkName"] = name;
        params["versionID"] = version;
        $.ajax({
            url: deployContext +"/apk/check_name",
            data: params,
            method: "GET",
            success: function (data) {
                if(data==1){
                    $("#versionDiv").removeClass("has-warning has-error");
                    $("#versionDiv").addClass("has-success");
                    $("#versionDiv").find("a").removeClass("fa-warning fa-times-circle-o");
                    $("#versionDiv").find("a").addClass("fa-check");
                    $("#versionDiv").find("span").text("版本号可用!");
                    ctrue=true;
                }else{
                    $("#versionDiv").removeClass("has-warning has-success");
                    $("#versionDiv").addClass("has-error");
                    $("#versionDiv").find("a").removeClass("fa-warning fa-check");
                    $("#versionDiv").find("a").addClass("fa-times-circle-o");
                    $("#versionDiv").find("span").text("版本号已存在!");
                    ctrue=false;
                    return false;
                }
                if(ctrue){
                    $("#apkForm").submit();
                    return true;
                }
            }

        });
    }
    return true;
}

function downLoad(id){
    var params = {};
    params["id"] = id;
    $.ajax({
        url: deployContext +"/apk/down_load",
        data: params,
        method: "GET",
        success: function (data) {
            //这边传过来就已经出现中文乱码,所以需要先在服务器上进行处理,URLEncoder.encode进行编码
            //alert("data url:"+data);
            //页面上进行中文乱码处理 decodeURIComponent 进行解码
            window.location=decodeURIComponent(data);
        }
    });
}

