/**
 * Created by yxy on 2015/1/4.
 */

$(function () {
    $("#btn_updatePwdSubmit").click(function(){
        var apkFlag=updatePwdValidation();
        if(apkFlag){
            $("#userForm").submit();
        }
    });
});

function updatePwdValidation(){
    var flag_1=flag_2=false;
    var oldPwd = $("#old_passwords").val();
    /*var temp_Pwd = $("#tmp_pwd").val();
    if (oldPwd == "" || temp_Pwd != oldPwd) {
        $("#oldPasswordDiv").removeClass("has-success");
        $("#oldPasswordDiv").addClass("has-warning");
        $("#oldPasswordDiv").find("a").removeClass("fa-check");
        $("#oldPasswordDiv").find("a").addClass("fa-warning");
        if(oldPwd == ""){
            $("#oldPasswordDiv").find("span").text("旧密码不能为空!");
        }else{
            $("#oldPasswordDiv").removeClass("has-warning has-success");
            $("#oldPasswordDiv").addClass("has-error");
            $("#oldPasswordDiv").find("a").removeClass("fa-warning fa-check");
            $("#oldPasswordDiv").find("a").addClass("fa-times-circle-o");
            $("#oldPasswordDiv").find("span").text("旧密码输入不正确!");
        }
        $("#old_passwords").focus();
        return false;
    }else{
        $("#oldPasswordDiv").removeClass("has-error has-warning");
        $("#oldPasswordDiv").addClass("has-success");
        $("#oldPasswordDiv").find("a").removeClass("fa-warning fa-times-circle-o");
        $("#oldPasswordDiv").find("a").addClass("fa-check");
        $("#oldPasswordDiv").find("span").text("旧密码输入正确!");
        flag_1=true;
    } */
    //check new pwd begin
    var newPwd=$("#new_passwords").val();
    if(newPwd==""){
        $("#newPasswordDiv").removeClass("has-success");
        $("#newPasswordDiv").addClass("has-warning");
        $("#newPasswordDiv").find("a").removeClass("fa-check");
        $("#newPasswordDiv").find("a").addClass("fa-warning");
        $("#newPasswordDiv").find("span").text("新密码不能为空!");
        $("#new_passwords").focus();
        //return false;
    }else{
        $("#newPasswordDiv").removeClass("has-warning");
        $("#newPasswordDiv").addClass("has-success");
        $("#newPasswordDiv").find("a").removeClass("fa-warning");
        $("#newPasswordDiv").find("a").addClass("fa-check");
        $("#newPasswordDiv").find("span").text("新密码输入正确!");
    }
    var confirmPwd=$("#confirm_passwords").val();
    if(confirmPwd=="" || newPwd!=confirmPwd ){
        $("#confirmPasswordDiv").removeClass("has-success");
        $("#confirmPasswordDiv").addClass("has-warning");
        $("#confirmPasswordDiv").find("a").removeClass("fa-check");
        $("#confirmPasswordDiv").find("a").addClass("fa-warning");
        if(confirmPwd==""){
            $("#confirmPasswordDiv").find("span").text("确认新密码不能为空!");
        }else{
            $("#confirmPasswordDiv").removeClass("has-warning has-success");
            $("#confirmPasswordDiv").addClass("has-error");
            $("#confirmPasswordDiv").find("a").removeClass("fa-warning fa-check");
            $("#confirmPasswordDiv").find("a").addClass("fa-times-circle-o");
            $("#confirmPasswordDiv").find("span").text("新密码输入不一致!");
        }
        $("#confirm_passwords").focus();
       // return false;
    }
    if(newPwd == confirmPwd && newPwd!="" ){
        $("#confirmPasswordDiv").removeClass("has-error has-warning");
        $("#confirmPasswordDiv").addClass("has-success");
        $("#confirmPasswordDiv").find("a").removeClass("fa-warning fa-times-circle-o");
        $("#confirmPasswordDiv").find("a").addClass("fa-check");
        $("#confirmPasswordDiv").find("span").text("新密码与确认密码一致!");
        flag_2=true;
    }
    //check new pwd end
    //check old pwd begin
    if(oldPwd==""){
        $("#oldPasswordDiv").removeClass("has-success has-error");
        $("#oldPasswordDiv").addClass("has-warning");
        $("#oldPasswordDiv").find("a").removeClass("fa-check fa-times-circle-o");
        $("#oldPasswordDiv").find("a").addClass("fa-warning");
        $("#oldPasswordDiv").find("span").text("旧密码不能为空!");
        $("#old_passwords").focus();
    }else{
        var params={};
        params["pwd"]=oldPwd;
        params["name"]=$("#userName").val();
        $.ajax({
            url:deployContext +"/user/check_pwd",
            data:params,
            method:"GET",
            success:function(data){
                if(data==1){
                    $("#oldPasswordDiv").removeClass("has-error has-warning");
                    $("#oldPasswordDiv").addClass("has-success");
                    $("#oldPasswordDiv").find("a").removeClass("fa-warning fa-times-circle-o");
                    $("#oldPasswordDiv").find("a").addClass("fa-check");
                    $("#oldPasswordDiv").find("span").text("旧密码输入正确!");
                    flag_1=true;
                }else{
                    $("#oldPasswordDiv").removeClass("has-warning has-success");
                    $("#oldPasswordDiv").addClass("has-error");
                    $("#oldPasswordDiv").find("a").removeClass("fa-warning fa-check");
                    $("#oldPasswordDiv").find("a").addClass("fa-times-circle-o");
                    $("#oldPasswordDiv").find("span").text("旧密码输入不正确!");
                }
                if(flag_1 && flag_2){
                    $("#userForm").submit();
                    return true;
                }
            }
        });
    }
    //check old pwd end

    if(!flag_1 || !flag_2){
        return false;
    }
}