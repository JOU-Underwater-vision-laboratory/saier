$().ready(function() {
    loadTypeJob();
    loadTypeGroup();
    loadTypeCategory();
	$('.summernote').summernote({
		height : '220px',
		lang : 'zh-CN',
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile(files);
            }
        }
	});
    var mintroduce = $("#mintroduce").val();
    var mintroduceEng = $("#mintroduceEng").val();
    var content_sn_area = $("#mResearea").val();
    var content_sn_area_Eng = $("#mReseareaEng").val();
    $('#content_sn').summernote('code', mintroduce);
    $('#content_sn_Eng').summernote('code', mintroduceEng);
    $('#content_sn_area').summernote('code', content_sn_area);
    $('#content_sn_area_Eng').summernote('code', content_sn_area_Eng);
	validateRule();
});


$.validator.setDefaults({
	submitHandler : function() {
		save(1);
	}
});
function save(status) {
	$("#status").val(status);
	var content_sn = $("#content_sn").summernote('code');
    var content_sn_Eng = $("#content_sn_Eng").summernote('code');
	$("#mintroduce").val(content_sn);
    $("#mintroduceEng").val(content_sn_Eng);

    var content_sn_area = $("#content_sn_area").summernote('code');
    var content_sn_area_Eng = $("#content_sn_area_Eng").summernote('code');

    $("#mResearea").val(content_sn_area);
    $("#mReseareaEng").val(content_sn_area_Eng);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/site/members/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(r) {
			if (r.code == 0) {
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
            mname : "required",
            mjob : "required",
            mgroup: "required",
            mintroduce : "required",
            mintroduceEng : "required",
            mResearea : "required",
            mReseareaEng : "required"
		},
		messages : {
            mname : "请填写成员姓名",
            mjob : "请填写成员身份",
            mgroup : "请选择分组",
            mintroduce : "请填写个人介绍",
            mintroduceEng : "请填写个人介绍英文版",
            mResearea : "请填写研究领域",
            mReseareaEng : "请填写研究领域英文版"
		}
	});
}

function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}


function loadTypeJob(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/site_member_job',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select_job").append(html);
            $(".chosen-select_job").chosen({
                maxHeight : 200
            });
            //点击事件
            $('.chosen-select_job').on('change', function(e, params) {
                // console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
                $('#exampleTable').bootstrapTable('refresh', opt);
            });
        }
    });
}
function loadTypeGroup(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/site_member_group',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select_group").append(html);
            $(".chosen-select_group").chosen({
                maxHeight : 200
            });
            //点击事件
            $('.chosen-select_group').on('change', function(e, params) {
                // console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
                $('#exampleTable').bootstrapTable('refresh', opt);
            });
        }
    });
}
function loadTypeCategory(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/site_member_category',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select_category").append(html);
            $(".chosen-select_category").chosen({
                maxHeight : 200
            });
            //点击事件
            $('.chosen-select_category').on('change', function(e, params) {
                // console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
                $('#exampleTable').bootstrapTable('refresh', opt);
            });
        }
    });
}