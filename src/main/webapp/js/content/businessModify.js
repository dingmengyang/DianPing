
$(function() {
    common.showMessage($("#message").val());
});
function goback() {
    location.href = $("#basePath").val() + '/businesses';
}

function modify() {
    $("#mainForm").submit();
}