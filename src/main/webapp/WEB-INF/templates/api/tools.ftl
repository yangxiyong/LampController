<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Service Test Tools</title>
<@css href="jquery-ui.min.css"/>
<@js src="jquery-2.1.1.min.js,jquery-ui.min.js"/>
    <style>
        body {
            font: 62.5% "Trebuchet MS", sans-serif;
            margin: 50px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#tabs").tabs();
        });
    </script>
</head>
<body>
<div id="tabs">
    <ul>
        <li><a href="#tabs-1">User</a></li>
        <li><a href="#tabs-2">User login</a></li>
        <li><a href="#tabs-3">Update Password</a></li>
        <li><a href="#tabs-4">Group find</a></li>
        <li><a href="#tabs-5">Model Load</a></li>
        <li><a href="#tabs-6">apk downLoad</a></li>
        <li><a href="#tabs-7">update route name</a></li>
        <li><a href="#tabs-8">switch filter</a></li>
    </ul>
    <div id="tabs-1">
        <h5>User Login</h5>
        UserName: <input type="text" value="" id="loginUserName"/>
        Password: <input type="text" value="" id="loginPassword"/><br/>
        <button id="loginButton">login</button>
    </div>
    <div id="tabs-2">
        <h5>User Login</h5>
        UserName: <input type="text" value="" id="signUserName"/>
        Password: <input type="text" value="" id="password"/><br/>
        <button id="signInButton">SignIn</button>
    </div>
    <div id="tabs-3">
        <h5>Change passwords</h5>
        UserName: <input type="text" value="" id="changeUserName"/>
        oldPassword: <input type="text" value="" id="oldPwd"/>
        Password: <input type="text" value="" id="newPwd"/><br/>
        <button id="changePwdButton">Submit</button>
    </div>
    <div id="tabs-4">
        <h5>User find</h5>
        <!--  <form action="/group/find" method="post"> -->
        groupId: <input type="text" value="" id="groupId"/>
        <input type="submit" value="submit"/>

        <br/>
        <button id="findButton">find</button>
    </div>
    <div id="tabs-5">
        <h5>Model load</h5>
        userName: <input type="text" value="" id="userName"/>
        modelID: <input type="text" value="" id="modelId"/>
        <button id="modelLoadBtn">Load</button>
    </div>
    <div id="tabs-6">
        <h5>apk downLoad</h5>
        userName: <input type="text" value="" id="userName"/>
        modelID: <input type="text" value="" id="modelId"/>
        <button id="download">download</button>
    </div>
    <div id="tabs-7">
        <h5>update routeName</h5>
        routeId: <input type="text" value="" id="routeId"/>
        routeName: <input type="text" value="" id="routeName"/>
        <button id="updateRouteName">提交</button>
    </div>
    <div id="tabs-8">
        <h5>switch filter</h5>
        userId: <input type="text" value="" id="userId"/>
        filterId: <input type="text" value="" id="filterId"/>
        <button id="switchFilter">转换</button>
    </div>
</div>
<div id="resultDiv">
</div>
<script type="text/javascript">
    $(function () {
        $("#switchFilter").click(function () {
            var params = {};
            //params["userId"] = $("#userId").val();
            params["filterId"] = $("#filterId").val();
            $.ajax({
                url: "<@url value='/api/filter/switch'/>",
                method: "POST",
                data: params,
                success: function (data) {
                    $("#resultDiv").html("message:" + data.message + ",Status:" + data.status);
                }
            });
        });

        $("#updateRouteName").click(function () {
            var params = {};
            params["routeId"] = $("#routeId").val();
            params["routeName"] = $("#routeName").val();
            $.ajax({
                url: "<@url value='/api/route/update_name'/>",
                method: "POST",
                data: params,
                success: function (data) {
                    $("#resultDiv").html("message:" + data.message + ",Status:" + data.status);
                }
            });
        });

        $("#download").click(function () {
            $.ajax({
                url: "<@url value='/api/apk/update'/>",
                method: "POST",
                success: function (data) {
                    $("#resultDiv").html("versionId:" + data.versionId + ",whatNews:" + data.whatNews + ",Status:" + data.status + ",address:" + data.address);
                }
            });
        });

        $("#modelLoadBtn").click(function () {
            var params = {};
            params["userName"] = $("#userName").val();
            params["modelId"] = $("#modelId").val();
            $.ajax({
                url: "<@url value='/api/model/load'/>",
                data: params,
                method: "POST",
                success: function (data) {
                    $("#resultDiv").html("userName:" + data.userName + ",groupName:" + data.groupName + ",Status:" + data.status + ",list:" + data.backClientDataAreaList);
                }
            });
        });


        $("#findButton").click(function () {
            var params = {};
            params["groupId"] = $("#groupId").val();
            $.ajax({
                url: "<@url value='/group/find'/>",
                data: params,
                method: "POST",
                success: function (data) {
                    $("#resultDiv").html("result:" + "Status:" + data.testStatus + ",bean::" + data.groupBean);
                }
            });
        });


        $("#loginButton").click(function () {
            var params = {};
            params["userName"] = $("#loginUserName").val();
            params["password"] = $("#loginPassword").val();
            $.ajax({
                url: "<@url value='/api/user/login' />",
                data: params,
                method: "POST",
                success: function (data) {
                    $("#resultDiv").html("UserName:" + data.userName + " " + "Status:" + data.message);
                }
            });
        });

        $("#signInButton").click(function () {
            var params = {};
            params["userName"] = $("#signUserName").val();
            params["password"] = $("#password").val()
            $.ajax({
                url: "<@url value='/api/user/entry'/>",
                data: params,
                method: "POST",
                success: function (data) {
                    $("#resultDiv").html("userName:" + data.userName + ",groupName:" + data.groupName + ",Status:" + data.status + ",list:" + data.backClientDataAreaList);
                }
            });
        });

        $("#changePwdButton").click(function () {
            var params = {};
            params["userName"] = $("#changeUserName").val();
            params["oldPassword"] = $("#oldPwd").val();
            params["newPassword"] = $("#newPwd").val();
            $.ajax({
                url: "<@url value='/api/user/updatepwd'/>",
                data: params,
                method: "POST",
                success: function (data) {
                    $("#resultDiv").html("result:" + data.toString());
                }
            });
        });


    });
</script>
</body>
</html>