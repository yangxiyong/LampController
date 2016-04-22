<!DOCTYPE html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>宜加灯控APK下载</title>
<@css href="core.css"/>
<@js src="jquery-2.1.1.min.js,jquery-ui.min.js"/>
    <style type="text/css">
        li{list-style-type:none;}
        .my-center{text-align: center;
            padding-top: 80px;
            padding-bottom: 30px;
            width: 100%;
            height: auto;
        }
        #download_btn{
            width: 80px;
            height: 40px;
            margin-top: 40px;;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#download_btn").click(function () {
                var url = $("#download_url").val();
                //alert("url:"+url);
                window.location = url;
            });
           /* $(window).resize(function(){
                $(".my-center").css({
                    position:'absolute',
                    left:( $(window).width - $(".my-center").outerWidth )/2,
                    top:( $(window).width - $(".my-center").outerWidth )/2 + $(document).scrollTop
                });
            });*/
        });
    </script>
</head>
<body>
<div id="header" class="my-center bg-light-blue">
    <section>
        <input id="download_url" type="hidden" value="${bean.address}">
        <li>
            <img src="<@url value='/assets/images/apk_icon.png' />" class="img-circle" alt="apk Image" />
            <p>${bean.apkName}</p>
            <p><small>V${bean.versionId}版本</small></p>
            <p><small>更新内容: ${bean.whatNews}</small></p>
        </li>
        <br/>
        <button id="download_btn" class="btn btn-danger" type="button">Download</button>
    </section>
</div>
</body>