$().ready(function() {

	$('.summernote').summernote({
		height : '220px',
		lang : 'zh-CN',
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile(files);
            }
        }
	});
    var content = $("#nContent").val();

    $('#content_sn').summernote('code', content);
	validateRule();
});


$.validator.setDefaults({
	submitHandler : function() {
		save(1);
	}
});
function save(status) {
	$("#status").val(status);
	var nContent = $("#content_sn").summernote('code');
	$("#nContent").val(nContent);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/site/news/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(r) {
			if (r.code === 0) {
				parent.layer.msg(r.msg);
				parent.reLoad();
				$("#nid").val(r.nid);

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
			// nlogo : "required",
			nmonth : "required",
			nContent : "required"
		},
		messages : {
            // nlogo : "",
            nmonth : "例如：5",
            nContent : "请填写动态内容"
		}
	});
}

function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}