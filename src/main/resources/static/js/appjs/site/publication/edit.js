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
    loadType_category();
	var content = $("#ppublications").val();

	$('#content_sn').summernote('code', content);
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save(status) {
	$("#status").val(status);

	var content_sn = $("#content_sn").summernote('code');
	$("#ppublications").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/site/publication/save",
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
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}
function loadType_category() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/site_publication_category',
        success: function (data) {
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select-category").append(html);
            $(".chosen-select-category").chosen({
                maxHeight: 200
            });
            $(".chosen-select-category").val($("#Tpcategories").val());
            $(".chosen-select-category").trigger("chosen:updated");
            // 点击事件z
            $('.chosen-select').on('change', function (e, params) {

            });
        }
    });
}

function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}

