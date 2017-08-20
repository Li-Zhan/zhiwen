$(function (){
	
	//登录界面
	$('#login').dialog({
		title:'登录后台',
		width:300,
		height:180,
		modal:true,
		buttons:'#btn',
		iconCls:'icon-login',
	});
	
	//管理员账号验证
	$('#manager').validatebox({
    required: true,
    missingMessage:'请输入管理员账号',
	invalidMessage:'管理员账号不能为空',
	});
	
   //管理员密码验证
	$('#password').validatebox({
    required: true,
	validType:'length[6,20]',                //密码6~20位之间
    missingMessage:'请输入管理员密码',
	invalidMessage:'管理员密码最少为6位',
	});

	
	//单击登录
	$('#btn a').click(function () {
		if (!$('#manager').validatebox('isValid')) {
			$('#manager').focus();
		} else if (!$('#password').validatebox('isValid')) {
			$('#password').focus();
		} else {
			$.ajax({
				url : 'adminAction_login',
				type : 'post',
				data : {
					adminName : $('#manager').val(),
					password : $('#password').val(),
				},
				beforeSend : function () {
					$.messager.progress({
						text : '正在登录中...',
					});
				},
				success : function (data, response, status) {
					$.messager.progress('close');
					
					if (data>0) {
						location.href = 'admin.jsp';
					} else {
						$.messager.alert('登录失败！', '用户名或密码错误！', 'warning', function () {
							$('#password').select();
						});
					}
				}
			});
		}
	});

});