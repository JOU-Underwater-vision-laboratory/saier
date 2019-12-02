
$(function () {
    laydate({
        elem : '#birth'
    });
});
/**
 * 基本信息提交
 */
$("#base_save").click(function () {
    // var hobbyStr = getHobbyStr();
    // $("#hobby").val(hobbyStr);
    if($("#basicInfoForm").valid()){
            $.ajax({
                cache : true,
                type : "POST",
                url :"/downPage/u/upload",
                data : $('#basicInfoForm').serialize(),
                async : true,
                error : function(request) {
                    laryer.alert("Connection error");
                },
                success : function(data) {
                    if (data.code == 0) {
                        parent.reLoad();
                        parent.layer.msg("更新成功");

                    } else {
                        parent.layer.alert(data.msg)
                    }
                }
            });
        }
});