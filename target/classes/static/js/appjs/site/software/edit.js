$().ready(function() {
    loadType_category();
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
    var soft_introduce = $("#softIntroduction").val();
    var soft_keywords = $("#softKeywords").val();
    var soft_function =  $("#softFunction").val();
    var soft_comment = $("#softComment").val();

    $('#content_sn').summernote('code', soft_introduce);
    $('#content_sn_area').summernote('code', soft_keywords);
    $('#content_sn_require').summernote('code', soft_function);
    $('#content_sn_rComment').summernote('code', soft_comment);
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
    $("#softIntroduction").val(rintroduce);
    $("#softKeywords").val(rResearea);

    var requirment = $("#content_sn_require").summernote('code');
    var rComment = $("#content_sn_rComment").summernote('code');

    $("#softFunction").val(requirment);
    $("#softComment").val(rComment);
	$.ajax({
		cache : true,
		type : "post",
		url : "/site/software/update",
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
function loadType_category() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/site_software_type',
        success: function (data) {
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight: 200
            });
            $(".chosen-select").val($("#tsoft_type").val());
            $(".chosen-select").trigger("chosen:updated");
            // 点击事件z
            $('.chosen-select').on('change', function (e, params) {

            });
        }
    });
}
