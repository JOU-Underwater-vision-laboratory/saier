var prefix = "/site/software"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
						showRefresh : true,
						showToggle : true,
						showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						// search : true, // 是否显示搜索框
						//showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
						// "server"

						queryParams : function(params) {
							return {
								// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit : params.limit,
								offset : params.offset,
							    soft_keywords:$('#searchName').val()
							    // rLinkman:$('#searchName').val()
							};
						},
						columns : [
								{
									checkbox : true
								},
								{
									visible : false,
									field : 'softId',
									title : ''
								},
                                {
                                	visible: false,
                                    field : 'softCreateDate',
                                    title : '发布日期'
                                },
								{
									visible : true,
									field : 'softName',
									title : '软件包名称'
                                },
								{
									visible: false,
									field : 'softIntroduction',
									title : '软件包介绍'
								},
								{
									visible : true,
									field : 'softKeywords',
									title : '关键词'
								},
								{
									visible : false,
									field : 'softFunction',
									title : '功能介绍'
								},
								{
								    visible: true,
									field : 'softAuthor',
									title : '作者'
								},
								{
									visible : false,
									field : 'softAuthorEmail',
									title : '联系邮箱'
								},
								{
									visible : true,
									field : 'softVersion',
									title : '版本'
								},
                                {
                                    visible : false,
                                    field : 'softType',
                                    title : '类型'
                                },
								{
									visible : true,
									field : 'softComment',
									title : '说明'
								},
                            {
                                visible: false,
                                field : 'softFid',
                                title : '关联文件id'
                            },
                            {
                                title : '操作',
                                field : 'id',
                                align : 'center',
                                formatter : function(value, row, index) {
                                    var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                        + row.softId
                                        + '\')"><i class="fa fa-edit"></i></a> ';
                                    var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                        + row.softId
                                        + '\')"><i class="fa fa-remove"></i></a> ';
                                    var f = '<a class="btn btn-success btn-sm '+s_preview_h+'" href="#"  title="发布/取消发布"  mce_href="#" onclick="preview(\''
                                        +row.softId
                                        +'\')"><i class="fa fa-rocket"></i></a> ';
                                    return e + d +f;
                                }
                            } ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	var addPage = layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '600px', '480px' ],
		content : prefix + '/add' // iframe的url
	});
	layer.full(addPage);
}
function edit(id) {
	var editPage = layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '600px', '480px' ],
		content : prefix + '/edit/'+id// iframe的url
	});
	layer.full(editPage);
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}

function upload() {
    // iframe层 上传软件包 element : test2
    layer.open({
        type : 2,
        title : '上传软件',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '600px', '480px' ],
        content : '/common/sysFile/add'
    });
}
function preview(id) {
    layer.confirm('确定确定要发布或者取消发布选中的服务/软件？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : "/downPage/softopen",
            type : "post",
            data : {
                "id":id
            },
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['softId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}