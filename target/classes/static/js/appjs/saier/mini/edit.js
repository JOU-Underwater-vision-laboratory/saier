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
        url : "/saier/mini/update",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(r) {
            if (r.code === 0) {
                parent.layer.msg(r.msg);
                parent.reLoad();
                $("#mid").val(r.mid);
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
            mid: "required",
            first: "required",
            second: "required",
            third: "required",
            forth: "required",
            fifth: "required"
        },
        messages : {
            mid: "请输入药盒ID",
            first:"请输入格内信息",
            second:"请输入格内信息",
            third:"请输入格内信息",
            forth:"请输入格内信息",
            fifth:"请输入格内信息"
        }
    });
}


function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}