$().ready(function() {
	validateRule();
});


$.validator.setDefaults({
	submitHandler : function() {
		save(1);
	}
});
function save(status) {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/saier/medicine/save",
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
            licensenumber : "required",
            medicinename : "required",
            activeingredient: "required",
            medcharacter : "required",
            dose : "required",
            dosage : "required",
            contraindication : "required",
            indication : "required",
            dosagefromdoc : "required",
            druginteraction: "required",
            periodvalidity : "required",
            manufacturer : "required",
            storageconditions : "required"
		},
		messages : {
            licensenumber : "请输入药准字号",
            medicinename : "请输入药名",
            activeingredient: "请输入有效成分",
            medcharacter : "请输入药物特征",
            dose : "请输入药物剂量",
            dosage : "请输入药量（医嘱）",
            contraindication : "请输入禁忌",
            indication : "请输入迹象",
            dosagefromdoc : "请输入药量（说明书）",
            druginteraction: "请输入药物相互作用",
            periodvalidity : "请输入有效期",
            manufacturer : "请输入生产厂家",
            storageconditions : "请输入存储条件"
		}
	});
}

function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}

