$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save(status) {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/saier/box/update",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(r) {
            if (r.code === 0) {
                parent.layer.msg(r.msg);
                parent.reLoad();
                $("#gridId").val(r.gridId);
            } else {
                parent.layer.alert(r.msg)
            }
        }
    });
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            gridId:"required",
            boxId:"required",
            mid:"required",
            rest:"required"
        },
        messages : {
            gridId:icon+"请输入",
            boxId:icon+"请输入",
            mid:icon+"请输入",
            rest:icon+"请输入"
        }
    });
}


function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}