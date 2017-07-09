$(function() {
    common.showMessage($("#message").val());
    //使用validation包下的表单验证插件
    $("#mainForm").validate({
        rules : {
            //自定义提示信息
            "title" : "required",
            "link" : "required",
            "weight" : {
                //直接使用封装好的
                required : true,
                digits : true,
                maxlength : 5
            }
        },
        messages : {
            "title" : "请输入标题！"
        }
    });
});

function goback() {
    location.href = $("#basePath").val() + '/ad';
}

function modify() {
    $("#mainForm").submit();
}