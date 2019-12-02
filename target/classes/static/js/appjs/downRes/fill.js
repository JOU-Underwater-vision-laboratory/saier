$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
        subsave();
	}
});
function subsave() {
	//$("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/downPage/d/download",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);

				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                reLoad();
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg);
			}

		}
	});

}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
            person_name : {
				required : true
			},
            person_email : {
				required : true,
                email : /^[a-z0-9._%-]+@([a-z0-9-]+.)+[a-z]{2,4}$/
			},
            person_country : {
                required : true
            },
            person_institution : {
                required : true
            }
		},
		messages : {
            person_name : {
				required : icon + "please input your name"
			},
            person_country : {
				required : icon + "please select your country"
			},
            person_institution : {
				required : icon + "please input your institutional unit"
			},
            person_email :  {
                required : icon + "please input your E-mail",
                email : icon + "please input correct E-mail"
            }
		}
	})
}

