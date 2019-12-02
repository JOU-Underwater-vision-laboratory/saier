$().ready(function() {
	$('.summernote').summernote({
		height : '220px',
		lang : 'zh-CN',
        callbacks: {
            onImageUpload: function(files, editor, $editable) {
                console.log("onImageUpload");
                sendFile(files);
            }
        }
    });
    var rintroduce = $("#rintroduce").val();
    var rResearea = $("#rResearea").val();
    var requirment =  $("#requirment").val();
    var rComment = $("#rComment").val();

    $('#content_sn').summernote('code', rintroduce);
    $('#content_sn_area').summernote('code', rResearea);
    $('#content_sn_require').summernote('code', requirment);
    $('#content_sn_rComment').summernote('code', rComment);
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save(status) {
	$("#status").val(status);
    var rintroduce = $("#content_sn").summernote('code');
    var rResearea = $("#content_sn_area").summernote('code');
    $("#rintroduce").val(rintroduce);
    $("#rResearea").val(rResearea);

    var requirment = $("#content_sn_require").summernote('code');
    var rComment = $("#content_sn_rComment").summernote('code');

    $("#requirment").val(requirment);
    $("#rComment").val(rComment);
	$.ajax({
		cache : true,
		type : "post",
		url : "/site/recruitment/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
            rintroduce: {required:true},
            rType:{required:true},
            rLinkman : {required:true},
            rLinkemail : {required:true},
            rEndtime: {required:true},
            rLinkaddress : {required:true},
            rResearea: {required:true},
            requirment : {required:true},
            rComment : {required:true}
		},
		messages : {
            rintroduce: {required:icon+ "请输入正确的组织简述"},
            rType:{required:icon+"请录入招生类型"},
            rLinkman : {required:icon+"请录入联系人"},
            rLinkemail : {required:icon+"请录入正确的联系邮箱"},
            rEndtime: {required:icon+"请录入截至时间"},
            rlLnkaddress : {required:icon+"请录入办公地址"},
            rResearea: {required:icon+"请录入研究方向"},
            requirment : {required:icon+"请录入投递要求"},
            rComment : {required:icon+"请录入招募说明"}

		}
	})
}

function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}

