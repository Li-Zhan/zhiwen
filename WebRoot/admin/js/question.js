$(function () {
	
	$('#question').datagrid({               //列表
		url : 'questionAction_listPage',
		fit : true,
		fitColumns : false,
		striped : true,
		rownumbers : true,        
		border : false,
		pagination : true,
		pageSize : 20,
		pageList : [10, 20, 30, 40, 50],
		pageNumber : 1,
		sortName : 'date',
		sortOrder : 'desc',
		remoteSort:false,                //定义是否从服务器排序数据，默认是true
		toolbar : '#question_tool',
		idField:'qid',  //指示哪个字段是标识字段
		columns : [[
			{
				field : 'qid',
				title : '自动编号',
				width : 100,
				checkbox : true,
			},
			{
				field : 'title',
				title : '标题',
				width : 300,
			},
			{
				field : 'userName',
				title : '用户',
				width : 300,
			},
			{
				field : 'date',
				title : '创建日期',
				sortable:true,                         //设置点击列可以排序
				width : 300,
			},
		]],
	});
	
	
	
	$('#question_add').dialog({         //弹出添加用户的对话框
		width : 350,
		title : '新增用户',
		modal : true,
		closed : true,
		iconCls : 'icon-user-add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-ok',
			handler : function () {               //通过ajax提交要添加的数据
				if ($('#question_add').form('validate')) {   //先判断是否验证通过
					$.ajax({
						url : 'userAction_addUser',
						type : 'post',
						data : {
							userName : $('input[name="userName"]').val(),
							password : $('input[name="password"]').val(),
							sex:$('input[name="sex"]:checked').val(),  //获得单选框的值
							email : $('input[name="email"]').val(),
							date:$('input[name="date"]').val(),
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在添加中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							
							if (data) {
								$.messager.show({
									title : '提示',
									msg : '添加成功',
								});
								$('#question_add').dialog('close').form('reset');
								$('#question').datagrid('reload');
							} else {
								$.messager.alert('添加失败！', '未知错误导致失败，请重试！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#question_add').dialog('close').form('reset');
			},
		}],
	});
	
	
	//添加时验证用户帐号
	$('input[name="userName"]').validatebox({
		required : true,
		missingMessage : '请输入用户名称',
		invalidMessage : '用户名称不能为空',
	});
	
	//添加时验证用户密码
	$('input[name="password"]').validatebox({
		required : true,
		validType : 'length[6,20]',
		missingMessage : '请输入用户密码',
		invalidMessage : '用户密码在 6-20 位',
	});
	
	//添加时验证邮箱
	$('input[name="email"]').validatebox({
		required : true,
		missingMessage : '请输入用户邮箱',
		validType:'email',
	});
	
	//编辑时验证帐号
	$('input[name="userName_edit"]').validatebox({
		required : true,
		missingMessage : '请输入用户名称',
		invalidMessage : '用户名称不能为空',
	});
	
	//编辑时验证密码
	$('input[name="password_edit"]').validatebox({
		required : true,
		validType : 'length[6,20]',
		missingMessage : '请输入管理密码',
		invalidMessage : '管理密码在 6-20 位',
	});
	
	//验证时验证邮箱
	$('input[name="email_edit"]').validatebox({
		required : true,
		missingMessage : '请输入用户邮箱',
		validType:'email',
	});
	
	//添加时创建日期
	$('#date').datebox({
	});
	
	//编辑时创建日期
	$('#date_edit').datebox({
	});
	
	
	$('#question_edit').dialog({           //弹出修改对话框
		width : 350,
		title : '修改管理',
		modal : true,
		closed : true,
		iconCls : 'icon-user-add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-edit-new',
			handler : function () {                         //通过ajax修改数据
				if ($('#question_edit').form('validate')) {
					$.ajax({
						url : 'userAction_update',
						type : 'post',
						data : {
							uid : $('input[name="uid"]').val(),
							userName:$('input[name="userName_edit"]').val(),        //允许修改名字
							password : $('input[name="password_edit"]').val(),
							sex: $('input[name="sex_edit"]:checked').val(),
							email : $('input[name="email_edit"]').val(),
							date:$('input[name="date_edit"]').val(),
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							
							if (data) {
								$.messager.show({
									title : '提示',
									msg : '修改管理成功',
								});
								$('#question_edit').dialog('close').form('reset');
								$('#question').datagrid('reload');
							} else {
								$.messager.alert('修改失败！', '未知错误或没有任何修改，请重试！', 'warning');
							}
						}
					});
				}		
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#question_edit').dialog('close').form('reset');
			},
		}],
	});
	
	
	question_tool = {                         //管理工具,定义点击按钮之后行为
		add : function () {                      //添加行为
			$('#question_add').dialog('open');
			$('input[name="adminName"]').focus();
		},
		edit:function(){                    //修改行为
			var rows = $('#question').datagrid('getSelections');     //判断选中的记录数，每次只允许修改一条记录
			if (rows.length > 1) {
				$.messager.alert('警告操作！', '编辑记录只能选定一条数据！', 'warning');
			} else if (rows.length == 1) {
				$.ajax({
					url : 'userAction_findOne',             //通过id查询数据库进行信息的显示
					type : 'post',
					data : {
						uid : rows[0].uid,
					},
					beforeSend : function () {
						$.messager.progress({
							text : '正在获取中...',
						});
					},
					success : function (data, response, status) {
						$.messager.progress('close');
						
						if (data) {			
							
							//回现数据
							$('#question_edit').form('load', {	
								uid : data.uid,
								userName_edit : data.userName,
								sex: data.sex,
								email_edit : data.email,
								date_edit :data.date,
							}).dialog('open');
							
						} else {
							$.messager.alert('获取失败！', '未知错误导致失败，请重试！', 'warning');
						}
					}
				});
			} else if (rows.length == 0) {
				$.messager.alert('警告操作！', '编辑记录至少选定一条数据！', 'warning');
			}
		},
		
		reload : function () {                                  //刷新
			$('#question').datagrid('reload');
		},
		
		redo : function () {                                    //取消选择
			$('#question').datagrid('unselectAll');
		},
		
		remove : function () {                                   //删除,允许多行删除
			var rows = $('#question').datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定操作', '您正在要删除所选的记录吗？', function (flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i ++) {
							ids.push(rows[i].qid);
						}
						
						$.ajax({
							type : 'POST',
							url : 'questionAction_delete',
							data : {
								ids : ids.join(','),              //通过ajax将选择的id数组以,分割形成一个字符串发送
							},
							beforeSend : function () {
								$('#question').datagrid('loading');
							},
							success : function (data) {
								if (data) {
									$('#question').datagrid('loaded');
									$('#question').datagrid('load');
									$('#question').datagrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : data + '条提问被删除成功！',
									});
								}
							},
						});
					}
				});
			} else {
				$.messager.alert('提示', '请选择要删除的记录！', 'info');
			}
		},
		
		search:function(){                    //查询行为
		
			var userName_find=$('#find').val();
			if(userName_find){
						$.ajax({
							type : 'POST',
							url : 'questionAction_findAllByUserName2',
							data : {
								userName : userName_find,    
							},
							beforeSend : function () {
								$('#question').datagrid('loading');
							},
							success : function (data) {
								if (data) {
									$.each(data,function(index,value){
										$('#question').datagrid('selectRecord',value.qid);
										$('#question').datagrid('loaded');
									});
								}else{
									$('#question').datagrid('loaded');
									$.messager.show({
										title : '提示',
										msg : '查询失败',
									});
								}
							},
						});
				
			}else{
				$.messager.alert('提示', '请输入要查询的账号！', 'info');
			}
		},
		
		
		
	};
	
	
	
	
});