/**
 * Created by yxy on 2015/1/26.
 */
$(function () {
    $("input:checkbox[name='ck_routId']").change(function(){
        var isChecked=$(this).is(":checked");
        if(!isChecked){
            $(this).parent().parent().find(".switch_checkbox_2").attr("checked",false); // Synchronizing the original checkbox:
            $(this).parent().parent().find(".switch_checkbox_2").val("0");
            $(this).parent().parent().find("span.tzCheckBox").removeClass("checked"); // Synchronizing the  new checkbox:
            $(this).parent().parent().find("span.tzCheckBox").find(".tzCBContent").html("OFF");//Synchronizing text(ON/OFF)
        }
    });

    $("#saveRoutes").click(function(){
        var sum_routeId="";
        $("input:checkbox:checked[name='ck_routId']").each(function(){
            // 处理开关没被点击的情况,某一个线路的开关没有被点击而直接勾选后提交,可在这类checkbox上添加初始值为0.即可
            //累加获取传给服务端的参数
            sum_routeId+=$(this).val()+','+$(this).parent().parent().find(".switch_checkbox_2").val()+";";
        });
        //alert("sum_routeId:"+sum_routeId);
        $("#temp_routeId_val").val(sum_routeId);
        $("#modelRouteForm").submit();
    });
    $("input[type='checkbox'].switch_checkbox_2").tzCheckbox({labels:['ON','OFF']});
});