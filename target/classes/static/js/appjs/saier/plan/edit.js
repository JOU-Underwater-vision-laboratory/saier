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
        url : "/saier/plan/update",
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
            id:"required",
            medlicense:"required",
            medbox:"required",
            times:"required",
            medicinetime:"required",
            num:"required"
        },
        messages : {
            id:icon+"请录入id",
            medlicense:icon+"请录入药准字号",
            medbox:icon+"请录入药盒mid",
            times:icon+"请录入服药次数",
            medicinetime:icon+"请录入服药时间",
            num:icon+"请录入服药剂量"
        }
    });
}


function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}