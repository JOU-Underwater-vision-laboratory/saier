$().ready(function() {
    loadType_job();
    loadType_group();
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
		save();
	}
});
function save(status) {
    $("#status").val(status);
    var content_sn = $("#content_sn").summernote('code');
    var content_sn_Eng = $("#content_sn_Eng").summernote('code');
    var content_sn_area = $("#content_sn_area").summernote('code');
    var content_sn_area_Eng = $("#content_sn_area_Eng").summernote('code');
    $("#mintroduce").val(content_sn);
    $("#mintroduceEng").val(content_sn_Eng);
    $("#mResearea").val(content_sn_area);
    $("#mReseareaEng").val(content_sn_area_Eng);
    $.ajax({
        cache : true,
        type : "POST",
        url : "/site/members/update",
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
            mintroduce : "required",
            mintroduceEng : "required",
            mResearea : "required",
            mReseareaEng : "required"
        },
        messages : {
            mname : "请填写成员姓名",
            mjob : "请填写成员身份",
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

function loadType_job(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/site_member_job',
        success : function(data) {
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select-job").append(html);
            $(".chosen-select-job").chosen({
                maxHeight : 200
            });
            $(".chosen-select-job").val($("#Tmjob").val());
            $(".chosen-select-job").trigger("chosen:updated");
            // 点击事件z
            $('.chosen-select').on('change', function(e, params) {

            });
        }
    });
}
function loadType_group() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/site_member_group',
        success: function (data) {
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select-group").append(html);
            $(".chosen-select-group").chosen({
                maxHeight: 200
            });
            $(".chosen-select-group").val($("#Tmgroup").val());
            $(".chosen-select-group").trigger("chosen:updated");
            // 点击事件z
            $('.chosen-select').on('change', function (e, params) {

            });
        }
    });
}

function loadType_category() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/site_member_category',
        success: function (data) {
            // 加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select-category").append(html);
            $(".chosen-select-category").chosen({
                maxHeight: 200
            });
            $(".chosen-select-category").val($("#Tcategory").val());
            $(".chosen-select-category").trigger("chosen:updated");
            // 点击事件z
            $('.chosen-select').on('change', function (e, params) {

            });
        }
    });
}
